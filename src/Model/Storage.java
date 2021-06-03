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
 * A Storage oszt�ly heterog�n m�don t�rolja egy l�ncolt list�ban a szem�lyeket, egy adatb�zist imit�lva.
 *
 */
public class Storage {
	
	private List<Person> people;
	
	/**
	 * A Storage konstruktora. Inicializ�lja a Listet.
	 */
	public Storage() {
		people = new LinkedList<Person>();
		
	}
	
	/**
	 * NameComparator seg�ts�v�vel a Collections.sort() haszn�lat�val sorba rendezi a n�v szerint a list�t.
	 */
	public void sortByName() {
		NameComparator nmCmp = new NameComparator();
		Collections.sort(people,nmCmp);
	}
	
	/**
	 * FacultyComparator seg�ts�v�vel a Collections.sort() haszn�lat�val sorba rendezi a szak szerint a list�t.
	 */
	public void sortByFaculty() {
		FacultyComparator fyCmp = new FacultyComparator();
		Collections.sort(people,fyCmp);
	}
	
	/**
	 * A list�hoz di�kot ad hozz�
	 * @param student - A list�hoz adand� di�k.
	 */
	public void addStudent(Student student) {
		people.add(student);
	}
	
	/**
	 * A list�hoz tan�rt ad hozz�
	 * @param teacher - A list�hoz adand� tan�r.
	 */
	public void addTeacher(Teacher teacher) {
		people.add(teacher);
	}
	
	/**
	 * Visszaadja az embereket tartalmaz� list�t.
	 * @return - List<Person>
	 */
	public List<Person> getPeople(){
		return people;
	}
	
	/**
	 * T�rli a lit�b�l az adott indexen tal�lhat� szem�lyt.
	 * @param idx
	 */
	public void removePerson(int idx) {
		people.remove(idx);
	}
	
	/**
	 * A tan�rok l�tsz�m�t megsz�ml�lja egy sz�ml�l� int v�ltoz� seg�ts�g�vel.
	 * @return - A tan�rok l�tsz�ma
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
	 * A di�kok l�tsz�m�t megsz�ml�lja egy sz�ml�l� int v�ltoz� seg�ts�g�vel.
	 * @return - A di�kok l�tsz�ma
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
	 * Megsz�molja a 18-35-ba tartoz� emberek sz�m�t
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
	 * Megsz�molja a 36-65-ba tartoz� emberek sz�m�t
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
	 * Megsz�molja a 65+-ba tartoz� emberek sz�m�t
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
	 * A f�jlba ment�st v�gzi el. Serializ�ci� r�sz�t k�pezi a f�ggv�ny. 
	 * @param file - Az a f�jl ahova ment�nk
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
	 * A f�jlb�l bet�lt�st v�gzi el. Serializ�ci� r�sz�t k�pezi a f�ggv�ny. 
	 * @param file - Az a f�jl ahonnan beolvasunk
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
	 * Egy �j list�ban visszaadja az adott nev� embereket.
	 * @param name - A n�v, ami alapj�n keres�nk.
	 * @return - Adott nev� embereket tartalmaz� lista.
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
	 * NameComparator implement�lja a Comparatort. 
	 * Ezzel seg�tve a n�v szerinti sorbarendez�s megval�s�t�s�t.
	 */
	public class NameComparator implements Comparator<Person>{
	    @Override
		public int compare(Person o1, Person o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}
	
	/**
	 * FacultyComparator implement�lja a Comparatort. 
	 * Ezzel seg�tve a szak szerinti sorbarendez�s megval�s�t�s�t.
	 */
	public class FacultyComparator implements Comparator<Person>{
	    @Override
		public int compare(Person o1, Person o2) {
			return o1.getFaculty().compareTo(o2.getFaculty());
		}
	}
}
