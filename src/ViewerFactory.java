/**
 * Class that creates the viewer object based on user selections
 * @author Group 15
 *
 */
public class ViewerFactory {
	
	private  String[] viewerNames;
	
	public ViewerFactory() {
		viewerNames = new String[] {"bar", "line", "scatter", "report", "pie"};
	}
	
	// TO DO: fix the units for 3 series analysis, add country as a parameter
	public Viewers createViewer(String name, AnalysisType a, int startYear, int endYear, String country) {
		String fullTitle = country.concat(": ").concat(a.getInstTitle());
		System.out.println(fullTitle);
		if (name.toLowerCase().contains(viewerNames[0])) {
			Data[] d = a.calculate();
			String[] u = a.getUnits();
			return new BarChart(fullTitle, d, startYear, endYear, "Years", convertUnits(d, u), getSeriesLabels(d, a.getInstTitle()));
		}
		else if (name.toLowerCase().contains(viewerNames[1])) {
			Data[] d = a.calculate();
			String[] u = a.getUnits();
			
			return new LineChart(fullTitle, d, startYear, endYear, "Years", convertUnits(d, u), getSeriesLabels(d, a.getInstTitle()));
		}
		else if (name.toLowerCase().contains(viewerNames[2])) {
			Data[] d = a.calculate();
			String[] u = a.getUnits();
			
			return new ScatterChart(fullTitle, d, startYear, endYear, "Years", convertUnits(d, u), getSeriesLabels(d, a.getInstTitle()));
		}
		else if (name.toLowerCase().contains(viewerNames[3])) {
			return new Report(fullTitle, a.calculate(), startYear, endYear, a.getUnits());
		}
		else if (name.toLowerCase().contains(viewerNames[4])) {
			return new PieChart(fullTitle, a.calculate(), startYear, endYear);
		}
		else {
			return null;
		}
	}
	// it is supposed to return like title.split(vs) instead of the units
	private String[][] getSeriesLabels(Data[] d, String title){
		int counter = 0;
		String [][] seriesLabels = new String[d.length][];
		String[] u;
		if (title.toLowerCase().contains(" vs. ")) {
			u = title.split(" vs. ");
		}
		else {
			u = title.split(" and ");
		}
		
		for (int i = 0; i < d.length; i++) {
			seriesLabels[i] = new String[d[i].getDataset().length];
			for (int k = 0; k < d[i].getDataset().length; k++) {
				seriesLabels[i][k] = u[counter];
				counter++;
			}
		}
		return seriesLabels;
	}
	
	private String[] convertUnits (Data[] d, String[] units) {
		String[] output = new String[d.length];
		int counter = 0;
		for (int i = 0; i < d.length; i++) {
			String temp = units[counter];
			counter++;
			for (int k = 1; k < d[i].getDataset().length; k++) {
				temp += " & "+units[counter];
				counter++;
			}
			output [i] = temp;
		}
		
		return output;
	}
}
