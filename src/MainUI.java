import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainUI instance;
	
	// stores important information
	private ArrayList<String> countryList = new ArrayList<String>(); // stores country names
	private ArrayList<String> countryCodeList = new ArrayList<String>(); // stores country ISO3 code names

	private static int startYear = 1962; // start year for year selection
	private static int endYear = 2021; // end year for year selection
	JComboBox<String> fromList;
	JComboBox<String> toList;
	
	// colours
	private Color greyBackground = new java.awt.Color(51, 51, 51);
	private Color greenLabel = new java.awt.Color(158, 235, 52);
	private Color greyComboBox = new java.awt.Color(102, 102, 102);
	private Color white = new java.awt.Color(255, 255, 255);
	
	// fonts
	private Font boldFont = new Font("Poppins", Font.BOLD, 14);
	private Font regularFont = new Font("Poppins", Font.PLAIN, 14);
	
	//buttons
	private JButton recalculate;
	private JButton addView;
	private JButton removeView;
	
	
	//selections
	private String selectedCountry;
	private String selectedViewer;
	private String selectedAnalysis;
	private String selectedStart;
	private String selectedEnd;
	private WBReader read;
	private ArrayList<String> analysisList;
	
	private Selection selection;
	
	// JPanel
	private JPanel north;
	private JPanel east;
	private JPanel west;
	private JPanel south;
	
	
	//JLabel
	private JLabel from;
	private JLabel to;
	private JLabel viewsLabel;
	private JLabel chooseCountryLabel;
	
	// combo boxes
	private JComboBox<String> countriesList;
	
	//performing analysis to get viewer
	private ArrayList<Double> data1;
	private ArrayList<Double> data2;
	private ArrayList<Double> data3;
	private Data dataset;
	private JPanel currentGraph;
	private ArrayList<Viewers> viewersList;
	private int viewerCount;
	private String countryFullName;
	private JComboBox<String> viewsList;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		// Set window title
		super("World Bank Database Visualization");
		//instance = this;
		selection = new Selection();
		viewersList = new ArrayList<Viewers>();
		read = new WBReader();
		
		JLabel methodLabel = createLabel("Choose Analysis Method: ");

		Vector<String> methodsNames = new Vector<String>(Arrays.asList(selection.getTitles()));
		/*
		methodsNames.add("CO2 Emissions vs. Energy Use vs. PM2.5 Air Pollution");
		methodsNames.add("PM2.5 Air Pollution vs. Forest Area");
		methodsNames.add("Ratio CO2 Emissions and GDP per Capita");
		methodsNames.add("Average Forest Area");
		methodsNames.add("Average of Government Expenditure on Education");
		methodsNames.add("Ratio of Hospital Beds and Current Health Expenditure");
		methodsNames.add("Current Health Expenses per Capita vs. Infant Mortality Rate");
		methodsNames.add("Ratio of Government Expenditure on Education vs. Current Health Expenditure");*/

		JComboBox<String> methodsList = createComboBox(methodsNames);
		methodsList.setPreferredSize(new Dimension(500,25));
		
		populateCountriesList("country_list.csv");

		analysisList = new ArrayList<String>();
		// Set top bar
		chooseCountryLabel = createLabel("Choose a Country: ");
		
		Vector<String> countriesNames = new Vector<String>();
		for(int i = 0; i < countryList.size(); i++) {
			countriesNames.add(countryList.get(i));
		}
		countriesNames.sort(null);
		countriesList = createComboBox(countriesNames);
		countriesList.setPreferredSize(new Dimension(400,25));

		
		from = createLabel("From ");
		to = createLabel("To ");


		Vector<String> years = new Vector<String>();
		for (int i = endYear; i >= startYear; i--) {
			years.add("" + i);
		}
		
		fromList = createComboBox(years);
		toList = createComboBox(years);
		
		north = new JPanel();
		north.setBackground(greyBackground);
		north.add(methodLabel);
		north.add(methodsList);
		north.add(chooseCountryLabel);
		north.add(countriesList);

		// Set bottom bar
		recalculate = createButton("Recalculate");
		recalculate.setForeground(greenLabel);
		recalculate.setVisible(false);
		
		viewsLabel = createLabel("Available Views: ");
		
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewsList = createComboBox(viewsNames);
		addView = createButton("+");
		removeView = createButton("-");
	

		south = new JPanel();
		south.setBackground(greyBackground);
		south.add(from);
		south.add(fromList);
		south.add(to);
		south.add(toList);
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);
		south.add(recalculate);

		east = new JPanel();
		east.setBackground(greyBackground);
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setSize(size);

		// Set charts region
		west = new JPanel();
		west.setPreferredSize(new Dimension(getContentPane().getWidth()-east.getWidth(), getContentPane().getHeight()-north.getHeight()-south.getHeight()));
		pack();
		west.setBackground(greyBackground);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		
		//ADD TO MAIN UI
		
		//setting the drop down menus as invisible for now
		countriesList.setVisible(false);
		fromList.setVisible(false);
		toList.setVisible(false);
		viewsList.setVisible(false);
		from.setVisible(false);
		to.setVisible(false);
		recalculate.setVisible(false);
		addView.setVisible(false);
		removeView.setVisible(false);
		viewsLabel.setVisible(false);
		chooseCountryLabel.setVisible(false);
		//ADD TO MAIN UI
		
		
		//retrieving analysis type 1st
		methodsList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {

				//hide windows other than analysis list
				fromList.setVisible(false);
				toList.setVisible(false);
				viewsList.setVisible(false);
				from.setVisible(false);
				to.setVisible(false);
				recalculate.setVisible(false);
				addView.setVisible(false);
				removeView.setVisible(false);
				viewsLabel.setVisible(false);

				//store previous analysis
				String temp = "";
				if(selectedAnalysis != null) {
					temp = selectedAnalysis;
				}

				JComboBox<String>combo= (JComboBox<String>) event.getSource();
				selectedAnalysis = (String) combo.getSelectedItem();
				selection.setIns_Analysis(selectedAnalysis);
				//compare the previous and current analysis, if they are different, remove all vieweres
				if((temp != "") && (!temp.equals(selectedAnalysis))) {
					viewersList.clear();
					west.removeAll();
					west.repaint(); 
				}
				
				//update country combo box with compatible countries
				ArrayList<String> availableList = selection.availableCountry();
				countriesList.removeAllItems();
				for(int i = 0; i < availableList.size(); i ++) {
					countriesList.addItem(availableList.get(i));
				}
				viewerCount = 0;
				chooseCountryLabel.setVisible(true);
				countriesList.setVisible(true);
				
			}
		});
					
		//retrieving selected country 2nd
		countriesList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				toList.setVisible(false);
				viewsList.setVisible(false);
				to.setVisible(false);
				recalculate.setVisible(false);
				addView.setVisible(false);
				removeView.setVisible(false);
				viewsLabel.setVisible(false);
				
				JComboBox<String>combo= (JComboBox<String>) event.getSource();
				if (combo.getItemCount() > 0) {
					selectedCountry = (String) combo.getSelectedItem();
					selection.setCountry(selectedCountry);
					selection.setCountryFull(selectedCountry);
					if(selection.compareCountryInput(selectedCountry)) {
						from.setVisible(true);
						fromList.setVisible(true);
					}
				}
			}
		});
		
		//retrieving start year 3rd
		fromList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				viewsList.setVisible(false);
				recalculate.setVisible(false);
				addView.setVisible(false);
				removeView.setVisible(false);
				viewsLabel.setVisible(false);
				//store selected start year
				JComboBox<String>combo= (JComboBox<String>) event.getSource();
				selectedStart= (String) combo.getSelectedItem();
				selection.setStartYear(Integer.parseInt(selectedStart));
				to.setVisible(true);
				toList.setVisible(true);
			}
		});
				
		//retrieving end year 4th
		toList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				JComboBox<String>combo= (JComboBox<String>) event.getSource();
				
				viewsList.setVisible(false);
				addView.setVisible(false);
				removeView.setVisible(false);
				viewsLabel.setVisible(false);
				
				//store selected end year
				selectedEnd = (String) combo.getSelectedItem();
				selection.setEndYear(Integer.parseInt(selectedEnd));
				if(selection.compareYearRange()) {
					//update compatible viewers
					ArrayList<String> newViewList = selection.updateCompatibleViewers(selectedAnalysis);
					viewsList.removeAllItems();
					for(int i = 0; i < newViewList.size(); i++) {
						viewsList.addItem(newViewList.get(i));
					}
					recalculate.setVisible(true);					
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), " Processing is not available for the year range.");
				}
				
			}
		});
		
		//RECALCULATE BUTTON 
		//sets user inputs for the Selection object
		recalculate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				viewsList.setVisible(true);
				addView.setVisible(true);
				removeView.setVisible(true);
				viewsLabel.setVisible(true);

				analysisList = selection.getAnalysis();
				selection.generateAnalysis();
			}
		});
			
		// TO DO: add verification code and error handling for viewer selection here
		viewsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>)event.getSource();
				selectedViewer = (String) combo.getSelectedItem();
			
			}
		});
		
		//add viewer button pressed
		addView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
		
				Viewers newView = selection.generateViewer((String)viewsList.getSelectedItem());
				viewersList.add(newView);
				
				if(viewersList.size()<=1) {
					west.removeAll();
					west.setLayout(new GridLayout(1,1));
					for(Viewers v : viewersList) {
						west.setPreferredSize(new Dimension(getContentPane().getWidth()-east.getWidth(), getContentPane().getHeight()-north.getHeight()-south.getHeight()));
						west.add(v);
						
					}
					west.repaint();
				}
				
				else if(viewersList.size()==2) {
					west.removeAll();
					west.setLayout(new GridLayout(1,2));
					for(Viewers v : viewersList) {
						renderViewer(v, 1, 2);
					}
				}
				
				else if(viewersList.size()==3 || viewersList.size()==4) {
					west.removeAll();
					west.setLayout(new GridLayout(2,2));
					for(Viewers v : viewersList) {
						renderViewer(v, 2, 2);
					}
				}
				
				else if(viewersList.size()==5 || viewersList.size()==6) {
					west.removeAll();
					west.setLayout(new GridLayout(2, 3));
					for(Viewers v : viewersList) {
						renderViewer(v, 2, 3);
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Max number of viewers reached. Please remove a viewer before adding another one.");
				}

				pack();
			}
		});
		
		
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				Viewers newView = selection.generateViewer((String)viewsList.getSelectedItem());
				for (int i = 0; i < viewersList.size(); i++) {
					if (viewersList.get(i).equals(newView)) {
						viewersList.remove(i);
						break;
					}
				}
				
				if(viewersList.size()<=1) {
					west.removeAll();
					west.setLayout(new GridLayout(1,1));
					for(Viewers v : viewersList) {
						west.setPreferredSize(new Dimension(getContentPane().getWidth()-east.getWidth(), getContentPane().getHeight()-north.getHeight()-south.getHeight()));
						west.add(v);
					}
					west.repaint();
				}
				
				else if(viewersList.size()==2) {
					west.removeAll();
					west.setLayout(new GridLayout(1,2));
					for(Viewers v : viewersList) {
						renderViewer(v, 1, 2);
					}
				}
				
				else if(viewersList.size()==3 || viewersList.size()==4) {
					west.removeAll();
					west.setLayout(new GridLayout(2,2));
					for(Viewers v : viewersList) {
						renderViewer(v, 2, 2);
					}
				}
				
				else if(viewersList.size()==5 || viewersList.size()==6) {
					west.removeAll();
					west.setLayout(new GridLayout(2, 3));
					for(Viewers v : viewersList) {
						renderViewer(v, 2, 3);
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Max number of viewers reached. Please remove a viewer before adding another one.");
				}

				instance.pack();
			}
		});
		pack();
		methodsList.setSelectedIndex(0);
		countriesList.setSelectedIndex(0);
		fromList.setSelectedIndex(fromList.getItemCount()-1);
		toList.setSelectedIndex(0);
		recalculate.doClick();
		viewsList.setSelectedIndex(0);
		addView.doClick();
		
	}
	
	//returns the viewers list
	private ArrayList<Viewers> getViewList(){
		return viewersList;
	}
	
	// populates countryList based on country_list.csv
	private void populateCountriesList(String filename) {
		Reader in;
		try {
			in = new FileReader("country_list.csv");
			Iterable<CSVRecord> records = (CSVFormat.DEFAULT).parse(in);
		    //read each row in csv
			for (CSVRecord record: records) {
				countryList.add(record.get(1));
				countryCodeList.add(record.get(5));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	// creates and formats a label
	private JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		label.setForeground(greenLabel);
		label.setFont(boldFont);
		return label;
	}
	
	// creates and formats a combo box
	private JComboBox<String> createComboBox(Vector<String> list) {
		JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setFont(regularFont);
		comboBox.setBackground(greyComboBox);
		comboBox.setForeground(white);
		return comboBox;
	}
	
	// creates and formats a button
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setFont(boldFont);
		button.setBackground(greyComboBox);
		button.setForeground(white);
		return button;
	}
	
	
	private void renderViewer(Viewers v, int rows, int columns) {
		west.setPreferredSize(new Dimension(getContentPane().getWidth()-east.getWidth(), getContentPane().getHeight()-north.getHeight()-south.getHeight()));
		v.setPreferredSize(new Dimension((int) west.getSize().getWidth()/columns, (int) west.getSize().getHeight()/rows));
		v.repaint();
		if(v.getClass().getSimpleName().equals("Report")) {
			JScrollPane sp = new JScrollPane(v);
			sp.setPreferredSize(new Dimension((int) west.getSize().getWidth()/columns, (int) west.getSize().getHeight()/rows));
			west.add(sp);
		}
		else {
			west.add(v);
		}
		west.repaint();
	}
}