/**
 * Abstract Class AnalysisType
 * A1 - A8 extend this abstract class
 */
public abstract class AnalysisType {
	
	protected static String title;
	protected static String[] indicators;
	protected String[] units;
	
	/**
	 * Performs calculation with data to be used by Viewers
	 * @return array of Data objects (datasets)
	 * Length of array (number of data objects) depends on the number of series 
	 */
	abstract Data[] calculate();
	
	abstract String getInstTitle();
	
	public String[] getUnits() {
		return units;
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static String[] getIndicators() {
		return indicators;
	}
}
