package View;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Person;
import Model.Student;


/**
 * PersonData az AbstractTableModelt�l �r�k�l.
 * Defini�lja a t�bla modelj�t.
 */
public class PersonData extends AbstractTableModel {

	private List<Person> people;
	private String[] columnNames = {"Id", "Name", "Faculty", "Student", "Neptun Id", "Gender", "Age Category"};
	
	/**
	 * Be�ll�tja a megjelen�tend� adatokat
	 * @param ppl - Megjelen�tend� emberek list�ja
	 */
	public void setDataList(List<Person> ppl) {
		people = ppl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return people.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount() {
		return 7;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Person tmp = people.get(rowIndex);
		
		switch(columnIndex){
			case 0:
				return tmp.getId();
			case 1:
				return tmp.getName();
			case 2:
				return tmp.getFaculty();
			case 3:
				return tmp.isStudent();
			case 4:
				if(tmp.isStudent())
					return ((Student) tmp).getNeptun();
				else
					return "";
			case 5:
				return tmp.getGender();
			case 6:
				return tmp.getAge();
		}
		
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
