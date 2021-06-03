package View;
import java.util.EventObject;

/**
 * AddPersonEvent �r�k�l az EventObject oszt�lyt�l.
 * R�gz�ti az �rlapon megadott emberek adatait.
 * Az MVC-t �tlete szerint igyekszik elv�lasztani a View modelt az implement�ci�t�l.
 */
public class AddPersonEvent extends EventObject {
	
	private String name;
	private String faculty;
	private String age;
	private String gender;
	private String neptun;
	private boolean isStudent;
	
	/**
	 * AddPersonEvent konstruktora.
	 * Be�ll�tja a bels� v�ltoz�kat.
	 */
	public AddPersonEvent(Object src, String n, String f, String age, String gender, boolean isStudent, String neptun) {
		super(src);
		this.name = n;
		this.faculty = f;
		this.age = age;
		this.gender = gender;
		this.neptun = neptun;
		this.isStudent = isStudent;
	}

	/**
	 * Visszadaja az ember nev�t.
	 * @return ember neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Visszaadja az ember sz�k�t.
	 * @return szak
	 */
	public String getFaculty() {
		return faculty;
	}

	/**
	 * Visszaadja az ember korcsoportj�t.
	 * @return korcsoport
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Visszaadja az ember nev�t
	 * @return nem
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Visszaadja a di�k neptun k�dj�t
	 * @return neptun k�d
	 */
	public String getNeptun() {
		return neptun;
	}

	/**
	 * Visszadaja, hogy az ember Di�k-e
	 * @return di�k-e
	 */
	public boolean isStudent() {
		return isStudent;
	}
}
