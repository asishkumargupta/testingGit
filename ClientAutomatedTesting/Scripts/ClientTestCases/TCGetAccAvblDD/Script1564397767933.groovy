import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import ioStructureAllClsAccoAvblty.AllClsAccoAvbltyOutput as TDRCdetails

String 		trainNo ='' ,
	jrnyDate = '',
	srcStnCode = '',
	destnStnCode = '',
	quotaCode = '' ,
	timenow = null ,
	outFileAvbltyListFileName = "NxtGenAVBL",
	testObject = '' ,
	lastClsPresent = '' ,
	statusMsg = '' ,
	dataInRow = '';
	
	char 	recordSeparator = ',' ;

int noOfClsPresent = 0;
timenow =  CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()
String outFile =  outFileAvbltyListFileName + timenow + ".csv" ;
GlobalVariable.newEnvFileName = outFile

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.url)
WebUI.maximizeWindow()

for (int rowNum = 1; rowNum <= findTestData('Enquiry/InputTDRCData').getRowNumbers(); rowNum++) {
	try { 
		
	
	
	
	noOfClsPresent = 0
	trainNo = findTestData('Enquiry/InputTDRCData').getValue(1, rowNum)
	jrnyDate = findTestData('Enquiry/InputTDRCData').getValue(2, rowNum)
	srcStnCode = findTestData('Enquiry/InputTDRCData').getValue(3, rowNum)
	destnStnCode = findTestData('Enquiry/InputTDRCData').getValue(4, rowNum)
	quotaCode = findTestData('Enquiry/InputTDRCData').getValue(5, rowNum)

	dataInRow = trainNo + recordSeparator + jrnyDate + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + quotaCode + recordSeparator
	System.out.println('Data In One Row =' + dataInRow)
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/span_Enquiries'))
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/span_AllClassAccomodationAvblty'))
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/inputTrainNo'))
	WebUI.setText(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/inputTrainNo'),
			trainNo)
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/trainNo_Name_firstSelected'))
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/input_jrnyDate'))
	//WebUI.delay(2)
	WebUI.sendKeys(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/input_jrnyDate'),
			Keys.chord(Keys.CONTROL, 'a'))
	//WebUI.delay(2)
	WebUI.sendKeys(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/input_jrnyDate'),
			Keys.chord(Keys.BACK_SPACE))
	CustomKeywords.'reusableComponents.CustomKeywords.setDateUsingJS'('Object Repository/NxtGenPRS_OR/PageAllClassesAccoAvailability/input_jrnyDate',
			jrnyDate)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.sendKeys(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/input_jrnyDate'),
			Keys.chord(Keys.ESCAPE), FailureHandling.CONTINUE_ON_FAILURE)

	WebUI.delay(GlobalVariable.sleepTimeInSec)

	WebUI.setText(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/inputStation'),
			srcStnCode)

	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/sourceStnCodeName_firstSelected'))

	WebUI.setText(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/input_Destination'),
			destnStnCode)

	WebUI.delay(GlobalVariable.sleepTimeInSec)

	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/destnStnCodeName_firstSelected'))
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/quotaCodeNameInputBox'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/quotaCodeName_firstSelected'))
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/classCodeNameInputBox'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/PageAllClassesAccoAvailability/class' + classCount

			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
				noOfClsPresent ++ ;
				System.out.println('Clicking class count  ' + classCount)
				WebUI.click(findTestObject(testObject)) //end of classc ount

				lastClsPresent = WebUI.getText(findTestObject(testObject))

			}
		}
	//	System.out.println('No of classes in the train ' + noOfClsPresent)
	//	System.out.println('Last class present in train ' + lastClsPresent)

	WebUI.delay(GlobalVariable.sleepTimeInSec)
	//	String emptyClickableSpace = "(.//*[normalize-space(text()) and normalize-space(.)='" + lastClsPresent +"'])[1]/preceding::div[" + noOfClsPresent + "]"
	CustomKeywords.'reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot'()
	WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/button_Fetch Availability'))
	WebUI.delay(GlobalVariable.toastErrorMsgDelay)

	WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/toastMsgAvbltyStatusFetchedMsg'),
			GlobalVariable.waitForAvailabilitySec)

	statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/toastMsgAvbltyStatusFetchedMsg'))

	System.out.println('Avaiability Status Msg = ' + statusMsg) //WebUI.click(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/button_Reset'))

	WebUI.verifyElementText(findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/toastMsgAvbltyStatusFetchedMsg'), 'Availability Status Fetched!')
	CustomKeywords.'reusableComponents.CustomKeywords.writeAllClsAvbltyOutputFile'(dataInRow , outFile,  findTestObject('NxtGenPRS_OR/PageAllClassesAccoAvailability/PageAllClassAvbltyOutputStatus/completeAvbltyStatusTable'),
			GlobalVariable.sleepTimeInSec)
	} catch (Exception e) {
		e.printStackTrace();
	}

	
	
	
} //End of all records in input file

