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
	String currentDate = null;
	String dataInRow= "";
		
	//String FS_TC_Client_ENQ_ACC_AVBL;
	//String outFile = 'FS_TC_Client_ENQ_ACC_AVBL.csv'
	
	
	currentDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getDate'();
	//String errorFileNameWithoutExtn = testCaseName + '_' + timenow ;
	//String partialImageStartName = '',
	//	exceptionImageNameOnly = '';
	//String outFile = (testCaseName	 + timenow) + '.csv'
	
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
		psgnDtlsString = '';
		trainNo      = inputAccAvbl_C1List.get(recordCount).getTrainNo() 
		journeyDate  = currentDate; // inputAccAvbl_C1List.get(recordCount).getJourneyDate()
		sourceCode   = inputAccAvbl_C1List.get(recordCount).getSourceCode()  
		destnCode    = inputAccAvbl_C1List.get(recordCount).getDestnCode() 
		classCode    = inputAccAvbl_C1List.get(recordCount).getClassCode()
		quotaCode    = inputAccAvbl_C1List.get(recordCount).getQuotaCode() 
		siteCode     = inputAccAvbl_C1List.get(recordCount).getSiteCode()
		siteName     = inputAccAvbl_C1List.get(recordCount).getSiteName()
		
		// journeyDate = currentDate+(GlobalVariable.noOfDays)
		
		System.out.println("siteCode" + siteCode)
		System.out.println("siteName" + siteName)
		
		OutputAccAvbl_C1 outputAccAvbl_C1 = new OutputAccAvbl_C1();
		String testCaseId ='TC_CL_ACCAVBL_ENQ';
		
	     String functionalityName ="Accommodation Availability Enquiry";
		
		outputAccAvbl_C1.setTestCaseId(testCaseId);
		outputAccAvbl_C1.setFunctionalityName(functionalityName);
		outputAccAvbl_C1.setSiteCode(siteCode);
		outputAccAvbl_C1.setSiteName(siteName);
		
		
		
		// dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
	
		
		CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfAccAvbl'(trainNo,journeyDate,
																					sourceCode,
																					destnCode,
																					quotaCode,classCode)
		
		
		if(WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/Fetched_availability'),
			GlobalVariable.waitForAvailabilitySec)){
		
		status='passed';
		outputAccAvbl_C1.setStatus(status);	
		outputAccAvbl_C1.setErrorMsg();
		outputAccAvbl_C1List.add(outputAccAvbl_C1)
		
		CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeAccAvbltyOutputFile'('OutputStatus',outputAccAvbl_C1List)
		}else{
		status='failed';
		outputAccAvbl_C1.setStatus(status);
		outputAccAvbl_C1.setErrorMsg();
		outputAccAvbl_C1List.add(outputAccAvbl_C1)
		
		CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeAccAvbltyOutputFile'('OutputStatus',outputAccAvbl_C1List)
		
		
		}
		
		//statusMsg = WebUI.getText(findTestObject('Object Repository/AccAvbl_OR/Page_NxtGenPRS/Total_AccAvbl_MsgFetched'))
		
		//System.out.println('Avaiability Status Msg = ' + statusMsg //WebUI.click(findTestObject('Nxt	GenPRS_OR/PageAllClassesAccoAvailability/button_Reset'))
																					
		
		
		} catch (Exception e) {	
	
		e.printStackTrace()
		
		}
		
		} 
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	