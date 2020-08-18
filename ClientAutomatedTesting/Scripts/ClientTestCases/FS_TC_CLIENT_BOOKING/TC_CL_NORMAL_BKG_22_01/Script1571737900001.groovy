import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import java.time.Duration;
import java.time.Instant;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import ioStructures.BookedTktPnrDtls as BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls as InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls as InputTDRCPsgnDtls

String testStatus = "FAILED" ,
projectName = "FunctionalityWiseHierarchy",
testPlanName = "Test Plan to test APOS Client automatically",
testCaseName = "TC_CL_NORMAL_BKG_22_01" ,
buildName = "Build to Test FS_TC_CLIENT_BOOKING",
execNotes = "" ,
testResultStatus = TestLinkAPIResults.TEST_FAILED,
expectedMsg = "Male with Age more than 11 is not allowed",
dataToTest = '',
actualMsg = '';



Instant startExecutionTime = null ;
startExecutionTime = Instant.now();


List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()

BookedTktPnrDtls bookedTktPnrDtls

String testCaseId = ''

List<InputTDRCPsgnDtls> bookedTktPnrDtlsList = new ArrayList()

char recordSeparator = ','

String timenow = null

timenow = CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()

String errorFileNameWithoutExtn = (testCaseName + '_err_') + timenow

String tktBookedDtlsFileNameWithoutExtn = (testCaseName + '_bookDtls_') + timenow

String inputTrainNo = ''

String inputJrnyDate = ''

String inputSrcStnCode = ''

String inputDestnStnCode = ''

String inputClassCode = ''

String inputNoOfPsgn = ''

String inputQuotaCode = ''

String inputPsgnCount = ''

String inputBoardingStnCode = ''

String inputResvnUptoStnCode = ''

String inputMobileNo = ''

String inputPaymentType = ''

String inputCompletePsgnDtls = ''

String inputSinglePsgnDtls = ''

String partialImageStartName = ''

System.out.println('READING FILE')

inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputConcessionalTDRCPsgnDtls'('.\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv')

System.out.println('===================================')

for (int recordCount = 0; recordCount < inputTDRCPsgnDtlsList.size(); recordCount++) {
	try {
		bookedTktPnrDtls = new BookedTktPnrDtls()

		List<InputTDRCPsgnDtls> psgnDtlsList = new ArrayList()

		recordString = ''

		psgnDtlsString = ''

		trainNo = inputTDRCPsgnDtlsList.get(recordCount).getTrainNo()

		testCaseId = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId()

		doj = inputTDRCPsgnDtlsList.get(recordCount).getDoj()

		srcStnCode = inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode()

		destnStnCode = inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode()

		clsCode = inputTDRCPsgnDtlsList.get(recordCount).getClassCode()

		quotaCode = inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode()

		noOfPsgn = inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size().toString()

		boardingStnCode = inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode()

		resvnUptoStnCode = inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode()

		coachChoice = inputTDRCPsgnDtlsList.get(recordCount).getCoachChoice()

		mobileNo = inputTDRCPsgnDtlsList.get(recordCount).getMobileNo()

		paymentType = inputTDRCPsgnDtlsList.get(recordCount).getPaymentType()

		partialImageStartName = (((('.\\UserDataFiles\\outfiles\\' + testCaseId) + 'exceptionImage') + timenow) + '.png')

		dataToTest = ((((((((testCaseId + recordSeparator) + trainNo) + recordSeparator) + srcStnCode) + recordSeparator) +
				destnStnCode) + recordSeparator) + clsCode)

		if (!(testCaseId.equals(testCaseName))) {
			continue
		}

		CustomKeywords.'reusableComponents.CustomKeywords.fillTDRCInQB'(trainNo, doj, srcStnCode, destnStnCode, clsCode,
				quotaCode, noOfPsgn, boardingStnCode, resvnUptoStnCode, coachChoice)

		psgnDtlsList = inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList()

		CustomKeywords.'reusableComponents.CustomKeywords.fillConcessionalPsgnDtlsInQB'(psgnDtlsList)

		CustomKeywords.'reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB'(mobileNo, paymentType)



		if (WebUI.verifyElementPresent(		findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/msg_Male with Age more than 11 is not allowed'),GlobalVariable.waitForElementPresent, FailureHandling.OPTIONAL) == true)
			actualMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/msg_Male with Age more than 11 is not allowed'))
		System.out.println('ACTUAL Message found=' + actualMsg)
		if (WebUI.verifyEqual(actualMsg, expectedMsg,FailureHandling.CONTINUE_ON_FAILURE))
			testResultStatus = TestLinkAPIResults.TEST_PASSED ;
		else
			testResultStatus = TestLinkAPIResults.TEST_FAILED ;




		//statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/span_Please fill passengers details'))





		execNotes += (((((((('\n' + testCaseName) + '::') + '\nData to Test::') + dataToTest) + '\nExpected Result::') +
				expectedMsg) + '\nActual Result::') + actualMsg)

		'------------------------------------------------' //	WebUI.delay(5)
	}
	catch (Exception e) {
		System.out.println('Exception Occured for test case id=' + testCaseId)

		//	CustomKeywords.'reusableComponents.CustomKeywords.takePartialScreenShot'(partialImageStartName ,findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/screenShotDiv'),GlobalVariable.waitForElementVisibleSec)
		testResultStatus = TestLinkAPIResults.TEST_FAILED

		CustomKeywords.'reusableComponents.CustomKeywords.takeCompleteScreenShot'(partialImageStartName)

		CustomKeywords.'reusableComponents.CustomKeywords.writeErrorLog'(errorFileNameWithoutExtn, testCaseId)
	}
	



}
Instant endExecutionTime = Instant.now();
Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;

CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
		testPlanName,
		testCaseName,
		buildName,
		execNotes,
		testResultStatus)

