import java.util.ArrayList;

/**
 * Class A2:
 * PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)
 * vs Forest area (% of land area) (2-series graphs)
 * @author Group 15
 */

public class A2 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = {"EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS"};
	public static final String title = "PM2.5 Air Pollution vs. Forest Area";		
	
	// arraylists that contain data
	private ArrayList<Double> airPollution;
	private ArrayList <Double> forestArea;

	private Data dataset1; // will include forestArea
	private Data dataset2; // will include airPollution
	private Data[] datasets;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param airPollution
	 * @param forestArea
	 */
	public A2(ArrayList <Double> airPollution, ArrayList<Double> forestArea) {
		
		units = new String[] {"micrograms per cubic meter", "% of land area"};
		
		// populates arraylists
		this.airPollution = new ArrayList<Double>(airPollution);
		this.forestArea = new ArrayList<Double>(forestArea);
		
		// initializes data structures 
		dataset1 = new Data(1);
		dataset2 = new Data(1);
		
		// populates dataset1 and dataset2
		dataset1.addAnalysisData(this.airPollution, 1);
		dataset2.addAnalysisData(this.forestArea, 1);
		
		// sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A2(String country, int startYear, int endYear) {
		
		units = new String[] {"micrograms per cubic meter", "% of land area"};
		
		// initializes data structures 
		Data dataset1 = new Data(1);
		Data dataset2 = new Data(1);
		
		// populates dataset1 and dataset2
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
