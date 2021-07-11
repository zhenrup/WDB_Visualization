import java.util.ArrayList;

/**
 * Class A6:
 * Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) (2-series graphs)
 * @author Group 15
 */

public class A6 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = new String[] {"SH.MED.BEDS.ZS", "SH.XPD.CHEX.PC.CD"};
	public static final String title = "Ratio of Hospital Beds and Current Health Expenditure";
	
	// arraylists that contain data
	private ArrayList <Double> hospitalBeds;
	private ArrayList<Double> expenditurePerCap;
	private ArrayList<Double> expenditurePer1000;
	
	private Data dataset1;
	private Data dataset2;
	private Data[] datasets;
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param hospitalBeds
	 * @param expenditure
	 */
	public A6(ArrayList <Double> hospitalBeds, ArrayList<Double> expenditure) {
		
		units = new String[] {"per 1,000 people", "per 1,000 people"};
		
		// populates arraylists
		this.hospitalBeds = new ArrayList<Double>(hospitalBeds);
		this.expenditurePerCap = new ArrayList<Double>(expenditure);
		expenditurePer1000 = new ArrayList<Double>();
		
		// initializes data structures
		dataset1 = new Data(1);
		dataset2 = new Data(1);
		
		// populates dataset1
		dataset1.addAnalysisData(this.hospitalBeds, 1);
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A6 (String country, int startYear, int endYear) {
		
		units = new String[] {"per 1,000 people", "per 1,000 people"};
		
		Data d = new Data(2);
		d.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		d.addAnalysisData(country, indicators[1], startYear, endYear, 2);
		// populates arraylists
		this.hospitalBeds = d.getDataset()[0];
		this.expenditurePerCap = d.getDataset()[1];
		expenditurePer1000 = new ArrayList<Double>();
		
		// initializes data structures
		dataset1 = new Data(1);
		dataset2 = new Data(1);
		
		// populates dataset1
		dataset1.addAnalysisData(this.hospitalBeds, 1);
	}
	
	/**
	 * Converts expenditure per capita to expenditure per 1000
	 * @return dataset
	 */
	public Data[] calculate() {
		// populates dataset2
		for(int i=0; i<expenditurePerCap.size(); i++) {
			expenditurePer1000.add(i, expenditurePerCap.get(i)*1000);
		}
		
		//add data to dataset
		dataset2.addAnalysisData(expenditurePer1000, 1);
		datasets = new Data[] {dataset1, dataset2};
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
