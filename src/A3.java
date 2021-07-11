import java.util.ArrayList;

/**
 * Class A3:
 * Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
 * @author Group 15
 */

public class A3 extends AnalysisType{
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD"};
	public static final String title = "Ratio CO2 Emissions and GDP per Capita";		
	
	// arraylists that contain data
	private ArrayList <Double> emissions;
	private ArrayList<Double> GDP;

	private Data dataset1; // will include emissions
	private Data dataset2; // will include GDP per capita
	private Data[] datasets;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param emissions
	 * @param GDP
	 */
	public A3(ArrayList <Double> emissions, ArrayList<Double> GDP) {
		
		units = new String[] {"metric tons per capita", "current US$"};
		
		// populates arraylists
		this.emissions = new ArrayList<Double>(emissions);
		this.GDP = new ArrayList<Double>(GDP);
		
		// initializes data structures
		dataset1 = new Data(1);
		dataset2 = new Data(1);
		
		// populates dataset1 and dataset2
		dataset1.addAnalysisData(this.emissions, 1);
		dataset2.addAnalysisData(this.GDP, 1);
		
		// sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A3(String country, int startYear, int endYear) {
		
		units = new String[] {"metric tons per capita", "current US$"};
		
		// initializes data structures
		Data dataset1 = new Data(1);
		Data dataset2 = new Data(1);
		
		//populates dataset1 and staset2
		dataset1.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		dataset2.addAnalysisData(country, indicators[1], startYear, endYear, 1);
		
		//sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
	}
	
	/**
	 * Calculates the average of forest area
	 * @return dataset
	 */
	public Data[] calculate() {
		return datasets;
	}
	
	/**
	 * Gets the static version of the main title of the analysis
	 * @return title
	 */
	public static String getTitle() {
		return title;
	}
	
	/**
	 * Gets the instance of the main title of the analysis
	 * @return title
	 */
	public String getInstTitle() {
		return title;
	}
	
	
	/**
	 * Gets the indicators for the analysis type
	 * @return indicators
	 */
	public static String[] getIndicators() {
		return indicators;
	}
}
