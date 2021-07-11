import java.util.ArrayList;

/**
 * Class A5:
 * Average of Government expenditure on education, total (% of GDP) for the selected years (1-series graphs – you can use a pie-chart here)
 * @author Group 15
 */

public class A5 extends AnalysisType{
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"SE.XPD.TOTL.GD.ZS"};
	public static final String title = "Average of Government Expenditure on Education";
	
	// arraylist that contain data
	private ArrayList <Double> expenditure;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param expenditure
	 */
	public A5(ArrayList <Double> expenditure) {
		
		units = new String[] {"% of GDP"};
		
		// populates arraylist
		this.expenditure= new ArrayList<Double>(expenditure);
		
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A5 (String country, int startYear, int endYear) {
		
		units = new String[] {"% of GDP"};
		
		//populates data structures
		Data data = new Data(1);
		
		//adding analysis data to the dataset
		data.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		expenditure = data.getDataset()[0];
		
	}
	
	/**
	 * Calculates the average of expenditure
	 * @return dataset
	 */
	public Data[] calculate() {
		double total = 0;
		int size = expenditure.size();
		
		//searching for empty data cells
		for(int i = 0; i < expenditure.size(); i++) {
			if (expenditure.get(i) == 0.0) {
				size--; // eliminates 0.0 values (no data)
			}
			else {
				total = total + expenditure.get(i); // adds all non-zero values
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
