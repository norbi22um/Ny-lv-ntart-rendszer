package Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Model.Person;
import Model.Storage;
import Model.Student;
import Model.Teacher;

/**
 * A Controller oszt�ly a Model �s View package k�z�tt biztos�t kommunik�ci�t az MCV model alapj�n.
 * A GUI �s Model k�z�tt itt t�rt�nik a kommunik�ci�t.
 * A Model sok f�ggv�nye �jra megvan val�s�tva itt, hogy a Modelt tov�bb abstrakt�lja �s lev�laszthat�v� tegye.
 */
public class Controller {
	Storage db = new Storage();
	
	/**
	 * A SidePanelben l�v� �rlapb�l �rkez� adatokkal megh�vja a Storage addStudent �s addTeacher f�ggv�ny�t.
	 * Ezzel a f�ggv�nnyel tudja a View felt�lteni adattal a Modelt.
	 * 
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 * @param isStudent
	 * @param neptun
	 */
	public void addPerson(String name, String faculty, String age, String gender, boolean isStudent, String neptun) {
		if(isStudent) {
			addStudent(name, faculty, age, gender, neptun);
		} else {
			addTeacher(name, faculty, age, gender);
		}
	}
	
	/**
	 * Megh�vja a Storage f�jlba ment�sre haszn�lt, saveToFile f�ggv�ny�t.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param file - a f�jl ahova ment�nk
	 * @throws IOException
	 */
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	/**
	 * Megh�vja a Storage f�jl bet�lt�s�re haszn�lt, loadFromFile f�ggv�ny�t.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param file - A f�jl, amit bet�lt�nk
	 * @throws IOException
	 */
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
	
	/**
	 * Megh�vja a Storageban az ember list�b�l val� t�rl�s�re haszn�lt f�ggv�ny�t.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param idx
	 */
	public void removePerson(int idx) {
		db.removePerson(idx);
	}
	
	/**
	 * Megh�vja a Storageban az di�kok hozz�ad�s�ra haszn�lt f�ggv�nyt.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 * @param neptun
	 */
	private void addStudent(String name, String faculty, String age, String gender, String neptun) {
		Student student = new Student(name, faculty, age, gender, neptun);
		db.addStudent(student);
	}
	
	/**
	 * Megh�vja a Storageban a tan�rok hozz�ad�s�ra haszn�lt f�ggv�nyt.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param name
	 * @param faculty
	 * @param age
	 * @param gender
	 */
	private void addTeacher(String name, String faculty, String age, String gender) {
		Teacher teacher = new Teacher(name, faculty, age, gender);
		db.addTeacher(teacher);
	}
	
	/**
	 * Megh�vja a Storageban a getPeople f�ggv�nyt, ami visszadja az emberek t�rol�s�ra haszn�lt list�t
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - List<Person>, az emberek t�rol�s�ra haszn�lt lista
	 */
	public List<Person> getPeople(){
		return db.getPeople();
	}
	
	/**
	 * Visszaadja a Controllerben p�ld�nyos�tott Storage implement�ci�t.
	 * @return - Storage p�ld�ny.
	 */
	public Storage getStorage() {
		return db;
	}
	
	/**
	 * Visszaadja a tan�rok sz�m�t, a Storage getNumberOfTeachers f�ggv�ny�t megh�vva.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - tan�rok sz�ma
	 */
	public int getNumberOfTeachers() {
		return db.getNumberOfTeachers();
	}
	
	/**
	 * Visszaadja a di�kok sz�m�t, a Storage getNumberOfStudents f�ggv�ny�t megh�vva.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - di�kok sz�ma
	 */
	public int getNumberOfStudents() {
		return db.getNumberOfStudents();
	}
	
	/**
	 * Visszaadja a 18-35 �vesek sz�m�t, a Storage getFirstAgeGroup f�ggv�nye megh�v�s�val.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - 18-35 �vesek sz�ma.
	 */
	public int getFirstAgeGroup() {
		return db.getFirstAgeGroup();
	}
	
	/**
	 * Visszaadja a 36-64 �vesek sz�m�t, a Storage getFirstAgeGroup f�ggv�nye megh�v�s�val.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - 36-64 �vesek sz�ma.
	 */
	public int getSecondAgeGroup() {
		return db.getSecondAgeGroup();
	}
	
	/**
	 * Visszaadja a 65+ �vesek sz�m�t, a Storage getFirstAgeGroup f�ggv�nye megh�v�s�val.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @return - 65+ �vesek sz�ma.
	 */
	public int getThirdAgeGroup() {
		return db.getThirdAgeGroup();
	}
	
	/**
	 * A Storage sortByName f�ggv�ny�t megh�vval sorba rendezi n�v szerint az embereket.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 */
	public void sortByName() {
		db.sortByName();
	}
	
	/**
	 * A Storage sortByFaculty f�ggv�ny�t megh�vval sorba rendezi szak szerint az embereket.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 */
	public void sortByFaculty() {
		db.sortByFaculty();
	}
	
	
	/**
	 * Megh�vja a Storage n�v keres�s�re haszn�lt, searchByName f�ggv�ny�t.
	 * A Model �s View elv�laszt�s�t seg�ti el�.
	 * @param name - Keres�si felt�tel.
	 * @return - A keres�si felt�telnek megfelel� nev� emberek list�ja.
	 */
	public List<Person> searchByName(String name) {
		return db.searchByName(name);
	}
	
}
