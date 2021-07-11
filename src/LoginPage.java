import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates user login window
 * @author Group 15
 *
 */
public class LoginPage extends JFrame {

	//color
	private Color greyBackground = new java.awt.Color(51, 51, 51);
	private Color greenLabel = new java.awt.Color(158, 235, 52);
	
	//font
	private Font boldFont = new Font("Poppins", Font.BOLD, 14);
	
	//variable declaration
	private UserLogin login = new UserLogin();
	private Container container = getContentPane();
	private JLabel userLabel = new JLabel("Username: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JTextField userIn = new JTextField();
	private JTextField passwordIn = new JTextField();
	private JButton loginButton = new JButton("Submit");
	
	/**
	 * Constructor creates the login page UI
	 * inherits from JFrame
	 */
	public LoginPage(){
		setLayout();

		setLocationAndSize();
	    
		addCompToContainer();
		
		//setting login page UI visual details
		setTitle("Login Page");
	    setBounds(10,10,300,200);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setBackground(greyBackground);
	    setResizable(false);
	    
	    //listener for when login button is pressed after user inputs credential details
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//after user click submit button, read user input
				if(e.getSource() == loginButton) {
					String userText = userIn.getText();
					String passwordText = passwordIn.getText();
					login.setUser(userText, passwordText);
					//if user input matches credential, display main UI window
					if(login.compareInput()) {
						JOptionPane.showMessageDialog(loginButton, "Login Successful");
						dispose();
						
						//open main UI window
						MainUI frame = MainUI.getInstance();
						frame.setSize(1500,800);
						frame.setVisible(true);
					
					//terminate program if user input does not match credential
					}else {
						JOptionPane.showMessageDialog(loginButton, "Invalid Credential. Program Terminated.");
						dispose();
					}
				}
			}
		});	
	}
	
	/**
	 * setting the layout to be empty initially
	 */
	public void setLayout() {
		container.setLayout(null);
	}
	
	/**
	 * creates the size and color of the text box and JButtom
	 */
	public void setLocationAndSize(){
		userLabel.setBounds(20,20,100,30);
	       passwordLabel.setBounds(20,60,100,30);
	       userIn.setBounds(110,20,150,30);
	       passwordIn.setBounds(110,60,150,30);
	       loginButton.setBounds(90,110,100,30);
       
       userLabel.setFont(boldFont);
       userLabel.setForeground(greenLabel);
       
       passwordLabel.setFont(boldFont);
       passwordLabel.setForeground(greenLabel);
       
       loginButton.setFont(boldFont);
       loginButton.setForeground(greenLabel);
       loginButton.setBackground(greyBackground);
	}
	
	/**
	 * adding labels and buttons the container
	 */
	public void addCompToContainer() {
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userIn);
		container.add(passwordIn);
		container.add(loginButton);
	}

}
