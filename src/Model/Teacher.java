package Model;

import java.io.Serializable;

/**
 * A Teacher oszt�ly az Persont�l �r�k�l �s implement�lja a Serializable interfacet.
 */
public class Teacher extends Person implements Serializable{
	
	/**
	 * A Teacher konstruktora. Megh�vja a sz�l� konstruktor�t.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 */
	public Teacher(String name, String faculty, String age, String gender) {
		super(name, faculty, age, gender, false);
	}
	
}
