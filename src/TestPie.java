import java.util.ArrayList;

import javax.swing.JFrame;
/**
 * test class for creating pie charts
 * @author Group 15
 *
 */
public class TestPie {

	public static void main(String[] args) {
		WBReader reader = new WBReader();
		int startYear = 2010;
		int endYear = 2020;
		
		// testing a4
		System.out.println("Testing A4");
		
		ArrayList<Double> forestArea = reader.readData("can", "AG.LND.FRST.ZS", startYear, endYear);
		for (int i = 0; i < forestArea.size(); i++) {
			System.out.println("Year : "+(i+startYear)+", "+forestArea.get(i));
		}
		
		A4 a4 = new A4(forestArea);
		Data[] a4Datasets = a4.calculate();
		
		PieChart a4Chart = new PieChart("A4", a4Datasets, startYear, endYear);
		Viewers a4Graph = a4Chart.buildGraph();
		JFrame a4f = new JFrame();
		a4f.add(a4Graph);
		a4f.pack();
		a4f.setVisible(true);
		
		// testing a5
		System.out.println("Testing A5");
		
		ArrayList<Double> expenditure = reader.readData("can", "SE.XPD.TOTL.GD.ZS", startYear, endYear);
		for (int i = 0; i < expenditure.size(); i++) {
			System.out.println("Year : "+(i+startYear)+", "+expenditure.get(i));
		}
		
		A5 a5 = new A5(expenditure);
		Data[] a5Datasets = a5.calculate();
		
		PieChart a5Chart = new PieChart("A5", a5Datasets, startYear, endYear);
		Viewers a5Graph = a5Chart.buildGraph();
		JFrame a5f = new JFrame();
		a5f.add(a5Graph);
		a5f.pack();
		a5f.setVisible(true);
	}
}
