import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
/**
 * Class of the viewer object
 * @author Group 15
 *
 */
public abstract class Viewers extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Title String of the viewer
	 */
	protected String title;
	
	/**
	 * Data array that contains all the datasets which viewers draws data with
	 */
	protected Data[] datasets;
	
	/**
	 * the range of years for which graphs are built
	 */
	protected int startYear, endYear;
	
	/**
	 * Creates a graph that can be rendered on the UI
	 * @return Viewers object
	 */
	abstract Viewers buildGraph() ;
	
	/**
	 * Checks if two viewers are equal by checking their titles, years, and class name
	 * @param v : Viewers Object that will be compared against
	 * @return true if they are equals, false otherwise
	 */
	public boolean equals(Viewers v) {
		boolean ifTitles = this.title.equals(v.title);
		boolean ifStart = this.startYear == v.startYear;
		boolean ifEnd = this.endYear == v.endYear;
		boolean ifClass = this.getClass().getSimpleName().equals(v.getClass().getSimpleName());
		
		return ifTitles && ifStart && ifEnd && ifClass;
	}
	
	/**
	 * Overrides JComponent.setPreferredSize(Dimension d)
	 * sets the size of the Viewer and then sets the size of the components within the viewer
	 */
	public void setPreferredSize(Dimension d) {
		super.setPreferredSize(d);
		for (Component i :this.getComponents()) {
			i.setPreferredSize(d);
		}
	}
	
}
