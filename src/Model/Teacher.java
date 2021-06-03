package Model;

import java.io.Serializable;

/**
 * A Teacher osztály az Persontól örököl és implementálja a Serializable interfacet.
 */
public class Teacher extends Person implements Serializable{
	
	/**
	 * A Teacher konstruktora. Meghívja a szülõ konstruktorát.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 */
	public Teacher(String name, String faculty, String age, String gender) {
		super(name, faculty, age, gender, false);
	}
	
}
