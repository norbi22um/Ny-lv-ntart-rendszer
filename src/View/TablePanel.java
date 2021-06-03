package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Model.Person;


/**
 * TablePanel a JPaneltõl örököl és implementálja az ActioListener interfészt.
 * Megjeleníti az adatokat egy JTableben.
 */
public class TablePanel extends JPanel implements ActionListener{
	
	private JTable table;
	private PersonData personModel;
	private JPopupMenu deletePopup;
	private PersonTableListener personTableListener;
	
	/**
	 * TablePanel konstruktora.
	 * Inicializálja a belsõváltozókat.
	 * JTablet táblát egy JScrollPanebe illesztve hozzáadja a Panelhez.
	 * A táblához egér klikkelés figyelõt ad és a "Delete" gombhoz önmagát ActionListenerként.
	 */
	public TablePanel() {
		
		personModel = new PersonData();
		table = new JTable(personModel);
		table.setBackground(Color.white);
		
		deletePopup = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Delete");
		deletePopup.add(deleteItem);
		
		
		//Az egyér klikkelés figyelést anoním osztállyal megvalósítása
		table.addMouseListener(new MouseAdapter() {
			/**
			 * Egér megnyomása után hívott függvény.
			 * Kiválasztja a táblában az egész helyzete alapján a megfelelõ tábla elemet.
			 * Egy JPopupMenu-t mutat, amiben a "deleteItem" JMenuItem található
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON3) {
					deletePopup.show(table, e.getX(), e.getY());
				}
				
			}
			
		});
	
		deleteItem.addActionListener(this);
		
		setLayout(new  BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	/**
	 * Beállítja a TablePanelben megjelenítendõ táblázat modeljének az adattárát.
	 * @param db - Megjelenítendõ emberek listája.
	 */
	public void setPersonData(List<Person> db) {
		personModel.setDataList(db);
	}
	
	/**
	 * Frissíti a tábla modeljét.
	 */
	public void refreshTable() {
		personModel.fireTableDataChanged();
	}
	
	/**
	 * Beállítja a TabelPanelben az interfészt, amin keresztûl kommunikál a MainFrammel.
	 * @param listener
	 */
	public void addPersonTableListener(PersonTableListener listener) {
		this.personTableListener = listener;
	}

	/**
	 * ActionListener kötelezõen implementált actionPerformed függvénye.
	 * Meghívásra kerül, amikor valaki rá megy a "Delete" gombra.
	 * Ha a beállított interface nem null, akkor törli a kiválasztott sort.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		
		if(personTableListener != null){
			personTableListener.deletedRow(row);
		}
	}
	
}
