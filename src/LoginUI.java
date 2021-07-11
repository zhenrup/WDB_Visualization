import javax.swing.SwingUtilities;

/**
 * Main login page for signing in and changing to the main UI page 
 * @author Group 15
 *
 */
public class LoginUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginPage login = new LoginPage();
				login.setVisible(true);
			}
		});
	}
}
