import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import ioStructures.BookedTktPnrDtls as BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls as InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls as InputTDRCPsgnDtls

import java.time.Duration;
import java.time.Instant;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select


String projectName = "FunctionalityWiseHierarchy",
testPlanName = "Test Plan to test APOS Client automatically",
testCaseName = "TC_CL_NORMAL_BKG_29_01" ,
buildName = "Build to Test FS_TC_CLIENT_BOOKING",
execNotes = "" ,
testResultStatus = TestLinkAPIResults.TEST_FAILED,
expectedMsg = "UPI Payment type should be present",
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
		clsCode =  inputTDRCPsgnDtlsList.get(recordCount).getClassCode()
		quotaCode = inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode()
		noOfPsgn = 	inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size().toString()
		boardingStnCode = inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode()
		resvnUptoStnCode = inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode()
		coachChoice = inputTDRCPsgnDtlsList.get(recordCount).getCoachChoice()
		mobileNo = inputTDRCPsgnDtlsList.get(recordCount).getMobileNo()
		paymentType = inputTDRCPsgnDtlsList.get(recordCount).getPaymentType()
		partialImageStartName = ".\\UserDataFiles\\outfiles\\" + testCaseId + "exceptionImage" + timenow + ".png" ;
//		dataInRow = inputTrainNo + recordSeparator + inputJrnyDate + recordSeparator + inputSrcStnCode + recordSeparator + inputDestnStnCode + recordSeparator +
		dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
		if  (!(testCaseId.equals(testCaseName)))
		continue ;
		
		
		CustomKeywords.'reusableComponents.CustomKeywords.fillTDRCInQB'(trainNo,
																					doj,
																					srcStnCode,
																					destnStnCode,
																					clsCode,
																					quotaCode,
																					noOfPsgn,
																					boardingStnCode,
																					resvnUptoStnCode,
																					coachChoice	)
		psgnDtlsList = inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList()
		CustomKeywords.'reusableComponents.CustomKeywords.fillConcessionalPsgnDtlsInQB'(psgnDtlsList)
		//For Clicking Payment type - start
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_PaymentType'))
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int paymentTypeCount = GlobalVariable.paymentCountStart; paymentTypeCount <= GlobalVariable.maxPaymentCount; paymentTypeCount++) {
			String span_paymentType = 'NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/span_paymentType' + paymentTypeCount
			if (WebUI.verifyElementPresent(findTestObject(span_paymentType), 1, FailureHandling.OPTIONAL) == true) {
				paymentType = WebUI.getText(findTestObject(span_paymentType))
				System.out.println('PAYMENT TYPE=' + paymentType)
				paymentList.add(paymentType)
				
		
				  
			}
		}
		//For Clicking Payment Type - end
		
		
		//For Clicking Payment Type - end
		
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			if (paymentList.contains('UPI')) {
				System.out.println('PAYMENT has UPI type')
				testResultStatus = TestLinkAPIResults.TEST_PASSED ;
				actualMsg = 'UPI payment option present'
			}
			else {
				testResultStatus = TestLinkAPIResults.TEST_FAILED ;
				actualMsg = 'UPI payment option not present'
			}
				
		
			CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()

		System.out.println('\n actualMsg = ' + actualMsg) ;
		

		execNotes += '\n'+ testCaseName +'::'+
		'\nData to Test::' + dataToTest + '\nExpected Result::' + expectedMsg + '\nActual Result::' + actualMsg
			'------------------------------------------------';
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

Instant endExecutionTime = Instant.now();
Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;

CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
		testPlanName,
		testCaseName,
		buildName,
		execNotes,
		testResultStatus)
