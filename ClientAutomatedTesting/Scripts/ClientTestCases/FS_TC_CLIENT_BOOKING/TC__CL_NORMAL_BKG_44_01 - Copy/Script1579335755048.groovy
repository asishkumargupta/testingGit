import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import java.time.Instant

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


import internal.GlobalVariable as GlobalVariable
import ioStructures.BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls
import testlink.api.java.client.TestLinkAPIResults



String 	projectName = "FunctionalityWiseHierarchy",
		testPlanName = "Test Plan to test APOS Client automatically",
		testCaseName = "TC_CL_NORMAL_BKG_44_01" ,
		buildName = "Build to Test FS_TC_CLIENT_BOOKING",
		execNotes = "" ,
		testResultStatus = TestLinkAPIResults.TEST_FAILED,
		expectedMsg = "On GST not applicable tickets system should print general GST information like SAC, GSTIN of IR, and state of supply.",
		dataToTest = '',
		timenow = null
		actualMsg = '';

		List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()
		BookedTktPnrDtls bookedTktPnrDtls ;
		String testCaseId ='';
		List<BookedTktPnrDtls> bookedTktPnrDtlsList = new ArrayList()
		char recordSeparator = ',';
		
		timenow = CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'();
		String errorFileNameWithoutExtn = testCaseName + '_' + timenow ;
		String partialImageStartName = '';
		
		
		Instant startExecutionTime = null ;
		startExecutionTime = Instant.now();
	
		
		
//		CustomKeywords.'reusableComponents.CustomKeywords.preConditionsInfo'('Ensure proper testing environments for input trains in dat file \n are Available for booking')
		System.out.println("READING FILE") ;
		//inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")
		
		inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'(".\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")
	

		for ( int recordCount = 0; recordCount < inputTDRCPsgnDtlsList.size(); recordCount++) {
			try { 
				
				bookedTktPnrDtls = new BookedTktPnrDtls() ;
				List<InputSinglePsgnDtls> psgnDtlsList = new ArrayList()
				recordString = '' ;
				psgnDtlsString = '';
				trainNo = inputTDRCPsgnDtlsList.get(recordCount).getTrainNo()
				testCaseId = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId()
				doj = inputTDRCPsgnDtlsList.get(recordCount).getDoj()
				srcStnCode = inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode()
				destnStnCode = inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode()
				clsCode =  inputTDRCPsgnDtlsList.get(recordCount).getClassCode()
				quotaCode = inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode()
				noOfPsgn = 	inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size().toString()
				boardingStnCode = inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode()
				resvnUptoStnCode = inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode()
				coachChoice = inputTDRCPsgnDtlsList.get(recordCount).getCoachChoice()
				mobileNo = inputTDRCPsgnDtlsList.get(recordCount).getMobileNo()
				paymentType = inputTDRCPsgnDtlsList.get(recordCount).getPaymentType()
				partialImageStartName = ".\\UserDataFiles\\outfiles\\" + testCaseId + "exceptionImage" + timenow + ".png" ;
		//		dataInRow = inputTrainNo + recordSeparator + inputJrnyDate + recordSeparator + inputSrcStnCode + recordSeparator + inputDestnStnCode + recordSeparator +
				dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
				if  (!(testCaseId.equals(testCaseName)))
				continue ;
				
				
				CustomKeywords.'reusableComponents.CustomKeywords.fillTDRCInQB'(trainNo,
																							doj,
																							srcStnCode,
																							destnStnCode,
																							clsCode,
																							quotaCode,
																							noOfPsgn,
																							boardingStnCode,
																							resvnUptoStnCode,
																							coachChoice	)
				psgnDtlsList = inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList()
				CustomKeywords.'reusableComponents.CustomKeywords.fillPsgnDtlsInQB'(psgnDtlsList)
				
				CustomKeywords.'reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB'(mobileNo,paymentType)
				bookedTktPnrDtls = CustomKeywords.'reusableComponents.CustomKeywords.getBookedTktDtlsClickNextQB'(testCaseId , noOfPsgn)
				bookedTktPnrDtlsList.add(bookedTktPnrDtls)
				//Verify GST - start
				gstDtlsTxt =  WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PrintTktPage/gstChargeDtls'))
				System.out.println('\nGST Dtls are as follows \n' +  gstDtlsTxt)
				
				gstnIR_SOSText = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PrintTktPage/sac_gstinIR_SOS'))
				
				
				serviceAccountingCode = gstnIR_SOSText.split(':')[1].split(' ')[0]
				gstinIR = gstnIR_SOSText.split(':')[2].split(' ')[0]
				stateOfSupply = gstnIR_SOSText.split(':')[3]

				
				System.out.println('\nINDIAN RAILWAY GST DETAILS=' +  gstnIR_SOSText)
				
				System.out.println('SAC=' + serviceAccountingCode) 
				System.out.println('GSTIN IR=' + gstinIR)
				System.out.println('STATE OF SUPPLY=' + stateOfSupply)
				System.out.println('STATE OF SUPPLY AFTER TRIM=' + stateOfSupply.trim())
				
				
				
				if ( (clsCode != 'SL') && (clsCode != '2S') && (clsCode != '2T') ) {
					if (stateOfSupply.trim().isEmpty()) {
						testResultStatus = TestLinkAPIResults.TEST_FAILED ; 
						System.out.println('TEST CASE FAILED inside 2S since state of supply is empty') }
					else {
						testResultStatus = TestLinkAPIResults.TEST_PASSED ;
						System.out.println('TEST CASE PASSED inside 2S since state of supply is empty') }
						
					actualMsg = 'GST Deduction Dtls=' + gstDtlsTxt +'\n' + 'IR GST Details=' + gstnIR_SOSText
				}
				else {
					if (stateOfSupply.trim().isEmpty()) {
						testResultStatus = TestLinkAPIResults.TEST_FAILED  ; 
						System.out.println('TEST CASE PASSED inside 3A since state of supply is empty') }
						
					else {
						testResultStatus = TestLinkAPIResults.TEST_PASSED ; 
						System.out.println('TEST CASE FAILED inside 3A since state of supply is empty')
						}
						
					actualMsg = 'GST Deduction Dtls=' + gstDtlsTxt +'\n' + 'IR GST Details=' + gstnIR_SOSText

				}
			
				

				System.out.println('\n actualMsg = ' + actualMsg) ;
				
		
				execNotes += '\n'+ testCaseName +'::'+
				'\nData to Test::' + dataToTest + '\nExpected Result::' + expectedMsg + '\nActual Result::' + actualMsg 
					'------------------------------------------------';
				System.out.println('\n Execution Notes = ' + execNotes) ;
				//Verify GST - end			
			} catch (Exception e) {
			
			System.out.println("Exception Occured for test case id="+ testCaseId)
			testResultStatus = TestLinkAPIResults.TEST_FAILED ;
		//	CustomKeywords.'reusableComponents.CustomKeywords.takePartialScreenShot'(partialImageStartName ,findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/screenShotDiv'),GlobalVariable.waitForElementVisibleSec)
			
			CustomKeywords.'reusableComponents.CustomKeywords.takeCompleteScreenShot'(partialImageStartName)
			CustomKeywords.'reusableComponents.CustomKeywords.writeErrorLog'(errorFileNameWithoutExtn,testCaseId)
			
		}


			
		
		} /*End of for loop for recordCount */
		
		Instant endExecutionTime = Instant.now();
		Duration timeElapsed = Duration.between(startExecutionTime, endExecutionTime);
		execNotes +=  "\nExecution Time is::" +  timeElapsed.toMillis()/1000 + " secs" ;
		
		CustomKeywords.'reusableComponents.CustomKeywords.updateResultInTestLinkOnline'(projectName ,
			testPlanName,
			testCaseName,
		   buildName,
		   execNotes,
		   testResultStatus)

						
	


