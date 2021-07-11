

public class AnalysisFactory {
	private String[] titles;
	private String [][] indicators;
	
	public AnalysisFactory() {
		titles = new String[] {A1.getTitle(), A2.getTitle(), A3.getTitle(), A4.getTitle(), A5.getTitle(), A6.getTitle(), A7.getTitle(), A8.getTitle()};
		indicators = new String[][] {A1.getIndicators(), A2.getIndicators(), A3.getIndicators(), A4.getIndicators(), A5.getIndicators(), A6.getIndicators(), A7.getIndicators(), A8.getIndicators()};
	}
	
	public AnalysisType createAnalysis(String title, String country, int startYear, int endYear) {
		int index = getIndex(title);
		switch (index) {
			case 0:
				return new A1(country, startYear, endYear);

			case 1:
				return new A2(country, startYear, endYear);
																																							
			case 2:
				return new A3(country, startYear, endYear);

			case 3:
				return new A4(country, startYear, endYear);

			case 4:
				return new A5(country, startYear, endYear);

			case 5:
				return new A6(country, startYear, endYear);

			case 6:
				return new A7(country, startYear, endYear);

			case 7:
				return new A8(country, startYear, endYear);
		}
		return null;
	}
	public String[][] getIndicators(){
		return indicators;
	}
	public String[] getTitles() {
		return titles;
	}
	
	public String[] getIndicators (String title) {
		int index = getIndex(title);
		return getIndicators()[index];
	}
	
	private int getIndex (String title) {
		int index = -1;
		for (int i = 0; i < titles.length; i++) {
			if (title.equalsIgnoreCase(titles[i])) {
				index = i;
				break;
			}
		}
		return index;
	}
}
