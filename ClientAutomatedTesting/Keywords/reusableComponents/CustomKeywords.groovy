package reusableComponents

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Desktop
import java.awt.Robot
import java.awt.event.KeyEvent
import java.awt.image.BufferedImage
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

import javax.imageio.ImageIO
import javax.swing.JOptionPane
import javax.swing.JPanel

import org.apache.commons.io.FileUtils
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.Point
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import inputOutputStructures.PnrDetails
import internal.GlobalVariable
import ioStructures.BookedTktFareDtls
import ioStructures.BookedTktPnrDtls
import ioStructures.BookedTktSinglePsgnDtls
import ioStructures.FailedBookingDtls as FailedBookingDtls
import ioStructures.InputFareDtls
import ioStructures.InputSinglePsgnDtls
import ioStructures.InputTDRCPsgnDtls
import testlink.api.java.client.TestLinkAPIClient;




public class CustomKeywords {

	@Keyword
	public void openHtmlPage(String url){

		File file = new File(url);
		URI oURL = file.toURI();
		Desktop.getDesktop().browse(oURL);
	}


	@Keyword
	public void openPage(String url){
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.url)
		WebUI.maximizeWindow()
	}




	/*
	 @Keyword
	 public void writeToOutputExcel(){
	 //	OutPnrStatDetails outPnrStatDetails = new OutPnrStatDetails ();
	 FileInputStream file = new FileInputStream (new File("D:\\Testdata.xlsx"))
	 XSSFWorkbook workbook = new XSSFWorkbook(file);
	 XSSFSheet sheet = workbook.getSheetAt(0);
	 'Read data from excel'
	 String Data_fromCell=sheet.getRow(1).getCell(1).getStringCellValue();
	 'Write data to excel'
	 //sheet.getRow(1).createCell(1).setCellValue("Mahesh2");
	 sheet.getRow(1).createCell(1).setCellValue("ASISH GUPTA")
	 file.close();
	 FileOutputStream outFile =new FileOutputStream(new File("D:\\Testdata.xlsx"));
	 workbook.write(outFile);
	 outFile.close();
	 }
	 */	
	@Keyword
	private  final static String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return df.format(new Date());
	}




	@Keyword

	public static void writeOutputFile(String outFileWithoutExtn , List<PnrDetails> pnrDetailsList ) {
		String timenow = null;
		String psgnStatusString = null;
		char psgnSeparator ='$';
		char psgnStatusSeparator = '%';
		char recordSeparator = '#';
		timenow = getDateTime();
		String outFile = outFileWithoutExtn + timenow + ".txt" ;


		//	File file = new File(outFile);

		File file = new File(".\\UserDataFiles\\outfiles\\" + outFile)

		FileWriter fr = null;
		BufferedWriter br = null;

		try{
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			br.write(" PNR NO " +
					recordSeparator + " ERROR MSG "	+
					recordSeparator + " TRAIN NO "	+
					recordSeparator + " BOARDING DATE " +
					recordSeparator + " FROM STN  " +
					recordSeparator + " TO STN "	+
					recordSeparator + " BOARDING STN "	+
					recordSeparator + " RESVN UPTO "	+
					recordSeparator + " JRNY CLS "	+
					recordSeparator + " QUOTA CODE "	+
					recordSeparator + " TOTAL AMOUNT " +
					recordSeparator + " CHART STATUS " +
					recordSeparator + " PENDING AMOUNT " +
					recordSeparator + " PSGNS STATUS " )


			br.newLine();

			for ( int i = 0; i < pnrDetailsList.size(); i++) {
				psgnStatusString = '' ;
				for ( int psgnCount = 0; psgnCount < pnrDetailsList.get(i).psgnDetailsList.size(); psgnCount++) {

					psgnStatusString = psgnStatusString	+
							pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnName()+
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnAge() +
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnGender() +
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnQuota() +

							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnBookingStatus() +
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnBookingDtls() +
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnCurrentStatus() +
							psgnStatusSeparator + pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnCurrentDtls();

					psgnStatusString = psgnStatusString + psgnSeparator ;

				}

				br.write(pnrDetailsList.get(i).getPnrNo() + recordSeparator
						+ pnrDetailsList.get(i).getErrorMsg() + recordSeparator

						+ pnrDetailsList.get(i).getJourneyDetails().getTrainNo() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getDateOfBoarding() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getJrnyFromCode() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getJrnyToCode() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getBoardingStnCode() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getResvUptoCode() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getJrnyClsCode() + recordSeparator
						+ pnrDetailsList.get(i).getJourneyDetails().getJrnyQuotaCode() + recordSeparator




						//	+ pnrDetailsList.get(i).getPaidFareDetails().getBookingAmount() + recordSeparator
						//	+ pnrDetailsList.get(i).getPaidFareDetails().getVoucherAmount() + recordSeparator
						//	+ pnrDetailsList.get(i).getPaidFareDetails().getCashAmount() + recordSeparator
						+ pnrDetailsList.get(i).getPaidFareDetails().getTotalAmount() + recordSeparator
						+ pnrDetailsList.get(i).getTrainInfoDetails().getChartStatus() + recordSeparator
						+ pnrDetailsList.get(i).getAdditionalDetails().getPendingAmount() + recordSeparator

						//	+ pnrDetailsList.get(i).getPaidFareDetails() + ","
						+ psgnStatusString + recordSeparator	);




				br.newLine();
			}
		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Keyword

	//def setDateUsingJS( String testObjectID,String inputDate){
	public static void setDateUsingJS( String testObjectID,String inputDate){
		// click calendar start
		//	WebUI.focus(findTestObject(testObjectID))
		WebElement element = WebUiCommonHelper.findWebElement(findTestObject(testObjectID),30)
		WebUI.executeJavaScript("arguments[0].removeAttribute('readonly')", Arrays.asList(element))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.setText(findTestObject(testObjectID), inputDate)
		WebUI.focus(findTestObject(testObjectID))
		WebUI.executeJavaScript("arguments[0].readOnly = true", Arrays.asList(element))

	}


	@Keyword
	public static int getDatainTable(TestObject to , int timeout) {
		List<String> columnHdr = new ArrayList<>();
		//To locate table.
		WebElement mytable = WebUiCommonHelper.findWebElement(to, timeout)

		List < WebElement >rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("Number of rows inside function " + rows_count);


		'Loop will execute for all the rows of the table'
		Loop:
		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of cells In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'
			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()

				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)

				'Checking if Cell text is matching with the expected value'

			}


		}

		return rows_count

	}


	@Keyword
	public static int getRowHeaderAndFirstRowDataInTable(TestObject to , int timeout) {
		List<String> columnHdr = new ArrayList<>();
		List<String> firstRowData = new ArrayList<>();

		//To locate table.
		WebElement mytable = WebUiCommonHelper.findWebElement(to, timeout)

		List < WebElement >rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("Number of rows inside function " + rows_count);


		'Loop will execute for all the rows of the table'
		Loop:
		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of cells In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'
			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()

				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)

				'Checking if Cell text is matching with the expected value'

			}


		}

		return rows_count

	}




	@Keyword
	public static String [][] storeTableDataInobject(TestObject to , int timeout) {
		List<String> columnHdr = new ArrayList<>();
		List<String> firstRowData = new ArrayList<>();

		//To locate table.
		WebElement mytable = WebUiCommonHelper.findWebElement(to, timeout)

		List < WebElement >rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("Number of rows inside function " + rows_count);
		List<WebElement> columnsInFirstRow = rows_table.get(0).findElements(By.tagName('td'))
		for (int column = 0; column < columnsInFirstRow.size(); column++) {
			System.out.println("Column header = " + columnsInFirstRow.get(column).getText());
		}


		//Make an array of dimension rows_count and columns_count
		String [][] tableDataArray = new String [rows_count][columnsInFirstRow.size()]
		'Loop will execute for all the rows of the table'
		Loop:
		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of cells In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'
			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()

				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
				tableDataArray[row][column]= celltext


			}


		}


		for (int i = 0; i < rows_count; i++) {
			for (int j = 0; j < columnsInFirstRow.size(); j++) {
				System.out.println("arr[" + i + "][" + j + "] = "
						+ tableDataArray[i][j]);
			}

		}


		return tableDataArray

	}


	@Keyword
	public <String> List<String> twoDArrayToList(String[][] twoDArray) {
		List<String> list = new ArrayList<String>();
		for (String[] array : twoDArray) {
			list.addAll(Arrays.asList(array));
		}
		return list;
	}


	//public  String convertStringToDateinMCP(String dateString) {

	@Keyword
	def static String convertDateFromAPOStoMCP(String dateString) {

		//System.out.println("String received =" + dateString);
		String monthName_Day = dateString.split(",")[1];
		//System.out.println("Month Name and Day=" + monthName_Day);
		String monthName = monthName_Day.split("\\s")[1]
		//	System.out.println("Month Name=" + monthName);


		int day = Integer.parseInt( monthName_Day.split("\\s")[2])

		//System.out.println("Day in String=" + day);

		String dd_mm ='';

		String mm = '';

		switch(monthName) {
			case 'Jan':
				mm = "01" ;
				break;
			case 'Feb':
				mm = "02" ;
				break;
			case 'Mar':
				mm = '03' ;
				break;
			case 'Apr':
				mm = '04' ;
				break;
			case 'May':
				mm = '05' ;
				break;
			case 'Jun':
				mm = '06' ;
				break;
			case 'Jul':
				mm = '07' ;
				break;
			case 'Aug':
				mm = '08' ;
				break;
			case 'Sep':
				mm = '09' ;
				break;
			case 'Oct':
				mm = '10' ;
				break;
			case 'Nov':
				mm = '11' ;
				break;
			case 'Dec':
				mm = '12' ;
				break;
			default:
				mm = "??"
		} //End of case

		dd_mm = String.format("%02d", day) + "/" + mm

		//		System.out.println("New date is  " + dd_mm);
		return dd_mm

	}





	@Keyword
	public static void writeAllClsAvbltyOutputFile(String dataToProcess , String outFileName , TestObject to , int timeout ) {

		char 	recordSeparator = ',' ,
		headerSeparator = '$';
		String	dateInMCPFormat = '' ;

		List<String> columnHdr = new ArrayList<>();
		List<String> firstRowData = new ArrayList<>();

		//To locate table.
		WebElement mytable = WebUiCommonHelper.findWebElement(to, timeout)

		List < WebElement >rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("Number of rows inside function " + rows_count);


		//	File file = new File(outFile);

		File file = new File(".\\UserDataFiles\\outfiles\\" + outFileName)


		try {
			if (file.createNewFile()) {
				System.out.println("New Text File is created!");
			} else {
				System.out.println("File already exists.");
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}

		FileWriter fr = null;
		BufferedWriter br = null;
		fr = new FileWriter(file,true);
		br = new BufferedWriter(fr);

		//To store column header
		Loop:
		for (int row = 0; row < 1 ; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			//		println((('Number of cells In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'
			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()

				//			println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
				columnHdr.add(celltext)



			}


		}

		//To store first row data
		for (int row = 1; row < 2 ; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			//		println((('Number of cells In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'
			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()

				//			println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
				firstRowData.add(celltext)



			}


		}
		//	String SingleTrainRecordString = '';
		String SingleTrainRecordString = dataToProcess


		try{
			for (int column = 0; column < columnHdr.size(); column++) {
				if (column == 0) {
					String dateInPage = firstRowData.get(column)
					System.out.println("Date in page=" + dateInPage) ;
					dateInMCPFormat =  convertDateFromAPOStoMCP(dateInPage)
					//			                CustomKeywords.'reusableComponents.CustomKeywords.convertStringToDateinMCP'(dateString  )
					//		String dd_mm =  convertStringToDateinMCP(dateInPage )
					System.out.println("Date after format is =" + dateInMCPFormat ) ;

					SingleTrainRecordString = SingleTrainRecordString  + columnHdr.get(column).split("-")[0] + headerSeparator + dateInMCPFormat + recordSeparator ;

				}
				else {
					SingleTrainRecordString = SingleTrainRecordString  + columnHdr.get(column).split("-")[0] + headerSeparator + firstRowData.get(column)+ recordSeparator ;
				}





			}
			System.out.println("String to write  = " + SingleTrainRecordString) ;
			br.write(SingleTrainRecordString)
			br.newLine();


		}	catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Keyword
	public static void sendEscapeKeysUsingRobot(t) {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	@Keyword
	public static void sendTabKeysUsingRobot(t) {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}


	@Keyword
	public static void diffOfTwoFiles(String fileName1 , String fileName2 ) {
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String sCurrentLine;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		//br1 = new BufferedReader(new FileReader("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\outfiles\\NxtGenAVBL2019-09-10_02_56_29.csv"));
		//br2 = new BufferedReader(new FileReader("D:\\APOSWorkSpaceInGradle\\UsingKatlon\\ClientAutomatedTesting\\UserDataFiles\\outfiles\\mcp2019-09-10_02_54_45.csv"));


		System.out.println('GETTING DIFF OF OUTPUT FILES' +  '\n' + fileName1 +  '\n' + fileName2 );
		System.out.println('==============<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>================' );

		br1 = new BufferedReader(new FileReader(fileName1));
		br2 = new BufferedReader(new FileReader(fileName2));


		while ((sCurrentLine = br1.readLine()) != null) {
			list1.add(sCurrentLine);
		}
		while ((sCurrentLine = br2.readLine()) != null) {
			list2.add(sCurrentLine);
		}
		List<String> tmpList = new ArrayList<String>(list1);
		tmpList.removeAll(list2);
		//	System.out.println("content from test.txt which is not there in test2.txt");
		System.out.println('CONTENTS FROM ' + fileName1 + ' which is not in ' + fileName2 );
		System.out.println('==============<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>================' );
		for(int i=0;i<tmpList.size();i++){
			System.out.println(tmpList.get(i)); //content from test.txt which is not there in test2.txt
		}

		//	System.out.println("content from test2.txt which is not there in test.txt");
		System.out.println('CONTENTS FROM ' + fileName2 + ' which is not in ' + fileName1 );
		System.out.println('==============<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>================' );

		tmpList = list2;
		tmpList.removeAll(list1);
		for(int i=0;i<tmpList.size();i++){
			System.out.println(tmpList.get(i)); //content from test2.txt which is not there in test.txt
		}

	}

	@Keyword

	public static void updateResultInTestLinkOffline(String projectName ,
			String testPlanName,
			String testCaseName,
			String buildName,
			String execNotes,
			String testResultStatus)
	{
		//	System.out.println("Entered DiffUtilityFunctions --updateTestLinkResult -- Going to Update in TestLink") ;
		//Enter your project API key here.
		//  String DEVKEY="040d169f3f1a2b38ced46c37d073a1c4";
		String DEVKEY="418449b8779c1d9adcc8e0dcb7ee06f3" ;
		//Enter your Test Link URL here
		String URL= "http://10.222.2.46:8080/testlink-1.9.14/lib/api/xmlrpc/v1/xmlrpc.php";

		//Enter your Test Project Name here
		//String testProject="AutomatedTestingProject";

		//Enter your Test Plan here
		//String testPlan="Test Plan to test APOS PRIMES";

		//Enter your Test build here
		//String build="Build for Test Plan to test APOS PRIMES";


		//result = TestLinkAPIResults.TEST_PASSED ;
		//  System.out.println("Entered DiffUtilityFunctions --updateTestLinkResult -- Going to execute try") ;
		try {
			TestLinkAPIClient testlinkAPIClient = null;
			// System.out.println("Inside DiffUtilityFunctions -- Going to instantiate") ;
			testlinkAPIClient = new TestLinkAPIClient(DEVKEY,URL);


			//	 System.out.println("Inside DiffUtilityFunctions -- Going to Update in TestLink") ;
			testlinkAPIClient.reportTestCaseResult(projectName ,
					testPlanName,
					testCaseName,
					buildName,
					execNotes,
					testResultStatus);
			//	System.out.println("Inside DiffUtilityFunctions -- Updated in TestLink") ;
		} catch (Exception e) {
			//	System.out.println("Inside DiffUtilityFunctions -- Exception Error=" + e.getMessage()) ;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Keyword

	public static void updateResultInTestLinkOnline(String projectName ,
			String testPlanName,
			String testCaseName,
			String buildName,
			String execNotes,
			String testResultStatus)
	{
		//	System.out.println("Entered DiffUtilityFunctions --updateTestLinkResult -- Going to Update in TestLink") ;
		//Enter your project API key here.
		//  String DEVKEY="040d169f3f1a2b38ced46c37d073a1c4";
		String DEVKEY="418449b8779c1d9adcc8e0dcb7ee06f3" ;
		//Enter your Test Link URL here
		String URL= "http://10.64.28.52/testlink-1.9.14/lib/api/xmlrpc/v1/xmlrpc.php";

		//Enter your Test Project Name here
		//String testProject="AutomatedTestingProject";

		//Enter your Test Plan here
		//String testPlan="Test Plan to test APOS PRIMES";

		//Enter your Test build here
		//String build="Build for Test Plan to test APOS PRIMES";


		//result = TestLinkAPIResults.TEST_PASSED ;
		//  System.out.println("Entered DiffUtilityFunctions --updateTestLinkResult -- Going to execute try") ;
		try {
			TestLinkAPIClient testlinkAPIClient = null;
			// System.out.println("Inside DiffUtilityFunctions -- Going to instantiate") ;
			testlinkAPIClient = new TestLinkAPIClient(DEVKEY,URL);


			// System.out.println("Inside DiffUtilityFunctions -- Going to Update in TestLink") ;
			testlinkAPIClient.reportTestCaseResult(projectName ,
					testPlanName,
					testCaseName,
					buildName,
					execNotes,
					testResultStatus);
			//	System.out.println("Inside DiffUtilityFunctions -- Updated in TestLink") ;
		} catch (Exception e) {
			//	System.out.println("Inside DiffUtilityFunctions -- Exception Error=" + e.getMessage()) ;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 @Keyword
	 public static void readTDRCPsgnDtls (String fileName) {
	 Path pathToFile = Paths.get(fileName);
	 try {
	 //	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
	 BufferedReader br = new BufferedReader(fileName);
	 String line = br.readLine();
	 while (line != null) {
	 String[] attributes = line.split(",");
	 System.out.println("Line Read=" + line) ;
	 line = br.readLine();
	 }
	 } catch (IOException ioe) {
	 ioe.printStackTrace();
	 }
	 }
	 */

	@Keyword

	public List<InputTDRCPsgnDtls> readInputTDRCPsgnDtls (String fileName) {
		int recordCount = 0;
		InputTDRCPsgnDtls inputTDRCPsgnDtls  = new InputTDRCPsgnDtls();
		InputSinglePsgnDtls inputSinglePsgnDtls = new InputSinglePsgnDtls();
		List<InputSinglePsgnDtls> psgnDtlsList = new ArrayList()
		List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				recordCount ++ ;
				inputTDRCPsgnDtls  = new InputTDRCPsgnDtls();
				psgnDtlsList = new ArrayList()
				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");
				inputTDRCPsgnDtls.setTestCaseId(attributes[0])
				inputTDRCPsgnDtls.setTrainNo(attributes[1])
				inputTDRCPsgnDtls.setDoj(attributes[2])
				inputTDRCPsgnDtls.setSrcStnCode(attributes[3])
				inputTDRCPsgnDtls.setDestnStnCode(attributes[4])
				inputTDRCPsgnDtls.setClassCode(attributes[5])
				inputTDRCPsgnDtls.setQuotaCode(attributes[6])

				inputTDRCPsgnDtls.setPsgnCount(attributes[7])

				inputTDRCPsgnDtls.setBoardingStnCode(attributes[8])
				inputTDRCPsgnDtls.setResvnUptoStnCode(attributes[9])
				//String[] psgnAttributes = attributes[10].split("%");

				String[] singlePsgnDtlsString =  attributes[10].split("#");
				System.out.println("All Passenger Details=" + singlePsgnDtlsString) ;
				System.out.println("No of Passenger=" + singlePsgnDtlsString.size()) ;
				for (int psgnCount = 0 ; psgnCount < singlePsgnDtlsString.size(); psgnCount++) {

					inputSinglePsgnDtls = new InputSinglePsgnDtls();

					String[] psgnAttributes = singlePsgnDtlsString[psgnCount].split("%");
					System.out.println("No of psgn Attributes =" + psgnAttributes.length) ;
					System.out.println("Single Passenger Name =" + psgnAttributes[0]) ;
					inputSinglePsgnDtls.setPsgnName(psgnAttributes[0])
					inputSinglePsgnDtls.setPsgnGender(psgnAttributes[1])
					inputSinglePsgnDtls.setPsgnAge(psgnAttributes[2])
					inputSinglePsgnDtls.setBerthPreference(psgnAttributes[3])
					inputSinglePsgnDtls.setFoodPreference(psgnAttributes[4])
					if (psgnAttributes.length > 5)
					{
						inputSinglePsgnDtls.setConcession(psgnAttributes[5])
					}
					psgnDtlsList.add(inputSinglePsgnDtls)
				}
				System.out.println("No of Passenger in object=" + psgnDtlsList.size()) ;
				inputTDRCPsgnDtls.setMobileNo(attributes[11])
				inputTDRCPsgnDtls.setPaymentType(attributes[12])
				inputTDRCPsgnDtls.setPsgnDtlsList(psgnDtlsList)
				inputTDRCPsgnDtlsList.add(inputTDRCPsgnDtls)
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputTDRCPsgnDtlsList;
	}



	@Keyword

	public static void writeInputTDRCPsgnDtls (String outFileWithoutExtn , List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList) {
		String timenow = null;
		String psgnStatusString = null;
		char psgnRecordSeparator ='#';
		char psgnStatusSeparator = '%';
		char recordSeparator = ',';
		String recordString = '';
		String psgnDtlsString = '';
		timenow = getDateTime();
		String outFile = outFileWithoutExtn + timenow + ".csv" ;
		File file = new File(".\\UserDataFiles\\outfiles\\" + outFile)
		FileWriter fr = null;
		BufferedWriter br = null;
		try{
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			br.write("TEST CASE NAME" +
					recordSeparator + "TRAINNo"	+
					recordSeparator + "JRNY DATE(MM-DD-YYYY)"	+
					recordSeparator + "SOURCE STN CODE" +
					recordSeparator + "DESTN STN CODE" +
					recordSeparator + "CLAS CODE"	+
					recordSeparator + "QUOTA CODE"	+
					recordSeparator + "PSGN COUNT"	+
					recordSeparator + "BRDG_STNCODE"	+
					recordSeparator + "RESVN_UPTO_STNCODE"	+
					recordSeparator + "PSGN DTLS" +
					recordSeparator + "MOBILE_NO" +
					recordSeparator + "PAYMENT_TYPE" )
			br.newLine();

			for ( int recordCount = 0; recordCount < inputTDRCPsgnDtlsList.size(); recordCount++) {
				recordString = '' ;
				psgnDtlsString = '';
				recordString = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTrainNo() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDoj() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getClassCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size()+
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode() ;
				System.out.println("For record Count " + recordCount) ;

				System.out.println("No of PSGN while writing =" + inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size()) ;
				for ( int psgnCount = 0; psgnCount < inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size(); psgnCount++) {

					psgnDtlsString += inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getPsgnName()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getPsgnGender()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getPsgnAge()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getBerthPreference()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getFoodPreference()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(recordCount).getPsgnDtlsList().get(psgnCount).getConcession()	+ psgnStatusSeparator ;

				}

				recordString = recordString + recordSeparator + psgnDtlsString +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getMobileNo() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getPaymentType() ;

				br.write(recordString)
				br.newLine();




			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	@Keyword

	public static void fillTDRCInQB(String trainNo,
			String doj,
			String srcStnCode,
			String destnStnCode,
			String clsCode,
			String quotaCode,
			String noOfPsgn,
			String boardingStnCode,
			String resvnUptoStnCode,
			String coachChoice,
			String rtsaFlag,
			String concessionFlag,
			String ticketType,
			String vipFlag)
	{
		String statusMsg = '',
		className = '',
		guiClassCode = '',
		guiQuotaCode = '',
		guiQuotaName = '',
		guiPsgnCount = '' ,
		testObject = '';

		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/span_Home'))
		WebUI.click(findTestObject('NxtGenPRS_OR/HomePage/bookingTab'))
		System.out.println('RTSA FLAG = ' + rtsaFlag)
		if (rtsaFlag.equals('Y')) {
			System.out.println('CLICKING RTSA FLAG' )

			WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_RtsaToggle'))
		}

		if (concessionFlag.equals('Y')) {
			System.out.println('CLICKING CONCESSION FLAG' )

			WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_ConcessionToggle'))
		}


		//	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_Train'))
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/div_Train'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/inputTrainNo'),trainNo)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/trainNo_Name_firstSelected'),
				GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/trainNo_Name_firstSelected'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//CustomKeywords.'reusableComponents.CustomKeywords.sendTabKeysUsingRobot'()
		System.out.println('Going to click sendTabKeysUsingRobot')
		sendTabKeysUsingRobot()
		System.out.println('Clicked sendTabKeysUsingRobot')
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		sendEscapeKeysUsingRobot()
		System.out.println('clicked sendEscapeKeysUsingRobot')
		setDateUsingJS('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/journeyDate',doj)
		System.out.println('Executed setDateUsingJS')
		//WebUI.delay(GlobalVariable.sleepTimeInSec)
		//WebUI.delay(GlobalVariable.sleepTimeInSec)
		sendTabKeysUsingRobot()
		//WebUI.delay(GlobalVariable.sleepTimeInSec)
		//WebUI.delay(GlobalVariable.sleepTimeInSec)







		if (WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)
			statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgFromStationsFetched'),FailureHandling.OPTIONAL)
		System.out.println('Station fetched Message = ' + statusMsg)
		WebUI.clearText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyFromStation'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyFromStation'), srcStnCode)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/src_FirstSelected'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.clearText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyToStation'))
		WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/jrnyToStation'), destnStnCode)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/destn_FirstSelected'))



		//For Clicking class code - start
		//WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_ClassClass'))
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/div_Class'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)

		for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/cls' + classCount
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
					break;
				}

			}
		}
		//For Clicking class code - end
		//For Clicking quota code - start
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_QuotaQuota'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int quotaCount = GlobalVariable.classCountStart; quotaCount <= GlobalVariable.maxQuota; quotaCount++) {

			testObject = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/quota' + quotaCount


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
		//For Clicking quota code - end
		if (WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgAvailabilityStatusFetched'),
		GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL) == true)

			statusMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/toastMsgAvailabilityStatusFetched'),FailureHandling.OPTIONAL)
		System.out.println('Availability Status fetched Message=' + statusMsg)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)

		//For Clicking no of psgn - start

		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/div_No of Passengers'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <= GlobalVariable.maxPsgn; psgnCount++) {
			testObject = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/noOfPsgn' + psgnCount
			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
				guiPsgnCount = WebUI.getText(findTestObject(testObject))
				System.out.println('PSGN COUNT IN WEBPAGE=' + guiPsgnCount)
				System.out.println('PSGN COUNT loop=' + psgnCount)
				if (guiPsgnCount.equals(noOfPsgn)) {
					System.out.println('CLICKING NO OF PSGN')
					WebUI.click(findTestObject(testObject)) //end of classcount
					break ;
				}

			}
		}
		//For Clicking no of psgn - end


		//For clicking ticket type - start

		//	if (!ticketType.equals('S')) {
		if (ticketType.equals('P')) {
			System.out.println('CLICKING SYSTEM DIV' )

			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/div_SystemTicketType'))
			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/span_Prebought'))



		}



		//For clicking ticket type - end


		//For clicking vip flag  - start

		if (vipFlag.equals('Y')) {
			System.out.println('CLICKING VIP FLAG' )

			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/div_VIP Flag'))
			WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_FillJrnyDtls/span_VIPYes'))


		}



		//For clicking vip flag - end




		//	sendTabKeysUsingRobot()
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('SETTING BOARDING STATION CODE WITH VALUES=' + inputBoardingStnCode)
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		if (!boardingStnCode.equals(srcStnCode)) {
			WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_BrdgStnCode'), boardingStnCode)
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/brdgStnCode_FirstSelected'))
			sendTabKeysUsingRobot()
		}
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		if (!resvnUptoStnCode.equals(destnStnCode)) {
			WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/input_ResvnUptoStnCode'), resvnUptoStnCode)
			WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/resvnUpto_FirstSelected'))
		}
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(5)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/button_Next'))



	} //End of fillTDRCUsingQuickBook










	/*
	 @Keyword
	 public static void fillPsgnDtlsInQB(List<InputSinglePsgnDtls> psgnDtlsList ) {
	 String guiGenderName = '' ;
	 //Passenger Details page - start
	 //	System.out.println('NO OF PSGN='+psgnDtlsList.size())
	 WebUI.delay(GlobalVariable.sleepTimeInSec)
	 for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <=  psgnDtlsList.size(); psgnCount++) {
	 String testPsgnNameObject = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 WebUI.click(findTestObject(testPsgnNameObject))
	 String testPsgnNameInput = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/psgnName' + psgnCount
	 //		WebUI.delay(GlobalVariable.sleepTimeInSec)
	 System.out.println('SETTING PSGN NAME AS='+psgnDtlsList.get(psgnCount-1).getPsgnName())
	 WebUI.setText(findTestObject(testPsgnNameInput), psgnDtlsList.get(psgnCount-1).getPsgnName())
	 //		WebUI.delay(GlobalVariable.sleepTimeInSec)
	 //For Clicking gender - start
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 String genderDiv = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Gender' + psgnCount
	 WebUI.click(findTestObject(genderDiv))
	 WebUI.delay(GlobalVariable.sleepTimeInSec)
	 for (int genderCount = GlobalVariable.genderCountStart; genderCount <= GlobalVariable.maxGenderCount; genderCount++) {
	 String span_gender = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/gender' + psgnCount + '_' + genderCount
	 if (WebUI.verifyElementPresent(findTestObject(span_gender), 1, FailureHandling.OPTIONAL) == true) {
	 guiGenderName = WebUI.getText(findTestObject(span_gender))
	 //		System.out.println('INPUT GENDER NAME=' + psgnDtlsList.get(psgnCount-1).getPsgnGender())
	 //		System.out.println('GENDER NAME IN GUI=' + guiGenderName)
	 if (psgnDtlsList.get(psgnCount-1).getPsgnGender().equals(guiGenderName)) {
	 //		System.out.println('CLICKING GENDER')
	 WebUI.click(findTestObject(span_gender))
	 break ;
	 }
	 }
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 }
	 //For Clicking gender - end
	 String ageDiv = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Age' + psgnCount
	 //For inputting Age - start
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 WebUI.click(findTestObject(ageDiv))
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 String inputAgeElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputAge' + psgnCount
	 WebUI.setText(findTestObject(inputAgeElement), psgnDtlsList.get(psgnCount-1).getPsgnAge())
	 //For Clicking Age - end
	 //For setting concessionCode - start
	 String inputConcessionElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/ConcessionCodePsgn' + psgnCount
	 System.out.println("Setting Concession code as " + psgnDtlsList.get(psgnCount-1).getConcession())
	 //WebUI.delay(GlobalVariable.sleepTimeInSec)
	 //WebUI.delay(GlobalVariable.sleepTimeInSec)
	 //WebUI.setText(findTestObject(inputConcessionElement),"asish")
	 WebUI.setText(findTestObject(inputConcessionElement),psgnDtlsList.get(psgnCount-1).getConcession())
	 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
	 //WebUI.delay(GlobalVariable.sleepTimeInSec)
	 //For setting concessionCode - end
	 String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
	 WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
	 } //End of fillPsgnDtlsUsingQuickBook
	 //	String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnDtlsList.size()-1
	 //	WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
	 System.out.println('COMPLETED PASSENGER DETAILS ADDITION')
	 }//End of fillPsgnDtlsInQB
	 */
	@Keyword
	public static void fillConcessionalPsgnDtlsInQB(List<InputSinglePsgnDtls> psgnDtlsList ) {

		String guiGenderName = '' ,
		testObject = '' ,
		className = '' ,
		guiClassCode = '',
		divIdCardToBeClicked ='',
		BerthPreferenceDiv = '',
		guiBerthPreference = '',
		guiIdCardText='' ;


		int scrollPosCount = 0;
		//Passenger Details page - start
		//	System.out.println('NO OF PSGN='+psgnDtlsList.size())
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int psgnCount = GlobalVariable.psgnCountStart; psgnCount <=  psgnDtlsList.size(); psgnCount++) {
			String testPsgnNameObject = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			WebUI.click(findTestObject(testPsgnNameObject))
			String testPsgnNameInput = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/psgnName' + psgnCount
			//		WebUI.delay(GlobalVariable.sleepTimeInSec)
			System.out.println('SETTING PSGN NAME AS='+psgnDtlsList.get(psgnCount-1).getPsgnName())
			WebUI.setText(findTestObject(testPsgnNameInput), psgnDtlsList.get(psgnCount-1).getPsgnName())
			//		WebUI.delay(GlobalVariable.sleepTimeInSec)
			//For Clicking gender - start
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			String genderDiv = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Gender' + psgnCount
			WebUI.click(findTestObject(genderDiv))
			//WebUI.delay(GlobalVariable.sleepTimeInSec)
			for (int genderCount = GlobalVariable.genderCountStart; genderCount <= GlobalVariable.maxGenderCount; genderCount++) {
				String span_gender = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/gender' + psgnCount + '_' + genderCount
				if (WebUI.verifyElementPresent(findTestObject(span_gender), 1, FailureHandling.OPTIONAL) == true) {
					guiGenderName = WebUI.getText(findTestObject(span_gender))
					System.out.println('INPUT GENDER NAME=' + psgnDtlsList.get(psgnCount-1).getPsgnGender())
					System.out.println('GENDER NAME IN GUI=' + guiGenderName)
					if (psgnDtlsList.get(psgnCount-1).getPsgnGender().equals(guiGenderName)) {
						System.out.println('CLICKING GENDER')
						WebUI.click(findTestObject(span_gender))
						break ;
					}
				}
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			}
			//For Clicking gender - end


			// for passenger age - start


			String ageDiv = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Age' + psgnCount
			//For inputting Age - start
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			WebUI.click(findTestObject(ageDiv))
			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			String inputAgeElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputAge' + psgnCount
			WebUI.setText(findTestObject(inputAgeElement), psgnDtlsList.get(psgnCount-1).getPsgnAge())

			// FOr passenger age - end
			//Changes for child berth option - start
			if (Integer.parseInt(psgnDtlsList.get(psgnCount-1).getPsgnAge()) <= GlobalVariable.chileAgeLimit) {
				System.out.println("\n\nChild Berth Option Entered is  =" + psgnDtlsList.get(psgnCount-1).getChildBertChoice())

				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				String childBerthDiv = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_ChildBerthOption' + psgnCount
				WebUI.click(findTestObject(childBerthDiv))
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				String span_childBerthOptionYes = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/span_ChildBerthYes' + psgnCount
				String span_childBerthOptionNo = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/span_ChildBerthNo' + psgnCount

				if (psgnDtlsList.get(psgnCount-1).getChildBertChoice().equals('Y'))
					WebUI.click(findTestObject(span_childBerthOptionYes))
				else
					WebUI.click(findTestObject(span_childBerthOptionNo))


			}

			//Changes for child berth option - end


			//For setting Berth Preference - start

			//WebUI.delay(GlobalVariable.sleepTimeInSec)

			System.out.println("Input Berth Choice =" + psgnDtlsList.get(psgnCount-1).getBerthPreference())
			if (psgnDtlsList.get(psgnCount-1).getBerthPreference().trim().length()>0) {
				BerthPreferenceDiv = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_BerthPreference' + psgnCount
				WebUI.click(findTestObject(BerthPreferenceDiv))
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				for (int berthPreferenceCount = GlobalVariable.berthPreferenceCountStart; berthPreferenceCount <= GlobalVariable.maxBerthPreferenceCount; berthPreferenceCount++) {

					String span_berthPreference = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/span_berthPref' + psgnCount + '_' + berthPreferenceCount
					System.out.println("span_berthPreference in GUI=" + span_berthPreference)
					if (WebUI.verifyElementPresent(findTestObject(span_berthPreference), 1, FailureHandling.OPTIONAL) == true) {
						guiBerthPreference = WebUI.getText(findTestObject(span_berthPreference))
						System.out.println('INPUT BERTH PREFERENCE=' + psgnDtlsList.get(psgnCount-1).getBerthPreference())
						System.out.println('GENDER NAME IN GUI=' + guiBerthPreference)
						if (guiBerthPreference.contains(psgnDtlsList.get(psgnCount-1).getBerthPreference())) {

							System.out.println('CLICKING BERTH PREFERENCE')
							WebUI.click(findTestObject(span_berthPreference))
							break ;
						}
					}
					//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				}


			}
			//For setting Berth Preference - end














			//For Clicking Age - end
			//For setting concessionCode - start

			//WebUI.delay(GlobalVariable.sleepTimeInSec)
			//WebUI.delay(GlobalVariable.sleepTimeInSec)
			//WebUI.setText(findTestObject(inputConcessionElement),"asish")
			System.out.println("Input Concession code is=" + psgnDtlsList.get(psgnCount-1).getConcession())
			if (psgnDtlsList.get(psgnCount-1).getConcession().trim().length()>0) {
				//sendTabKeysUsingRobot()
				//sendTabKeysUsingRobot()
				//sendTabKeysUsingRobot()
				String inputConcessionElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/ConcessionCodePsgn' + psgnCount
				System.out.println("Setting Concession code as " + psgnDtlsList.get(psgnCount-1).getConcession())
				//	System.out.println("inputConcessionElement=" + inputConcessionElement)
				//	WebUI.click(findTestObject(inputConcessionElement))
				//	System.out.println("clicking inputConcessionElement")
				//	sendEscapeKeysUsingRobot()


				//WebUI.clearText(findTestObject(inputConcessionElement))
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				WebUI.setText(findTestObject(inputConcessionElement),psgnDtlsList.get(psgnCount-1).getConcession())
				//	WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/ConcessionCodePsgn1'),psgnDtlsList.get(psgnCount-1).getConcession())

				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/concCode_Name_firstSelected'),
						GlobalVariable.waitForElementPresentSec,FailureHandling.OPTIONAL)
				WebUI.click(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/concCode_Name_firstSelected'))





			}
			//For setting concession code - end


			//For selecting ID Card Type - start

			System.out.println("Input ID Card type  is=" + psgnDtlsList.get(psgnCount-1).getIdCardType())
			if (psgnDtlsList.get(psgnCount-1).getIdCardType().trim().length()>0) {
				// WebUI.delay(GlobalVariable.sleepTimeInSec)
				divIdCardToBeClicked = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_IdCardType' + psgnCount
				WebUI.click(findTestObject(divIdCardToBeClicked))

				for (int idCardTypeCount = GlobalVariable.idCardCountStart; idCardTypeCount <= GlobalVariable.maxIdCardType; idCardTypeCount++) {

					//	 String span_idCardType = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/IdCardType' + psgnCount + '_' + idCardTypeCount
					String span_idCardType = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/IdCardType1' + '_' + idCardTypeCount
					//				 System.out.println("span_berthPreference in GUI=" + span_berthPreference)
					if (WebUI.verifyElementPresent(findTestObject(span_idCardType), 1, FailureHandling.OPTIONAL) == true) {
						guiIdCardText = WebUI.getText(findTestObject(span_idCardType))
						System.out.println('INPUT ID CARD=' + psgnDtlsList.get(psgnCount-1).getIdCardType())
						System.out.println('GUI ID CARD=' + guiIdCardText)
						if (guiIdCardText.contains(psgnDtlsList.get(psgnCount-1).getIdCardType())) {

							System.out.println('CLICKING ID CARD TYPE')
							WebUI.click(findTestObject(span_idCardType))
							break ;
						}

						//			 String scrollingPosition = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/IdCardType1' + psgnCount
						WebUI.scrollToElement(findTestObject(span_idCardType), 3)



					}
					//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				}






			}
			//For selecting ID Card Type - end







			//For setting Concession Doc No - start
			System.out.println("Input Concession doc no  is=" + psgnDtlsList.get(psgnCount-1).getConcessionDocNo())
			if (psgnDtlsList.get(psgnCount-1).getConcessionDocNo().trim().length()>0) {
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				String inputConcessionDocNoElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputConcessionDocNo' + psgnCount
				WebUI.waitForElementPresent(findTestObject(inputConcessionDocNoElement),
						GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
				WebUI.click(findTestObject(inputConcessionDocNoElement))


				//WebUI.delay(GlobalVariable.sleepTimeInSec)
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//	WebUI.click(findTestObject(inputConcessionDocNoElement))
				//	System.out.println("CLICKED Concession Doc No")
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				System.out.println("Setting Concession Doc No as " + psgnDtlsList.get(psgnCount-1).getConcessionDocNo())
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//WebUI.setText(findTestObject(inputConcessionElement),"asish")
				WebUI.setText(findTestObject(inputConcessionDocNoElement),psgnDtlsList.get(psgnCount-1).getConcessionDocNo())
				//	WebUI.delay(GlobalVariable.sleepTimeInSec)
				//WebUI.delay(GlobalVariable.sleepTimeInSec)
			}
			//For setting Concession Doc No - end




			//For setting Concession Authority - start
			/*
			 if (psgnDtlsList.get(psgnCount-1).getConcessionAuthority().length()>0) {
			 String inputConcessionAuthorityElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/Page_NxtGenPRS/inputConcessionAuthority' + psgnCount
			 System.out.println("Setting Concession Authority  as " + psgnDtlsList.get(psgnCount-1).getConcessionAuthority())
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.setText(findTestObject(inputConcessionElement),"asish")
			 WebUI.setText(findTestObject(inputConcessionAuthorityElement),psgnDtlsList.get(psgnCount-1).getConcessionAuthority())
			 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 }
			 */
			//For setting Concession Authority - end


			//For selecting ID Card Type - start
			/*
			 System.out.println("Input ID Card type  is=" + psgnDtlsList.get(psgnCount-1).getIdCardType())
			 if (psgnDtlsList.get(psgnCount-1).getIdCardType().trim().length()>0) {
			 WebUI.delay(GlobalVariable.sleepTimeInSec)
			 divIdCardToBeClicked = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_IdCardType' + psgnCount
			 WebUI.click(findTestObject(divIdCardToBeClicked))
			 String inputConcessionDocNoElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputConcessionDocNo' + psgnCount
			 System.out.println("Setting Concession Doc No as " + psgnDtlsList.get(psgnCount-1).getConcessionDocNo())
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.setText(findTestObject(inputConcessionElement),"asish")
			 WebUI.setText(findTestObject(inputConcessionDocNoElement),psgnDtlsList.get(psgnCount-1).getConcessionDocNo())
			 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
			 //WebUI.delay(GlobalVariable.sleepTimeInSec)
			 }
			 //For selecting ID Card Type - end
			 //	findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_IdCardType1')
			 //For setting issue Date - start
			 if (psgnDtlsList.get(psgnCount-1).getConcessionIssueDate().length()>0) {
			 String issueDateElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/Page_NxtGenPRS/ipConcessionIssueDate' + psgnCount
			 sendTabKeysUsingRobot()
			 //	WebUI.delay(GlobalVariable.sleepTimeInSec)
			 sendEscapeKeysUsingRobot()
			 setDateUsingJS(issueDateElement,psgnDtlsList.get(psgnCount-1).getConcessionIssueDate())
			 sendTabKeysUsingRobot()
			 }
			 //For setting Issue Date - end
			 //For clicking eligible class - start
			 //	findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/Page_NxtGenPRS/eligibleClass1_1')
			 if (psgnDtlsList.get(psgnCount-1).getEligibleClass().length()>0) {
			 String divEligibleClass = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/Page_NxtGenPRS/div_EligibleClass' + psgnCount
			 WebUI.click(findTestObject(divEligibleClass))
			 WebUI.delay(GlobalVariable.sleepTimeInSec)
			 String spanEligibleClassPsgnWise = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/Page_NxtGenPRS/eligibleClass' + psgnCount
			 for (int classCount = GlobalVariable.classCountStart; classCount <= GlobalVariable.maxCls; classCount++) {
			 testObject = spanEligibleClassPsgnWise + '_'  + classCount
			 //			if (WebUI.verifyElementPresent(findTestObject(testObject), 1, FailureHandling.OPTIONAL) == true) {
			 className = WebUI.getText(findTestObject(testObject))
			 System.out.println('CLASS NAME IS=' + className)
			 guiClassCode = className.split('-')[0].trim()
			 System.out.println('CLASS CODE IS=' + guiClassCode)
			 System.out.println('INPUT CLASS CODE IS=' + psgnDtlsList.get(psgnCount-1).getEligibleClass())
			 if (guiClassCode.equals(psgnDtlsList.get(psgnCount-1).getEligibleClass())) {
			 System.out.println('CLICKING CLASS CODE')
			 WebUI.click(findTestObject(testObject))
			 break;
			 }
			 if (classCount == 4) {
			 System.out.println('Scrolling eligible class')
			 scrollPosCount = classCount
			 String scrollingPosition = spanEligibleClassPsgnWise + '_'  + scrollPosCount
			 WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
			 }
			 }
			 }
			 //For clicking eligible class - end
			 */
			String scrollingPosition = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnCount
			WebUI.scrollToElement(findTestObject(scrollingPosition), 3)


		}
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//WebUI.delay(GlobalVariable.sleepTimeInSec)

		//	findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputConsessionDocNo1')

		//For setting concessionCode - end



		//	String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_PsgnDtls/div_Psgn' + psgnDtlsList.size()-1
		//	WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
		System.out.println('COMPLETED PASSENGER DETAILS ADDITION')

	}//End of fillConcessionalPsgnDtlsInQB()


	@Keyword
	public static void fillAddDtlsClickNextInPsgnDtlPageQB(String concessionFlag,String mobileNo , String paymentType) {

		String guipaymentType = '' ;
		//	WebUI.scrollToElement(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Mobile No'), 3)

		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_PaymentType'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		for (int paymentTypeCount = GlobalVariable.paymentCountStart; paymentTypeCount <= GlobalVariable.maxPaymentCount; paymentTypeCount++) {
			String span_paymentType = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/span_paymentType' + paymentTypeCount
			if (WebUI.verifyElementPresent(findTestObject(span_paymentType), 1, FailureHandling.OPTIONAL) == true) {
				guipaymentType = WebUI.getText(findTestObject(span_paymentType))
				System.out.println('PAYMENT TYPE=' + paymentType)
				if (guipaymentType.equals(paymentType)) {
					System.out.println('CLICKING PAYMENT TYPE')
					WebUI.click(findTestObject(span_paymentType))
					break ;
				}

			}
		}


		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Mobile No'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('GOING TO SET MOBILE NO')
		//		sendTabKeysUsingRobot()
		//		System.out.println('TAB KEY SENT')
		//		WebUI.waitForElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'),
		//			GlobalVariable.waitForAvailabilitySec,FailureHandling.OPTIONAL)
		//WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.scrollToElement(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Mobile No'), 3)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('CLICKING THE MOBILE NO')
		//	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'))
		//	System.out.println('CLICKED THE MOBILE NO')
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//		System.out.println('SET THE MOBILE NO start' + mobileNo)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.waitForElementVisible(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'), 10)
		//	System.out.println("ELEMENT NOW VISIBLE")
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebElement element = WebUiCommonHelper.findWebElement(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'),30)
		//	WebUI.executeJavaScript("arguments[0].click", Arrays.asList(element))
		//	setDateUsingJS('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo',"4455445")
		//WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'), mobileNo)

		if (concessionFlag.equals('Y')) {
			WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/input_ConcessionalMobileNo'),
					GlobalVariable.waitForElementPresent,FailureHandling.OPTIONAL)
			WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/input_ConcessionalMobileNo'), mobileNo)
		}
		else {
			WebUI.waitForElementPresent(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/input_nonConcessionalMobileNo'),
					GlobalVariable.waitForElementPresent,FailureHandling.OPTIONAL)

			WebUI.setText(findTestObject('Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/input_nonConcessionalMobileNo'), mobileNo)
		}



		//	System.out.println('SET THE MOBILE NO end' + mobileNo)
		//	//WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'), "4454455444",FailureHandling.OPTIONAL)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('TRYING SECOND TIME SETTING MOBILE NO' + mobileNo)
		//	WebUI.setText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/inputMobileNo'), "1234567890",FailureHandling.OPTIONAL)

		//	System.out.println('COMPLETED SETTING THE MOBILE NO' + mobileNo)
		//	WebUI.scrollToElement(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/div_Mobile No'), 3)
		//	WebUI.scrollToElement(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/button_NextInPsgnDtls'), 3)
		//	System.out.println('GOING 1 TO CLICK NEXT BUTTON ')
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('GOING 2 TO CLICK NEXT BUTTON ')
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_PsgnDtls/button_NextInPsgnDtls'))
		//	System.out.println('CLICKED NEXT BUTTON ')
	}




	public static  getBookedTktDtlsClickNextQB(String testCaseId , String noOfPsgn,BookedTktPnrDtls bookedTktPnrDtls) {
		String bookingSummaryTxt = '' ,
		disptrainNo = '' ,
		dispDOJ = '' ,
		dispCls = '' ,
		dispQuota = '' ,
		dispJrnyFrom = '' ,
		dispJrnyTo = '' ,
		cashToBeCollected = '' ,
		voucherFare = '' ,
		totalFare = '' ,
		generatedPNR = '' ,
		psgnSrlNoElement = '' ,
		psgnNameElement = '' ,
		psgnAgeElement = '' ,
		psgnGenderElement = '' ,
		psgnQuotaElement = '' ,
		psgnBookingStatusElement = '' ,
		psgnBookingDtlsElement = '' ,
		psgnCurStatusElement = '' ,
		psgnCurDtlsElement = '' ,
		psgnNameTxt = '' ,
		psgnSrlNoTxt = '';

		//	BookedTktPnrDtls bookedTktPnrDtls = new BookedTktPnrDtls() ;
		BookedTktFareDtls bookedTktFareDtls = new BookedTktFareDtls();
		BookedTktSinglePsgnDtls bookedTktSinglePsgnDtls ;
		List<BookedTktSinglePsgnDtls> bookedTktPsgnDtlsList =  new ArrayList()

		bookedTktPnrDtls.setTestCaseId(testCaseId)

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

		System.out.println("Booked Tkt Details without before adding in object")

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
		//	bookedTktPnrDtls.setCashToBeCollected(cashToBeCollected)

		bookedTktFareDtls.setCashToBeCollected(cashToBeCollected)
		bookedTktFareDtls.setVoucherFare(voucherFare)
		bookedTktFareDtls.setTotalFare(totalFare)

		bookedTktPnrDtls.setPnrNo(generatedPNR)



		//To add booked psgn Details in List - start

		for (int dispPsgnCount = 1 ; dispPsgnCount <= Integer.parseInt(noOfPsgn); dispPsgnCount++) {
			bookedTktSinglePsgnDtls = new BookedTktSinglePsgnDtls()

			//System.out.println('Single Psgn  =' + singlePsgnDtlsString[ipPsgnCount])
			//psgnAttribute = singlePsgnDtlsString[ipPsgnCount].split("%")
			//	System.out.println('\n\nPassenger Name is =' + psgnAttribute[0])

			psgnSrlNoElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnSrlNo' + dispPsgnCount


			psgnNameElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnName' + dispPsgnCount
			psgnAgeElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnAge'  + dispPsgnCount
			psgnGenderElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnGender'  + dispPsgnCount
			psgnQuotaElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnQuota' + dispPsgnCount
			psgnBookingStatusElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnBookingStatus' + dispPsgnCount
			psgnBookingDtlsElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnBookingDtls' + dispPsgnCount

			psgnCurStatusElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnCurStatus' + dispPsgnCount
			psgnCurDtlsElement = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/psgnCurDtls' + dispPsgnCount


			//	WebUI.delay(GlobalVariable.sleepTimeInSec)
			psgnSrlNoTxt =  WebUI.getText(findTestObject(psgnSrlNoElement))
			System.out.println('\n\nPassenger count in output tkt =' + psgnSrlNoTxt)
			psgnNameTxt =  WebUI.getText(findTestObject(psgnNameElement))
			System.out.println('\n\nPassenger Name is =' + psgnNameTxt)

			bookedTktSinglePsgnDtls.setPsgnSrlNo(psgnSrlNoTxt)
			bookedTktSinglePsgnDtls.setPsgnName(WebUI.getText(findTestObject(psgnNameElement)))
			bookedTktSinglePsgnDtls.setPsgnAge(WebUI.getText(findTestObject(psgnAgeElement)))
			bookedTktSinglePsgnDtls.setPsgnGender(WebUI.getText(findTestObject(psgnGenderElement)))

			bookedTktSinglePsgnDtls.setPsgnQuota(WebUI.getText(findTestObject(psgnQuotaElement)))
			bookedTktSinglePsgnDtls.setPsgnBookingStatus(WebUI.getText(findTestObject(psgnBookingStatusElement)))
			bookedTktSinglePsgnDtls.setPsgnBookingDtls(WebUI.getText(findTestObject(psgnBookingDtlsElement)))
			bookedTktSinglePsgnDtls.setPsgnCurStatus(WebUI.getText(findTestObject(psgnCurStatusElement)))
			//error here - asish
			bookedTktSinglePsgnDtls.setPsgnCurDtls(WebUI.getText(findTestObject(psgnCurDtlsElement)))


			//singlePsgnDtls.setPsgnGender(psgnAttribute[1])
			//singlePsgnDtls.setPsgnAge(psgnAttribute[2])
			//singlePsgnDtls.setPsgnBerthPreference(psgnAttribute[3])
			//singlePsgnDtls.setPsgnFoodPreference(psgnAttribute[4])
			bookedTktPsgnDtlsList.add(bookedTktSinglePsgnDtls)
		}


		bookedTktPnrDtls.setBookedTktFareDtls(bookedTktFareDtls)
		bookedTktPnrDtls.setPsgnDetailsList(bookedTktPsgnDtlsList)


		//To add booked psgn Details in List - end

		//To display added psgn list - start


		System.out.println('\n\nBooked PNR Details are as follows \n\n' )

		System.out.println( bookedTktPnrDtls.getTrainNo() + '#'
				+ bookedTktPnrDtls.getDoj() +  '#'
				+ bookedTktPnrDtls.getJrnyCls() +  '#'
				+ bookedTktPnrDtls.getQuota() + '#'
				+ bookedTktPnrDtls.getFromStnCodeName()+ '#'
				+ bookedTktPnrDtls.getToStnCodeName()+ '#'
				+ bookedTktPnrDtls.getBookedTktFareDtls().getCashToBeCollected()+ '#'
				+ bookedTktPnrDtls.getBookedTktFareDtls().getVoucherFare()+ '#'
				+ bookedTktPnrDtls.getBookedTktFareDtls().getTotalFare()+ '#'
				+ bookedTktPnrDtls.getPnrNo() + '#'
				)

		System.out.println('\n\nBooked Tkt PSGN Details are as follows \n\n' )
		for (int dispPsgnCount = 0 ; dispPsgnCount < bookedTktPsgnDtlsList.size(); dispPsgnCount++) {
			//pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnName()+

			bookedTktPnrDtls.setPsgnDetailsList(bookedTktPsgnDtlsList)


			System.out.println( bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnSrlNo()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnName()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnAge()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnGender()

					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnBookingDtls()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnBookingStatus()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnCurDtls()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnCurStatus()


					)

			//System.out.println( bookedTktSinglePsgnDtls.getPsgnName() + '#'
			//	+ bookedTktSinglePsgnDtls.getPsgnAge() +  '#'
			//	 )



		}



		System.out.println('\nSCROLLING TILL PROCEED\n' )
		String scrollingPosition = 'Object Repository/NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_Proceed'

		WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		System.out.println('\n\nCLICKING PROCEED \n\n' )
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_Proceed'))
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	System.out.println('\n\nCLICKING OK AFTER FLUSH \n\n' )
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_okAfterFlush'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)

		//	return bookedTktPnrDtls

		//To display added psgn list - end


		//Get output Details - end


	} //End of getBookedTktDtlsClickNextQB()



	public static BookedTktPnrDtls getBookedTktDtlsClickFlushQB(String testCaseId , String noOfPsgn) {
		String bookingSummaryTxt = '' ,
		disptrainNo = '' ,
		dispDOJ = '' ,
		dispCls = '' ,
		dispQuota = '' ,
		dispJrnyFrom = '' ,
		dispJrnyTo = '' ,
		cashToBeCollected = '' ,
		voucherFare = '' ,
		totalFare = '' ,
		generatedPNR = '' ,
		psgnSrlNoElement = '' ,
		psgnNameElement = '' ,
		psgnAgeElement = '' ,
		psgnGenderElement = '' ,
		psgnQuotaElement = '' ,
		psgnBookingStatusElement = '' ,
		psgnBookingDtlsElement = '' ,
		psgnCurStatusElement = '' ,
		psgnCurDtlsElement = '' ,
		psgnNameTxt = '' ;

		BookedTktPnrDtls bookedTktPnrDtls = new BookedTktPnrDtls() ;
		BookedTktSinglePsgnDtls bookedTktSinglePsgnDtls ;
		List<BookedTktSinglePsgnDtls> bookedTktPsgnDtlsList =  new ArrayList()

		bookedTktPnrDtls.setTestCaseId(testCaseId)

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

		System.out.println("Booked Tkt Details without before adding in object")

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
		bookedTktPnrDtls.setcashToBeCollected(cashToBeCollected)
		bookedTktPnrDtls.setVoucherFare(voucherFare)
		bookedTktPnrDtls.setTotalFare(totalFare)
		bookedTktPnrDtls.setPnrNo(generatedPNR)



		//To add booked psgn Details in List - start

		for (int dispPsgnCount = 1 ; dispPsgnCount <= Integer.parseInt(noOfPsgn); dispPsgnCount++) {
			bookedTktSinglePsgnDtls = new BookedTktSinglePsgnDtls()

			//System.out.println('Single Psgn  =' + singlePsgnDtlsString[ipPsgnCount])
			//psgnAttribute = singlePsgnDtlsString[ipPsgnCount].split("%")
			//	System.out.println('\n\nPassenger Name is =' + psgnAttribute[0])

			psgnSrlNoElement = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/psgnSrlNo' + dispPsgnCount
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
			//error here - asish
			bookedTktSinglePsgnDtls.setPsgnCurDtls(WebUI.getText(findTestObject(psgnCurDtlsElement)))


			//singlePsgnDtls.setPsgnGender(psgnAttribute[1])
			//singlePsgnDtls.setPsgnAge(psgnAttribute[2])
			//singlePsgnDtls.setPsgnBerthPreference(psgnAttribute[3])
			//singlePsgnDtls.setPsgnFoodPreference(psgnAttribute[4])
			bookedTktPsgnDtlsList.add(bookedTktSinglePsgnDtls)
		}



		bookedTktPnrDtls.setPsgnDetailsList(bookedTktPsgnDtlsList)


		//To add booked psgn Details in List - end

		//To display added psgn list - start


		System.out.println('\n\nBooked PNR Details are as follows \n\n' )

		System.out.println( bookedTktPnrDtls.getTrainNo() + '#'
				+ bookedTktPnrDtls.getDoj() +  '#'
				+ bookedTktPnrDtls.getClass() +  '#'
				+ bookedTktPnrDtls.getQuota() + '#'
				+ bookedTktPnrDtls.getFromStnCodeName()+ '#'
				+ bookedTktPnrDtls.getToStnCodeName()+ '#'
				+ bookedTktPnrDtls.getcashToBeCollected() + '#'
				+ bookedTktPnrDtls.getVoucherFare() + '#'
				+ bookedTktPnrDtls.getTotalFare() + '#'
				+ bookedTktPnrDtls.getPnrNo() + '#'
				)

		System.out.println('\n\nBooked Tkt PSGN Details are as follows \n\n' )
		for (int dispPsgnCount = 0 ; dispPsgnCount < bookedTktPsgnDtlsList.size(); dispPsgnCount++) {
			//pnrDetailsList.get(i).psgnDetailsList.get(psgnCount).getPsgnName()+

			bookedTktPnrDtls.setPsgnDetailsList(bookedTktPsgnDtlsList)


			System.out.println( bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnSrlNo()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnName()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnAge()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnGender()

					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnBookingDtls()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnBookingStatus()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnCurDtls()
					+ '#' + bookedTktPnrDtls.getPsgnDetailsList().get(dispPsgnCount).getPsgnCurStatus()


					)

			//System.out.println( bookedTktSinglePsgnDtls.getPsgnName() + '#'
			//	+ bookedTktSinglePsgnDtls.getPsgnAge() +  '#'
			//	 )



		}

		System.out.println('\nSCROLLING TILL FLUSH\n' )
		String scrollingPosition = 'Object Repository/NxtGenPRS_OR/QB_JourneyDtls/QB_DispTktDtls/button_Flush'

		WebUI.scrollToElement(findTestObject(scrollingPosition), 3)
		System.out.println('\n\nFLUSHING THE BOOKED TICKET \n\n' )
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_Flush'))
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		System.out.println('\n\nCLICKING OK AFTER FLUSH \n\n' )
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		WebUI.click(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/button_okAfterFlush'))
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)

		return bookedTktPnrDtls

		//To display added psgn list - end


		//Get output Details - end


	} //End of getBookedTktDtlsClickFlushQB()






	@Keyword

	public static void writeBookedTktDtls (String outFileWithoutExtn , List<BookedTktPnrDtls> bookedTktPnrDtlsList) {
		//	String timenow = null;
		String psgnStatusString = null;
		char psgnRecordSeparator ='#';
		char psgnStatusSeparator = '%';
		char recordSeparator = ',';
		String recordString = '';
		String psgnDtlsString = '';
		//	timenow = getDateTime();
		String outFile = outFileWithoutExtn + ".csv" ;
		File file = new File(".\\UserDataFiles\\outfiles\\" + outFile)
		FileWriter fr = null;
		BufferedWriter br = null;
		try{
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			br.write("TEST CASE NAME" +
					recordSeparator + "TRAINNo"	+
					recordSeparator + "JRNY DATE(MM-DD-YYYY)"	+
					recordSeparator + "SOURCE STN CODE" +
					recordSeparator + "DESTN STN CODE" +
					recordSeparator + "CLAS CODE"	+
					recordSeparator + "QUOTA CODE"	+
					recordSeparator + "PSGN COUNT"	+
					recordSeparator + "BRDG_STNCODE"	+
					recordSeparator + "RESVN_UPTO_STNCODE"	+
					recordSeparator + "PSGN DTLS" +
					recordSeparator + "MOBILE_NO" +
					recordSeparator + "PAYMENT_TYPE" )
			br.newLine();

			for ( int recordCount = 0; recordCount < bookedTktPnrDtlsList.size(); recordCount++) {
				recordString = '' ;
				psgnDtlsString = '';
				recordString = bookedTktPnrDtlsList.get(recordCount).getTestCaseId() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getTrainNo() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getDoj() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getQuota() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getFromStnCodeName() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getToStnCodeName() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getJrnyCls() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getCashToBeCollected() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getVoucherFare() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare() +

						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getPnrNo() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getErrorMsg() ;

				System.out.println("For record Count " + recordCount) ;

				System.out.println("No of PSGN while writing =" + bookedTktPnrDtlsList.get(recordCount).psgnDetailsList.size()) ;
				for ( int psgnCount = 0; psgnCount < bookedTktPnrDtlsList.get(recordCount).psgnDetailsList.size(); psgnCount++) {

					psgnDtlsString += bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnSrlNo()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnName()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnAge()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnGender()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnQuota()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingStatus()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingDtls()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurStatus()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurDtls()	+ psgnStatusSeparator ;

				}

				recordString = recordString + recordSeparator + psgnDtlsString +
						recordSeparator ;

				br.write(recordString)
				br.newLine();




			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // End of writeBookedTktDtls()


	@Keyword

	public static void writeErrorLog(String outFileWithoutExtn , testCaseId ) {

		String outFile = outFileWithoutExtn + ".err" ;
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(".\\UserDataFiles\\outfiles\\" + outFile, true)  //Set true for append mode
				);

		writer.write('Error Occured for ' +  testCaseId);
		writer.newLine();
		writer.close();
	} //End of function writeErrorLog



	@Keyword
	public static void preConditionsInfo(String preConditions) throws IOException {
		String inpString;
		//System.out.print("\nCalling capcha dialog joption pane");
		//inpString = JOptionPane.showInputDialog(preConditions);
		final JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, preConditions, "Question" ,
				JOptionPane.INFORMATION_MESSAGE);
		//return inpString;
	}

	@Keyword
	public static String takePartialScreenShot(String fileName, TestObject to , int timeout) {
		System.out.println("Entered function 1" ) ;
		WebDriver driver = DriverFactory.getWebDriver()



		WebElement ele = WebUiCommonHelper.findWebElement(to, timeout)


		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		System.out.println("Entered function 2" ) ;
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
				eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		System.out.println("Entered function 3" ) ;
		// Copy the element screenshot to disk
		File screenshotLocation = new File(fileName);
		System.out.println("Entered function 4" ) ;
		FileUtils.copyFile(screenshot, screenshotLocation);
		System.out.println("Entered function 5" ) ;

	}



	@Keyword
	public static String takeCompleteScreenShot(String fileName) {
		System.out.println("Taking Complete Screenshot" ) ;
		WebUI.takeScreenshot(fileName)


		System.out.println("Taken Complete Screenshot" ) ;

	}

	@Keyword

	public List<InputTDRCPsgnDtls> readInputConcessionalTDRCPsgnDtls (String fileName) {
		int recordCount = 0,
		storedRecordCount = 0;
		String psgnStatusString = null ,
		recordString = '' ,
		psgnDtlsString = '';
		char psgnRecordSeparator ='#';
		char psgnStatusSeparator = '%';
		char fareDtlsSeparator = '%';
		char recordSeparator = ',';

		InputTDRCPsgnDtls inputTDRCPsgnDtls  = new InputTDRCPsgnDtls();
		InputSinglePsgnDtls inputSinglePsgnDtls = new InputSinglePsgnDtls();
		InputFareDtls inputFareDtls = new InputFareDtls();
		List<InputSinglePsgnDtls> psgnDtlsList = new ArrayList()
		List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList = new ArrayList()


		try {
			//	BufferedReader br = Files.newReader(pathToFile,StandardCharsets.US_ASCII)
			InputStream fis=new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {

				if (line[0].equals('*')) {
					line = br.readLine()
					//	recordCount ++ ;
					//		System.out.println("Skipping Record " + recordCount ) ;
					continue ;
				}


				inputTDRCPsgnDtls  = new InputTDRCPsgnDtls();
				inputFareDtls = new InputFareDtls();
				psgnDtlsList = new ArrayList()
				System.out.println("Line Read=" + line) ;
				String[] attributes = line.split(",");
				inputTDRCPsgnDtls.setTestCaseId(attributes[0])
				inputTDRCPsgnDtls.setTrainNo(attributes[1])
				inputTDRCPsgnDtls.setDoj(attributes[2])
				inputTDRCPsgnDtls.setSrcStnCode(attributes[3].trim())
				inputTDRCPsgnDtls.setDestnStnCode(attributes[4].trim())
				inputTDRCPsgnDtls.setClassCode(attributes[5].trim())
				inputTDRCPsgnDtls.setQuotaCode(attributes[6].trim())

				inputTDRCPsgnDtls.setPsgnCount(attributes[7])

				inputTDRCPsgnDtls.setBoardingStnCode(attributes[8].trim())
				inputTDRCPsgnDtls.setResvnUptoStnCode(attributes[9].trim())
				//String[] psgnAttributes = attributes[10].split("%");

				String[] singlePsgnDtlsString =  attributes[10].split("#");
				//	System.out.println("All Passenger Details=" + singlePsgnDtlsString) ;
				//	System.out.println("No of Passenger=" + singlePsgnDtlsString.size()) ;
				for (int psgnCount = 0 ; psgnCount < singlePsgnDtlsString.size(); psgnCount++) {

					inputSinglePsgnDtls = new InputSinglePsgnDtls();

					String[] psgnAttributes = singlePsgnDtlsString[psgnCount].split("%",-1);
					//System.out.println("No of psgn Attributes =" + psgnAttributes.length) ;
					System.out.println("No of psgn Attributes in size =" + psgnAttributes.size()) ;
					//	System.out.println("Single Passenger Name =" + psgnAttributes[0]) ;
					inputSinglePsgnDtls.setPsgnName(psgnAttributes[0])
					inputSinglePsgnDtls.setPsgnGender(psgnAttributes[1])
					inputSinglePsgnDtls.setPsgnAge(psgnAttributes[2])
					inputSinglePsgnDtls.setBerthPreference(psgnAttributes[3])
					inputSinglePsgnDtls.setFoodPreference(psgnAttributes[4])
					if (psgnAttributes.length > 5)
					{
						inputSinglePsgnDtls.setNationality(psgnAttributes[5])
						inputSinglePsgnDtls.setIdCardType(psgnAttributes[6])
						inputSinglePsgnDtls.setIdCardNo(psgnAttributes[7])
						inputSinglePsgnDtls.setConcession(psgnAttributes[8])
						inputSinglePsgnDtls.setConcessionDocNo(psgnAttributes[9])
						inputSinglePsgnDtls.setConcessionAuthority(psgnAttributes[10])
						inputSinglePsgnDtls.setConcessionIssueDate(psgnAttributes[11])
						inputSinglePsgnDtls.setEligibleClass(psgnAttributes[12])
						inputSinglePsgnDtls.setPregnantLadyChoice(psgnAttributes[13])
						inputSinglePsgnDtls.setChildBertChoice(psgnAttributes[14])
						inputSinglePsgnDtls.setZoneCode(psgnAttributes[15])
						inputSinglePsgnDtls.setStationCode(psgnAttributes[16])
						inputSinglePsgnDtls.setBillUnitNo(psgnAttributes[17])

					}
					psgnDtlsList.add(inputSinglePsgnDtls)
				}

				inputTDRCPsgnDtls.setMobileNo(attributes[11])
				inputTDRCPsgnDtls.setPaymentType(attributes[12].trim())
				inputTDRCPsgnDtls.setRtsaFlag(attributes[13])
				inputTDRCPsgnDtls.setConcessionalFlag(attributes[14])
				inputTDRCPsgnDtls.setTicketType(attributes[15])
				inputTDRCPsgnDtls.setVipFlag(attributes[16])
				inputTDRCPsgnDtls.setRecNo(attributes[17])

				//Set Input Fare Details - start

				String[] fareDtlsString =  attributes[18].split("%",-1)

				System.out.println("\nNo of fare Dtls component using length=" + fareDtlsString.length )
				/*
				 //	System.out.println("\nNo of fare Dtls component using size=" + fareDtlsString.size() )
				 for (int fareComponentCount = 0 ; fareComponentCount < fareDtlsString.length; fareComponentCount++) {
				 System.out.println(" fareDtlsString " + fareComponentCount + "," + fareDtlsString[fareComponentCount] ) 
				 }
				 */
				inputFareDtls.setCashToBeCollected(fareDtlsString[0])
				inputFareDtls.setVoucherFare(fareDtlsString[1])
				inputFareDtls.setTotalFare(fareDtlsString[2])

				//Added for change in data format provided by DW - start

				//		(ENRESVFEE%PILGRIMTAX%RTSACHRG%BEDROLLCHRG%TPCHRG%SPLCHRG1%TOTAL)
				if (fareDtlsString.length > 4)
				{
					inputFareDtls.setBaseFare(fareDtlsString[0])
					inputFareDtls.setSupCharge(fareDtlsString[1])
					inputFareDtls.setMelaCharge(fareDtlsString[2])
					inputFareDtls.setSafetyCharge(fareDtlsString[3])
					inputFareDtls.setTiruCharge(fareDtlsString[4])
					inputFareDtls.setClericalCharge(fareDtlsString[5])
					inputFareDtls.setResFee(fareDtlsString[6])
					inputFareDtls.setDynFare(fareDtlsString[7])
					inputFareDtls.setEnresFeee(fareDtlsString[8])
					inputFareDtls.setPilgrimTax(fareDtlsString[9])
					inputFareDtls.setRtsaCharge(fareDtlsString[10])
					inputFareDtls.setBedRollCharge(fareDtlsString[11])
					inputFareDtls.setTourPackageCharge(fareDtlsString[12])
					inputFareDtls.setSplCharge1(fareDtlsString[13])
					inputFareDtls.setCeiledGST(fareDtlsString[14])
					inputFareDtls.setTotalFare(fareDtlsString[15])

				}

				// Added for change in data format provided by DW - end





				// Set input Fare Details - end
				System.out.println("\nTotal fare before adding in object=" + inputFareDtls.getTotalFare())

				inputTDRCPsgnDtls.setInputFareDtls(inputFareDtls)
				System.out.println("\nTotal fare after adding in object inputTDRCPsgnDtls=" + inputFareDtls.getTotalFare())
				inputTDRCPsgnDtls.setPsgnDtlsList(psgnDtlsList)
				inputTDRCPsgnDtlsList.add(inputTDRCPsgnDtls)

				System.out.println("\nTotal fare after adding in object list inputTDRCPsgnDtlsList=" + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getTotalFare())
				recordCount ++ ;
				line = br.readLine();
			}

			System.out.println("\nDetails stored in the object")

			//		System.out.println("No of Passenger in object=" + psgnDtlsList.size()) ;

			for (  storedRecordCount = 0; storedRecordCount < inputTDRCPsgnDtlsList.size(); storedRecordCount++) {
				recordString = '' ;
				psgnDtlsString = '';
				recordString = inputTDRCPsgnDtlsList.get(storedRecordCount).getTestCaseId() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getTrainNo() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getDoj() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getSrcStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getDestnStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getClassCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getQuotaCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).psgnDtlsList.size()+
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getBoardingStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getResvnUptoStnCode() ;
				System.out.println("For record Count " + storedRecordCount) ;
				//	Fare Details(cashToBeCollected%voucherFare%Total Fare%)	Remarks(Keep PNR)	DUMMY



				//			System.out.println("No of PSGN while writing =" + inputTDRCPsgnDtlsList.get(recordCount).psgnDtlsList.size()) ;
				for ( int psgnCount = 0; psgnCount < inputTDRCPsgnDtlsList.get(storedRecordCount).psgnDtlsList.size(); psgnCount++) {

					psgnDtlsString += inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getPsgnName()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getPsgnGender()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getPsgnAge()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getBerthPreference()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getFoodPreference()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getNationality()	+ psgnStatusSeparator +

							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getIdCardType() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getIdCardNo() + psgnStatusSeparator +

							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getConcession()	+ psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getConcessionDocNo() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getConcessionAuthority() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getConcessionIssueDate() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getEligibleClass() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getPregnantLadyChoice() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getChildBertChoice() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getZoneCode() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getStationCode() + psgnStatusSeparator +
							inputTDRCPsgnDtlsList.get(storedRecordCount).getPsgnDtlsList().get(psgnCount).getBillUnitNo() + psgnStatusSeparator + psgnRecordSeparator



				}

				recordString = recordString + recordSeparator + psgnDtlsString +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getMobileNo() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getPaymentType() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getRtsaFlag() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getConcessionalFlag() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getTicketType() +
						recordSeparator + inputTDRCPsgnDtlsList.get(storedRecordCount).getVipFlag() +
						recordSeparator +inputTDRCPsgnDtlsList.get(storedRecordCount).getRecNo() +
						recordSeparator +inputTDRCPsgnDtlsList.get(storedRecordCount).getInputFareDtls().getTotalFare() ;

				System.out.println("RecordString read = " + recordString) ;





			}

			//To get total fare stored recordwise - start

			System.out.println("TOTA FARED STORED OBJECTWISE IS GIVEN BELOW ") ;
			for (  int count = 0; count < inputTDRCPsgnDtlsList.size(); count++) {

				System.out.println("Fare in record " + count + "=" + inputTDRCPsgnDtlsList.get(count).getInputFareDtls().getTotalFare() ) ;


			}
			//To get total fare stored recordwise - end







		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return inputTDRCPsgnDtlsList;
	}




	public static FailedBookingDtls checkErrorInConfPayment()
	{
		FailedBookingDtls failedBookingDtls = new FailedBookingDtls()

		String errorMsg='' ,
		headerMsg='';
		WebUI.delay(GlobalVariable.sleepTimeInSec)
		//	WebUI.delay(GlobalVariable.sleepTimeInSec)
		if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/BookingFailedHeader'),GlobalVariable.waitForElementVisibleSec,FailureHandling.OPTIONAL)) {
			headerMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/BookingFailedHeader'),
					FailureHandling.CONTINUE_ON_FAILURE)

			System.out.println('Error Header inside function=' + headerMsg)


			//		if (WebUI.verifyElementPresent(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/BookingFailedReason'),GlobalVariable.waitForElementVisibleSec,FailureHandling.OPTIONAL)) {
			errorMsg = WebUI.getText(findTestObject('NxtGenPRS_OR/BookingPage/QB_JourneyDtls/QB_DispTktDtls/BookingFailedReason'),
					FailureHandling.CONTINUE_ON_FAILURE)

			System.out.println('Error Message inside function=' + errorMsg)
		}
		failedBookingDtls.setHeader(headerMsg)
		failedBookingDtls.setErrorMsg(errorMsg)
		return failedBookingDtls;

	} //End of checkErrorInConfPayment


	@Keyword

	public static void compareInputAndBookedTktDtls (String outFileWithoutExtn , List<InputTDRCPsgnDtls> inputTDRCPsgnDtlsList,List<BookedTktPnrDtls> bookedTktPnrDtlsList,long startTime) {
		//	String timenow = null;
		String psgnStatusString = null;
		char psgnRecordSeparator ='#';
		char psgnStatusSeparator = '%';
		char recordSeparator = ',';
		String recordString = '';
		String psgnDtlsString = '';
		//	timenow = getDateTime();
		String outFile = outFileWithoutExtn + ".csv" ;
		String logFilename = ".\\UserDataFiles\\outfiles\\" + outFileWithoutExtn + ".log" ;
		String matchingRecordsFilename = ".\\UserDataFiles\\outfiles\\" + outFileWithoutExtn + "MATCHRECS" + ".csv" ;
		String skipRecordsFilename = ".\\UserDataFiles\\outfiles\\" + outFileWithoutExtn + "SKIPRECS" + ".csv" ;

		File skipRecordsFile = new File(skipRecordsFilename);
		FileWriter fr_skipRecsFile = null;
		BufferedWriter br_skipRecsFile = null;


		File matchRecsFile = new File(matchingRecordsFilename);
		FileWriter fr_matchRecsFile = null;
		BufferedWriter br_matchRecsFile = null;

		File logFile = new File(logFilename);
		FileWriter fr_logfile = null;
		BufferedWriter br_logFile = null;
		int 	recordCountSkippedDueToException=0,
		recordCountMatchingFare = 0,
		recordCountNotMatchingFare = 0;


		File file = new File(".\\UserDataFiles\\outfiles\\" + outFile)

		FileWriter fr = null;
		BufferedWriter br = null;
		try{


			fr_matchRecsFile = new FileWriter(matchRecsFile);
			br_matchRecsFile = new BufferedWriter(fr_matchRecsFile);

			fr_logfile = new FileWriter(logFile);
			br_logFile = new BufferedWriter(fr_logfile);

			fr_skipRecsFile = new FileWriter(skipRecordsFile);
			br_skipRecsFile = new BufferedWriter(fr_skipRecsFile);

			fr = new FileWriter(file);
			br = new BufferedWriter(fr);




			System.out.println("No of records in inputTDRCPsgnDtlsList=" + inputTDRCPsgnDtlsList.size()) ;

			System.out.println("No of records in bookedTktPnrDtlsList=" + bookedTktPnrDtlsList.size()) ;



			br.write(
					"TEST CASEID" +
					recordSeparator + "REC NO"	+
					recordSeparator + "TRAINNo"	+
					recordSeparator + "JRNY DATE(MM-DD-YYYY)"	+
					recordSeparator + "SOURCE STN CODE" +
					recordSeparator + "DESTN STN CODE" +
					recordSeparator + "CLS CODE"	+
					recordSeparator + "QUOTA CODE"	+
					recordSeparator + "PSGN COUNT"	+
					recordSeparator + "BRDG_STNCODE"	+
					recordSeparator + "RESVN_UPTO_STNCODE"	+
					recordSeparator + "INPUT TKT TYPE"	+
					//	recordSeparator + "INPUT FARE DTLS(cashToBeCollected%voucherFare%Total Fare%)" +
					//		recordSeparator + "OUTPUT FARE DTLS(cashToBeCollected%voucherFare%Total Fare%)" +
					recordSeparator + "INPUT FARE DTLS(Total Fare)" +
					recordSeparator + "OUTPUT FARE DTLS(Total Fare)" +
					recordSeparator + "EXECUTION STATUS" +
					recordSeparator + "PNR in NxtGen" +
					recordSeparator + "PSGN CONC DTLS"
					)
			br.newLine();

			//For writing matching file

			br_matchRecsFile.write(
					"TEST CASEID" +
					recordSeparator + "REC NO"	+
					recordSeparator + "TRAINNo"	+
					recordSeparator + "JRNY DATE(MM-DD-YYYY)"	+
					recordSeparator + "SOURCE STN CODE" +
					recordSeparator + "DESTN STN CODE" +
					recordSeparator + "CLS CODE"	+
					recordSeparator + "QUOTA CODE"	+
					recordSeparator + "PSGN COUNT"	+
					recordSeparator + "BRDG_STNCODE"	+
					recordSeparator + "RESVN_UPTO_STNCODE"	+
					recordSeparator + "INPUT TKT TYPE"	+
					//recordSeparator + "INPUT FARE DTLS(cashToBeCollected%voucherFare%Total Fare%)" +
					//	recordSeparator + "OUTPUT FARE DTLS(cashToBeCollected%voucherFare%Total Fare%)" +
					recordSeparator + "INPUT FARE DTLS(Total Fare)" +
					recordSeparator + "OUTPUT FARE DTLS(Total Fare)" +
					recordSeparator + "EXECUTION STATUS" +
					recordSeparator + "PNR in NxtGen" +
					recordSeparator + "PSGN CONC DTLS"
					)
			br_matchRecsFile.newLine();

			br_skipRecsFile.write(
					"TEST CASEID" +
					recordSeparator + "REC NO"	+

					recordSeparator + "TRAINNo"	+
					recordSeparator + "JRNY DATE(MM-DD-YYYY)"	+
					recordSeparator + "SOURCE STN CODE" +
					recordSeparator + "DESTN STN CODE" +
					recordSeparator + "CLS CODE"	+
					recordSeparator + "QUOTA CODE"	+
					recordSeparator + "PSGN COUNT"	+
					recordSeparator + "BRDG_STNCODE"	+
					recordSeparator + "RESVN_UPTO_STNCODE"	+
					recordSeparator + "TICKET TYPE"	+
					recordSeparator + "EXECUTION STATUS" +
					recordSeparator + "ERROR SNAP NAME"
					)
			br_skipRecsFile.newLine();




			for ( int recordCount = 0; recordCount < bookedTktPnrDtlsList.size(); recordCount++) {
				recordString = '' ;
				psgnDtlsString = '';

				//For writing skipped rec details - start
				if (bookedTktPnrDtlsList.get(recordCount).getExecutionStatus().equals("FAILED")) {

					recordString = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getRecNo() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTrainNo() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDoj() +

							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getClassCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getQuotaCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getPsgnCount() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTicketType() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getExecutionStatus() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getErrorMsg() + recordSeparator;


					br_skipRecsFile.write(recordString)
					br_skipRecsFile.newLine();

					recordCountSkippedDueToException ++;
					continue;

				}
				//For writing skipped rec details - end

				System.out.println("cash to be collected in booked tkt=" + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getCashToBeCollected()) ;
				System.out.println("cash to be collected in input dtl=" + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getCashToBeCollected()) ;

				//For writing matching Records in file - start

				/*
				 if (
				 bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare().equals
				 (inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getTotalFare())
				 ) {
				 */

				if (
				Float.parseFloat(bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare()) ==
				(Float.parseFloat(inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getTotalFare()))
				) {
					recordString = inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getRecNo() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getTrainNo() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getDoj() +

							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getFromStnCodeName() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getToStnCodeName() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getJrnyCls() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getQuota() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getPsgnCount() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTicketType() +

							//	recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getCashToBeCollected() +
							//	psgnStatusSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getVoucherFare() +
							recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getTotalFare() +

							//		recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getCashToBeCollected() +
							//		psgnStatusSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getVoucherFare() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getExecutionStatus() +
							recordSeparator + bookedTktPnrDtlsList.get(recordCount).getPnrNo() ;

					for ( int psgnCount = 0; psgnCount < bookedTktPnrDtlsList.get(recordCount).psgnDetailsList.size(); psgnCount++) {
						/*
						 psgnDtlsString += bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnSrlNo()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnName()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnAge()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnGender()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnQuota()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingStatus()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingDtls()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurStatus()	+ psgnStatusSeparator +
						 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurDtls()	+ psgnStatusSeparator ;
						 */
						psgnDtlsString += bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnSrlNo()	+ psgnStatusSeparator +
								bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getConcessionCode()+ psgnStatusSeparator + psgnRecordSeparator;






					}

					recordString = recordString + recordSeparator + psgnDtlsString +
							recordSeparator ;




					br_matchRecsFile.write(recordString)
					br_matchRecsFile.newLine();

					recordCountMatchingFare ++;
					continue;


				}
				//For writing matching Records in file - end

				recordCountNotMatchingFare ++;
				System.out.println("Rec No in booked tkt=" + bookedTktPnrDtlsList.get(recordCount).getRecNo()) ;
				System.out.println("Rec No in input dtl=" + inputTDRCPsgnDtlsList.get(recordCount).getRecNo()) ;


				recordString =  inputTDRCPsgnDtlsList.get(recordCount).getTestCaseId() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getRecNo() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getTrainNo() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getDoj() +

						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getFromStnCodeName() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getToStnCodeName() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getJrnyCls() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getQuota() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getPsgnCount() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getBoardingStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getResvnUptoStnCode() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTicketType() +

						//				recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getCashToBeCollected() +
						//			psgnStatusSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getVoucherFare() +
						recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getInputFareDtls().getTotalFare() +

						//		recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getCashToBeCollected() +
						//		psgnStatusSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getVoucherFare() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getExecutionStatus() +
						recordSeparator + bookedTktPnrDtlsList.get(recordCount).getPnrNo() ;

				/*
				 recordString = inputTDRCPsgnDtlsList.get(recordCount).getRecNo() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getTrainNo() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDoj() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getSrcStnCode() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getDestnStnCode() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getClassCode()
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getQuota() +
				 recordSeparator + inputTDRCPsgnDtlsList.get(recordCount).getPsgnCount() +
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getCashToBeCollected() +
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getVoucherFare() +
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getBookedTktFareDtls().getTotalFare() +
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getPnrNo() +
				 recordSeparator + bookedTktPnrDtlsList.get(recordCount).getErrorMsg() ;
				 */					


				System.out.println("For record Count " + recordCount) ;

				System.out.println("No of PSGN while writing =" + bookedTktPnrDtlsList.get(recordCount).psgnDetailsList.size()) ;
				for ( int psgnCount = 0; psgnCount < bookedTktPnrDtlsList.get(recordCount).psgnDetailsList.size(); psgnCount++) {
					/*
					 psgnDtlsString += bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnSrlNo()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnName()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnAge()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnGender()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnQuota()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingStatus()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnBookingDtls()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurStatus()	+ psgnStatusSeparator +
					 bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnCurDtls()	+ psgnStatusSeparator ;
					 */
					psgnDtlsString += bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getPsgnSrlNo()	+ psgnStatusSeparator +
							bookedTktPnrDtlsList.get(recordCount).getPsgnDetailsList().get(psgnCount).getConcessionCode()+ psgnStatusSeparator + psgnRecordSeparator;






				}

				recordString = recordString + recordSeparator + psgnDtlsString +
						recordSeparator ;

				br.write(recordString)
				br.newLine();

			}
			long endTime = System.currentTimeMillis();
			long executionTimeInMilliSec = endTime - startTime;
			String hms = millsToDateFormat (executionTimeInMilliSec)

			br_logFile.write("Total Records Processed =" +  inputTDRCPsgnDtlsList.size());
			br_logFile.newLine();
			br_logFile.write("Total Records Skipped Due to Exception=" +  recordCountSkippedDueToException);
			br_logFile.newLine();
			br_logFile.write("Total Records with Matching Fare=" +  recordCountMatchingFare);
			br_logFile.newLine();
			br_logFile.write("Total Records with Non Matching Fare =" +  recordCountNotMatchingFare);
			br_logFile.newLine();
			br_logFile.write("Skipped Record Details - " + skipRecordsFile.getName() )
			br_logFile.newLine();
			br_logFile.write("Matching Record Details - " + matchRecsFile.getName() )
			br_logFile.newLine();
			br_logFile.write("Non Matching Record Details - " + file.getName() )
			br_logFile.newLine();
			//		br_logFile.write("Execution Time in MilliSec - " +  executionTimeInMilliSec )
			//		br_logFile.newLine();
			br_logFile.write("Execution Time in DD - hh:mm:sec - " +  hms )



		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally{
			try {
				br.close();
				fr.close();
				br_logFile.close();
				fr_logfile.close();
				br_matchRecsFile.close();
				fr_matchRecsFile.close();
				br_skipRecsFile.close();
				fr_skipRecsFile.close();


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // End of compareInputAndBookedTktDtls()

	//start of millsToDateFormat
	@Keyword
	public static String millsToDateFormat(long longInterval) {
		/*
		 Date date = new Date(mills);
		 DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		 String dateFormatted = formatter.format(date);
		 return dateFormatted; //note that it will give you the time in GMT+0
		 */


		long intMillis = longInterval;
		long dd = TimeUnit.MILLISECONDS.toDays(intMillis);
		long daysMillis = TimeUnit.DAYS.toMillis(dd);
		intMillis -= daysMillis;
		long hh = TimeUnit.MILLISECONDS.toHours(intMillis);
		long hoursMillis = TimeUnit.HOURS.toMillis(hh);
		intMillis -= hoursMillis;
		long mm = TimeUnit.MILLISECONDS.toMinutes(intMillis);
		long minutesMillis = TimeUnit.MINUTES.toMillis(mm);
		intMillis -= minutesMillis;
		long ss = TimeUnit.MILLISECONDS.toSeconds(intMillis);
		long secondsMillis = TimeUnit.SECONDS.toMillis(ss);
		intMillis -= secondsMillis;

		String stringInterval = "%02d days - %02d:%02d:%02d.%03d";
		return String.format(stringInterval , dd, hh, mm, ss, intMillis);
	}

	//end of millsToDateFormat

}







