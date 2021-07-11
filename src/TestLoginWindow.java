
/**
 * testing login window creation and login credentials check
 * @author Group 15
 *
 */
public class TestLoginWindow {
	
	public static void main(String[] args) {
		//create user login object
		UserLogin obj = new UserLogin();	
		
		//Test 1a: compare user input with credential excel
		boolean result = obj.compareInput();
		
		//display results
		if(result) {
			System.out.println("username and password are found in credentials");
		}
		else System.out.println("username and password are not found");
	}

}