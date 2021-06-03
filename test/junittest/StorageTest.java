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
	 * A teszteléshez hozzá ad a Storage osztályban lévõ List-hez pár objektumot.
	 */
	@Before
	public void setUp() {
		db = new Storage();
		//Students
		db.addStudent(new Student("Máté", "G", "18-35", "male", "AGLEK1"));
		db.addStudent(new Student("Viktor", "E", "18-35", "male", "SAKBJ2"));
		db.addStudent(new Student("Vanessza", "D", "18-35", "female", "SDSFJ2"));
		db.addStudent(new Student("Zsanett", "F", "18-35", "female", "JFGDJ2"));
		//Teachers
		db.addTeacher(new Teacher("Gábor", "C", "65+", "male"));
		db.addTeacher(new Teacher("István", "A", "65+", "male"));
		db.addTeacher(new Teacher("Anna", "B", "36-64", "female"));
	}

	/**
	 * A getNumberOfTeachers függvény mûködését teszteli, hogy valóban megfelelõen adja vissza a tanárok számát.
	 */
	@Test
	public void testNumberOfTeachers() {
		int result = db.getNumberOfTeachers();
		Assert.assertEquals(3, result, 0);
	}
	
	/**
	 * A getNumberOfStudents függvény mûködését teszteli, hogy valóban megfelelõen adja vissza a diákok számát.
	 */
	@Test
	public void testNumberOfStudents() {
		int result = db.getNumberOfStudents();
		Assert.assertEquals(4, result);
	}

	/**
	 * A loadFromFile függvényt teszteli, hogy megfelelõen kivételt dob-e nem létezõ fájl megadása esetén.
	 * @throws Exception
	 */
	@Test(expected=IOException.class)
	public void testLoadFromFile() throws Exception{
		db.loadFromFile(new File("NemLetezik"));
	}
	
	/**
	 * A saveToFile függvényt teszteli, hogy megfelelõen kivételt dob-e ha pl.: véleletnûl egy mappára kattintuk impotálásnál.
	 * @throws Exception
	 */
	@Test(expected=IOException.class)
	public void testSaveToFile() throws Exception{
		db.saveToFile(new File("src"));
	}
	
	/**
	 * seachByName függvényt teszteli, hogy az elvártak számû eredmény van a keresést követõen a visszaadott listában.
	 * @throws Exception
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSearchByNameWirthFalseName() throws Exception{
		String name = "AnnaA";
		List<Person> result = db.searchByName(name);
		Person p = result.get(0);
	}
	
	/**
	 * seachByName függvényt teszteli, hogy megfelelõ eredményt kaptunk a keresést követõen a visszaadott listában.
	 * @throws Exception
	 */
	@Test
	public void testSearchByName(){
		String name = "Anna";
		List<Person> result = db.searchByName(name);
		Assert.assertEquals("Anna", result.get(0).getName());
	}
	
	/**
	 * sortByName függvényt teszteli, hogy meghívása után név szerint sorba rendezi-e az Embereket.
	 */
	@Test
	public void testSortByName() {
		db.sortByName();
		Assert.assertEquals("Anna", db.getPeople().get(0).getName());
		Assert.assertEquals("Gábor", db.getPeople().get(1).getName());
		Assert.assertEquals("István", db.getPeople().get(2).getName());
		Assert.assertEquals("Máté", db.getPeople().get(3).getName());
		Assert.assertEquals("Vanessza", db.getPeople().get(4).getName());
		Assert.assertEquals("Viktor", db.getPeople().get(5).getName());
		Assert.assertEquals("Zsanett", db.getPeople().get(6).getName());
	}
	
	/**
	 * sortByFaculty függvényt teszteli, hogy meghívása után szak szerint sorba rendezi-e az Embereket.
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
	 * addStudent függvényt teszteli, hogy hozzá adja-e a diákot a listához.
	 */
	@Test
	public void testAddStudent() {
		Student newStudent = new Student("Milán", "G", "18-34", "male", "AGLEK1");
		db.addStudent(newStudent);
		Assert.assertEquals(true, db.getPeople().get(7).isStudent());
		Assert.assertEquals("Milán", db.getPeople().get(7).getName());
	}
	
	/**
	 * addTeacher függvényt teszteli, hogy hozzá adja-e a tanárt a listához.
	 */
	@Test
	public void testAddTeacher() {
		Teacher newTeacher = new Teacher("Tamás", "K", "18-34", "male");
		db.addTeacher(newTeacher);
		Assert.assertEquals(false, db.getPeople().get(7).isStudent());
		Assert.assertEquals("Tamás", db.getPeople().get(7).getName());
	}
	
	/**
	 * A getFirstAgeGroup függvényt teszteli, hogy megfelelõ határozza meg a "18-35" kor kategóriába tartozók számát.
	 */
	@Test
	public void testGetFirstAgeGroup() {
		int result = db.getFirstAgeGroup();
		Assert.assertEquals(4, result);
	}
	
	/**
	 * A getSecondAgeGroup függvényt teszteli, hogy megfelelõ határozza meg a "36-64" kor kategóriába tartozók számát.
	 */
	@Test
	public void testGetSecondAgeGroup() {
		int result = db.getSecondAgeGroup();
		Assert.assertEquals(1, result);
	}
	
	/**
	 * A getThidAgeGroup függvényt teszteli, hogy megfelelõ határozza meg a "65+" kor kategóriába tartozók számát.
	 */
	@Test
	public void testGetThridAgeGroup() {
		int result = db.getThirdAgeGroup();
		Assert.assertEquals(2, result);
	}
}
