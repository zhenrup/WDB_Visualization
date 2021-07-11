import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestLine {
	
	/**
	 * Class TestLine creates and renders LineChart objects for all the analysis types it is compatible with (A1, A2, A3, A6, A7, A8)
	 * The variables are the top allow the tester to change the year range and country indicator
	 * @author group15
	 */

	public static void main(String[] args) {
		
		WBReader read = new WBReader();
		
		int startYear = 2010; // set startYear to desired year
		int endYear = 2015; //set endYear to desired year
		String country = "can"; // set country indicator to desired country

		JFrame f = new JFrame();
		JPanel pane = new JPanel(new GridLayout(2, 3));
		
		// creates all the analysis type objects
		A1 aOne = new A1(read.readData(country, "EN.ATM.CO2E.PC", startYear, endYear), read.readData(country, "EG.USE.PCAP.KG.OE", startYear, endYear),read.readData(country, "EN.ATM.PM25.MC.M3", startYear, endYear));
		A2 aTwo = new A2(read.readData(country, "EN.ATM.PM25.MC.M3", startYear, endYear), read.readData(country, "AG.LND.FRST.ZS", startYear, endYear));
		A3 aThree = new A3(read.readData(country, "EN.ATM.CO2E.PC", startYear, endYear), read.readData(country, "NY.GDP.PCAP.CD", startYear, endYear));
		A6 aSix = new A6(read.readData(country, "SH.MED.BEDS.ZS", startYear, endYear), read.readData(country, "SH.XPD.CHEX.PC.CD", startYear, endYear));
		A7 aSeven = new A7(read.readData(country, "SH.XPD.CHEX.PC.CD", startYear, endYear), read.readData(country, "SP.DYN.IMRT.IN", startYear, endYear));
		A8 aEight = new A8(read.readData(country, "SE.XPD.TOTL.GD.ZS", startYear, endYear), read.readData(country, "SH.XPD.CHEX.GD.ZS", startYear, endYear));
		
		// 2D String array used for the legends of the charts
		String[][] a1Series = {{"CO2 emissions", "PM2.5 air pollution, mean annual exposure"}, {"Energy Use"}};
		String[][] a2Series = {{"PM2.5 air pollution, mean annual exposure"}, {"Forest area"}};
		String[][] a3Series = {{"Ratio of CO2 emissions"}, {"GDP per capita"}};
		String[][] a6Series = {{"Ratio of Hospital beds"}, {"Current health expenditure"}};
		String[][] a7Series = {{"Current health expenditure per capita"}, {"Mortality rate, infant"}};
		String[][] a8Series = {{"Ratio of Government expenditure on education, total", "Current health expenditure"}};
		
		// creates and renders charts
		LineChart sOne = new LineChart(aOne.getInstTitle(), aOne.calculate(), startYear, endYear, "Years", aOne.getUnits(), a1Series);
		pane.add(new JScrollPane(sOne.buildGraph()));
		
		LineChart sTwo = new LineChart(aTwo.getInstTitle(), aTwo.calculate(), startYear, endYear, "Years", aTwo.getUnits(), a2Series);
		pane.add(new JScrollPane(sTwo.buildGraph()));
		
		LineChart sThree = new LineChart(aThree.getInstTitle(), aThree.calculate(), startYear, endYear, "Years", aThree.getUnits(), a3Series);
		pane.add(new JScrollPane(sThree.buildGraph()));
		
		LineChart sSix = new LineChart(aSix.getInstTitle(), aSix.calculate(), startYear, endYear, "Years", aSix.getUnits(), a6Series);
		pane.add(new JScrollPane(sSix.buildGraph()));
		
		LineChart sSeven = new LineChart(aSeven.getInstTitle(), aSeven.calculate(), startYear, endYear, "Years", aSeven.getUnits(), a7Series);
		pane.add(new JScrollPane(sSeven.buildGraph()));
		
		LineChart sEight = new LineChart(aEight.getInstTitle(), aEight.calculate(), startYear, endYear, "Years", aEight.getUnits(), a8Series);
		pane.add(new JScrollPane(sEight.buildGraph()));
		
		
		f.getContentPane().add(pane);
		f.pack();
		f.setVisible(true);
	}

}
