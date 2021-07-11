import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**Creates Report object that displays a written report of the data selected
 * inherits from Viewers
 * @author Group 15
 */
public class Report extends Viewers{
	
	/**
	 * The text JComponent which will be displayed in the report
	 */
	private JTextArea text;
	/**
	 * The string representation of the displayed text
	 */
	private String textString;

	/**
	 * Constructor for creating the Report object
	 * @param title
	 * @param datasets
	 * @param startYear
	 * @param endYear
	 * @param units
	 */
	public Report (String title, Data[] datasets, int startYear, int endYear, String[] units) {
		
		//variable declaration
		this.title = title;
		this.datasets = datasets;
		this.startYear = startYear;
		this.endYear = endYear;
		
		//text area declaration
		text = new JTextArea();
		
		//sets it so the user cannot edit the text of the report
		text.setEditable(false);
		//creates a border around the report for better visibility
		text.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		//sets the background of the text area to white so that text can be read
		text.setBackground(Color.white);
		
		// Generates the textString
		textString = title + "\n";
		
		//creating a spacer in the text to separate the title from the data
		int numOfEquals = textString.length()/4;
		for (int i = 0; i < numOfEquals; i++) {
			textString += "=";
		}
		textString += "\n";
		
		// if the datasets only contain one value, then it prints out that the one value applies for the whole year range
		if (datasets.length == 1 && datasets[0].getDataset().length == 1 && datasets[0].getDataset()[0].size() == 1 && units.length == 1) {
			textString += "From "+startYear+" to "+endYear+"\n";
			//formatting for even column spacing
			textString += String.format("\t%20s", units[0]);
			textString += String.format("\t%-10.2f", datasets[0].getDataset()[0].get(0))+"\n";
		}
		//otherwise, it will print out each year and the values for each year, along with their units
		else {
			int labelCounter;
			for (int i = endYear; i >= startYear; i--) {
				textString += "Year: "+i+"\n"; //prints out the year
				labelCounter = 0; //counts which is the current unit that should be displayed
				for (int j = 0; j < datasets.length; j++) {
					ArrayList<Double>[] info = datasets[j].getDataset();
					for (int k = 0; k < info.length; k++) {
						//formatting for even spacing between the units and the values
						textString += String.format("\t%20s", units[labelCounter]);
						textString += String.format("\t%-10.2f", info[k].get(i-startYear))+"\n";
						labelCounter++;	
					}
				}
			}
		}
	}
	

	
	/**
	 * buildGraph() inherited from abstract class Viewers
	 * creates graph image
	 * @return Viewers
	 */
	public Viewers buildGraph() {
		text.setText(textString); //sets the textarea to display the constructed string
		this.removeAll();// to avoid double text if the graph is built more than once
		this.add(text);// adds the text to the Viewer
		this.setBackground(new java.awt.Color(51, 51, 51)); //sets the viewer background colour
		return this;
	}
	
}
