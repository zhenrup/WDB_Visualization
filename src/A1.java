import java.util.ArrayList;

/**
 * Class A1:
 * CO2 emissions (metric tons per capita) 
 * vs Energy use (kg of oil equivalent per capita) 
 * vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) (3-series graph)
 * @author Group 15
 */

public class A1 extends AnalysisType {
	
	//initializes indicators with the necessary indicators that represents the desired data in the World Bank Database
	public static final String[] indicators = {"EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3"};
	public static final String title = "CO2 Emissions vs. Energy Use vs. PM2.5 Air Pollution";
	
	// arraylists that contain data
	private ArrayList <Double> emissions;
	private ArrayList<Double> energy;
	private ArrayList<Double> airPollution;
	
	private Data dataset1; // will include emissions and airPollution (grouped together because they have a similar scale)
	private Data dataset2; // will include energy 
	private Data[] datasets; // calculate will return an array of Data objects
	
	/**
	 * Constructor passing through the data to create analysis object
	 * @param emissions
	 * @param energy
	 * @param airPollution
	 */
	public A1(ArrayList <Double> emissions, ArrayList<Double> energy, ArrayList<Double> airPollution) {
		
		units = new String[] {"metric tons per capita", "kg of oil equivalent per capita", "micrograms per cubic meter"};
				
		// populates arraylists
		this.emissions = new ArrayList<Double>(emissions);
		this.energy = new ArrayList<Double>(energy);
		this.airPollution = new ArrayList<Double>(airPollution);
		
		// initializes data structures
		dataset1 = new Data(2);
		dataset2 = new Data(1);
				
		// populates dataset1 with emissions and airPollution
		dataset1.addAnalysisData(this.emissions, 1);
		dataset1.addAnalysisData(this.airPollution, 2);
		
		// populates dataset2 with energy
		dataset2.addAnalysisData(this.energy, 1);
		
		// sets values for datasets
		datasets = new Data[] {dataset1, dataset2};
	}
	
	/**
	 * Overloading constructor - parameters do not include data inputs
	 * @param country
	 * @param startYear
	 * @param endYear
	 */
	public A1 (String country, int startYear, int endYear) {
		
		units = new String[] {"metric tons per capita", "kg of oil equivalent per capita", "micrograms per cubic meter"};
		
		// initializes data structures
		Data dataset1 = new Data(2);
		Data dataset2 = new Data(1);
				
		// populates dataset1 with emissions and airPollution
		dataset1.addAnalysisData(country, indicators[0], startYear, endYear, 1);
		dataset1.addAnalysisData(country, indicators[2], startYear, endYear, 2);
		
		// populates dataset2 with energy
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
	 * gets the instance of the main title of the analysis
	 * @return title 
	 */
	public String getInstTitle() {
		return title;
	}
	
	/**
	 * gets the indicators for the analysis type
	 * @return indicators
	 */
	public static String[] getIndicators() {
		return indicators;
	}
}
