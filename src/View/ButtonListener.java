package View;

import java.awt.event.ActionEvent;

/**
 * ButtonListener egy interfész.
 * Segíti az adatáramlását MCV szabályainak megfelelõen
 */
public interface ButtonListener {
	
	/**
	 * Az interfész felülirandó függvénye, ami megadja mit kell csinálni ha valaki rákattint a ToolBarban lévõ gombokra.
	 */
	public void btnPerformed(String text, ActionEvent e);
}
