package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Model.Person;
import Model.Storage;


/**
 * DiagramPanel a JPanel osztálytól örököl és implementálja az ActionListener interfészt.
 * Megjelenít egy oszlop- és kördiagrammot, amiket az emberek adatai alapján generál.
 */
public class DiagramPanel extends JPanel implements ActionListener{
	
	private int numOfTeachers = 0;
	private int numOfStudents = 0;
	private int firstAge = 0;
	private int secondAge = 0;
	private int thirdAge = 0;
	private JButton ratioBtn = new JButton("Student, Teacher Ration");
	private JButton ageGroupBtn = new JButton("Age Group");
	private JPanel chartsPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	/**
	 * DiagramPanel konstruktora.
	 * BorderLayouthoz hozzáad egy gombokat és egy diagrammokat tároló panelt.
	 * A gombokhoz önmagát(this) rendeli hozzá actionlistenerként.
	 */
	public DiagramPanel() {
		setLayout(new BorderLayout());
		
		add(new JScrollPane(chartsPanel), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		buttonPanel.add(ratioBtn);
		buttonPanel.add(ageGroupBtn);
		
		ratioBtn.setActionCommand("ratio");
		ratioBtn.addActionListener(this);
		
		ageGroupBtn.setActionCommand("age");
		ageGroupBtn.addActionListener(this);
		
	}
	
	/**
	 * Hozzá adja a diagram generálásához szükséges adatokat az osztályhoz.
	 * 
	 * @param numOfTeachers
	 * @param numOfStudents
	 * @param firstAge
	 * @param secondAge
	 * @param thirdAge
	 */
	public void setDisplayedData(int numOfTeachers, int numOfStudents, int firstAge, int secondAge, int thirdAge) {
		this.numOfTeachers = numOfTeachers;
		this.numOfStudents = numOfStudents;
		this.firstAge = firstAge;
		this.secondAge = secondAge;
		this.thirdAge = thirdAge;
	}
	

	/**
	 * ActionListener felülírt függvénye.
	 * ActionCommandtõl függõen kördiagrammot vagy oszlopdiagrammothoz létre, amit hozzá ad a chartsPanelhez, ami megjeleníti. 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ratio")) {
			
			DefaultCategoryDataset dcd = new DefaultCategoryDataset();
			dcd.setValue(numOfTeachers, "Number of People", "Teachers");
			dcd.setValue(numOfStudents, "Number of People", "Students");
			
			JFreeChart jchart = ChartFactory.createBarChart3D("Student, Teacher Ration", " ", "Number Of People", dcd, PlotOrientation.VERTICAL, false, true, false);
			
			CategoryPlot plot = jchart.getCategoryPlot();
			plot.setRangeGridlinePaint(Color.green);
			
			ChartPanel chartPanel = new ChartPanel(jchart);
			
			chartsPanel.removeAll();
			chartsPanel.add(chartPanel);
			chartsPanel.updateUI();
			
		} else if(e.getActionCommand().equals("age")) {
			DefaultPieDataset dataset = new DefaultPieDataset( );
			dataset.setValue( "18-35" , firstAge); 
		    dataset.setValue( "36-64" , secondAge);  
		    dataset.setValue( "65+" , thirdAge); 
			
		    JFreeChart chart = ChartFactory.createPieChart("Age Groups", dataset, true, true, false);
			
			ChartPanel chartPanel = new ChartPanel(chart);
			
			chartsPanel.removeAll();
			chartsPanel.add(chartPanel);
			chartsPanel.updateUI();
		}
	}
	
	
}
