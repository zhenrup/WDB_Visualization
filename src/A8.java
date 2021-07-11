import java.util.ArrayList;

/**
 * Class A8:
 * Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP)
 * @author Group 15
 */

public class A8 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS"};
	public static final String title = "Ratio of Government Expenditure on Education vs. Current Health Expenditure";
	
	// arraylists that contain data
	private ArrayList <Double> educationEXP;
	private ArrayList<Double> healthEXP;

	private Data dataset; // includes both series as they share the same unit
	private Data[] datasets;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param educationEXP
	 * @param healthEXP
	 */
	public A8(ArrayList <Double> educationEXP, ArrayList<Double> healthEXP) {
		
		units = new String[] {"% of GDP", "% of GDP"};
		
		// populates arraylists
		this.healthEXP = new ArrayList<Double>(healthEXP);
		this.educationEXP = new ArrayList<Double>(educationEXP);

		// initializes data structures
		dataset = new Data(2);
		
		// populates dataset
		dataset.addAnalysisData(this.educationEXP, 1);
		dataset.addAnalysisData(this.healthEXP, 2);
		
		// sets values for datasets
		datasets = new Data[] {dataset};
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A8 (String country, int startYear, int endYear) {
		
		units = new String[] {"% of GDP", "% of GDP"};
		
		// initializes data structures
		dataset = new Data(2);
		
		// populates dataset
		dataset.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		dataset.addAnalysisData(country, indicators[1], startYear, endYear, 2);
		
		// sets values for datasets
		datasets = new Data[] {dataset};
	}
	
	/**
	 * gets the final dataset 
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
