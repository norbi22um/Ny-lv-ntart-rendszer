package View;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import Controller.Controller;
import Model.Person;

/**
 * MainFrame a JFramet�l �r�k�l. A View-t implement�lja az MVC modelb�l.
 * Rajta kereszt�l t�rt�nik a kommunik�ci� a Controllerel.
 */
public class MainFrame extends JFrame{
	
	private TablePanel tablePanel;
	private NotePanel noteArea;
	private ToolBar toolbar;
	private SideBar sideBar;
	private JTabbedPane tabPane;
	private DiagramPanel diagramPanel;
	private Controller controller;
	
	//File chooser
	private JFileChooser fileChooser;
	
	/**
	 * MainFrame konstruktora.
	 * Be�ll�tja a Swing applik�ci� param�teret �s ActionListenereket.
	 * Hozz�adja a defini�lt komponenseket az ablakhoz.
	 */
	public MainFrame() {
		//Az ablak param�tereinek be�ll�t�sa
		super("Egyetemi Ny�lv�ntart� Rendszer");
		setSize(1000, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Inicializ�l�sok
		fileChooser = new JFileChooser();
		noteArea = new NotePanel();
		toolbar = new ToolBar();
		sideBar = new SideBar();
		tablePanel = new TablePanel();
		controller = new Controller();
		tabPane = new JTabbedPane();
		diagramPanel = new DiagramPanel();
		
		//TabbedPannel kapcsolatos be�ll�t�sok
		tabPane.addTab("Personal Data", tablePanel);
		tabPane.addTab("Charts", diagramPanel);
		tabPane.addTab("Notes", noteArea);
		
		//TablePanellel kapcsolatos be�ll�t�sok
		tablePanel.setPersonData(controller.getPeople());
		tablePanel.addPersonTableListener(new MyActionListener());
		
		//Toolbarral kapcsolatos be�ll�t�sok
		toolbar.setNameSortButtonListener(new MyActionListener());
		toolbar.setFacutltySortButtonListener(new MyActionListener());
		
		//Sidebarral kapcsolatos be�ll�t�sok
		sideBar.getSearchBar().setSearchBtnListener(new MyActionListener());
		sideBar.getSearchBar().setBackListener(new MyActionListener());
		sideBar.getAddPersonPanel().setFormListener(new MyActionListener());
		
		//Fejl�c men�j�nek a megval�s�t�sa.
		setJMenuBar(setUpTheMenuBar());
		
		//Komponensek hozz�ad�sa a frame-hez.
		add(toolbar, BorderLayout.PAGE_START);
		add(tabPane, BorderLayout.CENTER);
		add(sideBar, BorderLayout.WEST);
	}
	
	/**
	 * A fejl�cben l�v� men� �ssze�ll�t�s�t v�gze el. 
	 * K�l�n f�ggv�nybe raktam, hogy k�nnyebben olvashat� legyen a k�d.
	 * @return - Az el��ll�tott JMenuBar-t adja vissza.
	 */
	private JMenuBar setUpTheMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu view = new JMenu("View");
		JMenu showView = new JMenu("Show");
		
		menubar.add(file);
		menubar.add(view);
		
		JMenuItem exportFile = new JMenuItem("Export");
		JMenuItem importFile = new JMenuItem("Import");
		JMenuItem exitFile = new JMenuItem("Exit");
		
		JCheckBoxMenuItem sidebarShow = new JCheckBoxMenuItem("Sidebar");
		showView.add(sidebarShow);
		
		sidebarShow.setSelected(true);
		
		file.add(exportFile);
		file.add(importFile);
		file.addSeparator();
		file.add(exitFile);
		view.add(showView);
		
		///Show men�ben l�v� CheckBox hallgat�sa
		sidebarShow.setActionCommand("showSidebar");
		sidebarShow.addActionListener(new MyActionListener());
		
		///Import gomb hallgat�sa
		importFile.setActionCommand("import");
		importFile.addActionListener(new MyActionListener());
		
		///Export gomb hallgat�sa
		exportFile.setActionCommand("save");
		exportFile.addActionListener(new MyActionListener());
		
		//Exit gomb hallgat�sa
		exitFile.setActionCommand("exit");
		exitFile.addActionListener(new MyActionListener());
		
		return menubar;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// ACTIONLISTENER OSZT�LY ////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MyActionListener a saj�t ActionListener implement�ci�m.
	 * Implement�lja a PersonTableListener, ActionListener, ButtonListener �s AddPersonListener interf�szeket.
	 * Az programon bel�li �ssze hallgat�st egy oszt�lyon bel�l implement�ltam.
	 * Tudom, hogy ez nagyobb projektn�l nem lenne hat�sos megval�s�t�s, de adva a progam kis m�ret�t nem akartam
	 * +10 k�l�n hallgat� oszt�llyal ell�tni az �tl�that�s�g jegy�ben.
	 */
	class MyActionListener implements PersonTableListener, ActionListener, ButtonListener, AddPersonListener{

		/**
		 * A fejc�lben l�v� gombokat figyeli, az action commandj�k alapj�n tesz k�l�mbs�get k�zt�k.
		 * Ha az action command "showSideBar", a JCeckBoxMenuItem �ll�sa szerint �ll�tja a sidebar l�that�s�g�t.
		 * 
		 * Ha az action command "import" a controllernek megh�v�dik a loadFromFile f�ggv�nye �s friss�ti a TablePanel �s Diagramok �ll�s�t.
		 * 
		 * Ha az action command "save" a controllernek megh�v�dik a saveToFile f�ggv�nye.
		 * 
		 * Ha az action command "exit" az ablak bez�r�dik �s a program le�ll.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("showSidebar")) {
				JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
				sideBar.setVisible(item.isSelected());
			} else if(e.getActionCommand().equals("import")) {
				if((fileChooser.showOpenDialog(MainFrame.this)) == JFileChooser.APPROVE_OPTION){
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refreshTable();
						diagramPanel.setDisplayedData(controller.getNumberOfTeachers(), controller.getNumberOfStudents()
								,controller.getFirstAgeGroup(), controller.getSecondAgeGroup(), controller.getThirdAgeGroup());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else if(e.getActionCommand().equals("save")) {
				if((fileChooser.showSaveDialog(MainFrame.this)) == JFileChooser.APPROVE_OPTION){
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else if(e.getActionCommand().equals("exit")) {
				System.exit(0);
			}
			
		}
		
		/**
		 * A Controllernek jelzi, melyik adatot kell kit�r�lni �s sz�l a TablePanel-nek hogy friss�teni kell a t�bla megjelen�t�s�t.
		 */
		@Override
		public void deletedRow(int row) {
			controller.removePerson(row);
			tablePanel.refreshTable();
		}

		/**
		 * A ToolBar �s SearchBar gombjait figyeli.
		 * Ha az action command "nameSort" a Controllernek jelzi, hogy rendezze sorba az adatokat n�v szerint �s 
		 * sz�l a TablePanel-nek hogy friss�teni kell a t�bla megjelen�t�s�t.
		 * 
		 * Ha az action command "facultySort" a Controllernek jelzi, hogy rendezze sorba az adatokat szak szerint �s 
		 * sz�l a TablePanel-nek hogy friss�teni kell a t�bla megjelen�t�s�t.
		 * 
		 * Ha az action command "search" a Controllernek megh�vja a searchByName f�ggv�ny�t, ami visszaadja az adott param�ter szerint sz�rt emberek list�j�t.
		 * Ezt az �j ember list�val friss�ti a tablePanel megjelen�tend� adatait.
		 * V�g�l a refreshTable f�ggv�nnyel sz�l a tablePanelnek, hogy friss�tse az adatok megjelen�t�s�t.
		 * 
		 * Ha az action command "back" Vissza�ll�tja a TablePanel adat�llom�ny�t a keres�s el�ttire.
		 * (Ami nem felt�tlen�l ugyan az, hiszen ha keres�s k�zben t�r�ltek valamit az m�r itt sem fog megjelenni)
		 * V�g�l a refreshTable f�ggv�nnyel sz�l a tablePanelnek, hogy friss�tse az adatok megjelen�t�s�t.
		 */
		@Override
		public void btnPerformed(String text, ActionEvent e) {
			if(e.getActionCommand().equals("nameSort")) {
				controller.sortByName();
				tablePanel.refreshTable();
			} else if(e.getActionCommand().equals("facultySort")) {
				controller.sortByFaculty();
				tablePanel.refreshTable();
			} else if(e.getActionCommand().equals("search")) {
				List<Person> tmp = controller.searchByName(text);
				tablePanel.setPersonData(tmp);
				tablePanel.refreshTable();
			} else if(e.getActionCommand().equals("back")) {
				tablePanel.setPersonData(controller.getPeople());
				tablePanel.refreshTable();
			}
			
		}
		
		/**
		 * AddPersonEventb�l kiolvassa az ember adatait �s ezeket tov�bb�tja a Controllernek hogy t�rolja el.
		 * A TablePanelnek sz�l, hogy friss�tse a t�bla adatait.
		 * A DiagramPanelnek sz�l, hogy friss�tse a diagrammok adatait.
		 */
		@Override
		public void addPersonPerformed(AddPersonEvent e) {
			String name = e.getName();
			String faculty = e.getFaculty();
			String age = e.getAge();
			String gender = e.getGender();
			boolean isStudent = e.isStudent();
			String neptun = e.getNeptun();
	
			controller.addPerson(name, faculty, age, gender, isStudent, neptun);
			tablePanel.refreshTable();
			diagramPanel.setDisplayedData(controller.getNumberOfTeachers(), controller.getNumberOfStudents()
					,controller.getFirstAgeGroup(), controller.getSecondAgeGroup(), controller.getThirdAgeGroup());
		}
	}
}
  