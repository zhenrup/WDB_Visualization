import java.util.ArrayList;
/**
 * test class for creating Data objects (datasets)
 * @author Group 15
 *
 */
public class TestData {

	public static void main (String[] args) {
		//creation of Data object
		Data testDataset1 = new Data(2);
		Data testDataset2 = new Data(2);
		WBReader rd = new WBReader();
		
		//reading data
		ArrayList<Double> info1 = rd.readData("can", "EN.ATM.PM25.MC.M3", 1980, 2009);
		ArrayList<Double> info2 = rd.readData("can", "EG.USE.PCAP.KG.OE", 1980, 2009);
		
		//adding to dataset method 1
		testDataset1.addAnalysisData(info1, 1);
		testDataset1.addAnalysisData(info2, 2);
		
		//adding to dataset method 2
		testDataset2.addAnalysisData("can", "EN.ATM.PM25.MC.M3", 1980, 2009, 1);
		testDataset2.addAnalysisData("can", "EG.USE.PCAP.KG.OE", 1980, 2009, 2);
		
		//test dataset creation
		System.out.println("TestDataset 1:");
		testDataset1.printDataset();
		System.out.println("TestDataset 2:");
		testDataset2.printDataset();
	}
}
