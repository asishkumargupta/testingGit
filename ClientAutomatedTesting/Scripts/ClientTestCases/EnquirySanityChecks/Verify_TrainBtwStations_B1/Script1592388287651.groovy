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
import enquiryIoStructures.InputTrainBtwStations_B1 as InputTrainBtwStations_B1
import enquiryIoStructures.OutputTrainBtwStations_B1 as OutputTrainBtwStations_B1



InputTrainBtwStations_B1 inputTrainBtwStations_B1 ;

 

  List<InputTrainBtwStations_B1> inputTrainBtwStations_B1List = new ArrayList<InputTrainBtwStations_B1>();
  List<OutputTrainBtwStations_B1> outputTrainBtwStations_B1List = new ArrayList<OutputTrainBtwStations_B1>();
  char recordSeparator = ',';
  String jrnyDate,currentDate =null;
  String fileName= "EnquirySanityChecks";
   

 jrnyDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getJourneyDate'();
 currentDate= CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getCurrentDate'();
  String outputFile = (fileName + '_' + currentDate) + '.csv'

  System.out.println("currentDate") ;


 inputTrainBtwStations_B1List = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.readInputTrainBtwStn'(".\\UserDataFiles\\inputfiles\\EnquiryDataFiles\\Verify_TrainBtwStn_ENQ_B1.csv")

 System.out.println("No records in input file=" + inputTrainBtwStations_B1List.size())
 System.out.println("===================================") ;


   WebUI.openBrowser('')

   WebUI.navigateToUrl(GlobalVariable.url)

   WebUI.maximizeWindow()
   
  for ( int recordCount = 0; recordCount < inputTrainBtwStations_B1List.size(); recordCount++) {
	try {
		 inputTrainBtwStations_B1 = new InputTrainBtwStations_B1() ;
		 recordString = '' ;
		 psgnDtlsString = '';
		 testCaseId      = inputTrainBtwStations_B1List.get(recordCount).getTestCaseId()
		 journeyDate     = jrnyDate;
		 originCode      = inputTrainBtwStations_B1List.get(recordCount).getOriginCode()
		 destnCode       = inputTrainBtwStations_B1List.get(recordCount).getDestnCode()
		 classCode       = inputTrainBtwStations_B1List.get(recordCount).getClassCode()
		 quotaCode       = inputTrainBtwStations_B1List.get(recordCount).getQuotaCode()
		 flexibleDate    = inputTrainBtwStations_B1List.get(recordCount).getFlexibleDate()
		 siteName        = inputTrainBtwStations_B1List.get(recordCount).getSiteName()
		 siteCode        = inputTrainBtwStations_B1List.get(recordCount).getSiteCode()
		 
		 OutputTrainBtwStations_B1 outputTrainBtwStations_B1 = new OutputTrainBtwStations_B1();
		
		 String functionalityName ="Train Between Stations Enquiry";
		 outputTrainBtwStations_B1.setTestCaseId(testCaseId);
		 outputTrainBtwStations_B1.setFunctionalityName(functionalityName);
		 outputTrainBtwStations_B1.setSiteName(siteName);
		 outputTrainBtwStations_B1.setSiteCode(siteCode);
		 

		 // dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
		 CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfTrainBtwStations'(journeyDate,originCode,
			                                                              destnCode,quotaCode,classCode,flexibleDate)

		
		 if(WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/div_trainsfound'),
			 GlobalVariable.waitForOutput)){
			 status='passed';
			 outputTrainBtwStations_B1.setStatus(status);
			 outputTrainBtwStations_B1.setErrorMsg();
		  } else {
		  
			//errorMsg=WebUI.getText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/div_error'));
			  status="failed";
			outputTrainBtwStations_B1.setStatus(status);
			outputTrainBtwStations_B1.setErrorMsg();
		  }
		  outputTrainBtwStations_B1List.add(outputTrainBtwStations_B1)
		 

		
	} catch (Exception e) {
		e.printStackTrace()
	}
}
CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeTrainBtwStationsOutputFile'(outputFile,outputTrainBtwStations_B1List)


