import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * testing scatter plot for all compatible analysis objects
 * @author Group 15
 *
 */
public class TestScatter {
	
	public static void main (String[] args) {
		WBReader read = new WBReader();
		int [] testYears = {1998, 2012};

		JFrame f = new JFrame();
		JPanel pane = new JPanel(new GridLayout(2, 3));
		
		//creating compatible analysis objects
		A1 aOne = new A1(read.readData("can", "EN.ATM.CO2E.PC", testYears[0], testYears[testYears.length-1]), read.readData("can", "EG.USE.PCAP.KG.OE", testYears[0], testYears[testYears.length-1]),read.readData("can", "EN.ATM.PM25.MC.M3", testYears[0], testYears[testYears.length-1]));
		A2 aTwo = new A2(read.readData("can", "EN.ATM.PM25.MC.M3", testYears[0], testYears[testYears.length-1]), read.readData("can", "AG.LND.FRST.ZS", testYears[0], testYears[testYears.length-1]));
		A3 aThree = new A3(read.readData("can", "EN.ATM.CO2E.PC", testYears[0], testYears[testYears.length-1]), read.readData("can", "NY.GDP.PCAP.CD", testYears[0], testYears[testYears.length-1]));
		A6 aSix = new A6(read.readData("can", "SH.MED.BEDS.ZS", testYears[0], testYears[testYears.length-1]), read.readData("can", "SH.XPD.CHEX.PC.CD", testYears[0], testYears[testYears.length-1]));
		A7 aSeven = new A7(read.readData("can", "SH.XPD.CHEX.PC.CD", testYears[0], testYears[testYears.length-1]), read.readData("can", "SP.DYN.IMRT.IN", testYears[0], testYears[testYears.length-1]));
		A8 aEight = new A8(read.readData("can", "SE.XPD.TOTL.GD.ZS", testYears[0], testYears[testYears.length-1]), read.readData("can", "SH.XPD.CHEX.GD.ZS", testYears[0], testYears[testYears.length-1]));
		
		//axes names for each analysis type
		String[][] a1Series = {{"CO2 emissions", "PM2.5 air pollution, mean annual exposure"}, {"Energy Use"}};
		String[][] a2Series = {{"PM2.5 air pollution, mean annual exposure"}, {"Forest area"}};
		String[][] a3Series = {{"Ratio of CO2 emissions"}, {"GDP per capita"}};
		String[][] a6Series = {{"Ratio of Hospital beds"}, {"Current health expenditure"}};
		String[][] a7Series = {{"Current health expenditure per capita"}, {"Mortality rate, infant"}};
		String[][] a8Series = {{"Ratio of Government expenditure on education, total", "Current health expenditure"}};
		
		//creating scatter plot for each analysis type
		ScatterChart sOne = new ScatterChart(aOne.getInstTitle(), aOne.calculate(), testYears[0], testYears[testYears.length-1], "Years", aOne.getUnits(), a1Series);
		pane.add(new JScrollPane(sOne.buildGraph()));
		
		ScatterChart sTwo = new ScatterChart(aTwo.getInstTitle(), aTwo.calculate(), testYears[0], testYears[testYears.length-1], "Years", aTwo.getUnits(), a2Series);
		pane.add(new JScrollPane(sTwo.buildGraph()));
		
		ScatterChart sThree = new ScatterChart(aThree.getInstTitle(), aThree.calculate(), testYears[0], testYears[testYears.length-1], "Years", aThree.getUnits(), a3Series);
		pane.add(new JScrollPane(sThree.buildGraph()));
		
		
		ScatterChart sSix = new ScatterChart(aSix.getInstTitle(), aSix.calculate(), testYears[0], testYears[testYears.length-1], "Years", aSix.getUnits(), a6Series);
		pane.add(new JScrollPane(sSix.buildGraph()));
		
		ScatterChart sSeven = new ScatterChart(aSeven.getInstTitle(), aSeven.calculate(), testYears[0], testYears[testYears.length-1], "Years", aSeven.getUnits(), a7Series);
		pane.add(new JScrollPane(sSeven.buildGraph()));
		
		ScatterChart sEight = new ScatterChart(aEight.getInstTitle(), aEight.calculate(), testYears[0], testYears[testYears.length-1], "Years", aEight.getUnits(), a8Series);
		pane.add(new JScrollPane(sEight.buildGraph()));
		
		
		//putting the scatter charts in view
		f.getContentPane().add(pane);
		f.pack();
		f.setVisible(true);
	}
}
