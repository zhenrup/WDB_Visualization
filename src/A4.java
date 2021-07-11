import java.util.ArrayList;

/**
 * Class A4:
 * Average Forest area (% of land area) for the selected years (1-series graphs – you can use a pie-chart here)
 * @author Group 15
 */

public class A4 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"AG.LND.FRST.ZS"};
	public static final String title = "Average Forest Area";
	
	// arraylist that contain data
	private ArrayList <Double> forestArea;

	/**
	 * Constructor passing through the data to create analysis object
	 * @param forestArea
	 */
	public A4(ArrayList <Double> forestArea) {
		
		//setting units 
		units = new String[] {"% of land area"};
		
		// populates arraylist
		this.forestArea = new ArrayList<Double>(forestArea);
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A4(String country, int startYear, int endYear) {
		
		units = new String[] {"% of land area"};
		
		//populates data structures
		Data data = new Data(1);
		
		//adding analysis data to the dataset
		data.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		forestArea = data.getDataset()[0];
	}
	
	/**
	 * Calculates the average of forest area
	 * @return dataset
	 */
	public Data[] calculate() {
		double total = 0;
		int size = forestArea.size();
		
		//searching for empty data cells
		for(int i = 0; i < forestArea.size(); i++) {
			if (forestArea.get(i) == 0.0) {
				size--; // eliminates 0.0 values (no data)
			}
			else {
				total = total + forestArea.get(i); // adds all non-zero values
			}
		}
		
		double avgRatio;
		Data dataset = new Data(1);
		Data[] datasets = new Data[1];
		ArrayList<Double> averageRatio = new ArrayList<Double>();
		
		//calcualting the average of all data in the range of years
		if (size == 0) {
			avgRatio = 0; //if empty
		}
		else {
			avgRatio = total/size; // average calculation
		}
		
		//setting final calculations in the dataset
		averageRatio.add(avgRatio);
		dataset = new Data(1);
		dataset.addAnalysisData(averageRatio, 1);
		datasets[0] = dataset;
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
