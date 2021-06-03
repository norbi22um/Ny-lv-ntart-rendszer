package View;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ToolBar �r�k�l a JPanelt�l �s implement�lja az ActionListener interf�szt.
 * A sorbarendez�st megval�s�t� gomboknak ad otthont az ablak tetej�n.
 */
public class ToolBar extends JPanel implements ActionListener{

	private JButton nameSortB;
	private JButton facultySortB;
	private ButtonListener buttonListener;
	private ButtonListener nameSortListener;
	private ButtonListener facultySortListener;
	
	/**
	 * A ToolBar konstruktora. 
	 * Inicializ�lja a priv�t v�ltoz�kat.
	 * Be�ll�tja FlowLayoutra a panel layoutot �s hozz� adja a k�t gombot.
	 * A gombokhoz �nmag�t rendeli ActionListenerk�nt.
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
	 * Az ActionListener k�telez�en megval�s�tand� actionPerformed f�ggv�nye.
	 * A gombra kattint�s ut�n az ActionCommand alapj�n eld�nti melyik gombot nyomt�k meg.
	 * Majd megh�vja az adott gombhoz rendelt interf�szt.
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
	 * Be�ll�tja a ToolBarban a name sort interf�sz�t, amin kereszt�l kommunik�l a MainFrammel.
	 * @param buttonListener
	 */
	public void setNameSortButtonListener(ButtonListener buttonListener) {
		this.nameSortListener = buttonListener;
	}
	
	/**
	 * Be�ll�tja a ToolBarban a faculty sort interf�sz�t, amin kereszt�l kommunik�l a MainFrammel.
	 * @param buttonListener
	 */
	public void setFacutltySortButtonListener(ButtonListener buttonListener) {
		this.facultySortListener = buttonListener;
	}

	/**
	 * Visszaadja a n�v alapj�n rendez�sre haszn�lt gombot. Tesztel�s c�lj�b�l
	 * @return - JButton
	 */
	public JButton getNameSortB() {
		return nameSortB;
	}

	/**
	 * Visszaadja a szak alapj�n rendez�sre haszn�lt gombot. Tesztel�s c�lj�b�l
	 * @return - JButton
	 */
	public JButton getFacultySortB() {
		return facultySortB;
	}
}
