package View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SearchBar örököl a JPaneltõl és implementálja az ActionListener interfészt.
 * Megvalósítja a keresés funkciót.
 */
public class SearchBar extends JPanel implements ActionListener{
	private JTextField searchField = new JTextField("", 10);
	private JButton searchBtn = new JButton("Search");
	private JButton backBtn = new JButton("Back");
	private ButtonListener searchListener;
	private ButtonListener backListener;
	
	/**
	 * SearchBar konstuktora.
	 * Beállítja a borderlayoutot, bordert, hozzáadja a komponenseket.
	 * Az osztályt(this) beállítja actionlistenernek a gomboknak.
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
	 * Beállítja a ButtonListener interfészre a seach-höz tartozó interfészt.
	 * @param buttonListener
	 */
	public void setSearchBtnListener(ButtonListener buttonListener) {
		this.searchListener = buttonListener;
	}
	
	/**
	 * Beállítja a ButtonListener interfészre a Back-hez tartozó interfészt.
	 * @param backListener
	 */
	public void setBackListener(ButtonListener backListener) {
		this.backListener = backListener;
	}

	/**
	 * Az ActionListener interfész kötelezõen implementálandó függvénye.
	 * Action command alapján eldönti, hogy melyik gombot nyomták meg.
	 * A "Seach" gomb és annak listener interfészének létezése esetén 
	 * átadja az interfész btnPerformed függvényének a keresési paramétert (String).
	 * A "Back" gomb és annak listener inferfészének létezése esetén
	 * meghívja az interfész btnPerformed függvényét.
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
	 * Visszaadja a seach gombot tesztelés megkönnyítése érdekében.
	 * @return
	 */
	public JButton getSearchBtn() {
		return searchBtn;
	}

	/**
	 * Visszaadja a back gombot tesztelés megkönnyítése érdekében.
	 * @return
	 */
	public JButton getBackBtn() {
		return backBtn;
	}

	
}