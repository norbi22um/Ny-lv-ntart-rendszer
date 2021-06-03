package View;
import java.util.EventObject;

/**
 * AddPersonEvent örököl az EventObject osztálytól.
 * Rögzíti az ürlapon megadott emberek adatait.
 * Az MVC-t ötlete szerint igyekszik elválasztani a View modelt az implementációtól.
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
	 * Beállítja a belsõ változókat.
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
	 * Visszadaja az ember nevét.
	 * @return ember neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Visszaadja az ember szákát.
	 * @return szak
	 */
	public String getFaculty() {
		return faculty;
	}

	/**
	 * Visszaadja az ember korcsoportját.
	 * @return korcsoport
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Visszaadja az ember nevét
	 * @return nem
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Visszaadja a diák neptun kódját
	 * @return neptun kód
	 */
	public String getNeptun() {
		return neptun;
	}

	/**
	 * Visszadaja, hogy az ember Diák-e
	 * @return diák-e
	 */
	public boolean isStudent() {
		return isStudent;
	}
}
