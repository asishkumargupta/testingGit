package reusableComponents

import com.kms.katalon.core.annotation.Keyword






public class CustomKeywords_nativeEnv {

	@Keyword
	public  List<String> executeAutoItScript(String script) throws IOException, InterruptedException {
		//String CSV_PATH = "D:\\JavaPractice\\UsingEclipse\\External Docs\\Detail_Resvn.csv";
		//String CSV_PATH = "D:\\data\\Detail_Resvn.csv";
		//Process p =Runtime.getRuntime().exec("D:\\PRS Open Source Migration\\Assignments\\2017\\After First April\\Jenkins_Selenium_TestNG\\AutoIT\\scripts\\mcp\\partial book error with image.exe " + CSV_PATH);
		List<String> trainAvailabilityList = new ArrayList();
		Process p =Runtime.getRuntime().exec(script);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line;

		while ((line = input.readLine()) != null) {

			trainAvailabilityList.add(line)
			System.out.println("line in executeAutoItScript = " + line);

		}

		line = input.readLine();
		p.waitFor();

		return trainAvailabilityList;
	}




	@Keyword
	public static void writeAllClsAvbltyUsingMCP(String outFileName , List<String> trainAvailabilityList ) {

		Map<String, String> map = new HashMap<>();
		map.put(" ", " ");
		map.put("DATE", "DATE");
		map.put("CLASS:_  _", "");
		map.put("CLASS:_1A_", "FIRST AC");
		map.put("CLASS:_2A_", "SECOND AC");
		map.put("CLASS:_3A_", "THIRD AC");
		map.put("CLASS:_SL_", "SLEEPER ");
		map.put("CLASS:_CC_" , "CHAIR CAR");
		map.put("CLASS:_2S_" , "SECOND SITTING");
		map.put("AVBL" , "AVAILABLE-");
		map.put("DATE" , "Date");


		String recordToWrite = '' ,
		headerStringToWrite = '',
		availabilityStringToWrite = '',
		inputDataString ='',
		availabilityKeyWord  = '' ,
		availabilityCount = '' ;

		boolean isHeaderKeyPresent ,
		isAvailabilityKeyPresent ;

		char 	recordSeparator = ',' ,
		headerSeparator = '$';

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
		System.out.println('\n\n Now in writeAllClsAvbltyUsingMCP \n\n' )

		/*		
		 String headerName = trainAvailabilityList.get(1)
		 String availabilityString = trainAvailabilityList.get(3)
		 System.out.println("headerName = " + headerName  )
		 System.out.println("Availability String = " + availabilityString  )
		 */	





		for ( int i = 0; i+4 < trainAvailabilityList.size(); i+=5) {
			try {

				inputDataString = trainAvailabilityList.get(i)
				System.out.println("Input Data for this teration = " + inputDataString  )
				//	for (int noOfDataInOneRow=0; noOfDataInOneRow <  )
				String recordToWriteReceived = trainAvailabilityList.get(i)

				String headerString = trainAvailabilityList.get(i+2)
				System.out.println("headerName = " + headerString  )
				String availabilityString = trainAvailabilityList.get(i+4)
				System.out.println("Availability String = " + availabilityString  )

				String [] headerArray = headerString.split("\\*_")
				/*			
				 for (String columnName: headerArray){
				 //	System.out.println(map.get(columnName));
				 System.out.println(columnName);
				 }
				 */		
				for (int count = 0;count <headerArray.length; count++ ){

					System.out.println("headerArray[" + count + "]=" + headerArray[count]);
				}




				String [] availabiltyArray = availabilityString.split("\\*")
				/*		
				 for (String columnData: availabiltyArray){
				 System.out.println(columnData);
				 }
				 */		
				for (int count = 0;count <availabiltyArray.length; count++ ){

					System.out.println("availabiltyArray[" + count + "]=" + availabiltyArray[count]);
				}



				//		recordToWrite = '';
				recordToWrite = inputDataString
				for ( int countColumn = 0; countColumn < 7; countColumn++) {

					availabilityKeyWord = ''
					availabilityCount = ''
					//if ((headerArray[countColumn].isEmpty()) || (headerArray[countColumn].compareTo("CLASS:_  _") ))
					if ((headerArray[countColumn].isEmpty()) || (headerArray[countColumn]=="CLASS:_  _" ))
						continue ;

					isHeaderKeyPresent = map.containsKey(headerArray[countColumn])
					if (isHeaderKeyPresent)
						headerStringToWrite = map.get(headerArray[countColumn])
					else
						headerStringToWrite = headerArray[countColumn]


					availabilityKeyWord = availabiltyArray[countColumn].split("\\s+")[0]

					if (availabiltyArray[countColumn].split("\\s+").size()>1) {
						availabilityCount = availabiltyArray[countColumn].split("\\s+")[1]

						System.out.println("Availability count is now =" + availabilityCount )
						System.out.println("Availability count in Integer =" + Integer.parseInt(availabilityCount) )

						availabilityCount = String.format("%04d",Integer.parseInt(availabilityCount))

						System.out.println("Availability count=" + availabilityCount )


					}

					//			System.out.println("Availability key word=" + availabiltyArray[countColumn].split("\\s+")[0] )

					//System.out.println("Availability size=" + availabiltyArray[countColumn].split("\\s+").size())

					//	if (availabiltyArray[countColumn].split("\\s+").size()>1)
					//	System.out.println("Availability count" + availabiltyArray[countColumn].split("\\s+")[1] )


					isAvailabilityKeyPresent = map.containsKey(availabilityKeyWord)

					if (isAvailabilityKeyPresent)
						availabilityStringToWrite = map.get(availabilityKeyWord) + availabilityCount
					else
						availabilityStringToWrite = availabiltyArray[countColumn] + availabilityCount




					//	recordToWrite = recordToWrite + map.get(headerArray[countColumn]) + headerSeparator + availabiltyArray[countColumn]  + recordSeparator

					recordToWrite = recordToWrite + headerStringToWrite + headerSeparator + availabilityStringToWrite  + recordSeparator

					//		System.out.println("Now record to write is=" + recordToWrite )
				}

				System.out.println("Final RECORD TO WRITE=" + recordToWrite )



				//	System.out.println(recordToWrite )

				//	String[] recordToWrite  = recordToWriteReceived.split("@", 5);



				br.write(recordToWrite )
				br.newLine();

			}  /*End of try block */
			catch (Exception e ) {
				recordToWrite = "ERROR OCCURED DURING PROCESSING COUNT " + inputDataString
				System.out.println("RECORD TO WRITE AFTER EXCEPTION=" + recordToWrite )
				br.write(recordToWrite )
				br.newLine();

			}


			//	File file = new File(outFile);



		}  /*End of for ( int i = 0; i+3 < trainAvailabilityList.size(); i+=4) */
		br.close();
		fr.close();
	}






}

