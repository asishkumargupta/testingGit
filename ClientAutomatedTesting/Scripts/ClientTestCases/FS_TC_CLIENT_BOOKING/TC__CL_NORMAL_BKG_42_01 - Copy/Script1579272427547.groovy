import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import java.time.Instant

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import inputOutputStructures.SinglePsgnDtls 
import internal.GlobalVariable as GlobalVariable
import quickBook.BookedTktPnrDtls
import quickBook.BookedTktSinglePsgnDtls
import testlink.api.java.client.TestLinkAPIResults



String 	projectName = "FunctionalityWiseHierarchy",
		testPlanName = "Test Plan to test APOS Client automatically",
		testCaseName = "TC_CL_NORMAL_BKG_42_01" ,
		buildName = "Build to Test FS_TC_CLIENT_BOOKING",
		execNotes = "" ,
		testResultStatus = TestLinkAPIResults.TEST_FAILED,
		expectedMsg = "System should impose GST for tickets booked in classes other than  SL/2S/2T. ",
		dataToTest = '',
		timenow = null
		actualMsg = '';

char 	psgnAttributeSeparator = '$',
		psgnDtlsSeparator = '#',
		recordSeparator = '#';
		
timenow =  CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()

String 	inputTrainNo ='' ,
		inputJrnyDate = '',
		inputSrcStnCode = '',
		inputDestnStnCode = '',
		inputClassCode ='',
		inputNoOfPsgn = '' ,
		inputQuotaCode = '' ,		
		inputPsgnCount = '' ,
		inputBoardingStnCode ='' ,
		inputResvnUptoStnCode = '' ,
		inputMobileNo = '' ,
		inputPaymentType = '',
		inputCompletePsgnDtls = '' ,
		inputSinglePsgnDtls = '';
		
		
		
		
Instant startExecutionTime = null ;
startExecutionTime = Instant.now();
timenow =  CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'()

//List<TDRCdetails> tdrcDetailsList = new ArrayList()
for (int rowNum = 1; rowNum <= findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getRowNumbers(); rowNum++) {
	
	SinglePsgnDtls singlePsgnDtls = new SinglePsgnDtls()
	List<SinglePsgnDtls> psgnDtlsList =  new ArrayList()
	BookedTktSinglePsgnDtls bookedTktSinglePsgnDtls = new BookedTktSinglePsgnDtls()
	List<BookedTktSinglePsgnDtls> bookedTktPsgnDtlsList =  new ArrayList()
	BookedTktPnrDtls bookedTktPnrDtls = new BookedTktPnrDtls() ;
	
	inputTestCaseName = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(1, rowNum)
	System.out.println('TEST CASE NAME =' + inputTestCaseName)
	if  (!(inputTestCaseName.equals(testCaseName)))
			continue ;
		
	inputTrainNo = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(2, rowNum)
	inputJrnyDate = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(3, rowNum)
	inputSrcStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(4, rowNum)
	inputDestnStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(5, rowNum)
	inputClassCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(6 , rowNum)
	inputQuotaCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(7, rowNum)
	inputPsgnCount = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(8, rowNum)
	inputBoardingStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(9, rowNum)
	inputResvnUptoStnCode = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(10, rowNum)
	inputCompletePsgnDtls = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(11, rowNum)
	inputMobileNo = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(12, rowNum)
	inputPaymentType = findTestData('BookingData/DataFS_TC_CLIENT_BOOKING').getValue(13, rowNum)
	singlePsgnDtlsString = inputCompletePsgnDtls.split("#")
	
	dataInRow = inputTrainNo + recordSeparator + inputJrnyDate + recordSeparator + inputSrcStnCode + recordSeparator + inputDestnStnCode + recordSeparator +
	inputClassCode + recordSeparator + inputQuotaCode + recordSeparator + inputPsgnCount + recordSeparator  + inputBoardingStnCode+ recordSeparator +
	inputResvnUptoStnCode + recordSeparator + inputCompletePsgnDtls + recordSeparator + inputMobileNo + recordSeparator  + inputPaymentType
	System.out.println('Data In One Row =' + dataInRow)
	dataToTest = dataInRow 
	
	//To add psgn Details in List - start
	for (int ipPsgnCount = 0 ; ipPsgnCount < singlePsgnDtlsString.length; ipPsgnCount++) {
		singlePsgnDtls = new SinglePsgnDtls()
		//System.out.println('Single Psgn  =' + singlePsgnDtlsString[ipPsgnCount])
		psgnAttribute = singlePsgnDtlsString[ipPsgnCount].split("%")
	//	System.out.println('\n\nPassenger Name is =' + psgnAttribute[0])
		singlePsgnDtls.setPsgnName(psgnAttribute[0])
		singlePsgnDtls.setPsgnGender(psgnAttribute[1])
		singlePsgnDtls.setPsgnAge(psgnAttribute[2])
		singlePsgnDtls.setPsgnBerthPreference(psgnAttribute[3])
		singlePsgnDtls.setPsgnFoodPreference(psgnAttribute[4])
		psgnDtlsList.add(singlePsgnDtls)
	}
	//To add psgn Details in List - end
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Home'))
	WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_Train'))
	WebUI.setText(findTestObject('null'),inputTrainNo)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/trainNo_Name_firstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	CustomKeywords.'reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot'()
	CustomKeywords.'reusableComponents.CustomKeywords.setDateUsingJS'('Object Repository/NxtGenPRS_OR/QB_JourneyDtls/journeyDate',inputJrnyDate)
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'),
	GlobalVariable.waitForAvailabilitySec)
	statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'))
	System.out.println('Station fetched Message = ' + statusMsg) 
    WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyFromStation'), inputSrcStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/src_FirstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyToStation'), inputDestnStnCode)
    WebUI.delay(GlobalVariable.sleepTimeInSec)
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/destn_FirstSelected'))
	
	//For Clicking class code - start
    WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_ClassClass'))
    WebUI.delay(GlobalVariable.sleepTimeInSec)

    for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {
        
        String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/cls' + classCount

        if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			className = WebUI.getText(findTestObject(testObject))
		//	System.out.println('CLASS NAME IS=' + className)
			classCode = className.split('-')[0].trim()
		//	System.out.println('CLASS CODE IS=' + classCode)
		//	System.out.println('INPUT CLASS CODE IS=' + inputClassCode)
			if (classCode.equals(inputClassCode)) {
		//		System.out.println('CLICKING CLASS CODE')
				WebUI.click(findTestObject(testObject)) 
			}
           
        }
    }
	//For Clicking class code - end
	
	
	//For Clicking quota code - start
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_QuotaQuota'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int quotaCount = GlobalVariable.classCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {
		
		String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/quota' + quotaCount
		
		
		if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			
			quotaName = WebUI.getText(findTestObject(testObject))
		//	System.out.println('QUOTA NAME IS=' + quotaName)
			quotaCode = quotaName.split('-')[0].trim()
		//	System.out.println('QUOTA CODE IS=' + quotaCode)
		//	System.out.println('INPUT QUOTA CODE IS=' + inputQuotaCode)
			if (quotaCode.equals(inputQuotaCode)) {
			//	System.out.println('CLICKING QUOTA CODE')
				WebUI.click(findTestObject(testObject)) 
			}
		   
		}
	}
	//For Clicking quota code - end

	WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgAvailabilityStatusFetched'),
		GlobalVariable.waitForAvailabilitySec)
	
	statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgAvailabilityStatusFetched'))
	System.out.println('Availability Status fetched Message=' + statusMsg)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
		
		//For Clicking no of psgn - start
	
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_No of Passengers'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <= GlobalVariable.maxPsgn; psgnCount++) {
		String testObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/noOfPsgn' + psgnCount
		if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			noOfPsgn = WebUI.getText(findTestObject(testObject))
			//System.out.println('PSGN COUNT IN WEBPAGE=' + psgnCount)
			//System.out.println('PSGN COUNT INPUT=' + inputPsgnCount)
			if (noOfPsgn.equals(inputPsgnCount)) {
				System.out.println('CLICKING NO OF PSGN')
				WebUI.click(findTestObject(testObject)) 
				break ;
			}
			
		}
	}
		//For Clicking no of psgn - end
		

	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	//	System.out.println('SETTING BOARDING STATION CODE WITH VALUES=' + inputBoardingStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_BrdgStnCode'), inputBoardingStnCode)
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/brdgStnCode_FirstSelected'))
	CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_ResvnUptoStnCode'), inputResvnUptoStnCode)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/resvnUpto_FirstSelected'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/button_Next'))
	

	//Passenger Details page - start
//	System.out.println('NO OF PSGN='+psgnDtlsList.size())
	for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <=  psgnDtlsList.size(); psgnCount++) {
		System.out.println('psgnCount now is' + psgnCount)
		String testPsgnNameObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject(testPsgnNameObject))
		String testPsgnNameInput = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/psgnName' + psgnCount
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		System.out.println('SETTING PSGN NAME AS='+psgnDtlsList.get(psgnCount-1).getPsgnName())
		WebUI.setText(findTestObject(testPsgnNameInput), psgnDtlsList.get(psgnCount-1).getPsgnName())
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		//For Clicking gender - start
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		String genderDiv = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Gender' + psgnCount
		WebUI.click(findTestObject(genderDiv))
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int genderCount = GlobalVariable.genderCountStart; genderCount <= GlobalVariable.maxGenderCount; genderCount++) {
			String span_gender = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/gender' + psgnCount + '_' + genderCount
			if (WebUI.verifyElementPresent(findTestObject(span_gender), 1, FailureHandling.OPTIONAL) == true) {
				genderName = WebUI.getText(findTestObject(span_gender))
				//System.out.println('GENDER NAME=' + genderName)
				if (psgnDtlsList.get(psgnCount-1).getPsgnGender().equals(genderName)) {
				//	System.out.println('CLICKING NO OF PSGN')
					WebUI.click(findTestObject(span_gender))
					break ;
				}
				  
			}
		}
		//For Clicking gender - end
		String ageDiv = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Age' + psgnCount
		//For inputting Age - start
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject(ageDiv))
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		String inputAgeElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/inputAge' + psgnCount
		WebUI.setText(findTestObject(inputAgeElement), psgnDtlsList.get(psgnCount-1).getPsgnAge())
		//For Clicking Age - end
		String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
		WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
		
	} //End of psgnCount for loop
	
	
	
	
	//For Clicking Payment type - start
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_PaymentType'))
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	for (int paymentTypeCount = GlobalVariable.paymentCountStart; paymentTypeCount <= GlobalVariable.maxPaymentCount; paymentTypeCount++) {
		String span_paymentType = 'NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/span_paymentType' + paymentTypeCount
		if (WebUI.verifyElementPresent(findTestObject(span_paymentType), 1, FailureHandling.OPTIONAL) == true) {
			paymentType = WebUI.getText(findTestObject(span_paymentType))
			//System.out.println('PAYMENT TYPE=' + paymentType)
			if (paymentType.equals(inputPaymentType)) {
				System.out.println('CLICKING PAYMENT TYPE')
				WebUI.click(findTestObject(span_paymentType))
				break ;
			}
			  
		}
	}
	//For Clicking Payment Type - end
	
	WebUI.delay(GlobalVariable.sleepTimeInSec)
	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/button_NextInPsgnDtls'))
		//Passenger Details page - end
	if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/span_BookingSummaryTxt'), 1, FailureHandling.OPTIONAL) == true) {
		bookingSummaryTxt = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/span_BookingSummaryTxt'))
		System.out.println('bookingSummaryTxt=' + bookingSummaryTxt)
	}
		  
	//Get output Details - start
	disptrainNo =  WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispTrainNo'))
	dispDOJ = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispDOJ'))
	dispCls = 	WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispClass'))
	dispQuota = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispQuota'))
	dispJrnyFrom = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispFrom'))
	dispJrnyTo = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/dispTo'))
	cashToBeCollected = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/cashToBeCollected'))
	voucherFare = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/voucherFare'))
	totalFare = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/totalFare'))
	generatedPNR = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/span_PNRNo'))
		
		System.out.println( disptrainNo + '#'
							+ dispDOJ +  '#'
							+ dispCls +  '#'
							+ dispQuota + '#'
							+ dispJrnyFrom + '#'
							+ dispJrnyTo + '#'
							+ cashToBeCollected + '#'
							+ voucherFare + '#'
							+ totalFare + '#'
							+ generatedPNR + '#'
							 )
							
		
		bookedTktPnrDtls.setTrainNo(disptrainNo)
		bookedTktPnrDtls.setDoj(dispDOJ)
		bookedTktPnrDtls.setJrnyCls(dispCls)
		bookedTktPnrDtls.setQuota(dispQuota)
		bookedTktPnrDtls.setFromStnCodeName(dispJrnyFrom)
		bookedTktPnrDtls.setToStnCodeName(dispJrnyTo)
		bookedTktPnrDtls.setCashToBeCllected(cashToBeCollected)
		bookedTktPnrDtls.setVoucherFare(voucherFare)
		bookedTktPnrDtls.setTotalFare(totalFare)
		bookedTktPnrDtls.setPnrNo(generatedPNR)
		
		
		
		//To add booked psgn Details in List - start
		/*
		for (int dispPsgnCount = 1 ; dispPsgnCount <= singlePsgnDtlsString.length; dispPsgnCount++) {
			bookedTktSinglePsgnDtls = new BookedTktSinglePsgnDtls()
			//System.out.println('Single Psgn  =' + singlePsgnDtlsString[ipPsgnCount])
			//psgnAttribute = singlePsgnDtlsString[ipPsgnCount].split("%")
		//	System.out.println('\n\nPassenger Name is =' + psgnAttribute[0])
			psgnNameElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnName' + dispPsgnCount
			psgnAgeElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnAge'  + dispPsgnCount
			psgnGenderElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnAge'  + dispPsgnCount
			psgnQuotaElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnQuota' + dispPsgnCount
			psgnBookingStatusElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnBookingStatus' + dispPsgnCount
			psgnBookingDtlsElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnBookingDtls' + dispPsgnCount
			
			psgnCurStatusElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnCurStatus' + dispPsgnCount
			psgnCurDtlsElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnCurDtls' + dispPsgnCount
			
		
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		
			
			psgnNameTxt =  WebUI.getText(findTestObject(psgnNameElement))
			System.out.println('\n\nPassenger Name is =' + psgnNameTxt)
			
			bookedTktSinglePsgnDtls.setPsgnName(WebUI.getText(findTestObject(psgnNameElement)))
			bookedTktSinglePsgnDtls.setPsgnAge(WebUI.getText(findTestObject(psgnAgeElement)))
			bookedTktSinglePsgnDtls.setPsgnGender(WebUI.getText(findTestObject(psgnGenderElement)))
			bookedTktSinglePsgnDtls.setPsgnQuota(WebUI.getText(findTestObject(psgnQuotaElement)))
			bookedTktSinglePsgnDtls.setPsgnBookingStatus(WebUI.getText(findTestObject(psgnBookingStatusElement)))
			bookedTktSinglePsgnDtls.setPsgnBookingDtls(WebUI.getText(findTestObject(psgnBookingDtlsElement)))
			bookedTktSinglePsgnDtls.setPsgnCurStatus(WebUI.getText(findTestObject(psgnCurStatusElement)))
				
			bookedTktSinglePsgnDtls.setPsgnCurDtls(WebUI.getText(findTestObject(psgnCurDtlsElement)))
			
			
			//singlePsgnDtls.setPsgnGender(psgnAttribute[1])
			//singlePsgnDtls.setPsgnAge(psgnAttribute[2])
			//singlePsgnDtls.setPsgnBerthPreference(psgnAttribute[3])
			//singlePsgnDtls.setPsgnFoodPreference(psgnAttribute[4])
			bookedTktPsgnDtlsList.add(bookedTktSinglePsgnDtls)
		}
		
		
		
		bookedTktPnrDtls.setPsgnDetailsList(bookedTktPsgnDtlsList)
		
		*/
		//To add booked psgn Details in List - end
		
		//To display added psgn list - start
		
		
		System.out.println('\n\nBooked PNR Details are as follows \n\n' )
		
		System.out.println( bookedTktPnrDtls.getTrainNo() + '#'
			+ bookedTktPnrDtls.getDoj() +  '#'
			+ bookedTktPnrDtls.getClass() +  '#'
			+ bookedTktPnrDtls.getQuota() + '#'
			+ bookedTktPnrDtls.getFromStnCodeName()+ '#'
			+ bookedTktPnrDtls.getToStnCodeName()+ '#'
			+ bookedTktPnrDtls.getCashToBeCllected() + '#'
			+ bookedTktPnrDtls.getVoucherFare() + '#'
			+ bookedTktPnrDtls.getTotalFare() + '#'
			+ bookedTktPnrDtls.getPnrNo() + '#'
			 )
	
		
		//for (int dispPsgnCount = 1 ; dispPsgnCount <= singlePsgnDtlsString.length; dispPsgnCount++) {
			
//	}
		
		
		//To display added psgn list - end
		
		
	//Get output Details - end
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/button_Proceed'
		WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_Proceed'))
	//Verify GST - start 
		gstDtlsTxt =  WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PrintTktPage/gstChargeDtls'))
		System.out.println('\nGST Dtls are as follows \n' +  gstDtlsTxt)
		System.out.println('\nGST BREAK UP AS FOLLOWS')
		gstBreakUp = gstDtlsTxt.split(':')
		
		for (int count = 0 ; count < gstBreakUp.length; count++) {
			System.out.println('gst ='+gstBreakUp[count])
			
			
		}
	
		
		if ( (inputClassCode != 'SL') && (inputClassCode != '2S') && (inputClassCode != '2T') ) {
			if (!gstBreakUp[GlobalVariable.totalGSTPos].equals('Rs.0.0')) 
				testResultStatus = TestLinkAPIResults.TEST_PASSED ;
			else 
				testResultStatus = TestLinkAPIResults.TEST_FAILED ;
				
			actualMsg = 'GST DETAILS=' + gstDtlsTxt +'\n' + 'TOTAL GST=' + gstBreakUp[GlobalVariable.totalGSTPos]
		}
		else {
			if (gstBreakUp[GlobalVariable.totalGSTPos].equals('Rs.0.0'))
				testResultStatus = TestLinkAPIResults.TEST_PASSED ;
			else
				testResultStatus = TestLinkAPIResults.TEST_FAILED ;
			
			actualMsg = 'GST DETAILS=' + gstDtlsTxt +'\n' + 'TOTAL GST=' + gstBreakUp[GlobalVariable.totalGSTPos]
		}
		
	

	execNotes += '\n'+ testCaseName +'::'+
				'\nData to Test::' + dataToTest + "\nChecked for Class " + inputClassCode + 
					'\nExpected Result::' + expectedMsg + '\nActual Result::' + actualMsg + '\n' + 
					'------------------------------------------------';
	
	

		
	//Verify GST - end	

					WebUI.delay(GlobalVariable.sleepTimeInSec)
					// WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Quick Book'))
					 WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Home'))
					 WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
					 
						
	
} //End of all records in input file

//Update in TestLink - start
Instant endExecutionTime = Instant.now();
Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;



CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
						 testPlanName,
						 testCaseName,
						buildName,
						execNotes,
						testResultStatus)


//Update in TestLink - end

