package Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Model.Person;
import Model.Storage;
import Model.Student;
import Model.Teacher;

/**
 * A Controller osztály a Model és View package között biztosít kommunikációt az MCV model alapján.
 * A GUI és Model között itt történik a kommunikációt.
 * A Model sok függvénye újra megvan valósítva itt, hogy a Modelt tovább abstraktálja és leválaszthatóvá tegye.
 */
public class Controller {
	Storage db = new Storage();
	
	/**
	 * A SidePanelben lévõ ûrlapból érkezõ adatokkal meghívja a Storage addStudent és addTeacher függvényét.
	 * Ezzel a függvénnyel tudja a View feltölteni adattal a Modelt.
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
	 * Meghívja a Storage fájlba mentésre használt, saveToFile függvényét.
	 * A Model és View elválasztását segíti elõ.
	 * @param file - a fájl ahova mentünk
	 * @throws IOException
	 */
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	/**
	 * Meghívja a Storage fájl betöltésére használt, loadFromFile függvényét.
	 * A Model és View elválasztását segíti elõ.
	 * @param file - A fájl, amit betöltünk
	 * @throws IOException
	 */
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
	
	/**
	 * Meghívja a Storageban az ember listából való törlésére használt függvényét.
	 * A Model és View elválasztását segíti elõ.
	 * @param idx
	 */
	public void removePerson(int idx) {
		db.removePerson(idx);
	}
	
	/**
	 * Meghívja a Storageban az diákok hozzáadására használt függvényt.
	 * A Model és View elválasztását segíti elõ.
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
	 * Meghívja a Storageban a tanárok hozzáadására használt függvényt.
	 * A Model és View elválasztását segíti elõ.
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
	 * Meghívja a Storageban a getPeople függvényt, ami visszadja az emberek tárolására használt listát
	 * A Model és View elválasztását segíti elõ.
	 * @return - List<Person>, az emberek tárolására használt lista
	 */
	public List<Person> getPeople(){
		return db.getPeople();
	}
	
	/**
	 * Visszaadja a Controllerben példányosított Storage implementációt.
	 * @return - Storage példány.
	 */
	public Storage getStorage() {
		return db;
	}
	
	/**
	 * Visszaadja a tanárok számát, a Storage getNumberOfTeachers függvényét meghívva.
	 * A Model és View elválasztását segíti elõ.
	 * @return - tanárok száma
	 */
	public int getNumberOfTeachers() {
		return db.getNumberOfTeachers();
	}
	
	/**
	 * Visszaadja a diákok számát, a Storage getNumberOfStudents függvényét meghívva.
	 * A Model és View elválasztását segíti elõ.
	 * @return - diákok száma
	 */
	public int getNumberOfStudents() {
		return db.getNumberOfStudents();
	}
	
	/**
	 * Visszaadja a 18-35 évesek számát, a Storage getFirstAgeGroup függvénye meghívásával.
	 * A Model és View elválasztását segíti elõ.
	 * @return - 18-35 évesek száma.
	 */
	public int getFirstAgeGroup() {
		return db.getFirstAgeGroup();
	}
	
	/**
	 * Visszaadja a 36-64 évesek számát, a Storage getFirstAgeGroup függvénye meghívásával.
	 * A Model és View elválasztását segíti elõ.
	 * @return - 36-64 évesek száma.
	 */
	public int getSecondAgeGroup() {
		return db.getSecondAgeGroup();
	}
	
	/**
	 * Visszaadja a 65+ évesek számát, a Storage getFirstAgeGroup függvénye meghívásával.
	 * A Model és View elválasztását segíti elõ.
	 * @return - 65+ évesek száma.
	 */
	public int getThirdAgeGroup() {
		return db.getThirdAgeGroup();
	}
	
	/**
	 * A Storage sortByName függvényét meghívval sorba rendezi név szerint az embereket.
	 * A Model és View elválasztását segíti elõ.
	 */
	public void sortByName() {
		db.sortByName();
	}
	
	/**
	 * A Storage sortByFaculty függvényét meghívval sorba rendezi szak szerint az embereket.
	 * A Model és View elválasztását segíti elõ.
	 */
	public void sortByFaculty() {
		db.sortByFaculty();
	}
	
	
	/**
	 * Meghívja a Storage név keresésére használt, searchByName függvényét.
	 * A Model és View elválasztását segíti elõ.
	 * @param name - Keresési feltétel.
	 * @return - A keresési feltételnek megfelelõ nevû emberek listája.
	 */
	public List<Person> searchByName(String name) {
		return db.searchByName(name);
	}
	
}
