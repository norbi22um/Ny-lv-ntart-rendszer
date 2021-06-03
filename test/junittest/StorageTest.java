package junittest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.Person;
import Model.Storage;
import Model.Student;
import Model.Teacher;
import Model.Storage.NameComparator;

public class StorageTest {
	Storage db;
	
	/**
	 * A tesztel�shez hozz� ad a Storage oszt�lyban l�v� List-hez p�r objektumot.
	 */
	@Before
	public void setUp() {
		db = new Storage();
		//Students
		db.addStudent(new Student("M�t�", "G", "18-35", "male", "AGLEK1"));
		db.addStudent(new Student("Viktor", "E", "18-35", "male", "SAKBJ2"));
		db.addStudent(new Student("Vanessza", "D", "18-35", "female", "SDSFJ2"));
		db.addStudent(new Student("Zsanett", "F", "18-35", "female", "JFGDJ2"));
		//Teachers
		db.addTeacher(new Teacher("G�bor", "C", "65+", "male"));
		db.addTeacher(new Teacher("Istv�n", "A", "65+", "male"));
		db.addTeacher(new Teacher("Anna", "B", "36-64", "female"));
	}

	/**
	 * A getNumberOfTeachers f�ggv�ny m�k�d�s�t teszteli, hogy val�ban megfelel�en adja vissza a tan�rok sz�m�t.
	 */
	@Test
	public void testNumberOfTeachers() {
		int result = db.getNumberOfTeachers();
		Assert.assertEquals(3, result, 0);
	}
	
	/**
	 * A getNumberOfStudents f�ggv�ny m�k�d�s�t teszteli, hogy val�ban megfelel�en adja vissza a di�kok sz�m�t.
	 */
	@Test
	public void testNumberOfStudents() {
		int result = db.getNumberOfStudents();
		Assert.assertEquals(4, result);
	}

	/**
	 * A loadFromFile f�ggv�nyt teszteli, hogy megfelel�en kiv�telt dob-e nem l�tez� f�jl megad�sa eset�n.
	 * @throws Exception
	 */
	@Test(expected=IOException.class)
	public void testLoadFromFile() throws Exception{
		db.loadFromFile(new File("NemLetezik"));
	}
	
	/**
	 * A saveToFile f�ggv�nyt teszteli, hogy megfelel�en kiv�telt dob-e ha pl.: v�leletn�l egy mapp�ra kattintuk impot�l�sn�l.
	 * @throws Exception
	 */
	@Test(expected=IOException.class)
	public void testSaveToFile() throws Exception{
		db.saveToFile(new File("src"));
	}
	
	/**
	 * seachByName f�ggv�nyt teszteli, hogy az elv�rtak sz�m� eredm�ny van a keres�st k�vet�en a visszaadott list�ban.
	 * @throws Exception
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSearchByNameWirthFalseName() throws Exception{
		String name = "AnnaA";
		List<Person> result = db.searchByName(name);
		Person p = result.get(0);
	}
	
	/**
	 * seachByName f�ggv�nyt teszteli, hogy megfelel� eredm�nyt kaptunk a keres�st k�vet�en a visszaadott list�ban.
	 * @throws Exception
	 */
	@Test
	public void testSearchByName(){
		String name = "Anna";
		List<Person> result = db.searchByName(name);
		Assert.assertEquals("Anna", result.get(0).getName());
	}
	
	/**
	 * sortByName f�ggv�nyt teszteli, hogy megh�v�sa ut�n n�v szerint sorba rendezi-e az Embereket.
	 */
	@Test
	public void testSortByName() {
		db.sortByName();
		Assert.assertEquals("Anna", db.getPeople().get(0).getName());
		Assert.assertEquals("G�bor", db.getPeople().get(1).getName());
		Assert.assertEquals("Istv�n", db.getPeople().get(2).getName());
		Assert.assertEquals("M�t�", db.getPeople().get(3).getName());
		Assert.assertEquals("Vanessza", db.getPeople().get(4).getName());
		Assert.assertEquals("Viktor", db.getPeople().get(5).getName());
		Assert.assertEquals("Zsanett", db.getPeople().get(6).getName());
	}
	
	/**
	 * sortByFaculty f�ggv�nyt teszteli, hogy megh�v�sa ut�n szak szerint sorba rendezi-e az Embereket.
	 */
	@Test
	public void testSortByFaculty() {
		db.sortByFaculty();
		Assert.assertEquals("A", db.getPeople().get(0).getFaculty());
		Assert.assertEquals("B", db.getPeople().get(1).getFaculty());
		Assert.assertEquals("C", db.getPeople().get(2).getFaculty());
		Assert.assertEquals("D", db.getPeople().get(3).getFaculty());
		Assert.assertEquals("E", db.getPeople().get(4).getFaculty());
		Assert.assertEquals("F", db.getPeople().get(5).getFaculty());
		Assert.assertEquals("G", db.getPeople().get(6).getFaculty());
	}
	
	/**
	 * addStudent f�ggv�nyt teszteli, hogy hozz� adja-e a di�kot a list�hoz.
	 */
	@Test
	public void testAddStudent() {
		Student newStudent = new Student("Mil�n", "G", "18-34", "male", "AGLEK1");
		db.addStudent(newStudent);
		Assert.assertEquals(true, db.getPeople().get(7).isStudent());
		Assert.assertEquals("Mil�n", db.getPeople().get(7).getName());
	}
	
	/**
	 * addTeacher f�ggv�nyt teszteli, hogy hozz� adja-e a tan�rt a list�hoz.
	 */
	@Test
	public void testAddTeacher() {
		Teacher newTeacher = new Teacher("Tam�s", "K", "18-34", "male");
		db.addTeacher(newTeacher);
		Assert.assertEquals(false, db.getPeople().get(7).isStudent());
		Assert.assertEquals("Tam�s", db.getPeople().get(7).getName());
	}
	
	/**
	 * A getFirstAgeGroup f�ggv�nyt teszteli, hogy megfelel� hat�rozza meg a "18-35" kor kateg�ri�ba tartoz�k sz�m�t.
	 */
	@Test
	public void testGetFirstAgeGroup() {
		int result = db.getFirstAgeGroup();
		Assert.assertEquals(4, result);
	}
	
	/**
	 * A getSecondAgeGroup f�ggv�nyt teszteli, hogy megfelel� hat�rozza meg a "36-64" kor kateg�ri�ba tartoz�k sz�m�t.
	 */
	@Test
	public void testGetSecondAgeGroup() {
		int result = db.getSecondAgeGroup();
		Assert.assertEquals(1, result);
	}
	
	/**
	 * A getThidAgeGroup f�ggv�nyt teszteli, hogy megfelel� hat�rozza meg a "65+" kor kateg�ri�ba tartoz�k sz�m�t.
	 */
	@Test
	public void testGetThridAgeGroup() {
		int result = db.getThirdAgeGroup();
		Assert.assertEquals(2, result);
	}
}
