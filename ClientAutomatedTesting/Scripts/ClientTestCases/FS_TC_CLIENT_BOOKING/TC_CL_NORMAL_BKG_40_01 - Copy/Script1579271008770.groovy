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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select


String projectName = "FunctionalityWiseHierarchy",
testPlanName = "Test Plan to test APOS Client automatically",
testCaseName = "TC_CL_NORMAL_BKG_40_01" ,
buildName = "Build to Test FS_TC_CLIENT_BOOKING",
execNotes = "" ,
testResultStatus = TestLinkAPIResults.TEST_FAILED,
expectedMsg = "Mobile Number is Madatory if UPI mode is selected ",
dataToTest = '',
actualMsg = '';



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

SinglePsgnDtls singlePsgnDtls = new SinglePsgnDtls()
List<SinglePsgnDtls> psgnDtlsList =  new ArrayList()
ArrayList<String> paymentList = new ArrayList<String>();



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
	dataToTest = dataInRow 
	
	
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
	
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/destn_FirstSelected'))
	
	//For Clicking class code - start
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_ClassClass'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)

	for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {
		
		String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/cls' + classCount
		
		
		if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			
			className = WebUI.getText(findTestObject(testObject))
		
			classCode = className.split('-')[0].trim()

			if (classCode.equals(inputClassCode)) {
		
				WebUI.click(findTestObject(testObject))
			}
		   
		}
	}
	//For Clicking class code - end
	//For Clicking quota code - start
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_QuotaQuota'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int quotaCount = GlobalVariable.classCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {
		
		String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/quota' + quotaCount
		if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			quotaName = WebUI.getText(findTestObject(testObject))
		//	System.out.println('QUOTA NAME IS=' + quotaName)
			quotaCode = quotaName.split('-')[0].trim()
		//	System.out.println('QUOTA CODE IS=' + quotaCode)
		//	System.out.println('INPUT QUOTA CODE IS=' + inputQuotaCode)
			if (quotaCode.equals(inputQuotaCode)) {
			//	System.out.println('CLICKING QUOTA CODE')
				WebUI.click(findTestObject(testObject))
			}
		   
		}
	}
	//For Clicking quota code - end
	
	//For Clicking no of psgn - start
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_No of Passengers'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <= GlobalVariable.maxPsgn; psgnCount++) {
		String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/noOfPsgn' + psgnCount
		if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			noOfPsgn = WebUI.getText(findTestObject(testObject))
			//System.out.println('PSGN COUNT IN WEBPAGE=' + psgnCount)
			//System.out.println('PSGN COUNT INPUT=' + inputPsgnCount)
			if (noOfPsgn.equals(inputPsgnCount)) {
				System.out.println('CLICKING NO OF PSGN')
				WebUI.click(findTestObject(testObject)) //end of classc ount
				break ;
			}
			
		}
	}
		//For Clicking no of psgn - end
	
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	//	System.out.println('SETTING BOARDING STATION CODE WITH VALUES=' + inputBoardingStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_BrdgStnCode'), inputBoardingStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/brdgStnCode_FirstSelected'))
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_ResvnUptoStnCode'), inputResvnUptoStnCode)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/resvnUpto_FirstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/button_Next'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	
	
	//Passenger Details page - start
	//	System.out.println('NO OF PSGN='+psgnDtlsList.size())
		for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <=  psgnDtlsList.size(); psgnCount++) {
			String testPsgnNameObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			WebUI.click(findTestObject(testPsgnNameObject))
			String testPsgnNameInput = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/psgnName' + psgnCount
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			System.out.println('SETTING PSGN NAME AS='+psgnDtlsList.get(psgnCount-1).getPsgnName())
			WebUI.setText(findTestObject(testPsgnNameInput), psgnDtlsList.get(psgnCount-1).getPsgnName())
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			//For Clicking gender - start
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			String genderDiv = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Gender' + psgnCount
			WebUI.click(findTestObject(genderDiv))
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			for (int genderCount = GlobalVariable.genderCountStart; genderCount <= GlobalVariable.maxGenderCount; genderCount++) {
				String span_gender = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/gender' + psgnCount + '_' + genderCount
				if (WebUI.verifyElementPresent(findTestObject(span_gender), 1, FailureHandling.OPTIONAL) == true) {
					genderName = WebUI.getText(findTestObject(span_gender))
					//System.out.println('GENDER NAME=' + genderName)
					if (psgnDtlsList.get(psgnCount-1).getPsgnGender().equals(genderName)) {
						System.out.println('CLICKING NO OF PSGN')
						WebUI.click(findTestObject(span_gender))
						break ;
					}
					  
				}
			}
			//For Clicking gender - end
			String ageDiv = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Age' + psgnCount
			//For inputting Age - start
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			WebUI.click(findTestObject(ageDiv))
			WebUI.delay(GlobalVariable.sleepTimeInSec)
			String inputAgeElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/inputAge' + psgnCount
			WebUI.setText(findTestObject(inputAgeElement), psgnDtlsList.get(psgnCount-1).getPsgnAge())
			//For Clicking Age - end
			String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
			WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
			
		} //End of psgnCount for loop
		
		
		
		
	//For Clicking Payment type - start
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_PaymentType'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int paymentTypeCount = GlobalVariable.paymentCountStart; paymentTypeCount <= GlobalVariable.maxPaymentCount; paymentTypeCount++) {
		String span_paymentType = 'NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/span_paymentType' + paymentTypeCount
		if (WebUI.verifyElementPresent(findTestObject(span_paymentType), 1, FailureHandling.OPTIONAL) == true) {
			paymentType = WebUI.getText(findTestObject(span_paymentType))
			//System.out.println('PAYMENT TYPE=' + paymentType)
			if (paymentType.equals(inputPaymentType)) {
				System.out.println('CLICKING PAYMENT TYPE')
				WebUI.click(findTestObject(span_paymentType))
				break ;
			}
			  
		}
	}
	//For Clicking Payment Type - end
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/button_NextInPsgnDtls'))
	
	if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/mobileNoMandatoryError'),GlobalVariable.waitForElementPresent, FailureHandling.OPTIONAL) == true) {
		mobileNoMandatoryMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/mobileNoMandatoryError'))
		
		System.out.println('MOBILE MANDATORY MESSAGE=' + mobileNoMandatoryMsg)
		
		if (mobileNoMandatoryMsg.equals('You must enter a value.')) 
		{		
		
		testResultStatus = TestLinkAPIResults.TEST_PASSED ;
		actualMsg = "Mobile Number Mandatory message - PRESENT"
		
		}
	}	
	else {
		testResultStatus = TestLinkAPIResults.TEST_FAILED ;
		actualMsg = "Mobile Number Mandatory message - NOT PRESENT"
	}
	

	execNotes += testCaseName +'::'+
				'\nData to Test::' + dataToTest +  
					'\nExpected Result::' + expectedMsg + '\nActual Result::' + actualMsg;
	
	
	//WebUI.delay(GlobalVariable.sleepTimeInSec)
	//CustomKeywords.'reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot'()
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

