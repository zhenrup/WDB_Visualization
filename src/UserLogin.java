
import java.io.File;
import java.io.IOException;
import java.util.*;  
import java.io.*;


/**
 * Class that checks whether the user credentials match those in the credentials database when a user is logging on 
 * @author Group 15
 *
 */
public class UserLogin {

	//variable declaration
	private User user = User.getInstance("", "");
	private ArrayList<ArrayList<String>> credentials =  new ArrayList<ArrayList<String>>();
	
	/**
	 * Class constructor
	 * Reads all credentials in "credentials.xlsx" and store all username and password into 2D Arraylist credentials
	 * "credentials.txt" needs to be within the java project but outside of src folder
	 */
	public UserLogin() {
		try {
			File file = new File("credential.txt"); //credentials database for checking
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			String st = br.readLine();
			
			//reading the credentials database
			while (st != null) {
				String[] elements= st.split(" ");
				List<String> fixedLengthList = Arrays.asList(elements);
				ArrayList<String> cr = new ArrayList<String>(fixedLengthList);
				credentials.add(cr);
				st = br.readLine();
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * sets the users user name and password
	 * @param username
	 * @param password
	 */
	public void setUser(String n, String p) {
		user.setName(n);
		user.setPassword(p);
	}
	
	
	/**
	 * Compare user input with credentials. 
	 * @return Return true if user name and password matches, false otherwise
	 */
	public boolean compareInput() {
		
		String name = user.getName();
		String pw = user.getPassword();

		//searching through credentials database to find a match with the user input
		for(int i = 0; i < credentials.size(); i++) {
			if((credentials.get(i).get(0).equals(name)) && (credentials.get(i).get(1).equals(pw))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A class to test credentials array list is stored properly or not
	 */
	public void printCredentialList() {
		for(int i = 0; i < credentials.size(); i++) {
			for(int j = 0; j < credentials.get(i).size(); j++) {
				System.out.println(credentials.get(i).get(j));
			}
		}		
	}
}
