package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A SideBar örököl a JPanel osztálytól.
 * Otthont ad az AddPersonPanel és SeachBar panelnek.
 */
public class SideBar extends JPanel{
	
	private AddPersonPanel form;
	private SearchBar search;
	
	/**
	 * SideBar konstruktora.
	 * Beállítja a szélességét a panelnak 250 pixelre, hogy mindig olvasható lehessen a tartalma és bordert ad neki.
	 * BorderLayoutban hozzá adja a komponenseket.
	 */
	public SideBar() {
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		setPreferredSize(dimension);
		
		setBorder(BorderFactory.createEtchedBorder());
	
		form = new AddPersonPanel();
		search = new SearchBar();
		search.setPreferredSize(new Dimension(250, 100));
	
		setLayout(new BorderLayout());
		add(form, BorderLayout.CENTER);
		add(search, BorderLayout.NORTH);
		
	}
	
	/**
	 * Visszaadja az AddPersonPanelt
	 * @return AddPersonPanel
	 */
	public AddPersonPanel getAddPersonPanel() {
		return form;
	}
	
	/**
	 * Visszaadja a SearchBar panelt
	 * @return SearchBar
	 */
	public SearchBar getSearchBar() {
		return search;
	}
}
