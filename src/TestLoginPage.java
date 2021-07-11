import javax.swing.JFrame;

/**
 * testing the login page UI, testing visibility of login page
 * @author Group 15
 *
 */
public class TestLoginPage {
	
	public static void main(String[] a){
		
		//creaing login page object
        LoginPage frame = new LoginPage();
        
        //setting frame details
        frame.setTitle("Login Page");
        frame.setVisible(true);
        frame.setBounds(10,10,300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
      
   

	}
}
