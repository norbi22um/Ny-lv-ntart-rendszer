package View;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * NotePanel a JPanel osztálytól örököl
 * Jegyzetírást és olvasást teszi lehetõvé.
 */
public class NotePanel extends JPanel {
	
	private JTextArea szoveg;
	
	/**
	 * NotePanel konstruktora, ami inicializál egy JTextArea-t, amit hozzád ad egy JSrcrollPane-be ágyazva a Panelhez.
	 */
	public NotePanel() {
		szoveg = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(szoveg), BorderLayout.CENTER);
		szoveg.append("Take your notes here...\n");
	}
	
	/**
	 * Paraméterként megadott szöveget az hozzá a JTextArea-hoz.
	 * @param text - String, amit hozzá ad a JTextArea-hoz.
	 */
	public void addText(String text) {
		szoveg.append(text);
	}
	
}
