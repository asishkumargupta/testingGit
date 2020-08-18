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

List<String> trainAvailabilityList = new ArrayList();
String outFileAvbltyListFileName = "mcp",


timenow =  CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()
String outFile =  outFileAvbltyListFileName + timenow + ".csv" ;
GlobalVariable.oldEnvFileName = outFile
// trainAvailabilityList  = CustomKeywords.'reusableComponents.CustomKeywords_nativeEnv.executeAutoItScript'('D:\\TestingModule\\Assignments\\2019-2020\\AutomatedAvbltyEnquiry_17678_inProgress\\AUTOIT_scripts\\GetAllClsAvbltyUsingCSV.exe')

trainAvailabilityList  = CustomKeywords.'reusableComponents.CustomKeywords_nativeEnv.executeAutoItScript'('.\\autoScripts\\GetAllClsAvbltyUsingCSV.exe')

//System.out.println('resultReturned in TC_GetAllClsAccoAvblty  = ' + resultReturned )

System.out.println('\n\nNow in  TC_GetAllClsAccoAvblty = ' )

for ( int i = 0; i < trainAvailabilityList.size(); i++) { 
	
	System.out.println(trainAvailabilityList.get(i) )
	
}
/*
String strMain =" Welcome to Guru99 ";
String[] arrSplit_3 = strMain.split("\\s");
	for (int i=0; i < arrSplit_3.length; i++){
	  System.out.println("arrSplit_3[" + i +"]" + arrSplit_3[i]);
	}

*/

CustomKeywords.'reusableComponents.CustomKeywords_nativeEnv.writeAllClsAvbltyUsingMCP'(outFile, trainAvailabilityList )
