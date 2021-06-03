package View;

/**
 * AddPersonListener egy interfész.
 * Segíti az adatáramlását MCV szabályainak megfelelõen
 */
public interface AddPersonListener {
	
	/**
	 * Az interfész felülirandó függvénye, ami tovább ad egy AddPersonEventet, amibõl kiolvashatóak az ûrlapon hozzáadott emberek adataik..
	 */
	public void addPersonPerformed(AddPersonEvent e);
}
