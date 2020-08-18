import ioStructures.BookedTktPnrDtls
import ioStructures.InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls

	long startTime = System.currentTimeMillis();
	List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()
	BookedTktPnrDtls bookedTktPnrDtls ;
	String testCaseId ='' ,
			testCaseName = "TC_BOOK_SELECTED_ONLY";
	List<BookedTktPnrDtls> bookedTktPnrDtlsList = new ArrayList()
	char recordSeparator = ',';
	String timenow = null;
	
	timenow = CustomKeywords.'reusableComponents.CustomKeywords.getDateTime'();
	String errorFileNameWithoutExtn = testCaseName + '_' + timenow ;
	String partialImageStartName = '',
		exceptionImageNameOnly = '';
	String tktBookedDtlsFileNameWithoutExtn = testCaseName  + '_' + timenow;
	
	
	
	//String partialImageStartName = "D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\outfiles\\" + "exceptionImage" + timenow + ".png" ;
	

	
	
	//CustomKeywords.'reusableComponents.CustomKeywords.preConditionsInfo'('Ensure proper testing environments for input trains in dat file \n are Available for booking')
	System.out.println("READING FILE") ;
	//inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputTDRCPsgnDtls'("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")
	
	inputTDRCPsgnDtlsList = CustomKeywords.'reusableComponents.CustomKeywords.readInputConcessionalTDRCPsgnDtls'(".\\UserDataFiles\\inputfiles\\FS_TC_CLIENT_BOOKING_DATA.csv")
	
	System.out.println("No records in input file=" + inputTDRCPsgnDtlsList.size())
	System.out.println("===================================") ;
	//System.out.println("WRITING FILE") ;
	//CustomKeywords.'reusableComponents.CustomKeywords.writeInputTDRCPsgnDtls'("asish",inputTDRCPsgnDtlsList)
	System.out.println("===================================") ;
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
		
		rtsaFlag = inputTDRCPsgnDtlsList.get(recordCount).getRtsaFlag()
		System.out.println("rtsaFlag="+ rtsaFlag)
		concessionFlag = inputTDRCPsgnDtlsList.get(recordCount).getConcessionalFlag()
		System.out.println("concessionFlag="+ concessionFlag)
		ticketType = inputTDRCPsgnDtlsList.get(recordCount).getTicketType()
		System.out.println("ticketType="+ ticketType)
		vipFlag = inputTDRCPsgnDtlsList.get(recordCount).getVipFlag()
		System.out.println("vipFlag="+ vipFlag)
		
		 
		
		partialImageStartName = ".\\UserDataFiles\\outfiles\\" + testCaseId + "exceptionImage" + timenow + ".png" ;
		exceptionImageNameOnly = testCaseId + "exceptionImage" + timenow + ".png"
		dataToTest = testCaseId  + recordSeparator + trainNo + recordSeparator + srcStnCode + recordSeparator + destnStnCode + recordSeparator + clsCode
	//	if  (!(testCaseId.equals(testCaseName)))
	//	continue ;
		
		CustomKeywords.'reusableComponents.CustomKeywords.fillTDRCInQB'(trainNo,
																					doj,
																					srcStnCode,
																					destnStnCode,
																					clsCode,
																					quotaCode,
																					noOfPsgn,
																					boardingStnCode,
																					resvnUptoStnCode,
																					coachChoice,
																					rtsaFlag,
																					concessionFlag,
																					ticketType,
																					vipFlag	)
		
		
		psgnDtlsList = inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList()
		
		bookedTktPnrDtls.setRecNo(inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
		System.out.println("Rec count for booked dtls before adding in List 1=" + bookedTktPnrDtls.getRecNo())
	
		System.out.println("Rec No in input dtl before fillConcessionalPsgnDtlsInQB=" + inputTDRCPsgnDtlsList.get(recordCount).getRecNo()) ;
		
		System.out.println("Rec No in booked tkt before fillConcessionalPsgnDtlsInQB=" + bookedTktPnrDtls.getRecNo()) ;
		
	//	System.out.println("Rec No in input dtl before fillConcessionalPsgnDtlsInQB printed=" + inputTDRCPsgnDtlsList.get(recordCount).getRecNo()) ;
		
		CustomKeywords.'reusableComponents.CustomKeywords.fillConcessionalPsgnDtlsInQB'(psgnDtlsList)
		
		CustomKeywords.'reusableComponents.CustomKeywords.fillAddDtlsClickNextInPsgnDtlPageQB'(concessionFlag,mobileNo,paymentType)
		
	//	bookedTktPnrDtls = CustomKeywords.'reusableComponents.CustomKeywords.getBookedTktDtlsClickNextQB'(testCaseId , noOfPsgn)
		CustomKeywords.'reusableComponents.CustomKeywords.getBookedTktDtlsClickNextQB'(testCaseId , noOfPsgn,bookedTktPnrDtls )
	//	System.out.println("Rec count for booked dtls before adding in List 2 =" + bookedTktPnrDtls.getRecNo())
		bookedTktPnrDtls.setExecutionStatus("BOOKED OK")
		bookedTktPnrDtlsList.add(bookedTktPnrDtls)
		System.out.println("Rec count for booked dtls after adding in List=" + bookedTktPnrDtlsList.get(recordCount).getRecNo()) ;
	//	System.out.println("Rec count after bookedTktPnrDtlsList.add=" + recordCount)
	
		
	//	WebUI.delay(5)
		} catch (Exception e) {
	//	System.out.println("Exception 1 Occured for test case id="+ testCaseId)
		System.out.println("Exception  Occured for rec No of input file="+  inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
		bookedTktPnrDtls.setRecNo(inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
		//bookedTktPnrDtls.setErrorMsg("Error Occured for Input Rec No " + inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
		bookedTktPnrDtls.setErrorMsg("Refer Image: " + exceptionImageNameOnly)
		bookedTktPnrDtls.setExecutionStatus("FAILED")
		bookedTktPnrDtlsList.add(bookedTktPnrDtls)
	//	bookedTktPnrDtlsList.get(recordCount).setRecNo(inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
		//String recNo=inputTDRCPsgnDtlsList.get(recordCount).getRecNo()
	//		System.out.println("Exception 2 Occured for test case id="+ testCaseId)
			
	//		System.out.println("Exception Occured for record no ="+ inputTDRCPsgnDtlsList.get(recordCount).getRecNo())
			
		
			
			CustomKeywords.'reusableComponents.CustomKeywords.takeCompleteScreenShot'(partialImageStartName)
		//	CustomKeywords.'reusableComponents.CustomKeywords.writeErrorLog'(errorFileNameWithoutExtn,testCaseId)
			CustomKeywords.'reusableComponents.CustomKeywords.sendEscapeKeysUsingRobot'()
			
		}
		
		} /*End of for loop for recordCount */
		
//	CustomKeywords.'reusableComponents.CustomKeywords.writeBookedTktDtls'(tktBookedDtlsFileNameWithoutExtn,bookedTktPnrDtlsList)
	System.out.println("Going to start comparing the input output details")
	CustomKeywords.'reusableComponents.CustomKeywords.compareInputAndBookedTktDtls'(tktBookedDtlsFileNameWithoutExtn,inputTDRCPsgnDtlsList,bookedTktPnrDtlsList,startTime)
	System.out.println("Completedcomparing the input output details")
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	