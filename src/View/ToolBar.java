package View;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ToolBar örököl a JPaneltõl és implementálja az ActionListener interfészt.
 * A sorbarendezést megvalósító gomboknak ad otthont az ablak tetején.
 */
public class ToolBar extends JPanel implements ActionListener{

	private JButton nameSortB;
	private JButton facultySortB;
	private ButtonListener buttonListener;
	private ButtonListener nameSortListener;
	private ButtonListener facultySortListener;
	
	/**
	 * A ToolBar konstruktora. 
	 * Inicializálja a privát változókat.
	 * Beállítja FlowLayoutra a panel layoutot és hozzá adja a két gombot.
	 * A gombokhoz önmagát rendeli ActionListenerként.
	 */
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		nameSortB = new JButton("Sort By Name");
		facultySortB = new JButton("Sort By Faculty");
		nameSortB.setBackground(Color.LIGHT_GRAY);
		nameSortB.setOpaque(false);
		
		facultySortB.setBackground(Color.LIGHT_GRAY);
		facultySortB.setOpaque(false);
		
		nameSortB.setActionCommand("nameSort");
		nameSortB.addActionListener(this);
		facultySortB.setActionCommand("facultySort");
		facultySortB.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(nameSortB);
		add(facultySortB);
		
	}
	

	/**
	 * Az ActionListener kötelezõen megvalósítandó actionPerformed függvénye.
	 * A gombra kattintás után az ActionCommand alapján eldönti melyik gombot nyomták meg.
	 * Majd meghívja az adott gombhoz rendelt interfészt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton)e.getSource();
		if(tmp == nameSortB) {
			if(nameSortListener != null) {
				nameSortListener.btnPerformed("", e);
			}
		} else if(tmp == facultySortB) {
			if(facultySortListener != null) {
				facultySortListener.btnPerformed("", e);
			}
		}
		
	}
	
	/**
	 * Beállítja a ToolBarban a name sort interfészét, amin keresztûl kommunikál a MainFrammel.
	 * @param buttonListener
	 */
	public void setNameSortButtonListener(ButtonListener buttonListener) {
		this.nameSortListener = buttonListener;
	}
	
	/**
	 * Beállítja a ToolBarban a faculty sort interfészét, amin keresztûl kommunikál a MainFrammel.
	 * @param buttonListener
	 */
	public void setFacutltySortButtonListener(ButtonListener buttonListener) {
		this.facultySortListener = buttonListener;
	}

	/**
	 * Visszaadja a név alapján rendezésre használt gombot. Tesztelés céljából
	 * @return - JButton
	 */
	public JButton getNameSortB() {
		return nameSortB;
	}

	/**
	 * Visszaadja a szak alapján rendezésre használt gombot. Tesztelés céljából
	 * @return - JButton
	 */
	public JButton getFacultySortB() {
		return facultySortB;
	}
}
