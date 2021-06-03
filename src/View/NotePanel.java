package View;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * NotePanel a JPanel oszt�lyt�l �r�k�l
 * Jegyzet�r�st �s olvas�st teszi lehet�v�.
 */
public class NotePanel extends JPanel {
	
	private JTextArea szoveg;
	
	/**
	 * NotePanel konstruktora, ami inicializ�l egy JTextArea-t, amit hozz�d ad egy JSrcrollPane-be �gyazva a Panelhez.
	 */
	public NotePanel() {
		szoveg = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(szoveg), BorderLayout.CENTER);
		szoveg.append("Take your notes here...\n");
	}
	
	/**
	 * Param�terk�nt megadott sz�veget az hozz� a JTextArea-hoz.
	 * @param text - String, amit hozz� ad a JTextArea-hoz.
	 */
	public void addText(String text) {
		szoveg.append(text);
	}
	
}
