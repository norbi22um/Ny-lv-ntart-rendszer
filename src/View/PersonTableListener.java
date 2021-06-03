package View;

/**
 * PersonTableListener egy interfész.
 * Segíti az adatáramlását MCV szabályainak megfelelõen
 */
public interface PersonTableListener {
	/**
	 * Az interfész felülirandó függvénye, ami megadja mit kell csinálni ha valaki rákattint a "Delete" gombra.
	 */
	public void deletedRow(int row);
}
