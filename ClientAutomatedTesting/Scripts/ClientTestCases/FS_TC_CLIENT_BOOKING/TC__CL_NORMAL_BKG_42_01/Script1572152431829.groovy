import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import java.time.Instant

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import testlink.api.java.client.TestLinkAPIResults
import ioStructures.BookedTktPnrDtls as BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls as InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls as InputTDRCPsgnDtls
import ioStructures.FailedBookingDtls as FailedBookingDtls

String 	projectName = "FunctionalityWiseHierarchy",
testPlanName = "Test Plan to test APOS Client automatically",
testCaseName = "TC_CL_NORMAL_BKG_42_01" ,
buildName = "Build to Test FS_TC_CLIENT_BOOKING",
execNotes = "" ,
testResultStatus = TestLinkAPIResults.TEST_FAILED,
expectedMsg = "System should impose GST for tickets booked in classes other than  SL/2S/2T. ",
dataToTest = '',
actualMsg = '';

String timenow = null
ArrayList<String> paymentList = new ArrayList<String>();

List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()
BookedTktPnrDtls bookedTktPnrDtls ;
String testCaseId ='';
List<BookedTktPnrDtls> bookedTktPnrDtlsList = new ArrayList()
char recordSeparator = ',';

timenow = CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'();
String errorFileNameWithoutExtn = testCaseName + '_' + timenow ;
String partialImageStartName = '';


Instant startExecutionTime = null ;
startExecutionTime = Instant.now();

FailedBookingDtls failedBookingDtls = new FailedBookingDtls()

//		CustomKeywords.'reusableComponents.CustomKeywords.preConditionsInfo'('Ensure proper testing environments for input trains in dat file \n are Available for booking')
System.out.println("READING FILE") ;
//inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")

inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputConcessionalTDRCPsgnDtls'(".\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")


for ( int recordCount = 0; recordCount < inputTDRCPsgnDtlsList.size(); recordCount++) {
	try {

		bookedTktPnrDtls = new BookedTktPnrDtls() ;
		List<InputSinglePsgnDtls> psgnDtlsList = new ArrayList()
		recordString = '' ;
		psgnDtlsString = '';
		trainNo = inputTDRCPsgnDtlsList.get(recordCount).getTrainNo()
		testCaseId = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId()
		doj = inputTDRCPsgnDtlsList.get(recordCount).getDoj()
		srcStnCode = inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode()
		destnStnCode = inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode()
		inputClassCode =  inputTDRCPsgnDtlsList.get(recordCount).getClassCode()
		quotaCode = inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode()
		noOfPsgn = 	inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size().toString()
		boardingStnCode = inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode()
		resvnUptoStnCode = inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode()
		coachChoice = inputTDRCPsgnDtlsList.get(recordCount).getCoachChoice()
		mobileNo = inputTDRCPsgnDtlsList.get(recordCount).getMobileNo()
		paymentType = inputTDRCPsgnDtlsList.get(recordCount).getPaymentType()
		partialImageStartName = ".\\UserDataFiles\\outfiles\\" + testCaseId + "exceptionImage" + timenow + ".png" ;
		//		dataInRow = inputTrainNo + recordSeparator + inputJrnyDate + recordSeparator + inputSrcStnCode + recordSeparator + inputDestnStnCode + recordSeparator +
		dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + inputClassCode
		if  (!(testCaseId.equals(testCaseName)))
			continue ;


		CustomKeywords.'reusableComponents.CustomKeywords.fillTDRCInQB'(trainNo,
				doj,
				srcStnCode,
				destnStnCode,
				inputClassCode,
				quotaCode,
				noOfPsgn,
				boardingStnCode,
				resvnUptoStnCode,
				coachChoice	)
		psgnDtlsList = inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList()
		CustomKeywords.'reusableComponents.CustomKeywords.fillConcessionalPsgnDtlsInQB'(psgnDtlsList)
		CustomKeywords.'reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB'(mobileNo,paymentType)
		
		failedBookingDtls = CustomKeywords.'reusableComponents.CustomKeywords.checkErrorInConfPayment'()
		System.out.println('ErrorHeader in main =' + failedBookingDtls.getHeader());
		System.out.println('ErrorMessage in main =' + failedBookingDtls.getErrorMsg());
		
		if ( failedBookingDtls.getHeader().isEmpty() || failedBookingDtls.getHeader() == null ) 
		{
			bookedTktPnrDtls = CustomKeywords.'reusableComponents.CustomKeywords.getBookedTktDtlsClickNextQB'(testCaseId , noOfPsgn)
			bookedTktPnrDtlsList.add(bookedTktPnrDtls)

		
		
			//Verify GST - start
			gstDtlsTxt =  WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PrintTktPage/gstChargeDtls'))
			System.out.println('\nGST Dtls are as follows \n' +  gstDtlsTxt)
			System.out.println('\nGST BREAK UP AS FOLLOWS')
			gstBreakUp = gstDtlsTxt.split(':')
			
			for (int count = 0 ; count < gstBreakUp.length; count++) {
			System.out.println('gst ='+gstBreakUp[count])


			}


			if ( (inputClassCode != 'SL') && (inputClassCode != '2S') && (inputClassCode != '2T') ) {
				if (!gstBreakUp[GlobalVariable.totalGSTPos].equals('Rs.0.0'))
					testResultStatus = TestLinkAPIResults.TEST_PASSED ;
				else
					testResultStatus = TestLinkAPIResults.TEST_FAILED ;

					actualMsg = 'GST DETAILS=' + gstDtlsTxt +'\n' + 'TOTAL GST=' + gstBreakUp[GlobalVariable.totalGSTPos]
			}
			else {
			if (gstBreakUp[GlobalVariable.totalGSTPos].equals('Rs.0.0'))
				testResultStatus = TestLinkAPIResults.TEST_PASSED ;
			else
				testResultStatus = TestLinkAPIResults.TEST_FAILED ;

			actualMsg = 'GST DETAILS=' + gstDtlsTxt +'\n' + 'TOTAL GST=' + gstBreakUp[GlobalVariable.totalGSTPos]
		}
		} 
		
		else {
			testResultStatus = TestLinkAPIResults.TEST_FAILED ;
			actualMsg = failedBookingDtls.getHeader() + '\n' + failedBookingDtls.getErrorMsg() ;
		}


		execNotes += '\n'+ testCaseName +'::'+
				'\nData to Test::' + dataToTest + "\nChecked for Class " + inputClassCode +
				'\nExpected Result::' + expectedMsg + '\nActual Result::' + actualMsg + '\n' +
				'------------------------------------------------';

				


		//Verify GST - end
		System.out.println('\n Execution Notes = ' + execNotes) ;
		//Verify GST - end
	} catch (Exception e) {

		System.out.println("Exception Occured for test case id="+ testCaseId)
		testResultStatus = TestLinkAPIResults.TEST_FAILED ;
		//	CustomKeywords.'reusableComponents.CustomKeywords.takePartialScreenShot'(partialImageStartName ,findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/screenShotDiv'),GlobalVariable.waitForElementVisibleSec)
		execNotes +='\nEXCEPTION OCCURED=' + e.getMessage().substring(GlobalVariable.exceptionErrorMsglength)
		CustomKeywords.'reusableComponents.CustomKeywords.takeCompleteScreenShot'(partialImageStartName)
		CustomKeywords.'reusableComponents.CustomKeywords.writeErrorLog'(errorFileNameWithoutExtn,testCaseId)

	}




} /*End of for loop for recordCount */










//===============

//Update in TestLink - start
Instant endExecutionTime = Instant.now();
Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;



CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
		testPlanName,
		testCaseName,
		buildName,
		execNotes,
		testResultStatus)


//Update in TestLink - end

