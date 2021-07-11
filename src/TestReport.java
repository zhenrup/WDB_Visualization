import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * testing the creation of a report for each compatible analysis type
 * @author Group 15
 *
 */
public class TestReport {
	
	public static void main (String[] args) {
		WBReader read = new WBReader();
		int [] testYears = {2009, 2010, 2011, 2012};
	
		//Initializing Jframe and panels
		JFrame f = new JFrame();
		JPanel pane = new JPanel(new GridLayout(2, 4));
		f.getContentPane().add(pane);
		f.pack();
		f.setVisible(true);
		
		//creation of analysis objects
		A1 aOne = new A1(read.readData("can", "EN.ATM.CO2E.PC", testYears[0], testYears[3]), read.readData("can", "EG.USE.PCAP.KG.OE", testYears[0], testYears[3]),read.readData("can", "EN.ATM.PM25.MC.M3", testYears[0], testYears[3]));
		A2 aTwo = new A2(read.readData("can", "EN.ATM.PM25.MC.M3", testYears[0], testYears[3]), read.readData("can", "AG.LND.FRST.ZS", testYears[0], testYears[3]));
		A3 aThree = new A3(read.readData("can", "EN.ATM.CO2E.PC", testYears[0], testYears[3]), read.readData("can", "NY.GDP.PCAP.CD", testYears[0], testYears[3]));
		A4 aFour = new A4(read.readData("can", "AG.LND.FRST.ZS", testYears[0], testYears[3]));
		A5 aFive = new A5(read.readData("can", "SE.XPD.TOTL.GD.ZS", testYears[0], testYears[3]));
		A6 aSix = new A6(read.readData("can", "SH.MED.BEDS.ZS", testYears[0], testYears[3]), read.readData("can", "SH.XPD.CHEX.PC.CD", testYears[0], testYears[3]));
		A7 aSeven = new A7(read.readData("can", "SH.XPD.CHEX.PC.CD", testYears[0], testYears[3]), read.readData("can", "SP.DYN.IMRT.IN", testYears[0], testYears[3]));
		A8 aEight = new A8(read.readData("can", "SE.XPD.TOTL.GD.ZS", testYears[0], testYears[3]), read.readData("can", "SH.XPD.CHEX.GD.ZS", testYears[0], testYears[3]));
		
		
		//creation of each report object for each analysis type
		Report rOne = new Report(aOne.getInstTitle(), aOne.calculate(), testYears[0], testYears[3], aOne.getUnits());
		pane.add(new JScrollPane(rOne.buildGraph()));
		
		Report rTwo = new Report(aTwo.getInstTitle(), aTwo.calculate(), testYears[0], testYears[3], aTwo.getUnits());
		pane.add(new JScrollPane(rTwo.buildGraph()));
		
		Report rThree = new Report(aThree.getInstTitle(), aThree.calculate(), testYears[0], testYears[3], aThree.getUnits());
		pane.add(new JScrollPane(rThree.buildGraph()));
		
		Report rFour = new Report(aFour.getInstTitle(), aFour.calculate(), testYears[0], testYears[3], aFour.getUnits());
		pane.add(new JScrollPane(rFour.buildGraph()));
		
		Report rFive = new Report(aFive.getInstTitle(), aFive.calculate(), testYears[0], testYears[3], aFive.getUnits());
		pane.add(new JScrollPane(rFive.buildGraph()));
		
		Report rSix = new Report(aSix.getInstTitle(), aSix.calculate(), testYears[0], testYears[3], aSix.getUnits());
		pane.add(new JScrollPane(rSix.buildGraph()));
		
		Report rSeven = new Report(aSeven.getInstTitle(), aSeven.calculate(), testYears[0], testYears[3], aSeven.getUnits());
		pane.add(new JScrollPane(rSeven.buildGraph()));
		
		Report rEight = new Report(aEight.getInstTitle(), aEight.calculate(), testYears[0], testYears[3], aEight.getUnits());
		pane.add(new JScrollPane(rEight.buildGraph()));
		
		f.pack();
		
	}
}
