import internal.GlobalVariable
import ioStructures.BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

	List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()
	BookedTktPnrDtls bookedTktPnrDtls ;
	String testCaseId ='' ,
			testCaseName = "TC_CONCESSION_BOOKING";
	List<BookedTktPnrDtls> bookedTktPnrDtlsList = new ArrayList()
	char recordSeparator = ',';
	String timenow = null;
	timenow = CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'();
	String errorFileNameWithoutExtn = 'TC_ConcessionalBooking_' + timenow ;
	String tktBookedDtlsFileNameWithoutExtn = 'ConcessionalBookedTktDtls_' + timenow;
	
	
	
	String partialImageStartName = '';
	
	//String partialImageStartName = "D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\outfiles\\" + "exceptionImage" + timenow + ".png" ;
	

	
	System.out.println("READING FILE") ;
	//inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")
	
	//inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'(".\\UserDataFiles\\inputfiles\\ConcessionalBookingData.csv")
	inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputConcessionalTDRCPsgnDtls'(".\\UserDataFiles\\inputfiles\\CONCESSIONAL_BOOKING_DATA.csv")
	
	//System.out.println("===================================") ;
	//System.out.println("WRITING FILE") ;
//	CustomKeywords.'reusableComponents.CustomKeywords.writeInputTDRCPsgnDtls'("asish",inputTDRCPsgnDtlsList)
	System.out.println("===================================") ;
	
	CustomKeywords.'reusableComponents.CustomKeywords.preConditionsInfo'('Ensure proper testing environments for input trains in dat file \n are Available for booking')

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
		
		CustomKeywords.'reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB'(mobileNo,paymentType)
		bookedTktPnrDtls = CustomKeywords.'reusableComponents.CustomKeywords.getBookedTktDtlsClickNextQB'(testCaseId , noOfPsgn)
		bookedTktPnrDtlsList.add(bookedTktPnrDtls)
	//	WebUI.delay(5)
		} catch (Exception e) {
			
			System.out.println("Exception Occured for test case id="+ testCaseId)
		//	CustomKeywords.'reusableComponents.CustomKeywords.takePartialScreenShot'(partialImageStartName ,findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/screenShotDiv'),GlobalVariable.waitForElementVisibleSec)
			
			CustomKeywords.'reusableComponents.CustomKeywords.takeCompleteScreenShot'(partialImageStartName)
			CustomKeywords.'reusableComponents.CustomKeywords.writeErrorLog'(errorFileNameWithoutExtn,testCaseId)
			
		}
		
		} /*End of for loop for recordCount */
		
	CustomKeywords.'reusableComponents.CustomKeywords.writeBookedTktDtls'(tktBookedDtlsFileNameWithoutExtn,bookedTktPnrDtlsList)