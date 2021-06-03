package Model;

import java.io.Serializable;

/**
 * Person az embert definiáló alap osztály.
 * Megvalósítja a Serializable interfacet.
 */
public class Person implements Serializable{
	
	private int id;
	private String name;
	private String faculty;
	private String age;
	private String gender;
	private boolean isStudent;
	private static int cnt = 0;
	
	/**
	 * A Person konstuktora.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 * @param isStudent
	 */
	public Person(String name, String faculty, String age, String gender, boolean isStudent) {
		this.name = name;
		this.faculty = faculty;
		this.age = age;
		this.gender = gender;
		this.isStudent = isStudent;
		this.id = cnt;
		cnt++;
	}
	
	/**
	 * Visszaadja az ember nevét.
	 * @return - name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Visszaadja az ember szakát
	 * @return - faculty
	 */
	public String getFaculty() {
		return faculty;
	}

	/**
	 * Visszaadja az ember korcsoportját.
	 * @return - age
	 */
	public String getAge() {
		return age;
	}

	
	/**
	 * Visszaadja az ember nemét
	 * @return - gender
	 */
	public String getGender() {
		return gender;
	}

	
	/**
	 * Visszaadja, hogy az ember diák-e
	 * @return - isStudent
	 */
	public boolean isStudent() {
		return isStudent;
	}

	
	/**
	 * Visszaadja az adott ember ID számát, amit statikus változó segítségével állít elõ, hogy egyedi legyen.
	 * @return
	 */
	public int getId() {
		return id;
	}
}
