package View;

/**
 * PersonTableListener egy interf�sz.
 * Seg�ti az adat�raml�s�t MCV szab�lyainak megfelel�en
 */
public interface PersonTableListener {
	/**
	 * Az interf�sz fel�lirand� f�ggv�nye, ami megadja mit kell csin�lni ha valaki r�kattint a "Delete" gombra.
	 */
	public void deletedRow(int row);
}
