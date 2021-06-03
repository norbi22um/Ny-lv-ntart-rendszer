package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * A Storage osztály heterogén módon tárolja egy láncolt listában a személyeket, egy adatbázist imitálva.
 *
 */
public class Storage {
	
	private List<Person> people;
	
	/**
	 * A Storage konstruktora. Inicializálja a Listet.
	 */
	public Storage() {
		people = new LinkedList<Person>();
		
	}
	
	/**
	 * NameComparator segítsévével a Collections.sort() használatával sorba rendezi a név szerint a listát.
	 */
	public void sortByName() {
		NameComparator nmCmp = new NameComparator();
		Collections.sort(people,nmCmp);
	}
	
	/**
	 * FacultyComparator segítsévével a Collections.sort() használatával sorba rendezi a szak szerint a listát.
	 */
	public void sortByFaculty() {
		FacultyComparator fyCmp = new FacultyComparator();
		Collections.sort(people,fyCmp);
	}
	
	/**
	 * A listához diákot ad hozzá
	 * @param student - A listához adandó diák.
	 */
	public void addStudent(Student student) {
		people.add(student);
	}
	
	/**
	 * A listához tanárt ad hozzá
	 * @param teacher - A listához adandó tanár.
	 */
	public void addTeacher(Teacher teacher) {
		people.add(teacher);
	}
	
	/**
	 * Visszaadja az embereket tartalmazó listát.
	 * @return - List<Person>
	 */
	public List<Person> getPeople(){
		return people;
	}
	
	/**
	 * Törli a litából az adott indexen található személyt.
	 * @param idx
	 */
	public void removePerson(int idx) {
		people.remove(idx);
	}
	
	/**
	 * A tanárok létszámát megszámlálja egy számláló int változó segítségével.
	 * @return - A tanárok létszáma
	 */
	public int getNumberOfTeachers() {
		int teacherCnt = 0;
		for(Person tmp: people) {
			if(!tmp.isStudent()) {
				teacherCnt++;
			}
		}
		return teacherCnt;
	}
	
	/**
	 * A diákok létszámát megszámlálja egy számláló int változó segítségével.
	 * @return - A diákok létszáma
	 */
	public int getNumberOfStudents() {
		int studentCnt = 0;
		for(Person tmp: people) {
			if(tmp.isStudent()) {
				studentCnt++;
			}
		}
		return studentCnt;
	}
	
	/**
	 * Megszámolja a 18-35-ba tartozó emberek számát
	 * @return
	 */
	public int getFirstAgeGroup() {
		int tmp = 0;
		for(Person person: people) {
			if(person.getAge().equals("18-35")) {
				tmp++;
			}
		}
		return tmp;
	}
	
	/**
	 * Megszámolja a 36-65-ba tartozó emberek számát
	 * @return
	 */
	public int getSecondAgeGroup() {
		int tmp = 0;
		for(Person person: people) {
			if(person.getAge().equals("36-64")) {
				tmp++;
			}
		}
		return tmp;
	}
	
	/**
	 * Megszámolja a 65+-ba tartozó emberek számát
	 * @return
	 */
	public int getThirdAgeGroup() {
		int tmp = 0;
		for(Person person: people) {
			if(person.getAge().equals("65+")) {
				tmp++;
			}
		}
		return tmp;
	}
	
	/**
	 * A fájlba mentést végzi el. Serializáció részét képezi a függvény. 
	 * @param file - Az a fájl ahova mentünk
	 * @throws IOException
	 */
	public void saveToFile(File file) throws IOException {
		FileOutputStream oStream = new FileOutputStream(file);
		ObjectOutputStream os = new ObjectOutputStream(oStream);
		
		Person[] peopArray = people.toArray(new Person[people.size()]);
		
		os.writeObject(peopArray);
		
		os.close();
	}
	
	/**
	 * A fájlból betöltést végzi el. Serializáció részét képezi a függvény. 
	 * @param file - Az a fájl ahonnan beolvasunk
	 * @throws IOException
	 */
	public void loadFromFile(File file) throws IOException{
		FileInputStream iStream = new FileInputStream(file);
		ObjectInputStream is = new ObjectInputStream(iStream);
		
		try {
			Person[] peopArray = (Person[])is.readObject();
			
			people.clear();
			people.addAll(Arrays.asList(peopArray));
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		is.close();
	}
	
	/**
	 * Egy új listában visszaadja az adott nevû embereket.
	 * @param name - A név, ami alapján keresünk.
	 * @return - Adott nevû embereket tartalmazó lista.
	 */
	public List<Person> searchByName(String name) {
		List<Person> tmp = new LinkedList<Person>();
		for(Person person:people) {
			if(person.getName().equals(name)) {
				tmp.add(person);
			}
		}
		return tmp;
	}
	
	
	/*
	 * COMPARATOR INNER CLASSES
	 */
	
	/**
	 * NameComparator implementálja a Comparatort. 
	 * Ezzel segítve a név szerinti sorbarendezés megvalósítását.
	 */
	public class NameComparator implements Comparator<Person>{
	    @Override
		public int compare(Person o1, Person o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}
	
	/**
	 * FacultyComparator implementálja a Comparatort. 
	 * Ezzel segítve a szak szerinti sorbarendezés megvalósítását.
	 */
	public class FacultyComparator implements Comparator<Person>{
	    @Override
		public int compare(Person o1, Person o2) {
			return o1.getFaculty().compareTo(o2.getFaculty());
		}
	}
}
