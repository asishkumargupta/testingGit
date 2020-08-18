package reusableComponents

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import enquiryIoStructures.InputAccAvbl_C1 as InputAccAvbl_C1
import enquiryIoStructures.InputCurrentStatusOfPsgn_A1
import enquiryIoStructures.OutputAccAvbl_C1 as OutputAccAvbl_C1
import internal.GlobalVariable
import enquiryIoStructures.InputFareEnq_B3 as InputFareEnq_B3
import enquiryIoStructures.InputRmtWiseChartStatus_D3
import enquiryIoStructures.InputTrainBtwStations_B1 as InputTrainBtwStations_B1
import enquiryIoStructures.OutputTrainBtwStations_B1 as OutputTrainBtwStations_B1
import enquiryIoStructures.OutputFareEnq_B3 as OutputFareEnq_B3
import enquiryIoStructures.OutputRmtWiseChartStatus_D3 as OutputRmtWiseChartStatus_D3
import enquiryIoStructures.OutputCurrentStatusOfPsgn_A1 as OutputCurrentStatusOfPsgn_A1
import java.awt.event.KeyEvent
import java.awt.Robot
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import reusableComponents.CustomKeywords as CustomKeywords
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



public class CustomKeywordsForEnquiry {


	@Keyword
	private  final static String getJourneyDate() {

		System.out.println("Default day : " + GlobalVariable.noOfDays);
		LocalDateTime ldt1 = LocalDateTime.now();
		ldt1=ldt1.plusDays((long)GlobalVariable.noOfDays);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String mmddyyyy3 = ldt1.format(format);
		System.out.println("Formatted Date 3" + mmddyyyy3);
		return mmddyyyy3;
	}

	@Keyword
	private  final static String getCurrentDate() {

		System.out.println("Default day : " + GlobalVariable.noOfDays);
		LocalDateTime ldt1 = LocalDateTime.now();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String mmddyyyy3 = ldt1.format(format);
		System.out.println("Formatted Date 3" + mmddyyyy3);
		return mmddyyyy3;
	}
	@Keyword

	public List<InputAccAvbl_C1> readInputAccAvblDtls (String fileName) {

		int recordCount = 0;

		char recordSeparator = ',';
		InputAccAvbl_C1 inputAccAvbl_C1  = null;
		List<InputAccAvbl_C1> inputAccAvbl_C1List = new ArrayList<InputAccAvbl_C1>();

		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			line = br.readLine();
			while ((line = br.readLine())!=null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					continue ;
				}

				inputAccAvbl_C1  = new InputAccAvbl_C1();

				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");
				inputAccAvbl_C1.setTestCaseId(attributes[0])
				inputAccAvbl_C1.setTrainNo(attributes[1].trim())
				inputAccAvbl_C1.setSiteName(attributes[2].trim())
				inputAccAvbl_C1.setSiteCode(attributes[3].trim())
				inputAccAvbl_C1.setSourceCode(attributes[4].trim())
				inputAccAvbl_C1.setDestnCode(attributes[5].trim())
				inputAccAvbl_C1.setClassCode(attributes[6].trim())
				inputAccAvbl_C1.setQuotaCode(attributes[7].trim())

				inputAccAvbl_C1List.add(inputAccAvbl_C1);
			}

			System.out.println("\nDetails stored in the object")

			String recordString ,storedRecordCount= '' ;
			for (storedRecordCount = 0; storedRecordCount < inputAccAvbl_C1List.size(); storedRecordCount++) {
				recordString   =
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getTrainNo() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getJourneyDate() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getSourceCode() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getDestnCode() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getClassCode() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getQuotaCode() +
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getSiteCode()+
						recordSeparator + inputAccAvbl_C1List.get(storedRecordCount).getSiteName() ;
				System.out.println("For record Count " + storedRecordCount) ;
				System.out.println("recordString " + recordString) ;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputAccAvbl_C1List;
	}
	@Keyword

	public static void fillDtlsOfAccAvbl(String trainNo,
			String journeyDate,
			String sourceCode,
			String destnCode,
			String classCode,
			String quotaCode)
	{
		String statusMsg = '',
		className = '',
		guiClassCode = '',
		guiQuotaCode = '',
		guiQuotaName = '',
		guiPsgnCount = '' ,
		testObject = '';



		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/button_Enquiries'))

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/button_C1 ACCOMMODATION AVAILABILITY'))

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/input_TrainNo'))

		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/input_TrainNo'),trainNo)

		WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/trainNoNameSelected'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)

		System.out.println('trainNo' +  trainNo);

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/trainNoNameSelected'))



		CustomKeywords.sendTabKeysUsingRobot()

		System.out.println('Going to click sendTabKeysUsingRobot')

		System.out.println('Clicked sendTabKeysUsingRobot')

		CustomKeywords.sendEscapeKeysUsingRobot()
		System.out.println('clicked sendEscapeKeysUsingRobot')

		WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/input_journeyDate'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		WebUI.clearText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/input_journeyDate'))


		CustomKeywords.setDateUsingJS('Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/input_journeyDate',journeyDate)
		System.out.println('Executed setDateUsingJS')

		CustomKeywords.sendEscapeKeysUsingRobot();
		System.out.println('clicked sendEscapeKeysUsingRobot')


		if (WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/jryFromStation'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)

			WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/jryFromStation'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/jryFromStation'), sourceCode)

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/Source_selected'))

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/JryToStation'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/JryToStation'), destnCode)

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/Destination_selected'))


		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/div_Quota'))



		for (int quotaCount = GlobalVariable.quotaCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/Quota' + quotaCount


			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {

				guiQuotaName = WebUI.getText(findTestObject(testObject))
				System.out.println('guiQuotaName QUOTA NAME IS=' + guiQuotaName)
				guiQuotaCode = guiQuotaName.split('-')[0].trim()
				System.out.println('QUOTA CODE IS=' + quotaCode)
				System.out.println('INPUT QUOTA CODE IS=' + quotaCode)
				if (guiQuotaCode.equals(quotaCode)) {
					System.out.println('CLICKING QUOTA CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}



		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/div_ClassClass'))




		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/class'+ classCount

			if (WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.waitForElementPresentSec, FailureHandling.OPTIONAL) == true) {


				className = WebUI.getText(findTestObject(testObject))

				guiClassCode = className.split('-')[0].trim()
				System.out.println(' GUI CLass CLASS CODE IS=' + guiClassCode)
				System.out.println('INPUT CLASS CODE IS=' + classCode)
				if (guiClassCode.equals(classCode)) {
					System.out.println('CLICKING CLASS CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}



		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/AccAvblEnq_C1/button_Fetch Availability'))


	}

	@Keyword
	public static void	writeAccAvbltyOutputFile (String outputFile , List<OutputAccAvbl_C1> outputAccAvbl_C1List ){

		char recordSeparator = ',';

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{

			File file = new File(".\\UserDataFiles\\outfiles\\Enquiry\\"+outputFile)
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write("TESTCASE ID " +
					recordSeparator + " FUNCTIONALITY NAME "+
					recordSeparator + " SITE NAME "	+
					recordSeparator + " SITE CODE "	+
					recordSeparator + " STATUS "	+
					recordSeparator + " ERROR MESSAGE ")

			pw.write("\n");

			for ( int i = 0; i < outputAccAvbl_C1List.size(); i++) {

				System.out.println("status") ;
				pw.println(outputAccAvbl_C1List.get(i).getTestCaseId() + recordSeparator
						+ outputAccAvbl_C1List.get(i).getFunctionalityName() + recordSeparator
						+ outputAccAvbl_C1List.get(i).getSiteName() + recordSeparator
						+ outputAccAvbl_C1List.get(i).getSiteCode() + recordSeparator
						+ outputAccAvbl_C1List.get(i).getStatus()+ recordSeparator
						+ outputAccAvbl_C1List.get(i).getErrorMsg() + recordSeparator);


			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	@Keyword

	public List<InputFareEnq_B3> readInputFareEnqDtls (String fileName) {
		int recordCount = 0;

		char recordSeparator = ',';
		InputFareEnq_B3 inputFareEnq_B3  = null;
		List<InputFareEnq_B3> inputFareEnq_B3List = new ArrayList<InputFareEnq_B3>();


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			line = br.readLine();
			while ((line = br.readLine())!=null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					continue ;
				}

				inputFareEnq_B3  = new InputFareEnq_B3();

				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");
				inputFareEnq_B3.setTestCaseId(attributes[0].trim())
				inputFareEnq_B3.setTrainNo(attributes[1].trim())
				inputFareEnq_B3.setSiteName(attributes[2].trim())
				inputFareEnq_B3.setSiteCode(attributes[3].trim())
				inputFareEnq_B3.setSrcCode(attributes[4].trim())
				inputFareEnq_B3.setDestnStnCode(attributes[5].trim())
				inputFareEnq_B3.setClassCode(attributes[6].trim())
				inputFareEnq_B3.setQuotaCode(attributes[7].trim())
				inputFareEnq_B3.setCatering(attributes[8].trim())
				inputFareEnq_B3.setAdultNo(attributes[9].trim())
				inputFareEnq_B3.setChildNo(attributes[10].trim())

				inputFareEnq_B3List.add(inputFareEnq_B3);
			}

			System.out.println("\nDetails stored in the object")

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputFareEnq_B3List;
	}

	@Keyword

	public static void fillDtlsOfFareEnq(String trainNo,
			String journeyDate,
			String srcCode,
			String destnStnCode,
			String clsCode,
			String quotaCode,
			String catering,
			String adultNo,
			String childNo)
	{
		String statusMsg = '',
		className = '',
		guiClassCode = '',
		guiCatering = '',
		guiQuotaCode = '',
		guiQuotaName = '',
		guiPsgnCount = '' ,
		cateringOption = '',
		testObject = '';


		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/button_Enquiries'))

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/button_B3 FARE ENQUIRY'))

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_Fare Enquiry_inputTrainNo'))

		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_Fare Enquiry_inputTrainNo'),trainNo)

		WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/trainNameSelected'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)

		System.out.println('trainNo' +  trainNo);

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/trainNameSelected'))


		CustomKeywords.sendTabKeysUsingRobot();
		System.out.println('Clicked sendTabKeysUsingRobot')

		CustomKeywords.sendEscapeKeysUsingRobot();
		System.out.println('clicked sendEscapeKeysUsingRobot')

		WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_Train_journeyDate'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		WebUI.clearText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_Train_journeyDate'))
		//CustomKeywords.sendEscapeKeysUsingRobot();
		//System.out.println('clicked sendEscapeKeysUsingRobot')
		CustomKeywords.setDateUsingJS('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_Train_journeyDate', journeyDate)

		System.out.println('Executed setDateUsingJS')

		CustomKeywords.sendEscapeKeysUsingRobot();
		System.out.println('clicked sendEscapeKeysUsingRobot')


		if (WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/source_Station'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)

			WebUI.clearText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/source_Station'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/source_Station'), srcCode)

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/selected_SourceStation'))

		WebUI.clearText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/destination_Station'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/destination_Station'), destnStnCode)

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/selected_DestinationStation'))


		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/div_Quota'))



		for (int quotaCount = GlobalVariable.quotaCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {

			testObject = 'NxtGenPRS_OR/Enquiry/FareEnq_B3/Quota' + quotaCount


			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {

				guiQuotaName = WebUI.getText(findTestObject(testObject))
				System.out.println('guiQuotaName QUOTA NAME IS=' + guiQuotaName)
				guiQuotaCode = guiQuotaName.split('-')[0].trim()
				System.out.println('QUOTA CODE IS=' + quotaCode)
				System.out.println('INPUT QUOTA CODE IS=' + quotaCode)
				if (guiQuotaCode.equals(quotaCode)) {
					System.out.println('CLICKING QUOTA CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}



		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/div_ClassClass'))




		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'NxtGenPRS_OR/Enquiry/FareEnq_B3/class'+ classCount

			if (WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.waitForElementPresentSec, FailureHandling.OPTIONAL) == true) {
				//if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.CONTINUE_ON_FAILURE) == true) {

				className = WebUI.getText(findTestObject(testObject))
				//	System.out.println('CLASS NAME IS=' + className)
				guiClassCode = className.split('-')[0].trim()
				System.out.println(' GUI CLass CLASS CODE IS=' + guiClassCode)
				System.out.println('INPUT CLASS CODE IS=' + clsCode)
				if (guiClassCode.equals(clsCode)) {
					System.out.println('CLICKING CLASS CODE')
					WebUI.click(findTestObject(testObject))
					CustomKeywords.sendEscapeKeysUsingRobot();
					break;
				}

			}
		}

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/div_catering'))
		for (int cateringCount = GlobalVariable.cateringCountStart; cateringCount <= GlobalVariable.maxCatering; cateringCount++) {

			testObject = 'NxtGenPRS_OR/Enquiry/FareEnq_B3/catering'+ cateringCount


			if (WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.waitForElementPresentSec, FailureHandling.OPTIONAL) == true) {


				cateringOption = WebUI.getText(findTestObject(testObject))

				guiCatering = cateringOption.split('-')[0].trim()
				System.out.println(' guicatering=' + guiCatering)
				System.out.println('INPUT catering=' + catering)
				if (guiCatering.equals(catering)) {
					System.out.println('click catering')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}

		CustomKeywords.sendTabKeysUsingRobot();

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_adultNo'))
		WebUI.clearText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_adultNo'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_adultNo'), adultNo)

		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_childNo'))
		WebUI.clearText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_childNo'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/input_childNo'), childNo)




		WebUI.click(findTestObject('NxtGenPRS_OR/Enquiry/FareEnq_B3/button_Get Fare'))
		WebUI.delay(GlobalVariable.sleepTimeInSec);


	}

	@Keyword
	public static void	writeFareEnqOutputFile (String outputFile , List<OutputFareEnq_B3> outputFareEnq_B3List ){

		char recordSeparator = ',';

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{

			File file = new File(".\\UserDataFiles\\outfiles\\Enquiry\\"+outputFile)
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write("TESTCASE ID " +
					recordSeparator + " FUNCTIONALITY NAME "+
					recordSeparator + " SITE NAME "	+
					recordSeparator + " SITE CODE "	+
					recordSeparator + " STATUS "	+
					recordSeparator + " ERROR MESSAGE ")

			pw.write("\n");


			for ( int i = 0; i < outputFareEnq_B3List.size(); i++) {

				System.out.println("status") ;
				pw.println(outputFareEnq_B3List.get(i).getTestCaseId() + recordSeparator
						+ outputFareEnq_B3List.get(i).getFunctionalityName() + recordSeparator
						+ outputFareEnq_B3List.get(i).getSiteName() + recordSeparator
						+ outputFareEnq_B3List.get(i).getSiteCode() + recordSeparator
						+ outputFareEnq_B3List.get(i).getStatus()+ recordSeparator
						+ outputFareEnq_B3List.get(i).getErrorMsg() + recordSeparator);


			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Keyword

	public List<InputTrainBtwStations_B1> readInputTrainBtwStn (String fileName) {
		int recordCount = 0;

		char recordSeparator = ',';
		InputTrainBtwStations_B1 inputTrainBtwStations_B1  = null;
		List<InputTrainBtwStations_B1> inputTrainBtwStations_B1List = new ArrayList<InputTrainBtwStations_B1>();


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			line = br.readLine();
			while ((line = br.readLine())!=null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					continue ;
				}

				inputTrainBtwStations_B1  = new InputTrainBtwStations_B1();

				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");

				inputTrainBtwStations_B1.setTestCaseId(attributes[0].trim())
				inputTrainBtwStations_B1.setSiteName(attributes[1].trim())
				inputTrainBtwStations_B1.setSiteCode(attributes[2].trim())
				inputTrainBtwStations_B1.setOriginCode(attributes[3].trim())
				inputTrainBtwStations_B1.setDestnCode(attributes[4].trim())
				inputTrainBtwStations_B1.setQuotaCode(attributes[5].trim())
				inputTrainBtwStations_B1.setClassCode(attributes[6].trim())
				inputTrainBtwStations_B1.setFlexibleDate(attributes[7].trim())


				inputTrainBtwStations_B1List.add(inputTrainBtwStations_B1);
			}

			System.out.println("\nDetails stored in the object")

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputTrainBtwStations_B1List;
	}
	@Keyword

	public static void fillDtlsOfTrainBtwStations(
			String journeyDate,
			String originCode,
			String destnCode,
			String quotaCode,
			String classCode,
			String flexibleDate)


	{
		String statusMsg = '',
		className = '',
		guiClassCode = '',
		guiQuotaCode = '',
		guiQuotaName = '',
		testObject = '';


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/button_Enquiries'))

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/button_B1 TRAIN BETWEEN STATIONS'))


		if (WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_Source'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)

			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_Source'))
		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_Source'), originCode)

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/selectedSourceStation'))

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_Destination'))
		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_Destination'), destnCode)

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/selectedDestStn'))

		CustomKeywords.sendTabKeysUsingRobot()
		System.out.println('clicked sendTabKeysUsingRobot')

		CustomKeywords.sendEscapeKeysUsingRobot()
		System.out.println('clicked sendEscapeKeysUsingRobot')
		findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_journeyDate')

		WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_journeyDate'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		WebUI.clearText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_journeyDate'))


		CustomKeywords.setDateUsingJS('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/input_journeyDate',journeyDate)
		System.out.println('Executed setDateUsingJS')

		CustomKeywords.sendEscapeKeysUsingRobot();
		System.out.println('clicked sendEscapeKeysUsingRobot')

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/div_Quota'))

		for (int quotaCount = GlobalVariable.quotaCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/Quota' + quotaCount


			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {

				guiQuotaName = WebUI.getText(findTestObject(testObject))
				System.out.println('guiQuotaName QUOTA NAME IS=' + guiQuotaName)
				guiQuotaCode = guiQuotaName.split('-')[0].trim()
				System.out.println('QUOTA CODE IS=' + quotaCode)
				System.out.println('INPUT QUOTA CODE IS=' + quotaCode)
				if (guiQuotaCode.equals(quotaCode)) {
					System.out.println('CLICKING QUOTA CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/div_Class'))

		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/Class'+ classCount

			if (WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.waitForElementPresentSec, FailureHandling.OPTIONAL) == true) {


				className = WebUI.getText(findTestObject(testObject))

				guiClassCode = className.split('-')[0].trim()
				System.out.println(' GUI CLass CLASS CODE IS=' + guiClassCode)
				System.out.println('INPUT CLASS CODE IS=' + classCode)
				if (guiClassCode.equals(classCode)) {
					System.out.println('CLICKING CLASS CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}

		if (flexibleDate.equals('N')) {
			System.out.println('CLICKING RTSA FLAG' )

			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/span_ Flexible with Date'))
		}

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/TrainBtwStations/button_Search Trains'))
		WebUI.delay(GlobalVariable.sleepTimeInSec);

	}

	@Keyword
	public static void	writeTrainBtwStationsOutputFile (String outputFile , List<OutputTrainBtwStations_B1> outputTrainBtwStations_B1List ){

		char recordSeparator = ',';

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{

			File file = new File(".\\UserDataFiles\\outfiles\\Enquiry\\"+outputFile)
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write("TESTCASE ID " +
					recordSeparator + " FUNCTIONALITY NAME "+
					recordSeparator + " SITE NAME "	+
					recordSeparator + " SITE CODE "	+
					recordSeparator + " STATUS "	+
					recordSeparator + " ERROR MESSAGE ")

			pw.write("\n");



			for ( int i = 0; i < outputTrainBtwStations_B1List.size(); i++) {

				System.out.println("status") ;
				pw.println(outputTrainBtwStations_B1List.get(i).getTestCaseId() + recordSeparator
						+ outputTrainBtwStations_B1List.get(i).getFunctionalityName() + recordSeparator
						+ outputTrainBtwStations_B1List.get(i).getSiteName() + recordSeparator
						+ outputTrainBtwStations_B1List.get(i).getSiteCode() + recordSeparator
						+ outputTrainBtwStations_B1List.get(i).getStatus()+ recordSeparator
						+ outputTrainBtwStations_B1List.get(i).getErrorMsg() + recordSeparator);


			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@Keyword

	public List<InputRmtWiseChartStatus_D3> readInputRmtWiseChartStatus (String fileName) {
		int recordCount = 0;

		char recordSeparator = ',';
		InputRmtWiseChartStatus_D3 inputRmtWiseChartStatus_D3  = null;
		List<InputRmtWiseChartStatus_D3> inputRmtWiseChartStatus_D3List = new ArrayList<InputRmtWiseChartStatus_D3>();


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			line = br.readLine();
			while ((line = br.readLine())!=null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					continue ;
				}

				inputRmtWiseChartStatus_D3  = new InputRmtWiseChartStatus_D3();

				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");

				inputRmtWiseChartStatus_D3.setTestCaseId(attributes[0].trim())
				inputRmtWiseChartStatus_D3.setTrainNo(attributes[1].trim())
				inputRmtWiseChartStatus_D3.setSiteName(attributes[2].trim())
				inputRmtWiseChartStatus_D3.setSiteCode(attributes[3].trim())
				inputRmtWiseChartStatus_D3.setSourceCode(attributes[4].trim())
				inputRmtWiseChartStatus_D3.setDestnCode(attributes[5].trim())
				inputRmtWiseChartStatus_D3.setClassCode(attributes[6].trim())



				inputRmtWiseChartStatus_D3List.add(inputRmtWiseChartStatus_D3);
			}

			System.out.println("\nDetails stored in the object")

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputRmtWiseChartStatus_D3List;
	}
	@Keyword

	public static void fillDtlsOfRmtWiseChartStatus(
			String trainNo,
			String journeyDate,
			String sourceCode,
			String destnCode,
			String classCode)

	{
		String statusMsg = '',
		className = '',
		guiClassCode = '',
		testObject = '';


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/button_Enquiries'))


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/button_D3 REMOTE WISE CHARTING STATUS'))


		if (WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_TrainNo'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)



		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_TrainNo'))

		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_TrainNo'),trainNo)

		WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/selectedTrainName'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)

		System.out.println('trainNo' +  trainNo);
		System.out.println('journeyDate' +  journeyDate);


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/selectedTrainName'))

		CustomKeywords.sendTabKeysUsingRobot()
		System.out.println('clicked sendTabKeysUsingRobot')

		CustomKeywords.sendEscapeKeysUsingRobot()
		System.out.println('clicked sendEscapeKeysUsingRobot')



		WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_Train_journeyDate'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		WebUI.clearText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_Train_journeyDate'))


		CustomKeywords.setDateUsingJS('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_Train_journeyDate',journeyDate)
		System.out.println('Executed setDateUsingJS')

		CustomKeywords.sendEscapeKeysUsingRobot();
		System.out.println('clicked sendEscapeKeysUsingRobot')



		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_sourceStation'))
		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_sourceStation'), sourceCode)

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/selectedSourceStation'))


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_Destination'))
		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/input_Destination'), destnCode)

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/selectedDestinationStation'))


		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/div_ClassClass'))

		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/Class'+ classCount

			if (WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.waitForElementPresentSec, FailureHandling.OPTIONAL) == true) {


				className = WebUI.getText(findTestObject(testObject))

				guiClassCode = className.split('-')[0].trim()
				System.out.println(' GUI CLass CLASS CODE IS=' + guiClassCode)
				System.out.println('INPUT CLASS CODE IS=' + classCode)
				if (guiClassCode.equals(classCode)) {
					System.out.println('CLICKING CLASS CODE')
					WebUI.click(findTestObject(testObject))
					break;
				}

			}
		}

		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/RmtWiseChartStatus/button_Fetch Charting Status'))
		//WebUI.delay(2);

	}
	@Keyword
	public static void	writeRmtWiseChartStatusOutputFile (String outputFile , List<OutputRmtWiseChartStatus_D3> outputRmtWiseChartStatus_D3List ){

		char recordSeparator = ',';

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{

			File file = new File(".\\UserDataFiles\\outfiles\\Enquiry\\"+outputFile)
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write("TESTCASE ID " +
					recordSeparator + " FUNCTIONALITY NAME "+
					recordSeparator + " SITE NAME "	+
					recordSeparator + " SITE CODE "	+
					recordSeparator + " STATUS "	+
					recordSeparator + " ERROR MESSAGE ")

			pw.write("\n");



			for ( int i = 0; i < outputRmtWiseChartStatus_D3List.size(); i++) {

				System.out.println("status") ;
				pw.println(outputRmtWiseChartStatus_D3List.get(i).getTestCaseId() + recordSeparator
						+ outputRmtWiseChartStatus_D3List.get(i).getFunctionalityName() + recordSeparator
						+ outputRmtWiseChartStatus_D3List.get(i).getSiteName() + recordSeparator
						+ outputRmtWiseChartStatus_D3List.get(i).getSiteCode() + recordSeparator
						+ outputRmtWiseChartStatus_D3List.get(i).getStatus()+ recordSeparator
						+ outputRmtWiseChartStatus_D3List.get(i).getErrorMsg() + recordSeparator);


			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	@Keyword

	public List<InputCurrentStatusOfPsgn_A1> readInputCurrentStatusOfPsgn (String fileName) {
		int recordCount = 0;

		char recordSeparator = ',';
		InputCurrentStatusOfPsgn_A1 inputCurrentStatusOfPsgn_A1  = null;
		List<InputCurrentStatusOfPsgn_A1> inputCurrentStatusOfPsgn_A1List = new ArrayList<InputCurrentStatusOfPsgn_A1>();


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			line = br.readLine();
			while ((line = br.readLine())!=null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					continue ;
				}

				inputCurrentStatusOfPsgn_A1  = new InputCurrentStatusOfPsgn_A1();

				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");

				inputCurrentStatusOfPsgn_A1.setTestCaseId(attributes[0].trim())
				inputCurrentStatusOfPsgn_A1.setSiteName(attributes[1].trim())
				inputCurrentStatusOfPsgn_A1.setSiteCode(attributes[2].trim())
				inputCurrentStatusOfPsgn_A1.setPnrNo(attributes[3].trim())

				inputCurrentStatusOfPsgn_A1List.add(inputCurrentStatusOfPsgn_A1);
			}

			System.out.println("\nDetails stored in the object")

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputCurrentStatusOfPsgn_A1List;
	}
	@Keyword

	public static void fillDtlsOfCurrentStatusOfPsgn(String pnrNo)


	{
	
		
		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/button_Enquiries'))
		
		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/button_A1 CURRENT STATUS OF PASSENGERS'))
		
		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/input_PnrNo'))
		
		WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/input_PnrNo'),pnrNo)
		
		WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/Enquiry/CurrentStatusOfPsgn/button_Check PNR status'))

	}
	
	@Keyword
	public static void	writeCurrentStatusOfPsgnOutputFile (String outputFile , List<OutputCurrentStatusOfPsgn_A1> outputCurrentStatusOfPsgn_A1List ){

		char recordSeparator = ',';

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try{

			File file = new File(".\\UserDataFiles\\outfiles\\Enquiry\\"+outputFile)
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);

			pw.write("TESTCASE ID " +
					recordSeparator + " FUNCTIONALITY NAME "+
					recordSeparator + " SITE NAME "	+
					recordSeparator + " SITE CODE "	+
					recordSeparator + " PNR NO "	+
					recordSeparator + " STATUS "	+
					recordSeparator + " ERROR MESSAGE ")

			pw.write("\n");



			for ( int i = 0; i < outputCurrentStatusOfPsgn_A1List.size(); i++) {

				System.out.println("status") ;
				
				pw.println(outputCurrentStatusOfPsgn_A1List.get(i).getTestCaseId() + recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getFunctionalityName() + recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getSiteName() + recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getSiteCode() + recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getPnrNo() + recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getStatus()+ recordSeparator
						+ outputCurrentStatusOfPsgn_A1List.get(i).getErrorMsg() + recordSeparator);


			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}
