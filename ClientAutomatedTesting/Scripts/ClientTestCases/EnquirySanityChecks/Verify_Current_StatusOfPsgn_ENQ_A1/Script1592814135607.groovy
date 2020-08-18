import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
import enquiryIoStructures.InputCurrentStatusOfPsgn_A1 as InputCurrentStatusOfPsgn_A1
import enquiryIoStructures.OutputCurrentStatusOfPsgn_A1 as OutputCurrentStatusOfPsgn_A1

InputCurrentStatusOfPsgn_A1 inputCurrentStatusOfPsgn_A1 ;

 

  List<InputCurrentStatusOfPsgn_A1> inputCurrentStatusOfPsgn_A1List = new ArrayList<InputCurrentStatusOfPsgn_A1>();
  List<OutputCurrentStatusOfPsgn_A1> outputCurrentStatusOfPsgn_A1List = new ArrayList<OutputCurrentStatusOfPsgn_A1>();
  char recordSeparator = ',';
  String jrnyDate,currentDate =null;
  String fileName= "EnquirySanityChecks";
   

 jrnyDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getJourneyDate'();
 currentDate= CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getCurrentDate'();
  String outputFile = (fileName + '_' + currentDate) + '.csv'

  System.out.println("currentDate") ;


 inputCurrentStatusOfPsgn_A1List = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.readInputCurrentStatusOfPsgn'(".\\UserDataFiles\\inputfiles\\EnquiryDataFiles\\Verify_Current_StatusOfPsgn_A1.csv")

 System.out.println("No records in input file=" + inputCurrentStatusOfPsgn_A1List.size())
 System.out.println("===================================") ;
 
 WebUI.openBrowser('')
 
	WebUI.navigateToUrl(GlobalVariable.url)
 
	WebUI.maximizeWindow()
	
   for ( int recordCount = 0; recordCount < inputCurrentStatusOfPsgn_A1List.size(); recordCount++) {
	   
	 try {
		 
		  inputCurrentStatusOfPsgn_A1 = new InputCurrentStatusOfPsgn_A1() ;
		  recordString = '' ;
	
		  testCaseId      = inputCurrentStatusOfPsgn_A1List.get(recordCount).getTestCaseId()
		  journeyDate     = jrnyDate;
		  pnrNo           = inputCurrentStatusOfPsgn_A1List.get(recordCount).getPnrNo()
		  siteName           = inputCurrentStatusOfPsgn_A1List.get(recordCount).getSiteName()
		  siteCode           = inputCurrentStatusOfPsgn_A1List.get(recordCount).getSiteCode()
		  
		  OutputCurrentStatusOfPsgn_A1 outputCurrentStatusOfPsgn_A1 = new OutputCurrentStatusOfPsgn_A1();
		  
		   String functionalityName ="Current Status Of Passengers";
		   outputCurrentStatusOfPsgn_A1.setTestCaseId(testCaseId);
		   outputCurrentStatusOfPsgn_A1.setFunctionalityName(functionalityName);
		   outputCurrentStatusOfPsgn_A1.setPnrNo(pnrNo);
		   outputCurrentStatusOfPsgn_A1.setSiteCode(siteCode);
		   outputCurrentStatusOfPsgn_A1.setSiteName(siteName);
		   
  
		   // dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
		   CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfCurrentStatusOfPsgn'(pnrNo)
		  
		  
		   if(WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/div_Journey Details'),
			   GlobalVariable.waitForOutput)){
			   status='passed';
			   outputCurrentStatusOfPsgn_A1.setStatus(status);
			   outputCurrentStatusOfPsgn_A1.setErrorMsg();
			}
			   else {
			
			  errorMsg=WebUI.getText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/div_error Error'));
				status="failed";
			  if(errorMsg.length()>50){
			  
		  errorMsg = errorMsg.substring(0,GlobalVariable.errorMsgLength);
		  }
		  outputCurrentStatusOfPsgn_A1.setErrorMsg(errorMsg);
		  outputCurrentStatusOfPsgn_A1.setStatus(status);
		  
		  System.out.println(errorMsg);
		  }
			outputCurrentStatusOfPsgn_A1List.add(outputCurrentStatusOfPsgn_A1)
		
  
		  
	  } catch (Exception e) {
		  e.printStackTrace()
	  }
  }
  CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeCurrentStatusOfPsgnOutputFile'(outputFile,outputCurrentStatusOfPsgn_A1List)
  
  
  
  
