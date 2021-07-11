import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.io.FileWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Class CreateCountryAnalysisDB: creates a textfile with available analysis types and years for each country
 * This database will be used by Selection to validate user choices
 * @author Group 15
 */

public class CreateCountryAnalysisDB {
	
	/**
	 * Creates a text file with available analysis types for each country
	 * @param fileName
	 */
	@SuppressWarnings("unchecked")
	public static void createDB(String fileName) {
		
		// creates the URL needed to access the World Bank data using the formatting specified by the API
		final String[] TYPES = {"SP.POP.TOTL", "EN.ATM.CO2E.PC", "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS", "EG.USE.PCAP.KG.OE", 
				"NY.GDP.PCAP.CD", "SH.MED.BEDS.ZS", "SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.PC.CD", "SH.XPD.CHEX.GD.ZS", "SP.DYN.IMRT.IN"};
		
		HashMap<String, ArrayList<Integer>[]> dict = new HashMap<String, ArrayList<Integer>[]>();
		
		for (int j = 0; j < TYPES.length; j++) {
			String urlString = String.format("http://api.worldbank.org/v2/country/all/indicator/%s?format=json", TYPES[j]); //accessing the world bank api
			int numPages = 1;
			
			try {
				
				for (int i = 1; i <= numPages; i++) {
					//connecting to the API
					URL url = new URL (urlString+"&page="+i);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					
					int responseCode = conn.getResponseCode();
					
					//successful connection
					if (responseCode == 200) {
						String inline = "";
						Scanner sc = new Scanner(url.openStream()); //Initializing scanner
						
						//scanning each line
						while(sc.hasNext()) {
							inline += sc.nextLine();
						}
						sc.close(); //close scanner
						
						JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
						
						int tempPages = jsonArray.get(0).getAsJsonObject().get("pages").getAsInt();
						if (tempPages != numPages) {
							numPages = tempPages;
						}
						
						JsonArray info = jsonArray.get(1).getAsJsonArray();
						int sizeOfResults = info.size();
						String cCountry = "";
						int cDate = 0;
						JsonElement cValue = null;
						for (int k = 0; k < sizeOfResults; k++) {
							cCountry = info.get(k).getAsJsonObject().get("countryiso3code").getAsString();
							cDate = info.get(k).getAsJsonObject().get("date").getAsInt();
							cValue = info.get(k).getAsJsonObject().get("value");
							if (!cValue.isJsonNull() && !cCountry.equals("")) {
								if (!dict.containsKey(cCountry)) {
									dict.put(cCountry, (ArrayList<Integer>[])(new ArrayList[TYPES.length]));
								}
								if (dict.get(cCountry)[j] == null) {
									dict.get(cCountry)[j] = new ArrayList<Integer>();
								}
								dict.get(cCountry)[j].add(cDate);
							}
						}
					}
				}
			}
			catch (IOException ioe) {
				
			}
		}
		try {
			FileWriter fw = new FileWriter(fileName);
			String line = "";
			fw.write("Country analysis1[year1,year2,...];analysis2[year1,...]\n");
			Set<String> keys = dict.keySet();
			ArrayList<Integer>[] aYears = null;
			for (String cKey : keys) {
				line = cKey+" ";
				aYears = dict.get(cKey);
				for (int i = 0; i < aYears.length; i++) {
					line += TYPES[i]+"[";
					if (aYears[i] != null) {
						for (int k = 0; k < aYears[i].size(); k++) {
							line += aYears[i].get(k) + ",";
						}
						line = line.substring(0, line.length()-1);
					}
					line += "];";
				}
				line = line.substring(0, line.length()-1) + "\n";
				fw.write(line);
			}
			fw.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Main invokes createDB for Selection to reference and validate user choices
	 * @param args
	 */
	public static void main (String[] args) {
		createDB("CountryAnalysisComboDB.txt");
	}
}
