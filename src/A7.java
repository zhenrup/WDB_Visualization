import java.util.ArrayList;

/**
 * Class A7:
 * Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births) (2-series graphs)
 * @author Group 15
 */

public class A7 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"SH.XPD.CHEX.PC.CD", "SP.DYN.IMRT.IN"};
	public static final String title = "Current Health Expenses per Capita vs. Infant Mortality Rate";
	
	// arraylists that contain data
	private ArrayList <Double> expenditure;
	private ArrayList<Double> mortality;

	private Data dataset1;
	private Data dataset2;
	private Data[] datasets;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param expenditure
	 * @param mortality
	 */
	public A7(ArrayList <Double> expenditure, ArrayList<Double> mortality) {
		
		units = new String[] {"current US$", "per 1,000 live births"};
		
		// populates arraylists
		this.expenditure = new ArrayList<Double>(expenditure);
		this.mortality = new ArrayList<Double>(mortality);
		
		// initializes data structures
		dataset1 = new Data(1);
		dataset2 = new Data(1);
		
		// populates dataset1 and dataset2
		dataset1.addAnalysisData(this.expenditure, 1);
		dataset2.addAnalysisData(this.mortality, 1);
		
		// sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A7 (String country, int startYear, int endYear) {
		
		units = new String[] {"current US$", "per 1,000 live births"};
		
		// initializes data structures
		Data dataset1 = new Data(1);
		Data dataset2 = new Data(1);
		
		// populates dataset1 and dataset2
		dataset1.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		dataset2.addAnalysisData(country, indicators[1], startYear, endYear, 1);
		
		// sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
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
