/**
 * User object created when system is first run, includes their username and password
 * @author Group 15
 *
 */
public class User {
	
	//variable declaration
	private static User instance = null;
	private String name;
	private String password;
	
	/**
	 * Private constructor follows singleton design pattern
	 * @param id
	 * @param pw
	 */
	private User(String id, String pw) {
		name = id;
		password = pw;
	}
	
	/**
	 * Public static getInstance() follows singleton design pattern
	 * @param id
	 * @param pw
	 * @return User
	 */
	public static User getInstance(String id, String pw) {
		if (instance == null) {
			instance = new User(id, pw);
		}
		return instance;
	}

	/**
	 * set method to set the user name inputed by the user
	 * @param username
	 */
	public void setName(String username) {
		name = username;
	}
	
	/**
	 * set method to set the password inputed by the user
	 * @param pw
	 */
	public void setPassword(String pw) {
		password = pw;
	}
	
	/**
	 * get method that returns the user name of the user
	 * @return user name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get method that returns the user's password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	
}