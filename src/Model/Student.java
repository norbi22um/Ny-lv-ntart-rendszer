package Model;

import java.io.Serializable;

/**
 * A Student osztály az Persontól örököl és implementálja a Serializable interfacet.
 */
public class Student extends Person implements Serializable {

	private String neptun;
	
	/**
	 * A Student konstruktora. Meghívja a szülõ konstruktorát.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 * @param neptun
	 */
	public Student(String name, String faculty, String age, String gender, String neptun) {
		super(name, faculty, age, gender, true);
		this.neptun = neptun;
	}
	
	/**
	 * Visszaadja a neptunkódot.
	 * @return
	 */
	public String getNeptun() {
		return neptun;
	}

}
