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
 * TablePanel a JPanelt�l �r�k�l �s implement�lja az ActioListener interf�szt.
 * Megjelen�ti az adatokat egy JTableben.
 */
public class TablePanel extends JPanel implements ActionListener{
	
	private JTable table;
	private PersonData personModel;
	private JPopupMenu deletePopup;
	private PersonTableListener personTableListener;
	
	/**
	 * TablePanel konstruktora.
	 * Inicializ�lja a bels�v�ltoz�kat.
	 * JTablet t�bl�t egy JScrollPanebe illesztve hozz�adja a Panelhez.
	 * A t�bl�hoz eg�r klikkel�s figyel�t ad �s a "Delete" gombhoz �nmag�t ActionListenerk�nt.
	 */
	public TablePanel() {
		
		personModel = new PersonData();
		table = new JTable(personModel);
		table.setBackground(Color.white);
		
		deletePopup = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Delete");
		deletePopup.add(deleteItem);
		
		
		//Az egy�r klikkel�s figyel�st anon�m oszt�llyal megval�s�t�sa
		table.addMouseListener(new MouseAdapter() {
			/**
			 * Eg�r megnyom�sa ut�n h�vott f�ggv�ny.
			 * Kiv�lasztja a t�bl�ban az eg�sz helyzete alapj�n a megfelel� t�bla elemet.
			 * Egy JPopupMenu-t mutat, amiben a "deleteItem" JMenuItem tal�lhat�
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
	 * Be�ll�tja a TablePanelben megjelen�tend� t�bl�zat modelj�nek az adatt�r�t.
	 * @param db - Megjelen�tend� emberek list�ja.
	 */
	public void setPersonData(List<Person> db) {
		personModel.setDataList(db);
	}
	
	/**
	 * Friss�ti a t�bla modelj�t.
	 */
	public void refreshTable() {
		personModel.fireTableDataChanged();
	}
	
	/**
	 * Be�ll�tja a TabelPanelben az interf�szt, amin kereszt�l kommunik�l a MainFrammel.
	 * @param listener
	 */
	public void addPersonTableListener(PersonTableListener listener) {
		this.personTableListener = listener;
	}

	/**
	 * ActionListener k�telez�en implement�lt actionPerformed f�ggv�nye.
	 * Megh�v�sra ker�l, amikor valaki r� megy a "Delete" gombra.
	 * Ha a be�ll�tott interface nem null, akkor t�rli a kiv�lasztott sort.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		
		if(personTableListener != null){
			personTableListener.deletedRow(row);
		}
	}
	
}
