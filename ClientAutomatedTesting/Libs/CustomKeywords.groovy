
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import java.util.List

import com.kms.katalon.core.testobject.TestObject

import com.kms.katalon.core.model.FailureHandling


def static "reusableComponents.CustomKeywords.openHtmlPage"(
    	String url	) {
    (new reusableComponents.CustomKeywords()).openHtmlPage(
        	url)
}

def static "reusableComponents.CustomKeywords.openPage"(
    	String url	) {
    (new reusableComponents.CustomKeywords()).openPage(
        	url)
}

def static "reusableComponents.CustomKeywords.getDateTime"() {
    (new reusableComponents.CustomKeywords()).getDateTime()
}

def static "reusableComponents.CustomKeywords.writeOutputFile"(
    	String outFileWithoutExtn	
     , 	java.util.List<inputOutputStructures.PnrDetails> pnrDetailsList	) {
    (new reusableComponents.CustomKeywords()).writeOutputFile(
        	outFileWithoutExtn
         , 	pnrDetailsList)
}

def static "reusableComponents.CustomKeywords.setDateUsingJS"(
    	String testObjectID	
     , 	String inputDate	) {
    (new reusableComponents.CustomKeywords()).setDateUsingJS(
        	testObjectID
         , 	inputDate)
}

def static "reusableComponents.CustomKeywords.getDatainTable"(
    	TestObject to	
     , 	int timeout	) {
    (new reusableComponents.CustomKeywords()).getDatainTable(
        	to
         , 	timeout)
}

def static "reusableComponents.CustomKeywords.getRowHeaderAndFirstRowDataInTable"(
    	TestObject to	
     , 	int timeout	) {
    (new reusableComponents.CustomKeywords()).getRowHeaderAndFirstRowDataInTable(
        	to
         , 	timeout)
}

def static "reusableComponents.CustomKeywords.storeTableDataInobject"(
    	TestObject to	
     , 	int timeout	) {
    (new reusableComponents.CustomKeywords()).storeTableDataInobject(
        	to
         , 	timeout)
}

def static "reusableComponents.CustomKeywords.twoDArrayToList"(
    	String[][] twoDArray	) {
    (new reusableComponents.CustomKeywords()).twoDArrayToList(
        	twoDArray)
}

def static "reusableComponents.CustomKeywords.convertDateFromAPOStoMCP"(
    	String dateString	) {
    (new reusableComponents.CustomKeywords()).convertDateFromAPOStoMCP(
        	dateString)
}

def static "reusableComponents.CustomKeywords.writeAllClsAvbltyOutputFile"(
    	String dataToProcess	
     , 	String outFileName	
     , 	TestObject to	
     , 	int timeout	) {
    (new reusableComponents.CustomKeywords()).writeAllClsAvbltyOutputFile(
        	dataToProcess
         , 	outFileName
         , 	to
         , 	timeout)
}

def static "reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot"(
    	Object t	) {
    (new reusableComponents.CustomKeywords()).sendEscapeKeysUsingRobot(
        	t)
}

def static "reusableComponents.CustomKeywords.sendTabKeysUsingRobot"(
    	Object t	) {
    (new reusableComponents.CustomKeywords()).sendTabKeysUsingRobot(
        	t)
}

def static "reusableComponents.CustomKeywords.diffOfTwoFiles"(
    	String fileName1	
     , 	String fileName2	) {
    (new reusableComponents.CustomKeywords()).diffOfTwoFiles(
        	fileName1
         , 	fileName2)
}

def static "reusableComponents.CustomKeywords.updateResultInTestLinkOffline"(
    	String projectName	
     , 	String testPlanName	
     , 	String testCaseName	
     , 	String buildName	
     , 	String execNotes	
     , 	String testResultStatus	) {
    (new reusableComponents.CustomKeywords()).updateResultInTestLinkOffline(
        	projectName
         , 	testPlanName
         , 	testCaseName
         , 	buildName
         , 	execNotes
         , 	testResultStatus)
}

def static "reusableComponents.CustomKeywords.updateResultInTestLinkOnline"(
    	String projectName	
     , 	String testPlanName	
     , 	String testCaseName	
     , 	String buildName	
     , 	String execNotes	
     , 	String testResultStatus	) {
    (new reusableComponents.CustomKeywords()).updateResultInTestLinkOnline(
        	projectName
         , 	testPlanName
         , 	testCaseName
         , 	buildName
         , 	execNotes
         , 	testResultStatus)
}

def static "reusableComponents.CustomKeywords.readInputTDRCPsgnDtls"(
    	String fileName	) {
    (new reusableComponents.CustomKeywords()).readInputTDRCPsgnDtls(
        	fileName)
}

def static "reusableComponents.CustomKeywords.writeInputTDRCPsgnDtls"(
    	String outFileWithoutExtn	
     , 	java.util.List<ioStructures.InputTDRCPsgnDtls> inputTDRCPsgnDtlsList	) {
    (new reusableComponents.CustomKeywords()).writeInputTDRCPsgnDtls(
        	outFileWithoutExtn
         , 	inputTDRCPsgnDtlsList)
}

def static "reusableComponents.CustomKeywords.fillTDRCInQB"(
    	String trainNo	
     , 	String doj	
     , 	String srcStnCode	
     , 	String destnStnCode	
     , 	String clsCode	
     , 	String quotaCode	
     , 	String noOfPsgn	
     , 	String boardingStnCode	
     , 	String resvnUptoStnCode	
     , 	String coachChoice	
     , 	String rtsaFlag	
     , 	String concessionFlag	
     , 	String ticketType	
     , 	String vipFlag	) {
    (new reusableComponents.CustomKeywords()).fillTDRCInQB(
        	trainNo
         , 	doj
         , 	srcStnCode
         , 	destnStnCode
         , 	clsCode
         , 	quotaCode
         , 	noOfPsgn
         , 	boardingStnCode
         , 	resvnUptoStnCode
         , 	coachChoice
         , 	rtsaFlag
         , 	concessionFlag
         , 	ticketType
         , 	vipFlag)
}

def static "reusableComponents.CustomKeywords.fillConcessionalPsgnDtlsInQB"(
    	java.util.List<ioStructures.InputSinglePsgnDtls> psgnDtlsList	) {
    (new reusableComponents.CustomKeywords()).fillConcessionalPsgnDtlsInQB(
        	psgnDtlsList)
}

def static "reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB"(
    	String concessionFlag	
     , 	String mobileNo	
     , 	String paymentType	) {
    (new reusableComponents.CustomKeywords()).fillAddDtlsClickNextInPsgnDtlPageQB(
        	concessionFlag
         , 	mobileNo
         , 	paymentType)
}

def static "reusableComponents.CustomKeywords.writeBookedTktDtls"(
    	String outFileWithoutExtn	
     , 	java.util.List<ioStructures.BookedTktPnrDtls> bookedTktPnrDtlsList	) {
    (new reusableComponents.CustomKeywords()).writeBookedTktDtls(
        	outFileWithoutExtn
         , 	bookedTktPnrDtlsList)
}

def static "reusableComponents.CustomKeywords.writeErrorLog"(
    	String outFileWithoutExtn	
     , 	Object testCaseId	) {
    (new reusableComponents.CustomKeywords()).writeErrorLog(
        	outFileWithoutExtn
         , 	testCaseId)
}

def static "reusableComponents.CustomKeywords.preConditionsInfo"(
    	String preConditions	) {
    (new reusableComponents.CustomKeywords()).preConditionsInfo(
        	preConditions)
}

def static "reusableComponents.CustomKeywords.takePartialScreenShot"(
    	String fileName	
     , 	TestObject to	
     , 	int timeout	) {
    (new reusableComponents.CustomKeywords()).takePartialScreenShot(
        	fileName
         , 	to
         , 	timeout)
}

def static "reusableComponents.CustomKeywords.takeCompleteScreenShot"(
    	String fileName	) {
    (new reusableComponents.CustomKeywords()).takeCompleteScreenShot(
        	fileName)
}

def static "reusableComponents.CustomKeywords.readInputConcessionalTDRCPsgnDtls"(
    	String fileName	) {
    (new reusableComponents.CustomKeywords()).readInputConcessionalTDRCPsgnDtls(
        	fileName)
}

def static "reusableComponents.CustomKeywords.compareInputAndBookedTktDtls"(
    	String outFileWithoutExtn	
     , 	java.util.List<ioStructures.InputTDRCPsgnDtls> inputTDRCPsgnDtlsList	
     , 	java.util.List<ioStructures.BookedTktPnrDtls> bookedTktPnrDtlsList	
     , 	long startTime	) {
    (new reusableComponents.CustomKeywords()).compareInputAndBookedTktDtls(
        	outFileWithoutExtn
         , 	inputTDRCPsgnDtlsList
         , 	bookedTktPnrDtlsList
         , 	startTime)
}

def static "reusableComponents.CustomKeywords.millsToDateFormat"(
    	long longInterval	) {
    (new reusableComponents.CustomKeywords()).millsToDateFormat(
        	longInterval)
}

def static "reusableComponents.CustomKeywords_nativeEnv.executeAutoItScript"(
    	String script	) {
    (new reusableComponents.CustomKeywords_nativeEnv()).executeAutoItScript(
        	script)
}

def static "reusableComponents.CustomKeywords_nativeEnv.writeAllClsAvbltyUsingMCP"(
    	String outFileName	
     , 	java.util.List<String> trainAvailabilityList	) {
    (new reusableComponents.CustomKeywords_nativeEnv()).writeAllClsAvbltyUsingMCP(
        	outFileName
         , 	trainAvailabilityList)
}

def static "reusableComponents.CustomKeywordsForEnquiry.getJourneyDate"() {
    (new reusableComponents.CustomKeywordsForEnquiry()).getJourneyDate()
}

def static "reusableComponents.CustomKeywordsForEnquiry.getCurrentDate"() {
    (new reusableComponents.CustomKeywordsForEnquiry()).getCurrentDate()
}

def static "reusableComponents.CustomKeywordsForEnquiry.readInputAccAvblDtls"(
    	String fileName	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).readInputAccAvblDtls(
        	fileName)
}

def static "reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfAccAvbl"(
    	String trainNo	
     , 	String journeyDate	
     , 	String sourceCode	
     , 	String destnCode	
     , 	String classCode	
     , 	String quotaCode	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).fillDtlsOfAccAvbl(
        	trainNo
         , 	journeyDate
         , 	sourceCode
         , 	destnCode
         , 	classCode
         , 	quotaCode)
}

def static "reusableComponents.CustomKeywordsForEnquiry.writeAccAvbltyOutputFile"(
    	String outputFile	
     , 	java.util.List<enquiryIoStructures.OutputAccAvbl_C1> outputAccAvbl_C1List	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).writeAccAvbltyOutputFile(
        	outputFile
         , 	outputAccAvbl_C1List)
}

def static "reusableComponents.CustomKeywordsForEnquiry.readInputFareEnqDtls"(
    	String fileName	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).readInputFareEnqDtls(
        	fileName)
}

def static "reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfFareEnq"(
    	String trainNo	
     , 	String journeyDate	
     , 	String srcCode	
     , 	String destnStnCode	
     , 	String clsCode	
     , 	String quotaCode	
     , 	String catering	
     , 	String adultNo	
     , 	String childNo	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).fillDtlsOfFareEnq(
        	trainNo
         , 	journeyDate
         , 	srcCode
         , 	destnStnCode
         , 	clsCode
         , 	quotaCode
         , 	catering
         , 	adultNo
         , 	childNo)
}

def static "reusableComponents.CustomKeywordsForEnquiry.writeFareEnqOutputFile"(
    	String outputFile	
     , 	java.util.List<enquiryIoStructures.OutputFareEnq_B3> outputFareEnq_B3List	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).writeFareEnqOutputFile(
        	outputFile
         , 	outputFareEnq_B3List)
}

def static "reusableComponents.CustomKeywordsForEnquiry.readInputTrainBtwStn"(
    	String fileName	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).readInputTrainBtwStn(
        	fileName)
}

def static "reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfTrainBtwStations"(
    	String journeyDate	
     , 	String originCode	
     , 	String destnCode	
     , 	String quotaCode	
     , 	String classCode	
     , 	String flexibleDate	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).fillDtlsOfTrainBtwStations(
        	journeyDate
         , 	originCode
         , 	destnCode
         , 	quotaCode
         , 	classCode
         , 	flexibleDate)
}

def static "reusableComponents.CustomKeywordsForEnquiry.writeTrainBtwStationsOutputFile"(
    	String outputFile	
     , 	java.util.List<enquiryIoStructures.OutputTrainBtwStations_B1> outputTrainBtwStations_B1List	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).writeTrainBtwStationsOutputFile(
        	outputFile
         , 	outputTrainBtwStations_B1List)
}

def static "reusableComponents.CustomKeywordsForEnquiry.readInputRmtWiseChartStatus"(
    	String fileName	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).readInputRmtWiseChartStatus(
        	fileName)
}

def static "reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfRmtWiseChartStatus"(
    	String trainNo	
     , 	String journeyDate	
     , 	String sourceCode	
     , 	String destnCode	
     , 	String classCode	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).fillDtlsOfRmtWiseChartStatus(
        	trainNo
         , 	journeyDate
         , 	sourceCode
         , 	destnCode
         , 	classCode)
}

def static "reusableComponents.CustomKeywordsForEnquiry.writeRmtWiseChartStatusOutputFile"(
    	String outputFile	
     , 	java.util.List<enquiryIoStructures.OutputRmtWiseChartStatus_D3> outputRmtWiseChartStatus_D3List	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).writeRmtWiseChartStatusOutputFile(
        	outputFile
         , 	outputRmtWiseChartStatus_D3List)
}

def static "reusableComponents.CustomKeywordsForEnquiry.readInputCurrentStatusOfPsgn"(
    	String fileName	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).readInputCurrentStatusOfPsgn(
        	fileName)
}

def static "reusableComponents.CustomKeywordsForEnquiry.fillDtlsOfCurrentStatusOfPsgn"(
    	String pnrNo	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).fillDtlsOfCurrentStatusOfPsgn(
        	pnrNo)
}

def static "reusableComponents.CustomKeywordsForEnquiry.writeCurrentStatusOfPsgnOutputFile"(
    	String outputFile	
     , 	java.util.List<enquiryIoStructures.OutputCurrentStatusOfPsgn_A1> outputCurrentStatusOfPsgn_A1List	) {
    (new reusableComponents.CustomKeywordsForEnquiry()).writeCurrentStatusOfPsgnOutputFile(
        	outputFile
         , 	outputCurrentStatusOfPsgn_A1List)
}

def static "com.katalon.plugin.keyword.calendar.SetDateCalendarKeyword.setDate"(
    	TestObject to	
     , 	int day	
     , 	int month	
     , 	int year	
     , 	int slideTimeOut	
     , 	FailureHandling flowControl	) {
    (new com.katalon.plugin.keyword.calendar.SetDateCalendarKeyword()).setDate(
        	to
         , 	day
         , 	month
         , 	year
         , 	slideTimeOut
         , 	flowControl)
}
