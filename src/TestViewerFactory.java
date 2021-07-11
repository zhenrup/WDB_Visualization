import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * Test class to test the creation of viewer objects
 * @author Group 15
 *
 */
public class TestViewerFactory {
	
	public static void main(String[] a){
		
		//initialising test variables
		ViewerFactory vf = new ViewerFactory();
		int startYear = 2012;
		int endYear = 2020;
		String country = "can";
		String countryName = "Canada";
		//initialising reader object
		WBReader read = new WBReader();
		
		//initialising the frame and panel for display
		JFrame f1 = new JFrame();
		JFrame f2 = new JFrame();
		JPanel pane1 = new JPanel(new GridLayout(2, 3));
		JPanel pane2 = new JPanel(new GridLayout(2, 3));
		
		//creation of each analysis object (1-8)
		A1 aOne = new A1(read.readData(country, "EN.ATM.CO2E.PC", startYear, endYear), read.readData(country, "EG.USE.PCAP.KG.OE", startYear, endYear),read.readData(country, "EN.ATM.PM25.MC.M3", startYear, endYear));
		A2 aTwo = new A2(read.readData(country, "EN.ATM.PM25.MC.M3", startYear, endYear), read.readData(country, "AG.LND.FRST.ZS", startYear, endYear));
		A3 aThree = new A3(read.readData(country, "EN.ATM.CO2E.PC", startYear, endYear), read.readData(country, "NY.GDP.PCAP.CD", startYear, endYear));
		A4 aFour = new A4(read.readData(country, "AG.LND.FRST.ZS", startYear, endYear));
		A5 aFive = new A5(read.readData(country, "SE.XPD.TOTL.GD.ZS", startYear, endYear));
		A6 aSix = new A6(read.readData(country, "SH.MED.BEDS.ZS", startYear, endYear), read.readData(country, "SH.XPD.CHEX.PC.CD", startYear, endYear));
		A7 aSeven = new A7(read.readData(country, "SH.XPD.CHEX.PC.CD", startYear, endYear), read.readData(country, "SP.DYN.IMRT.IN", startYear, endYear));
		A8 aEight = new A8(read.readData(country, "SE.XPD.TOTL.GD.ZS", startYear, endYear), read.readData(country, "SH.XPD.CHEX.GD.ZS", startYear, endYear));
		
		//test: create bar chart viewer
		Viewers b1 = vf.createViewer("bar", aOne, startYear, endYear, countryName).buildGraph();
		Viewers b2 = vf.createViewer("bar", aTwo, startYear, endYear, countryName).buildGraph();
		
		//test: create line chart viewer
		Viewers l1 = vf.createViewer("line", aThree, startYear, endYear, countryName).buildGraph();
		Viewers l2 = vf.createViewer("line", aTwo, startYear, endYear, countryName).buildGraph();
		
		//test: create pie chart viewer
		Viewers p1 = vf.createViewer("pie", aFour, startYear, endYear, countryName).buildGraph();
		Viewers p2 = vf.createViewer("pie", aFive, startYear, endYear, countryName).buildGraph();
		
		//test: create scatter plot viewer
		Viewers s1 = vf.createViewer("scatter", aSeven, startYear, endYear, countryName).buildGraph();
		Viewers s2 = vf.createViewer("scatter", aEight, startYear, endYear, countryName).buildGraph();
		
		//test: create report viewer
		Viewers r1 = vf.createViewer("report", aSix, startYear, endYear, countryName).buildGraph();
				
		//adding all viewers to a pane
		pane1.add(new JScrollPane(b1));
		pane1.add(new JScrollPane(b2));
		pane1.add(new JScrollPane(l1));
		pane1.add(new JScrollPane(l2));
		pane1.add(new JScrollPane(p1));
		pane1.add(new JScrollPane(p2));
		
		pane2.add(new JScrollPane(s1));
		pane2.add(new JScrollPane(s2));
		pane2.add(new JScrollPane(r1));
		
		//adding all panes to the frame 
		f1.add(pane1);
		f1.pack();
		f1.setVisible(true);
		
		f2.add(pane2);
		f2.pack();
		f2.setVisible(true);
	}
}
