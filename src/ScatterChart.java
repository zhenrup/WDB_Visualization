import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

/**
 * Class ScatterChart
 * Compatible with A1, A2, A3, A6, A7, A8
 * Inherits from Viewers
 * @author Group 15
 */

public class ScatterChart extends Viewers {
	
	private String xLabel;
	private String[] yLabel; // string array storing labels for the y-axis (either has 1 or 2 entries depending on the number of datasets)
	String[][] seriesNames; // names used for the legend (2D array because the datasets sets are grouped similarly)
	
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
	public ScatterChart(String title, Data[] datasets, int start, int end, String xLabel, String[] yLabel, String[][] seriesNames) {
		this.title = title;
		this.datasets = datasets;
		this.startYear = start;
		this.endYear = end;
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

		XYPlot plot = new XYPlot();
		
		for (int i = 0; i < datasets.length; i++) { // i represents the index of the Data object in datasets
			XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);;
			TimeSeriesCollection chartDataset = new TimeSeriesCollection();
			for(int j = 0; j < datasets[i].getDataset().length; j++) { // j represents the index of arraylists in object Data
				TimeSeries series = new TimeSeries(seriesNames[i][j]); 
				for(int k = startYear; k <= endYear; k++) { // k represents the year
					int l = k - startYear; // l represents the index of the arraylist
					if (datasets[i].getDataset()[j].get(l) != 0) {
						series.add(new Year(k), datasets[i].getDataset()[j].get(l));
					}
				}
				chartDataset.addSeries(series);
			}
			plot.setDataset(i, chartDataset); // sets dataset for plot
			plot.setRenderer(i, renderer); // sets renderer
			DateAxis domainAxis = new DateAxis(xLabel);
			if(startYear == endYear) { 
				domainAxis.setDateFormatOverride(new SimpleDateFormat("yyyy")); // ensures the x-axis shows the year rather than a time
			}
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(i, new NumberAxis(yLabel[i])); // creates a y axis label
			plot.mapDatasetToRangeAxis(i, i);

		}
		JFreeChart chart = new JFreeChart(this.title, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		ChartPanel panel = new ChartPanel(chart);
		
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.setBackground(new java.awt.Color(51, 51, 51));
		this.add(panel);
		return this;
	}
}
