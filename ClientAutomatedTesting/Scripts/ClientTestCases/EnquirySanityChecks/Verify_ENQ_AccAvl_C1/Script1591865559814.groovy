 import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import enquiryIoStructures.InputAccAvbl_C1 as InputAccAvbl_C1
import enquiryIoStructures.OutputAccAvbl_C1 as OutputAccAvbl_C1
import java.awt.Desktop as Desktop
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.awt.image.BufferedImage as BufferedImage
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat


	InputAccAvbl_C1 inputAccAvbl_C1 ;
	
	List<InputAccAvbl_C1> inputAccAvbl_C1List = new ArrayList<InputAccAvbl_C1>();
	List<OutputAccAvbl_C1> outputAccAvbl_C1List = new ArrayList<OutputAccAvbl_C1>();
	
	char recordSeparator = ',';
	String jrnyDate,currentDate = null;
	
	String fileName= "EnquirySanityChecks";
	
	
	jrnyDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getJourneyDate'();
	currentDate= CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getCurrentDate'();
	String outputFile = (fileName + '_' + currentDate) + '.csv'
	
	System.out.println("READING FILE") ;
	
	
	inputAccAvbl_C1List = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.readInputAccAvblDtls'(".\\UserDataFiles\\inputfiles\\EnquiryDataFiles\\Verify_ENQ_AccAvl_C1.csv")
	
	System.out.println("No records in input file=" + inputAccAvbl_C1List.size())
	System.out.println("===================================") ;
	
	
	WebUI.openBrowser('')
	
	
			WebUI.navigateToUrl(GlobalVariable.url)
	
			WebUI.maximizeWindow()
			
	for ( int recordCount = 0; recordCount < inputAccAvbl_C1List.size(); recordCount++) {
			
	try {
		
		inputAccAvbl_C1 = new InputAccAvbl_C1() ;
		recordString = '' ;
		testCaseId   = inputAccAvbl_C1List.get(recordCount).getTestCaseId()
		trainNo      = inputAccAvbl_C1List.get(recordCount).getTrainNo() 
		journeyDate  = jrnyDate; 
		sourceCode   = inputAccAvbl_C1List.get(recordCount).getSourceCode()  
		destnCode    = inputAccAvbl_C1List.get(recordCount).getDestnCode() 
		classCode    = inputAccAvbl_C1List.get(recordCount).getClassCode()
		quotaCode    = inputAccAvbl_C1List.get(recordCount).getQuotaCode() 
		siteCode     = inputAccAvbl_C1List.get(recordCount).getSiteCode()
		siteName     = inputAccAvbl_C1List.get(recordCount).getSiteName()
		
	
		
		
		
		OutputAccAvbl_C1 outputAccAvbl_C1 = new OutputAccAvbl_C1();
		String testCaseId ='TC_CL_ACCAVBL_ENQ';
		
	     String functionalityName ="Accommodation Availability Enquiry";
		
		outputAccAvbl_C1.setTestCaseId(testCaseId);
		outputAccAvbl_C1.setFunctionalityName(functionalityName);
		outputAccAvbl_C1.setSiteCode(siteCode);
		outputAccAvbl_C1.setSiteName(siteName);
		
		
		
		
		
		CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfAccAvbl'(trainNo,journeyDate,
																					sourceCode,
																					destnCode,
																					quotaCode,classCode)
		
		
		if(WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/Fetched_availability'),
			GlobalVariable.waitForOutput)){
		
		status='passed';
		outputAccAvbl_C1.setStatus(status);	
		
		
		
		}else{
		
		errorMsg=WebUI.getText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/div_error'));
		status='failed';
		
		if(errorMsg.length()>50){
			
		errorMsg = errorMsg.substring(0,GlobalVariable.errorMsgLength);
		}
		outputAccAvbl_C1.setErrorMsg(errorMsg);
		outputAccAvbl_C1.setStatus(status);
		
		System.out.println(errorMsg);
		}
		
		 outputAccAvbl_C1List.add(outputAccAvbl_C1)
																					
	} catch (Exception e) {	
	
		e.printStackTrace()
		
	}
		} 
		
	   CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeAccAvbltyOutputFile'(outputFile,outputAccAvbl_C1List)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	