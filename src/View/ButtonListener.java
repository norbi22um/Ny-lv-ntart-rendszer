package View;

import java.awt.event.ActionEvent;

/**
 * ButtonListener egy interf�sz.
 * Seg�ti az adat�raml�s�t MCV szab�lyainak megfelel�en
 */
public interface ButtonListener {
	
	/**
	 * Az interf�sz fel�lirand� f�ggv�nye, ami megadja mit kell csin�lni ha valaki r�kattint a ToolBarban l�v� gombokra.
	 */
	public void btnPerformed(String text, ActionEvent e);
}
