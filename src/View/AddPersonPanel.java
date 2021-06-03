package View;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * AddPersonPanel �r�k�l a JPanel oszt�lyt�l �s implement�lja az ActionListener interf�szt.
 * GridBagLayout seg�ts�g�vel egy �rlapot k�sz�t, amin kereszt�l embereket lehet adni a rendszerhez.
 */
public class AddPersonPanel extends JPanel implements ActionListener {
	
	//Label-�k
	private JLabel nameLabel;
	private JLabel facultyLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel neptunLabel;
	private JLabel statusLabel;
	
	//�rlapon l�v� kompenensek
	private JTextField facultyField;
	private JTextField nameField;
	private JButton submitBtn;
	private JList ageList;
	private JComboBox genderCBox;
	private JCheckBox studentCheck;
	private JTextField neptunField;
	
	//Listenerk�nt haszn�lt interf�sz.
	private AddPersonListener formListener;

	
	/**
	 * AddPersonPanel konstruktora.
	 * 
	 */
	public AddPersonPanel() {
		
		setBorder(BorderFactory.createTitledBorder("Add Person"));
		
		//Inicializ�l�s
		nameLabel = new JLabel("Name:");
		facultyLabel = new JLabel("Faculty:");
		genderLabel = new JLabel("Gender:");
		ageLabel = new JLabel("Age:");
		statusLabel = new JLabel("Are you a student?:");
		neptunLabel = new JLabel("Neptun ID:");
		
		
		nameField = new JTextField(10);
		facultyField = new JTextField(10);
		submitBtn = new JButton("Submit");
		studentCheck  = new JCheckBox();
		neptunField = new JTextField(10);
		
		
		//Neptun be�ll�t�sa
		neptunLabel.setEnabled(false);
		neptunField.setEnabled(false);
				
		//StudentCheck-nek be�ll�tom az action commandj�t �s az oszt�lyt (this) az ActionListnerjek�nt.	
		studentCheck.setActionCommand("IsStudent");
		studentCheck.addActionListener(this);
				
		
		
		//Gender ComboBox be�ll�t�sa
		genderCBox = new JComboBox();
		DefaultComboBoxModel genderVarients = new DefaultComboBoxModel();
		genderVarients.addElement("male");
		genderVarients.addElement("female");
		genderVarients.addElement("other");
		genderCBox.setModel(genderVarients);
		
		
		
		//Age List be�ll�t�sa
		ageList = new JList();
		DefaultListModel ageVarients = new DefaultListModel();
		ageVarients.addElement("18-35");
		ageVarients.addElement("36-64");
		ageVarients.addElement("65+");
		
		ageList.setModel(ageVarients);
		ageList.setSelectedIndex(0);
		
		ageList.setPreferredSize(new Dimension(112,55));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		
		submitBtn.setActionCommand("Submit");
		submitBtn.addActionListener(this);
		
		setFormLayout();
		
		
		
		
	}
	
	public void setFormListener(AddPersonListener listener) {
		this.formListener = listener;
	}

	/**
	 * Az ActionListener interf�sz k�telez�en implement�land� f�ggv�nye.
	 * Action Command alapj�n ellez�rzi, hogy a checkbox vagy gomb megnyom�sa t�rt�nt meg.
	 * A checkbox eset�n megv�ltoztatja a neptun textfield el�rhet�s�g�t.
	 * A sumbit gomb eset�n �j AddPersonEvent-et hoz l�tre, amit �t az az AddPersonListener interf�sz addPersonPerformed f�ggv�ny�nek.
	 * Az eventen kereszt�l el�rhet�ek a megadott adatok m�s oszt�lyok sz�m�ra.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Submit")) {
			String name = nameField.getText();
			String occupation = facultyField.getText();
			String ageCategory = (String)ageList.getSelectedValue();
			String genderCategory = (String)genderCBox.getSelectedItem();
			String neptun = neptunField.getText();
			boolean isStudent = studentCheck.isSelected();
			
			AddPersonEvent event = new AddPersonEvent(this, name, occupation, ageCategory, genderCategory, isStudent, neptun);
			
			if(formListener != null) {
				formListener.addPersonPerformed(event);
			}
		} else if(e.getActionCommand().equals("IsStudent")) {
			boolean isChecked = studentCheck.isSelected();
			neptunField.setEnabled(isChecked);
			neptunLabel.setEnabled(isChecked);
		}
		
	}
	
	/**
	 * Priv�t f�ggv�ny, ami be�ll�tja a panel layoutj�t GridBagLayoutra.
	 * A komponenseknek egyenk�nt be�ll�tja a "x", "y" helyzet�ket, s�lyukat, constraintjeiket �s insertsjeiket.
	 * Majd hozz� adja a panelhez.
	 */
	private void setFormLayout() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		Insets labelInsets = new Insets(0,0,0,5);
		Insets otherInsets = new Insets(0,0,0,0);
		
		//Els� Sor
		
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = labelInsets;
		add(nameLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = otherInsets;
		add(nameField, gbc);
		
		//M�sodik Sor
		
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = labelInsets;
		add(facultyLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = otherInsets;
		add(facultyField, gbc);
		
		//Harmadik Sor
		
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = labelInsets;
		add(genderLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = otherInsets;
		add(genderCBox, gbc);
		
		//Negyedik Sor
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = labelInsets;
		add(ageLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = otherInsets;
		add(ageList, gbc);
		
		//�t�dik
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = labelInsets;
		add(statusLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = otherInsets;
		add(studentCheck, gbc);
		
		//Hatodik
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = labelInsets;
		add(neptunLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = otherInsets;
		add(neptunField, gbc);
		
		//Gomb sora
		gbc.weightx = 1;
		gbc.weighty = 2.0;
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = otherInsets;
		add(submitBtn, gbc);
	}	
}
