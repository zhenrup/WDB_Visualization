import java.util.*;  
import java.io.*;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Unfinished selection class
 * @author ZP
 *
 */
public class Selection {

	//variable declaration
	private final String DBFILE = "CountryAnalysisComboDB.txt";
	private final String CompatibleVFile = "compatibleViewers.txt";
	private HashMap<String, HashMap<String, int[]>> comboDB;
	private HashMap<String, String[]> viewersDB;
	private ArrayList<String> selectedAnalysis = new ArrayList<String>();
	private String selectedCountry;
	private int selectedStartYear;
	private int selectedEndYear;
	private ArrayList<String> selectedViewers;
	private String ItemType;
	private String countryFullName;
	private Hashtable <String, ArrayList<String>> compatibleViewers;
	ArrayList<ArrayList<String>> countryName = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<Integer>> yearCollection = new ArrayList<ArrayList<Integer>>();
	private AnalysisFactory af;
	private String analysisTitle;
	private ViewerFactory vf;
	private AnalysisType currentAnalysis;
	
	/**
	 * constructor reads the country_list.csv and store all data into two array lists
	 */
	public Selection() {
	
		//variable initialization
		af = new AnalysisFactory();
		vf = new ViewerFactory();
		
		selectedAnalysis = new ArrayList<String>();
		selectedCountry = "";
		selectedStartYear = 0;
		selectedEndYear = 0;
		int counter = 0;
		compatibleViewers= new Hashtable<String, ArrayList<String>>();

		
		try {
			//open csv provided in project resources
			Reader in = new FileReader("country_list.csv");
		    Iterable<CSVRecord> records = (CSVFormat.DEFAULT).parse(in);
		    //read each row in csv
			for (CSVRecord record: records) {
				//creates a temporary array list to store all the name and code of one country
				ArrayList<String>oneCountry = new ArrayList<String> ();
				//creates a temporary array list to store the valid start year and end year of one country
				ArrayList<Integer>countryYear = new ArrayList<Integer> ();
				
				//append country code name / full name / abbreviation name into a temporary array list
				oneCountry.add(record.get(0)); //country code
				oneCountry.add(record.get(1)); //country name, full
				oneCountry.add(record.get(2)); //country name, abrv

				oneCountry.add(record.get(4)); //iso2
				oneCountry.add(record.get(5)); //iso3
				
				//append valid start year and end year into a temporary array list 
				countryYear.add(Integer.parseInt(record.get(6)));
				
				String endYear = record.get(7);
				//if end year in csv is "NOW" which means to be replaced to 2021
				//otherwise convert end year to an int type and store it
				if(!NumberUtils.isNumber(endYear)) {
					countryYear.add(2021);
				}
				else {
					countryYear.add(Integer.parseInt(endYear));
				}	
				
				//after finishing reading each row, append array list with names of one country and array list with year to 2D array lists separately
				countryName.add(counter, oneCountry);
				yearCollection.add(counter, countryYear);	
				counter++;
			}

		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	
	/**
	 * 
	 * @param filename
	 */
	public void initializeViewerDB(String filename) {
		try {
			File db = new File(filename);
			Scanner readDB = new Scanner(db);
			String line = "";
			HashMap<String, String[]> tempDict = new HashMap<String, String[]>();
			String[] tempArray, viewers;
			
			while (readDB.hasNextLine()) {
				line = readDB.nextLine();
				tempArray = line.split(" ");
				tempDict.put(tempArray[0], new String[tempArray.length-1]);
				viewers = tempDict.get(tempArray[0]);
				//copies the valid viewers into the string[] in the dictionary
				for (int i = 1; i < tempArray.length; i++) {
					viewers[i-1] = tempArray[i];
				}
			}
			viewersDB = tempDict;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void initializeComboDB(String fileName){
		try {
			File db = new File(fileName);
			Scanner readDB = new Scanner(db);
			String line = "";
			String[] outerTemp;
			String[] middleTemp;
			String[] innerTemp;
			String[] yearsTemp;
			HashMap<String, int[]> tempDict;
			int[] yearsArray;
			readDB.nextLine(); //skips the format explanation line
			while(readDB.hasNextLine()) {
				line = readDB.nextLine();
				outerTemp = line.split(" ");
				comboDB.put(outerTemp[0], new HashMap<String, int[]>()); //puts the 
				tempDict = comboDB.get(outerTemp[0]);
				
				middleTemp = outerTemp[1].split(";");
				for (int i = 0; i < middleTemp.length; i++) {
					innerTemp = middleTemp[i].split("\\[");
					innerTemp[1] = innerTemp[1].substring(0, innerTemp[1].length()-1);
					yearsTemp = innerTemp[1].split(",");
					if (yearsTemp.length <= 1 && yearsTemp[0].equals("")) {
						tempDict.put(innerTemp[0], null);
					}
					else {
						tempDict.put(innerTemp[0], new int[yearsTemp.length]);
						yearsArray = tempDict.get(innerTemp[0]);
						for (int k = 0; k < yearsArray.length; k++) {
							yearsArray[k] = Integer.parseInt(yearsTemp[k]);
						}
						Arrays.sort(yearsArray);
					}
				}
				
			}
			readDB.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method determines whether selected country is compatible with analysis
	 * @param in: user input for country
	 * @return true if user selected country is found in country_list, false otherwise
	 */
	public boolean compareCountryInput(String in) {
		if (comboDB == null) {
			comboDB = new HashMap<String, HashMap<String, int[]>>();
			initializeComboDB(DBFILE);
		}
		//check if user input is country code
		if(NumberUtils.isNumber(in)) {
			for(int i = 0; i < countryName.size(); i++) {
				if(in.equalsIgnoreCase(countryName.get(i).get(0))){
					// checks if the country iso3 code is part of the combination database
					if (comboDB.containsKey(countryName.get(i).get(4))) {
						HashMap <String, int[]> tempCountry = comboDB.get(countryName.get(i).get(4));
						//checks if the selected analysis is part of the current country's database
						for(int j = 0; j < selectedAnalysis.size(); j++) {
							if (tempCountry.containsKey(selectedAnalysis.get(j))) {
								if(tempCountry.get(selectedAnalysis.get(j)) != null)	
									return true;
							}
						}						
					}
				}
			}
		}
		//the selected country is ISO3, 
		else {
			for(int i = 0; i < countryName.size(); i++) {
				for(int j = 1; j < countryName.get(i).size(); j++) {
					if(in.equalsIgnoreCase(countryName.get(i).get(j))){
						// checks if the country iso3 code is part of the combination database
						if (comboDB.containsKey(countryName.get(i).get(4))) {
							HashMap <String, int[]> tempCountry = comboDB.get(countryName.get(i).get(4));
							//checks if the selected analysis is part of the current country's database
							//if analysis is found in coresponding country name return true
							for(int k = 0; k < selectedAnalysis.size(); k++) {
								if (tempCountry.containsKey(selectedAnalysis.get(k))) {
									if(tempCountry.get(selectedAnalysis.get(k)) != null)	
										return true;
								}
							}
						}
					}
				}
			}
		}
		return false;		
	}
	
	public ArrayList<String> availableCountry(){
		ArrayList<String> availableList = new ArrayList<String>();
		if (comboDB == null) {
			comboDB = new HashMap<String, HashMap<String, int[]>>();
			initializeComboDB(DBFILE);
		}
		for(int i = 0; i < countryName.size(); i++) {
			if (comboDB.containsKey(countryName.get(i).get(4))) {

				HashMap <String, int[]> tempCountry = comboDB.get(countryName.get(i).get(4));
				int counter = 0;
				for(int k = 0; k < selectedAnalysis.size(); k++) {
					if (tempCountry.containsKey(selectedAnalysis.get(k))) {
						if(tempCountry.get(selectedAnalysis.get(k)) != null)	
							counter++;
					}
				}
				if(counter == selectedAnalysis.size()) {
					availableList.add(countryName.get(i).get(1));
				}
			}

		}
		
		return availableList;
		
	}
	/**
	 * This method determines whether user inputs for start and end year are in the valid range
	 * @return true if user input of start and end year is in the valid range, false otherwise
	 */
	public boolean compareYearRange() {		

		if (comboDB == null) {
			comboDB = new HashMap<String, HashMap<String, int[]>>();
			initializeComboDB(DBFILE);
		}
		//put all available year of different analysis indicator together in a list 
		ArrayList<Integer> allYearRange = new ArrayList<Integer>();
		int totalCheck = selectedAnalysis.size();		//number of analysis
		//add all available year into a new list
		for (int i = 0; i < totalCheck; i++) {
			//System.out.println(comboDB);
			System.out.println(selectedCountry);
			//System.out.println(selectedAnalysis);
			int[] validYears = comboDB.get(selectedCountry).get(selectedAnalysis.get(i));
			//if there is no available year for an analysis, the year range is invalid
			if(validYears == null) {
				return false;
			}
			//if allYearRange is empty, store the year range of the first indication in the allYearRange
			if(i == 0) {
				for(int j = 0; j < validYears.length; j++) {
					allYearRange.add(validYears[j]);
				}
			}
			else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int j = 0; j < validYears.length; j++) {
					temp.add(validYears[j]);
				}
				//find common elements in both array lists and store them in allYearaRange
				allYearRange.retainAll(temp);
			}
		}

		//sort elements in descending order
		Collections.sort(allYearRange, Collections.reverseOrder());
		
		int startFound = -1;
		int endFound = -1;
		int listLength = allYearRange.size();
		//if there is no year available for analysis, the selected year range is invalid
		if(listLength == 0) {
			return false;
		}
		//if selected start and end year are larger than all the available year, the selected year range is invalid
		if((allYearRange.get(0) < selectedEndYear) && (allYearRange.get(0) < selectedStartYear)) {
			return false;
		}
		//if selected start and end year are smaller than all the available year, the selected year range is invalid
		else if ((allYearRange.get(listLength - 1) > selectedEndYear) && (allYearRange.get(listLength - 1) > selectedStartYear)) {
			return false;
		}
		
		//loop through all available year and see if selection match with one elements
		for(int i = 0; i < allYearRange.size(); i ++) {
			if(allYearRange.get(i) == selectedStartYear) {
				startFound = 1;
			}
			if(allYearRange.get(i) == selectedEndYear) {
				endFound = 1;
			}

		}
		//if there are no match for one selection, loop through all available year again
		if((startFound != 1) || (endFound != 1)) {
			if((allYearRange.get(listLength - 1) > selectedStartYear) && (startFound == -1)) {
				setStartYear(allYearRange.get(listLength - 1)) ;
				startFound = 1;
			}
			if ((allYearRange.get(0) < selectedEndYear) && (endFound == -1)) {
				setEndYear(allYearRange.get(0)) ;
				endFound = 1;
			}
			for(int i = 0; i < allYearRange.size() - 1; i ++) {
				//if the value of the selected start year is between two elements, substitutes with the smaller year
				if((allYearRange.get(i) < selectedStartYear) && (allYearRange.get(i - 1) > selectedStartYear) && (startFound == -1)) {
					setStartYear(allYearRange.get(i - 1)) ;
					startFound = 1;
				}
				//if the value of the selected end year is between two elements, substitutes with the larger year
				if((allYearRange.get(i) < selectedStartYear) && (allYearRange.get(i - 1) > selectedStartYear) && (endFound == -1)) {
					setEndYear(allYearRange.get(i)) ;
					endFound = 1;
				}
			}
			
		}
		//only return true if start and end year both are set
		if((startFound == 1) && (endFound == 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * this class reads compatibleView.txt and store it
	 */
	public void readCompatibleViewers() {
		try {
			File compatibleV = new File(CompatibleVFile);
			Scanner readCompatible = new Scanner(compatibleV); //creating scanner for reading text file
			ArrayList <String> viewList = new ArrayList<String>();
			
			//reads each line of the data file and stores it in an array
			while(readCompatible.hasNextLine()) {
				String data = readCompatible.nextLine();
				String [] splitChar = data.split(",");
				viewList = new ArrayList<String>();
				
				for(int i=1; i<splitChar.length;i++) {
					viewList.add(splitChar[i]);
				}
				compatibleViewers.put(splitChar[0], viewList); //adding data to dictionary
			}
			readCompatible.close();
		
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Checking whether the analysis type is compatible with the viewer selected returns true if viewer is in list of compatible viewers.
	 * @param analysisType
	 * @param viewer
	 * @return check boolean
	 */
	//changing to return an arraylist with compatible viewer list
	public ArrayList<String> updateCompatibleViewers(String analysisType) {
		readCompatibleViewers();
		ArrayList<String> compatibleViewerList = compatibleViewers.get(analysisType);	
		return compatibleViewerList;
		
	}
	
	public void setStartYear(int start) {
		selectedStartYear = start;
	}
	
	public void setEndYear(int end) {
		selectedEndYear = end;
	}
	
	public void setYears(String start, String end) {
		this.selectedStartYear = Integer.parseInt(start);
		this.selectedEndYear = Integer.parseInt(end);
	}
	
	public void setIns_Analysis(String title) {
		selectedAnalysis.clear();
		selectedAnalysis.addAll(Arrays.asList(af.getIndicators(title)));
		analysisTitle = title;
	}
	
	/**
	 * setter method that sets the selected analysis as the ISO3 code
	 * @param analysis
	 */
	public void setAnalysis(String analysis) {
		selectedAnalysis.add(analysis);
		
	}
	
	/**
	 * converts selected country to the ISO3 code
	 * @param country
	 */
	public void setCountry(String country) {
		//check if user input is country code
		if(NumberUtils.isNumber(country)) {
			for(int i = 0; i < countryName.size(); i++) {
				if(country.equalsIgnoreCase(countryName.get(i).get(0))){
					this.selectedCountry = countryName.get(i).get(4); //converts country code to iso3
				}
			}
		}
		else {
			for(int i = 0; i < countryName.size(); i++) {
				for(int j = 1; j < countryName.get(i).size(); j++) {
					if(country.equalsIgnoreCase(countryName.get(i).get(j))){
						this.selectedCountry = countryName.get(i).get(4); //converts country code to iso3
					}
				}
			}
		}
	}
	
	/**
	 * Tells AnalysisFactory to create an analysis object and initialize instance variable
	 * 
	 */
	public void generateAnalysis() {
		this.currentAnalysis =  af.createAnalysis(analysisTitle, selectedCountry, selectedStartYear, selectedEndYear);
	}
	
	public Viewers generateViewer(String name) {
		return vf.createViewer(name, currentAnalysis, selectedStartYear, selectedEndYear, countryFullName).buildGraph();
	}
	/**
	 * returns the selected start year
	 * @return start year
	 */
	public int getStartYear() {
		return selectedStartYear;
	}
	
	/**
	 * returns the selected end year
	 * @return end year
	 */
	public int getEndYear() {
		return selectedEndYear;

	}
	
	/**
	 * returns the country code
	 * @return country code
	 */
	public String getCountryCode() {
		return this.selectedCountry;
	}
	
	/**
	 * returns the list of analysis types with their indicator codes
	 * @return array list of analysis types
	 */
	public ArrayList<String> getAnalysis() {
		return this.selectedAnalysis;
	}
	
	public String[] getTitles() {
		return af.getTitles();
	}
	
/**	public void addViewers(Viewers view) {
		selectedViewers.add(e)
	}**/
	//test printing case

	
	/**
	 * prints the of selected analysis as a test
	 */
	/**public String getCountryFull() {
		return countryFullName;
	}**/
	public void printAnalysisList() {
		for(int i = 0; i < selectedAnalysis.size(); i++) {
			System.out.println(selectedAnalysis.get(i));
		}	
	}
	
	/**
	 * print the list of countries
	 */
	public void printCountryList (){
		for(int i = 0; i < countryName.size(); i++) {
			System.out.println(countryName.get(i).get(0));
		}		
	}
	
	/**
	 * print all the years
	 */
	public void printYearCollection (){
		for(int i = 0; i < yearCollection.size(); i++) {
			System.out.println(yearCollection.get(i));
		}	
	}
	public void setCountryFull (String c) {
		countryFullName = c;
	}
	public AnalysisType getCurrentAnalysis() { 
		return currentAnalysis;
	}
	
	public void selectionMessage() {
	
	
	}

}