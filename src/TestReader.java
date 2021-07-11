import java.util.ArrayList;
/**
 * Test class for the Reader
 * @author Group 15
 */
public class TestReader {
	
	public static void main (String[] args) {
		
		int startYear = 1962, endYear = 2020;
		WBReader rd = new WBReader();
		
		// total population analysis
		System.out.println("Total Population");
		ArrayList<Double> info =rd.readData("can", "SP.POP.TOTL", 1962, 2020);
		//how to error check for non-existent years
		if (info.size() < endYear-startYear+1) {
			startYear = endYear - info.size()+1;
		}
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+startYear)+", "+info.get(i));
		}
		
		// co2 emissions (metric ton per capita)
		System.out.println("CO2 Emissions (Metric Ton per Capita");
		info = rd.readData("can", "EN.ATM.CO2E.PC", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)
		System.out.println("PM2.5 air pollution, mean annual exposure (micrograms per cubic meter");
		info = rd.readData("can", "EN.ATM.PM25.MC.M3", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// Forest area (% of land area)
		System.out.println("Forest area (% of land area)");
		info = rd.readData("can", "AG.LND.FRST.ZS", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// Energy use (kg of oil equivalent per capita)
		System.out.println("Energy use (kg of oil equivalent per capita)");
		info = rd.readData("can", "EG.USE.PCAP.KG.OE", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// GDP per capita (current US$)
		System.out.println("GDP per capita (current US$)");
		info = rd.readData("can", "NY.GDP.PCAP.CD", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		
		
		//HEALTH
		// Hospital beds (per 1,000 people)
		System.out.println("Hospital beds (per 1000 people)");
		info = rd.readData("can", "SH.MED.BEDS.ZS", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// Government expenditure on education, total (% of GDP)
		System.out.println("Government expenditure on education, total (% of GDP)");
		info = rd.readData("can", "SE.XPD.TOTL.GD.ZS", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// Current health expenditure per capita (current US$)
		System.out.println("Current health expenditure per capita (current US$)");
		info = rd.readData("can", "SH.XPD.CHEX.PC.CD", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
		// Current health expenditure (% of GDP)
		System.out.println("Current health expenditure (% of GDP)");
		info = rd.readData("can", "SH.XPD.CHEX.GD.ZS", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		 
		// Mortality rate, infant (per 1,000 live births)
		System.out.println("Mortality rate, infant (per 1,000 live births)");
		info = rd.readData("can", "SP.DYN.IMRT.IN", 1980, 2009);
		for (int i = 0; i < info.size(); i++) {
			System.out.println("Year : "+(i+1980)+", "+info.get(i));
		}
		
	}
}

