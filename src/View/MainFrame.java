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
 * MainFrame a JFrametõl örököl. A View-t implementálja az MVC modelbõl.
 * Rajta keresztûl történik a kommunikáció a Controllerel.
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
	 * Beállítja a Swing applikáció paraméteret és ActionListenereket.
	 * Hozzáadja a definiált komponenseket az ablakhoz.
	 */
	public MainFrame() {
		//Az ablak paramétereinek beállítása
		super("Egyetemi Nyílvántartó Rendszer");
		setSize(1000, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Inicializálások
		fileChooser = new JFileChooser();
		noteArea = new NotePanel();
		toolbar = new ToolBar();
		sideBar = new SideBar();
		tablePanel = new TablePanel();
		controller = new Controller();
		tabPane = new JTabbedPane();
		diagramPanel = new DiagramPanel();
		
		//TabbedPannel kapcsolatos beállítások
		tabPane.addTab("Personal Data", tablePanel);
		tabPane.addTab("Charts", diagramPanel);
		tabPane.addTab("Notes", noteArea);
		
		//TablePanellel kapcsolatos beállítások
		tablePanel.setPersonData(controller.getPeople());
		tablePanel.addPersonTableListener(new MyActionListener());
		
		//Toolbarral kapcsolatos beállítások
		toolbar.setNameSortButtonListener(new MyActionListener());
		toolbar.setFacutltySortButtonListener(new MyActionListener());
		
		//Sidebarral kapcsolatos beállítások
		sideBar.getSearchBar().setSearchBtnListener(new MyActionListener());
		sideBar.getSearchBar().setBackListener(new MyActionListener());
		sideBar.getAddPersonPanel().setFormListener(new MyActionListener());
		
		//Fejléc menüjének a megvalósítása.
		setJMenuBar(setUpTheMenuBar());
		
		//Komponensek hozzáadása a frame-hez.
		add(toolbar, BorderLayout.PAGE_START);
		add(tabPane, BorderLayout.CENTER);
		add(sideBar, BorderLayout.WEST);
	}
	
	/**
	 * A fejlécben lévõ menü összeállítását végze el. 
	 * Külön függvénybe raktam, hogy könnyebben olvasható legyen a kód.
	 * @return - Az elõállított JMenuBar-t adja vissza.
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
		
		///Show menüben lévõ CheckBox hallgatása
		sidebarShow.setActionCommand("showSidebar");
		sidebarShow.addActionListener(new MyActionListener());
		
		///Import gomb hallgatása
		importFile.setActionCommand("import");
		importFile.addActionListener(new MyActionListener());
		
		///Export gomb hallgatása
		exportFile.setActionCommand("save");
		exportFile.addActionListener(new MyActionListener());
		
		//Exit gomb hallgatása
		exitFile.setActionCommand("exit");
		exitFile.addActionListener(new MyActionListener());
		
		return menubar;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// ACTIONLISTENER OSZTÁLY ////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MyActionListener a saját ActionListener implementációm.
	 * Implementálja a PersonTableListener, ActionListener, ButtonListener és AddPersonListener interfészeket.
	 * Az programon belüli össze hallgatást egy osztályon belül implementáltam.
	 * Tudom, hogy ez nagyobb projektnél nem lenne hatásos megvalósítás, de adva a progam kis méretét nem akartam
	 * +10 külön hallgató osztállyal ellátni az átláthatóság jegyében.
	 */
	class MyActionListener implements PersonTableListener, ActionListener, ButtonListener, AddPersonListener{

		/**
		 * A fejcélben lévõ gombokat figyeli, az action commandjük alapján tesz külömbséget köztük.
		 * Ha az action command "showSideBar", a JCeckBoxMenuItem állása szerint állítja a sidebar láthatóságát.
		 * 
		 * Ha az action command "import" a controllernek meghívódik a loadFromFile függvénye és frissíti a TablePanel és Diagramok állását.
		 * 
		 * Ha az action command "save" a controllernek meghívódik a saveToFile függvénye.
		 * 
		 * Ha az action command "exit" az ablak bezáródik és a program leáll.
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
		 * A Controllernek jelzi, melyik adatot kell kitörölni és szól a TablePanel-nek hogy frissíteni kell a tábla megjelenítését.
		 */
		@Override
		public void deletedRow(int row) {
			controller.removePerson(row);
			tablePanel.refreshTable();
		}

		/**
		 * A ToolBar és SearchBar gombjait figyeli.
		 * Ha az action command "nameSort" a Controllernek jelzi, hogy rendezze sorba az adatokat név szerint és 
		 * szól a TablePanel-nek hogy frissíteni kell a tábla megjelenítését.
		 * 
		 * Ha az action command "facultySort" a Controllernek jelzi, hogy rendezze sorba az adatokat szak szerint és 
		 * szól a TablePanel-nek hogy frissíteni kell a tábla megjelenítését.
		 * 
		 * Ha az action command "search" a Controllernek meghívja a searchByName függvényét, ami visszaadja az adott paraméter szerint szûrt emberek listáját.
		 * Ezt az új ember listával frissíti a tablePanel megjelenítendõ adatait.
		 * Végül a refreshTable függvénnyel szól a tablePanelnek, hogy frissítse az adatok megjelenítését.
		 * 
		 * Ha az action command "back" Visszaállítja a TablePanel adatállományát a keresés elõttire.
		 * (Ami nem feltétlenûl ugyan az, hiszen ha keresés közben töröltek valamit az már itt sem fog megjelenni)
		 * Végül a refreshTable függvénnyel szól a tablePanelnek, hogy frissítse az adatok megjelenítését.
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
		 * AddPersonEventbõl kiolvassa az ember adatait és ezeket továbbítja a Controllernek hogy tárolja el.
		 * A TablePanelnek szól, hogy frissítse a tábla adatait.
		 * A DiagramPanelnek szól, hogy frissítse a diagrammok adatait.
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
  