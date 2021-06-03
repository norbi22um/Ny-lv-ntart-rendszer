package Model;

import java.io.Serializable;

/**
 * A Student oszt�ly az Persont�l �r�k�l �s implement�lja a Serializable interfacet.
 */
public class Student extends Person implements Serializable {

	private String neptun;
	
	/**
	 * A Student konstruktora. Megh�vja a sz�l� konstruktor�t.
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
	 * Visszaadja a neptunk�dot.
	 * @return
	 */
	public String getNeptun() {
		return neptun;
	}

}
