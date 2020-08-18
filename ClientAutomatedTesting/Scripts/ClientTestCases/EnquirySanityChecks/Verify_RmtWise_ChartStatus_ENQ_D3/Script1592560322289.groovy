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
import enquiryIoStructures.InputRmtWiseChartStatus_D3 as InputRmtWiseChartStatus_D3
import enquiryIoStructures.OutputRmtWiseChartStatus_D3 as OutputRmtWiseChartStatus_D3


InputRmtWiseChartStatus_D3 inputRmtWiseChartStatus_D3 ;

 

  List<InputRmtWiseChartStatus_D3> inputRmtWiseChartStatus_D3List = new ArrayList<InputRmtWiseChartStatus_D3>();
  List<OutputRmtWiseChartStatus_D3> outputRmtWiseChartStatus_D3List = new ArrayList<OutputRmtWiseChartStatus_D3>();
  char recordSeparator = ',';
  String jrnyDate,currentDate =null;
  String fileName= "EnquirySanityChecks";
   

 jrnyDate = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getJourneyDate'();
 currentDate= CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.getCurrentDate'();
  String outputFile = (fileName + '_' + currentDate) + '.csv'

  System.out.println("currentDate") ;


 inputRmtWiseChartStatus_D3List = CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.readInputRmtWiseChartStatus'(".\\UserDataFiles\\inputfiles\\EnquiryDataFiles\\Verify_RmtWise_ChartStatus_ENQ_D3.csv")

 System.out.println("No records in input file=" + inputRmtWiseChartStatus_D3List.size())
 System.out.println("===================================") ;


   WebUI.openBrowser('')

   WebUI.navigateToUrl(GlobalVariable.url)

   WebUI.maximizeWindow()
   
  for ( int recordCount = 0; recordCount < inputRmtWiseChartStatus_D3List.size(); recordCount++) {
	try {
		 inputRmtWiseChartStatus_D3 = new InputRmtWiseChartStatus_D3() ;
		 recordString = '' ;
		 psgnDtlsString = '';
		 testCaseId      = inputRmtWiseChartStatus_D3List.get(recordCount).getTestCaseId()
		 journeyDate     = jrnyDate;
		 trainNo         = inputRmtWiseChartStatus_D3List.get(recordCount).getTrainNo()
		 sourceCode      = inputRmtWiseChartStatus_D3List.get(recordCount).getSourceCode()
		 destnCode       = inputRmtWiseChartStatus_D3List.get(recordCount).getDestnCode()
		 classCode       = inputRmtWiseChartStatus_D3List.get(recordCount).getClassCode()
		 siteName        = inputRmtWiseChartStatus_D3List.get(recordCount).getSiteName()
		 siteCode        = inputRmtWiseChartStatus_D3List.get(recordCount).getSiteCode()
		 
		 OutputRmtWiseChartStatus_D3 outputRmtWiseChartStatus_D3 = new OutputRmtWiseChartStatus_D3();
		
		 String functionalityName ="Remote Wise Charting Status";
		 outputRmtWiseChartStatus_D3.setTestCaseId(testCaseId);
		 outputRmtWiseChartStatus_D3.setFunctionalityName(functionalityName);
		 outputRmtWiseChartStatus_D3.setSiteName(siteName);
		 outputRmtWiseChartStatus_D3.setSiteCode(siteCode);
		 

		 // dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
		 CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfRmtWiseChartStatus'(trainNo,journeyDate,sourceCode,
																		  destnCode,classCode)
		
		
		 if(WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/get_ChartStatus'),
			 GlobalVariable.waitForOutput)){
			 status='passed';
			 outputRmtWiseChartStatus_D3.setStatus(status);
			 outputRmtWiseChartStatus_D3.setErrorMsg();
		  } 
			 else {
		  
			errorMsg=WebUI.getText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/span_ ErrorMsg'));
			  status="failed";
			if(errorMsg.length()>50){
			
		errorMsg = errorMsg.substring(0,GlobalVariable.errorMsgLength);
		}
		outputRmtWiseChartStatus_D3.setErrorMsg(errorMsg);
		outputRmtWiseChartStatus_D3.setStatus(status);
		
		System.out.println(errorMsg);
		}
		  outputRmtWiseChartStatus_D3List.add(outputRmtWiseChartStatus_D3)
		 

		
	} catch (Exception e) {
		e.printStackTrace()
	}
}
CustomKeywords.'reusableComponents.CustomKeywordsForEnquiry.writeRmtWiseChartStatusOutputFile'(outputFile,outputRmtWiseChartStatus_D3List)



