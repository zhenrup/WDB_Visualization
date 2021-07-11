import java.awt.Font;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class BarChart creates bar chart panel
 * Compatible with A1, A2, A3, A6, A7, A8
 * Inherits from Viewers
 * @author Group 15
 */

public class BarChart extends Viewers{
	

	private String xLabel;
	private String[] yLabel; // string array storing labels for the y-axis (either has 1 or 2 entries depending on the number of datasets)
	String[][] seriesNames; // names used for the legend (2D array because the datasets sets are grouped similarly)
	String yearRange;
	
	/**
	 * Constructor stores information based on parameter values
	 * @param title: sets the title of the graph
	 * @param datasets: array of data objects that were returned from AnalysisType.calculate() 
	 * @param startYear
	 * @param endYear
	 * @param xLabel: stores the x-axis label
	 * @param yLabel: stores the y-axis labels (either 1 or 2 depending on number of datasets)
	 * @param seriesNames: names used for the legend (2D array because the datasets sets are grouped similarly)
	 */
	public BarChart(String title, Data[] datasets, int startYear, int endYear, String xLabel, String[] yLabel, String[][] seriesNames) {
		this.title = title;
		this.datasets = datasets;
		this.startYear = startYear;
		this.endYear = endYear;
		this.xLabel = xLabel;
		this.yLabel= yLabel;
		this.seriesNames = seriesNames;		
	}
	
	/**
	 * buildGraph() inherited from abstract class Viewers
	 * creates graph image
	 * @return Viewers
	 */
	public Viewers buildGraph() {
		
		CategoryPlot plot = new CategoryPlot(); // initializes new category plot
					
		for (int i = 0; i < datasets.length; i++) { // i represents the index of the Data object in datasets
			BarRenderer barrenderer = new BarRenderer();
			DefaultCategoryDataset chartDataset = new DefaultCategoryDataset( );
			for(int j = 0; j < datasets[i].getDataset().length; j++) { // j represents the index of arraylists in object Data
				for(int k = startYear; k <= endYear; k++) { // k represents the year
					int l = k - startYear; // l represents the index of the arraylist
					chartDataset.addValue(datasets[i].getDataset()[j].get(l), seriesNames[i][j], String.valueOf(k));
				}
			}
			plot.setDataset(i, chartDataset); // sets dataset for plot
			plot.setRenderer(i, barrenderer); // sets renderer
			plot.setRangeAxis(i, new NumberAxis(yLabel[i])); // creates a y axis label
			plot.mapDatasetToRangeAxis(i, i);
		}
		
		CategoryAxis domainAxis = new CategoryAxis(xLabel); // initializes CategoryAxis for the x-axis
		plot.setDomainAxis(domainAxis); // sets x-axis to domainAxis
		JFreeChart barChart = new JFreeChart(title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		barChart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font("Serif", Font.PLAIN, 8));
		
		//creating a panel for the chart
		ChartPanel panel = new ChartPanel(barChart);
		this.setBackground(new java.awt.Color(51, 51, 51));
		this.add(panel);
		return this;
	}
}