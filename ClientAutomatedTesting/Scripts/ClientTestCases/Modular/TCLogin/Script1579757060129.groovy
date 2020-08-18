import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


KeywordLogger logger = new KeywordLogger()

//PropertyConfigurator.configure("path of .properties file")

logger.logInfo("TESTING LOGGER IN KATALON using Keyword Logger")
KeywordUtil.logInfo("TESTING LOGGER IN KATALON")

WebUI.openBrowser('')


WebUI.navigateToUrl(GlobalVariable.url)

WebUI.maximizeWindow()
WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
//WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/div_Office PC IP address'),GlobalVariable.officePcIp)
WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/input_Login_mat-input-0'), GlobalVariable.clientUserid)

WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/input_User Name_mat-input-1'),
		GlobalVariable.clientPasswd)


//WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/inputIpAddress'),GlobalVariable.officePcIp)


WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/LogInDtls/inputIpAddress'),GlobalVariable.officePcIp)

WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/button_Login'))

if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/input_ticketNo'), GlobalVariable.waitForElementVisibleSec, FailureHandling.OPTIONAL) ==
	true) {
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/input_ticketNo'))
	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/input_ticketNo'),GlobalVariable.ticketNumber)
	

	}

	


WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/input_ReEnterTicketNo'),
	GlobalVariable.ticketNumber)

WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/imprestCash'),
	GlobalVariable.imprestCash)


WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/personalCash'),
	GlobalVariable.personalCash)






WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/stockEntryUserID'),GlobalVariable.clientSupervisorId)

WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/stockEntryUserPassword'),
		GlobalVariable.clientSupervisorPasswd)




WebUI.waitForElementClickable(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/button_SubmitStockDetails'), GlobalVariable.waitForElementClickableSec)

System.out.println('\nSCROLLING TILL SUBMIT\n' )
String scrollingPosition = 'Object Repository/NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/button_SubmitStockDetails'

WebUI.scrollToElement(findTestObject(scrollingPosition), GlobalVariable.sleepTimeInSec)


WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/LogInDtls/StockDtls/button_SubmitStockDetails'))

