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
import inputOutputStructures.PnrDetails as PnrDetails
import inputOutputStructures.PsgnDetails as PsgnDetails
import inputOutputStructures.AdditionalDetails
import inputOutputStructures.PaidFareDetails as PaidFareDetails
import inputOutputStructures.TrainInfoDetails as TrainInfoDetails
import inputOutputStructures.AdditionalDetails as AdditionalDetails
import inputOutputStructures.JourneyDetails as JourneyDetails 



List<PnrDetails> pnrDetailsList = new ArrayList()

PsgnDetails psgnDetails ;




List<PsgnDetails> psgnDetailsList = new ArrayList();
int noOfPsgn = 0;

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.url)
WebUI.maximizeWindow()
PnrDetails pnrDetails ;
PaidFareDetails paidFareDetails ;
TrainInfoDetails trainInfoDetails ;
AdditionalDetails additionalDetails ;
JourneyDetails journeyDetails ;


for (int rowNum = 1; rowNum <= findTestData('Enquiry/InputPNRData').getRowNumbers(); rowNum++) {

	pnrDetails = new PnrDetails()
	paidFareDetails  = new PaidFareDetails ()
	trainInfoDetails = new TrainInfoDetails ()
	additionalDetails = new AdditionalDetails ()
	journeyDetails = new JourneyDetails  ()
	
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/span_Enquiries'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/span_A1  CURRENT STATUS OF PASSENGERS'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/span_Reset'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/div_PNR No'))
	String pnrNo = findTestData('Enquiry/InputPNRData').getValue(1, rowNum)
	WebUI.setText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/toastMessage_inputPnrNo'), pnrNo)
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/span_Check PNR status'))
	WebUI.delay(GlobalVariable.toastErrorMsgDelay)
	String statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/span_toastMsg'))
	System.out.println('errorMsg = ' + statusMsg)

	pnrDetails.setpnrNo(pnrNo)

	pnrDetails.setErrorMsg(statusMsg)



	if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/div_completePsgnDetails'),
	1, FailureHandling.OPTIONAL) == false) {
	pnrDetailsList.add(pnrDetails)
		continue
	}

	//To get no of psgn - start

	noOfPsgn = 0
	if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn6'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 6;
	} else if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn5'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 5;
	} else if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn4'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 4;
	} else if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn3'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 3;

	} else if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn2'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 2;

	} else if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/Page_PNRDetails/td_psgn1'), 1, FailureHandling.OPTIONAL) ==
	true) {
		noOfPsgn = 1;
	}
	System.out.println('No of psgn in the PNR = ' + noOfPsgn)
	//To get no of psgn - end

	pnrDetails.setNoOfPsgn(noOfPsgn)

//	int countForTestObject =0;
//	PsgnDetails psgnDetails ;
	//List<PsgnDetails> psgnDetailsList = new ArrayList()
	for ( int psgnCount = 1; psgnCount <= noOfPsgn; psgnCount++) {

		psgnDetails  = new PsgnDetails ()
		
	
		//String testObject =  "Object Repository/NxtGenPRS_OR/Page_NxtGenPRS/Page_PNRDetails/psgnName" + psgnCount;
		//System.out.println("\n\nTEST OBJECT =" + testObject);



		String psgnName = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnName' + psgnCount));
		String psgnAge = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnAge'+ psgnCount));
		
		String psgnGender = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnGender'+ psgnCount));
		
		String psgnQuota = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnQuota'+ psgnCount));
	//	System.out.println("\n\nPASSENGER NAME =" + psgnName);
		
		
		String psgnBookStatus = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnBookStatus' + psgnCount));
		//System.out.println("\n\nPASSENGER BOOK STATUS  =" + psgnBookStatus);
		String psgnBookDtls = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnBookDtls' + psgnCount));
		//System.out.println("\n\nPASSENGER BOOK DETAILS  =" + psgnBookDtls);
		
	
		String psgnCurrentStatus = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnCurrentStatus' + psgnCount));
		//System.out.println("\n\nPASSENGER CURRENT STATUS  =" + psgnBookStatus);
		String psgnCurrentDtls = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/psgnCurrentDtls' + psgnCount));
		//System.out.println("\n\nPASSENGER CURRENT DETAILS  =" + psgnBookDtls);
		
		psgnDetails.setPsgnName(psgnName);
		psgnDetails.setPsgnAge(psgnAge) ;
		psgnDetails.setPsgnGender(psgnGender) ;
		psgnDetails.setPsgnQuota(psgnQuota) ;
		psgnDetails.setPsgnBookingStatus(psgnBookStatus)
		psgnDetails.setPsgnBookingDtls(psgnBookDtls)
		
		psgnDetails.setPsgnCurrentStatus(psgnCurrentStatus)
		psgnDetails.setPsgnCurrentDtls(psgnCurrentDtls)
		
		pnrDetails.psgnDetailsList.add(psgnDetails)

	} //End of for loop of psgnCount 
//To Get Paid Fare Details - start
	String bkgAmount = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/bookingAmount'));
	String voucherAmount = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/voucherAmount'));
	String cashAmount = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/cashAmount'));
	String totalAmount = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/totalAmount'));
	
	paidFareDetails.setBookingAmount(bkgAmount);
	paidFareDetails.setVoucherAmount(voucherAmount);
	paidFareDetails.setCashAmount(cashAmount);
	paidFareDetails.setTotalAmount(totalAmount);
	
	
	
	
	pnrDetails.setPaidFareDetails(paidFareDetails);
	
//To Get Paid Fare Details - end 
	
	//To Get Train Information  - start
	String chartStatus = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/chartStatus'));
	String trainStatus  = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/trainStatus'));
	String expectedPfNo = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/expectedPfNo'));
	
	
	
	trainInfoDetails.setChartStatus(chartStatus);
	trainInfoDetails.setTrainStatus(trainStatus);
	trainInfoDetails.setExpectedPfNo(expectedPfNo);
	pnrDetails.setTrainInfoDetails(trainInfoDetails);
	
	
	//To Get Train Information  - end
	

	
	//To Get Additional Details  - start
	String vikalpOptedYN = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/vikalpOptedYN'));
	String upgradationYN = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/upgradationYN'));
	String ticketLostMarkedYN = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/tktLostMarkedYN'));
	String vipYN = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/vipYN'));
	String mobileNoAvailYN = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/mobileNoAvailYN'));
	String pendingAmount = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/pendingAmnt'));
	
	additionalDetails.setVikalpOptedYN(vikalpOptedYN)
	additionalDetails.setUpgradationYN(upgradationYN)
	additionalDetails.setTktLostMarkedYN(ticketLostMarkedYN)
	additionalDetails.setVipYN(vipYN)
	additionalDetails.setMobileNoYN(mobileNoAvailYN)
	additionalDetails.setPendingAmount(pendingAmount)
	pnrDetails.setAdditionalDetails(additionalDetails)
	//To Get Additional Details  - end
	
	//To Get Journey Details - start
	String trainNo = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/trainNo_Name')).split("-")[0].trim();
	String boardingDate = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/boardingDate'));
	String jrnyClsCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/jrnyClassCode_Name')).split("-")[0].trim();
	String jrnyQuotaCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/jrnyQuotaCode_Name')).split("-")[0].trim();
	String jrnyFromCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/jrnyFromCode_Name')).split("-")[0];
	String jrnyToCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/jrnyToCode_Name')).split("-")[0];
	String boardingStnCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/boardingStnCode_Name')).split("-")[0];
	String resvnUptoCode = WebUI.getText(findTestObject('NxtGenPRS_OR/Page_PNRDetails/resvnUptoCode_Name')).split("-")[0];

	journeyDetails.setTrainNo(trainNo)
	journeyDetails.setDateOfBoarding(boardingDate)
	journeyDetails.setJrnyClsCode(jrnyClsCode)
	journeyDetails.setJrnyQuotaCode(jrnyQuotaCode)
	journeyDetails.setJrnyFromCode(jrnyFromCode)
	journeyDetails.setJrnyToCode(jrnyToCode)
	journeyDetails.setBoardingStnCode(boardingStnCode)
	journeyDetails.setResvUptoCode(resvnUptoCode)

	pnrDetails.setJourneyDetails(journeyDetails)
//To Get Journey Details - end
	
	
	
	

		

	
	pnrDetailsList.add(pnrDetails)

	WebUI.delay(GlobalVariable.sleepTimeInSec)
} //End of for loop for all data in input file





CustomKeywords.'reusableComponents.CustomKeywords.writeOutputFile'('OutputStatus', pnrDetailsList)

