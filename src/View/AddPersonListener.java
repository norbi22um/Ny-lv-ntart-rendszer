package View;

/**
 * AddPersonListener egy interf�sz.
 * Seg�ti az adat�raml�s�t MCV szab�lyainak megfelel�en
 */
public interface AddPersonListener {
	
	/**
	 * Az interf�sz fel�lirand� f�ggv�nye, ami tov�bb ad egy AddPersonEventet, amib�l kiolvashat�ak az �rlapon hozz�adott emberek adataik..
	 */
	public void addPersonPerformed(AddPersonEvent e);
}
