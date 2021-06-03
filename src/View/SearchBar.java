package View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SearchBar �r�k�l a JPanelt�l �s implement�lja az ActionListener interf�szt.
 * Megval�s�tja a keres�s funkci�t.
 */
public class SearchBar extends JPanel implements ActionListener{
	private JTextField searchField = new JTextField("", 10);
	private JButton searchBtn = new JButton("Search");
	private JButton backBtn = new JButton("Back");
	private ButtonListener searchListener;
	private ButtonListener backListener;
	
	/**
	 * SearchBar konstuktora.
	 * Be�ll�tja a borderlayoutot, bordert, hozz�adja a komponenseket.
	 * Az oszt�lyt(this) be�ll�tja actionlistenernek a gomboknak.
	 */
	public SearchBar() {
		setBorder(BorderFactory.createTitledBorder("Search Person"));
		
		add(searchField);
		add(searchBtn);
		add(backBtn);
		
		searchBtn.setActionCommand("search");
		searchBtn.addActionListener(this);
		backBtn.setActionCommand("back");
		backBtn.addActionListener(this);
	}
	
	/**
	 * Be�ll�tja a ButtonListener interf�szre a seach-h�z tartoz� interf�szt.
	 * @param buttonListener
	 */
	public void setSearchBtnListener(ButtonListener buttonListener) {
		this.searchListener = buttonListener;
	}
	
	/**
	 * Be�ll�tja a ButtonListener interf�szre a Back-hez tartoz� interf�szt.
	 * @param backListener
	 */
	public void setBackListener(ButtonListener backListener) {
		this.backListener = backListener;
	}

	/**
	 * Az ActionListener interf�sz k�telez�en implement�land� f�ggv�nye.
	 * Action command alapj�n eld�nti, hogy melyik gombot nyomt�k meg.
	 * A "Seach" gomb �s annak listener interf�sz�nek l�tez�se eset�n 
	 * �tadja az interf�sz btnPerformed f�ggv�ny�nek a keres�si param�tert (String).
	 * A "Back" gomb �s annak listener inferf�sz�nek l�tez�se eset�n
	 * megh�vja az interf�sz btnPerformed f�ggv�ny�t.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("search") && searchListener != null) {
			searchListener.btnPerformed(searchField.getText(),e);
		} else if(e.getActionCommand().equals("back") && backListener != null) {
			backListener.btnPerformed("", e);
		}
			
	}

	/**
	 * Visszaadja a seach gombot tesztel�s megk�nny�t�se �rdek�ben.
	 * @return
	 */
	public JButton getSearchBtn() {
		return searchBtn;
	}

	/**
	 * Visszaadja a back gombot tesztel�s megk�nny�t�se �rdek�ben.
	 * @return
	 */
	public JButton getBackBtn() {
		return backBtn;
	}

	
}