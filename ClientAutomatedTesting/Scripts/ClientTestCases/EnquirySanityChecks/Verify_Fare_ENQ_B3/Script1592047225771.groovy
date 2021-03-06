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
import enquiryIoStructures.InputFareEnq_B3 as InputFareEnq_B3
import enquiryIoStructures.OutputFareEnq_B3 as OutputFareEnq_B3


 InputFareEnq_B3 inputFareEnq_B3 ;

  

  List<InputFareEnq_B3> inputFareEnq_B3List = new ArrayList<InputFareEnq_B3>();
  List<OutputFareEnq_B3> outputFareEnq_B3List = new ArrayList<OutputFareEnq_B3>();
  char recordSeparator = ',';
  String jrnyDate,currentDate =null;
  String fileName= "EnquirySanityChecks";
   

 jrnyDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getJourneyDate'();
 currentDate= CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getCurrentDate'();
  String outputFile = (fileName + '_' + currentDate) + '.csv'

  System.out.println("currentDate") ;


 inputFareEnq_B3List = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.readInputFareEnqDtls'(".\\UserDataFiles\\inputfiles\\EnquiryDataFiles\\Verify_Fare_ENQ.csv")

 System.out.println("No records in input file=" + inputFareEnq_B3List.size())
 System.out.println("===================================") ;


   WebUI.openBrowser('')

   WebUI.navigateToUrl(GlobalVariable.url)

   WebUI.maximizeWindow()
   
  for ( int recordCount = 0; recordCount < inputFareEnq_B3List.size(); recordCount++) {
    try {
         inputFareEnq_B3 = new InputFareEnq_B3() ;
		 recordString = '' ;
		 testCaseId   = inputFareEnq_B3List.get(recordCount).getTestCaseId()
         trainNo      = inputFareEnq_B3List.get(recordCount).getTrainNo()
         journeyDate  = jrnyDate;
         srcCode      = inputFareEnq_B3List.get(recordCount).getSrcCode()
         destnStnCode = inputFareEnq_B3List.get(recordCount).getDestnStnCode()
         clsCode      = inputFareEnq_B3List.get(recordCount).getClassCode()
         quotaCode    = inputFareEnq_B3List.get(recordCount).getQuotaCode()
         catering     = inputFareEnq_B3List.get(recordCount).getCatering()
         adultNo      = inputFareEnq_B3List.get(recordCount).getAdultNo()
         childNo      = inputFareEnq_B3List.get(recordCount).getChildNo()
		 siteName     = inputFareEnq_B3List.get(recordCount).getSiteName()
		 siteCode     = inputFareEnq_B3List.get(recordCount).getSiteCode()

		 OutputFareEnq_B3 outputFareEnq_B3 = new OutputFareEnq_B3();
		 
		 String functionalityName ="Fare Enquiry";
		 outputFareEnq_B3.setTestCaseId(testCaseId);
		 outputFareEnq_B3.setFunctionalityName(functionalityName);
		 outputFareEnq_B3.setSiteName(siteName);
		 outputFareEnq_B3.setSiteCode(siteCode);

		 // dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
		 CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfFareEnq'(trainNo,journeyDate,
																		   srcCode,
																		   destnStnCode,
																		   quotaCode,clsCode,catering,adultNo,childNo)
		
		 
		 if(WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/FareEnq_B3/div_Base Fare'),
			 GlobalVariable.waitForOutput)){
         	status='passed';
			 outputFareEnq_B3.setStatus(status);	
			 outputFareEnq_B3.setErrorMsg();
          } else {
		  
		    errorMsg=WebUI.getText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/FareEnq_B3/div_error Error'));
		  	status="failed";
			  if(errorMsg.length()>50){
				  
			  errorMsg = errorMsg.substring(0,GlobalVariable.errorMsgLength);
			  outputFareEnq_B3.setErrorMsg(errorMsg);
			  }
			  
				  
			  outputFareEnq_B3.setErrorMsg(errorMsg);
			  
			  
			  outputFareEnq_B3.setStatus(status);
			  
			
		  }
		  outputFareEnq_B3List.add(outputFareEnq_B3)
		 

		
	} catch (Exception e) {
		e.printStackTrace()
	}
}
CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeFareEnqOutputFile'(outputFile,outputFareEnq_B3List)


