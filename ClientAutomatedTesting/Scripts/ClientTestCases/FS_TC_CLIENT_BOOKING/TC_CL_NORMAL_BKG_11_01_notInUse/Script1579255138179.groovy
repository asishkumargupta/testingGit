import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import inputOutputStructures.SinglePsgnDtls 
import quickBook.BookedTktPnrDtls
import quickBook.BookedTktSinglePsgnDtls

import java.time.Duration;
import java.time.Instant;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;



String valueString = "EMPTY",
testStatus = "FAILED" ,
projectName = "FunctionalityWiseHierarchy",
testPlanName = "Test Plan to test APOS Client automatically",
testCaseName = "TC_CL_NORMAL_BKG_11_01" ,
buildName = "Build to Test FS_TC_CLIENT_BOOKING",
execNotes = "" ,
testResultStatus = TestLinkAPIResults.TEST_FAILED;




Instant startExecutionTime = null ;
startExecutionTime = Instant.now();



String 	inputTrainNo ='' ,
		inputJrnyDate = '',
		inputSrcStnCode = '',
		inputDestnStnCode = '',
		inputClassCode ='',
		inputNoOfPsgn = '' ,
		inputQuotaCode = '' ,
		
		inputPsgnCount = '' ,
		inputBoardingStnCode ='' ,
		inputResvnUptoStnCode = '' ,
		inputMobileNo = '' ,
		inputPaymentType = '',
		inputCompletePsgnDtls = '' ,
		inputSinglePsgnDtls = '';
		
		char psgnAttributeSeparator = '$';
	
		char psgnDtlsSeparator = '#';
		String timenow = null;
		
		
		char recordSeparator = '#';
		timenow =  CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()
		//execNotes += "Execution started at " + timenow

//List<TDRCdetails> tdrcDetailsList = new ArrayList()
				SinglePsgnDtls singlePsgnDtls = new SinglePsgnDtls()
  List<SinglePsgnDtls> psgnDtlsList =  new ArrayList()
  //BookedTktSinglePsgnDtls bookedTktSinglePsgnDtls = new BookedTktSinglePsgnDtls()
  //List<BookedTktSinglePsgnDtls> bookedTktPsgnDtlsList =  new ArrayList()
  
  // BookedTktPnrDtls bookedTktPnrDtls = new BookedTktPnrDtls()
  //BookedTktPnrDtls bookedTktPnrDtls ;
  



for (int rowNum = 1; rowNum <= findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getRowNumbers(); rowNum++) {
	
	inputTestCaseName = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(1, rowNum)
	System.out.println('TEST CASE NAME =' + inputTestCaseName)
	if  (!(inputTestCaseName.equals(testCaseName)))
			continue ;
		
	inputTrainNo = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(2, rowNum)
	inputJrnyDate = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(3, rowNum)
	inputSrcStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(4, rowNum)
	inputDestnStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(5, rowNum)
	inputClassCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(6 , rowNum)
	inputQuotaCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(7, rowNum)
	inputPsgnCount = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(8, rowNum)
	inputBoardingStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(9, rowNum)
	inputResvnUptoStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(10, rowNum)
	inputCompletePsgnDtls = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(11, rowNum)
	inputMobileNo = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(12, rowNum)
	inputPaymentType = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(13, rowNum)
	singlePsgnDtlsString = inputCompletePsgnDtls.split("#")
	
	dataInRow = inputTrainNo + recordSeparator + inputJrnyDate + recordSeparator + inputSrcStnCode + recordSeparator + inputDestnStnCode + recordSeparator +
	inputClassCode + recordSeparator + inputQuotaCode + recordSeparator + inputPsgnCount + recordSeparator  + inputBoardingStnCode+ recordSeparator +
	inputResvnUptoStnCode + recordSeparator + inputCompletePsgnDtls + recordSeparator + inputMobileNo + recordSeparator  + inputPaymentType
	System.out.println('Data In One Row =' + dataInRow)
	
	
	//To add psgn Details in List - start
	for (int ipPsgnCount = 0 ; ipPsgnCount < singlePsgnDtlsString.length; ipPsgnCount++) {
		singlePsgnDtls = new SinglePsgnDtls()
		//System.out.println('Single Psgn  =' + singlePsgnDtlsString[ipPsgnCount])
		psgnAttribute = singlePsgnDtlsString[ipPsgnCount].split("%")
	//	System.out.println('\n\nPassenger Name is =' + psgnAttribute[0])
		singlePsgnDtls.setPsgnName(psgnAttribute[0])
		singlePsgnDtls.setPsgnGender(psgnAttribute[1])
		singlePsgnDtls.setPsgnAge(psgnAttribute[2])
		singlePsgnDtls.setPsgnBerthPreference(psgnAttribute[3])
		singlePsgnDtls.setPsgnFoodPreference(psgnAttribute[4])
		psgnDtlsList.add(singlePsgnDtls)
	}
	//To add psgn Details in List - end
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Home'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_Train'))
	WebUI.setText(findTestObject('null'),inputTrainNo)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/trainNo_Name_firstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	CustomKeywords.'reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot'()
	CustomKeywords.'reusableComponents.CustomKeywords.setDateUsingJS'('Object Repository/NxtGenPRS_OR/QB_JourneyDtls/journeyDate',inputJrnyDate)
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'),
	GlobalVariable.waitForAvailabilitySec)
	statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'))
	System.out.println('Station fetched Message = ' + statusMsg) 
    WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyFromStation'), inputSrcStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/src_FirstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyToStation'), inputDestnStnCode)
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
    WebUI.delay(GlobalVariable.sleepTimeInSec)
	
	if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_JourneyToErrorMsg'), 1, FailureHandling.OPTIONAL) == true) {
		statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_JourneyToErrorMsg'))
		System.out.println('ERROR MESSAGE IS =' + statusMsg)
		if (WebUI.verifyEqual(statusMsg, 'Please enter a valid Station.')) {
			testResultStatus = TestLinkAPIResults.TEST_PASSED ;	
			
		} 
		else {
			testResultStatus = TestLinkAPIResults.TEST_FAILED ;
			
		}
	}
	
	execNotes += dataInRow +  "\n" + testCaseName +'::'+ statusMsg ;
	
	
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	
	
   // WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Quick Book'))
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Home'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
	
	
	Instant endExecutionTime = Instant.now();
	Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
	execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;
	
	
	
	CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
		 					testPlanName, 			
						 	testCaseName,			
							buildName,		
							execNotes,		
							testResultStatus)


	
	
} //End of all records in input file

