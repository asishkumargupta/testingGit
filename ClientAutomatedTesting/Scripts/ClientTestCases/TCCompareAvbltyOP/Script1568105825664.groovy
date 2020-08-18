import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import ioStructureAllClsAccoAvblty.AllClsAccoAvbltyOutput as TDRCdetails
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint



WebUI.callTestCase(findTestCase('ClientTestCases/TCGetAccAvblDD'), [('inputPNRNo') : '', ('trainNo') : '', ('jrnyDate') : ''
        , ('sourceStnCode') : '', ('destnStnCode') : '', ('quotaCode') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('ClientTestCases/TC_GetAllClsAccoAvblty_MCP'), [('Username') : '', ('Password') : ''], FailureHandling.STOP_ON_FAILURE)



String mcpCreatedFileName = ".\\UserDataFiles\\outfiles\\" + GlobalVariable.oldEnvFileName 
String nxtGenCreatedFileName = ".\\UserDataFiles\\outfiles\\" + GlobalVariable.newEnvFileName 

System.out.println('Old Env Filename= ' + mcpCreatedFileName )
System.out.println('New Env Filename=' + nxtGenCreatedFileName)




CustomKeywords.'reusableComponents.CustomKeywords.diffOfTwoFiles'(mcpCreatedFileName,nxtGenCreatedFileName )



