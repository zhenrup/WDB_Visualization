import java.util.ArrayList;
import java.util.Arrays;

//combining all data into a dataset
public class Data {
	
	private ArrayList<Double>[] dataset;
	private int analysisNum;
	
	/**
	 * Creates an empty dataset
	 * @param num: number of analysis types being added to the dataset
	 */
	public Data(int num) {
		analysisNum = num; //number of analysis types there are
		dataset = new ArrayList[analysisNum];
	}
	
	/**
	 * Adds analysis array to dataset
	 * @param data : data to be added
	 * @param num : position of dataset to be added to
	 */
	public void addAnalysisData(ArrayList <Double> data, int num) {
		dataset[num-1] = data;
	}
	
	public void addAnalysisData(String country, String indicator, int start, int end, int num) {
		WBReader r = new WBReader();
		dataset[num-1] = r.readData(country, indicator, start, end);
	}
	
	/**
	 * Returns the dataset
	 * @return dataset
	 */
	public ArrayList<Double>[] getDataset() {
		return dataset;
	}
	
	
	/**
	 *Prints the dataset
	 */
	public void printDataset() {
		System.out.println(Arrays.deepToString(dataset));
	}
}
