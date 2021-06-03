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
 * AddPersonPanel örököl a JPanel osztálytól és implementálja az ActionListener interfészt.
 * GridBagLayout segítségével egy ûrlapot készít, amin keresztûl embereket lehet adni a rendszerhez.
 */
public class AddPersonPanel extends JPanel implements ActionListener {
	
	//Label-ök
	private JLabel nameLabel;
	private JLabel facultyLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel neptunLabel;
	private JLabel statusLabel;
	
	//Ûrlapon lévõ kompenensek
	private JTextField facultyField;
	private JTextField nameField;
	private JButton submitBtn;
	private JList ageList;
	private JComboBox genderCBox;
	private JCheckBox studentCheck;
	private JTextField neptunField;
	
	//Listenerként használt interfész.
	private AddPersonListener formListener;

	
	/**
	 * AddPersonPanel konstruktora.
	 * 
	 */
	public AddPersonPanel() {
		
		setBorder(BorderFactory.createTitledBorder("Add Person"));
		
		//Inicializálás
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
		
		
		//Neptun beállítása
		neptunLabel.setEnabled(false);
		neptunField.setEnabled(false);
				
		//StudentCheck-nek beállítom az action commandjét és az osztályt (this) az ActionListnerjeként.	
		studentCheck.setActionCommand("IsStudent");
		studentCheck.addActionListener(this);
				
		
		
		//Gender ComboBox beállítása
		genderCBox = new JComboBox();
		DefaultComboBoxModel genderVarients = new DefaultComboBoxModel();
		genderVarients.addElement("male");
		genderVarients.addElement("female");
		genderVarients.addElement("other");
		genderCBox.setModel(genderVarients);
		
		
		
		//Age List beállítása
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
	 * Az ActionListener interfész kötelezõen implementálandó függvénye.
	 * Action Command alapján ellezõrzi, hogy a checkbox vagy gomb megnyomása történt meg.
	 * A checkbox esetén megváltoztatja a neptun textfield elérhetõségét.
	 * A sumbit gomb esetén új AddPersonEvent-et hoz létre, amit át az az AddPersonListener interfész addPersonPerformed függvényének.
	 * Az eventen keresztûl elérhetõek a megadott adatok más osztályok számára.
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
	 * Privát függvény, ami beállítja a panel layoutját GridBagLayoutra.
	 * A komponenseknek egyenként beállítja a "x", "y" helyzetüket, súlyukat, constraintjeiket és insertsjeiket.
	 * Majd hozzá adja a panelhez.
	 */
	private void setFormLayout() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		Insets labelInsets = new Insets(0,0,0,5);
		Insets otherInsets = new Insets(0,0,0,0);
		
		//Elsõ Sor
		
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
		
		//Második Sor
		
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
		
		//Ötödik
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
