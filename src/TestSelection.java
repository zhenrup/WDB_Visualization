/**
 * testing whether the selection class sets variables correctly and error checks correctly
 * @author Group 15
 *
 */
public class TestSelection {

	public static void main(String[] args) {
		//creation of selection object
		Selection se = new Selection();

		//setting analysis
		se.setAnalysis("SH.XPD.CHEX.PC.CD");
		se.setAnalysis("SP.DYN.IMRT.IN");
		
		//setting country
		se.setCountry("IND");
		se.printAnalysisList();	//array list stored elements
		
		System.out.println(se.compareCountryInput("AFG"));
		
		//setting the year range
		se.setStartYear(1977);
		se.setEndYear(2018);
		
		//printing to see results
		System.out.println(se.compareYearRange());
		System.out.println("Start: "+se.getStartYear());
		System.out.println("End: "+ se.getEndYear());
		
		System.out.println(se.updateCompatibleViewers("Average Forest Area"));

	}
		
}
	

