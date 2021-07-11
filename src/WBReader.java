import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Reader class that communicates with the World Bank API to extract analysis data
 * 
 * @author Group 15
 *
 */
public class WBReader {

	/**
	 * Accesses World Bank data for the specified parameter and returns the values as a double array
	 * @param country : the ISO3 country code that specifies which country's data will be extracted
	 * @param analysis : the World Bank API Code that specifies the type of data that will be accessed
	 * @param startYear : the start of the year range for which data will be pulled
	 * @param endYear : the end of the year range for which data will be pulled
	 * @return double[] containing the values of the analysis for each year, with index 0 being the start year and index length-1 being the end year
	 */
	public ArrayList<Double> readData (String country, String analysis, int startYear, int endYear) {
		// creates the URL needed to access the World Bank data using the formatting specified by the API
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", country, analysis, startYear, endYear);
		//initializes the List that will be returned
		ArrayList<Double> values = new ArrayList<Double>();
		int numPages = 1;
		try {
			for (int i = 1; i <= numPages; i++) {
				// creates the url by taking the base url string and adding the current page number to it
				URL url = new URL (urlString+"&page="+i);
				//establishes the connection to the World Bank by issuing remote GET requests
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				
				//gets the response code and continues with execution if the GET request succeeded
				int responseCode = conn.getResponseCode();
				if (responseCode == 200) {
					String inline = ""; //initializes variable that will hold the entire json containing the data from the World Bank
					Scanner sc = new Scanner(url.openStream()); //uses scanner to process the data stream from the World Bank
					//reads the entire json
					while (sc.hasNext()) {
						inline += sc.nextLine();
					}
					sc.close(); //closes the scanner
					// stores the JSON as a JsonArray
					JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
					int tempPages = jsonArray.get(0).getAsJsonObject().get("pages").getAsInt();
					if (tempPages != numPages) {
						numPages = tempPages;
					}
					
					//jsonArray.get(1) is an element consisting of a second jsonArray with all the actual analysis info
					JsonArray info = jsonArray.get(1).getAsJsonArray(); // do this because jsonArray.get(0) just has page format stuff
					int sizeOfResults = info.size(); 
					// initializing temporary variables for the date and data value
					JsonElement cDate = info.get(sizeOfResults-1).getAsJsonObject().get("date"); //gets the earliest data
					JsonElement cValue = null;
					
					for (int k = 0; k < sizeOfResults; k++) {
						//gets the current json array element (current year) of analysis data and then gets the specific needed fields in that element
						cDate = info.get(k).getAsJsonObject().get("date");
						cValue = info.get(k).getAsJsonObject().get("value");
						// error message for a missing year of data 
						if (cValue.isJsonNull()) {
							values.add(0.0); // empty entry in the list of data values
						}
						//otherwise, adds the current year of data values to the end of the list
						else {
							values.add(cValue.getAsDouble());
						}
					}
					
				}
			}
		}
		catch (IOException ioe){
			
		}
		//reverse the order of the values as currently it will be from newest data to oldest data
		for(int i = 0, j = values.size()-1; i < j; i++) {
	        values.add(i, values.remove(j));
	    }
		return values;
	}
}
