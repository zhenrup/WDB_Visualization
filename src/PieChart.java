import java.util.ArrayList;

import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Class PieChart
 * Compatible with A4, A5
 * Inherits from Viewers
 * @author Group 15
 */

public class PieChart extends Viewers{
	private ArrayList<Double> dataset; // stores the first dataset
	DefaultPieDataset datasetPIE; // stores the dataset as a DefaultPieDataset to create piechart
	private String yearTitle; // label for the sections of the pie chart
	
	/**
	 * Constructor stores information based on parameter values
	 * @param title: stores the title of the graph
	 * @param datasets: array of data objects that were returned from AnalysisType.calculate() 
	 * @param startYear
	 * @param endYear
	 */
	public PieChart(String title, Data[] datasets, int startYear, int endYear) {
		//setting the variables 
		this.title = title;
		this.startYear = startYear;
		this.endYear = endYear;
		
		//getting the dataset for the chart
		dataset = datasets[0].getDataset()[0];
		
		//creating title of graph
		yearTitle = String.valueOf(this.startYear).concat(" to ").concat(String.valueOf(this.endYear)).concat("(").concat(String.valueOf(Math.round(dataset.get(0)))).concat("%)");// years[years.length]
		
		//creating pie chart dataset
		datasetPIE = new DefaultPieDataset();
		datasetPIE.setValue(yearTitle, dataset.get(0));
		datasetPIE.setValue("Other".concat("(").concat(Double.toString(Math.round(100-dataset.get(0)))).concat("%)"), 100-dataset.get(0));
	}
	
	/**
	 * buildGraph() inherited from abstract class Viewers
	 * creates graph image
	 * @return Viewers
	 */
	public Viewers buildGraph() {
		//creating chart and panel 
		JFreeChart chart = ChartFactory.createPieChart(title, datasetPIE);
		ChartPanel panel = new ChartPanel(chart);
		
		//setting panel visibility details
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.setBackground(new java.awt.Color(51, 51, 51));
		this.add(panel);
		return this;
	}
		
}


