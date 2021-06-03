package junittest;

import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import View.ButtonListener;
import View.ToolBar;
import org.junit.Assert;

public class ToolBarTest {

	ToolBar toolbar;
	boolean reachedNameSortBtn = false;
	boolean reachedFacultySortBtn = false;
	
	/**
	 * A tesztel�shez alakotott action listener oszt�ly, ami implement�lja az ButtonListener interf�szt.
	 */
	class TesztActionListener implements ButtonListener{
		@Override
		public void btnPerformed(String text, ActionEvent e) {
			if(e.getActionCommand().equals("nameSort")) {
				reachedNameSortBtn = true;
			} else if(e.getActionCommand().equals("facultySort")) {
				reachedFacultySortBtn = true;
			}
		}
	}
	
	/**
	 * A toolbarhoz hozz� adja listenernek az �n oszt�lyomat a tesztel�s kedv��rt.
	 */
	@Before
	public void setUp() {
		toolbar = new ToolBar();
		toolbar.setNameSortButtonListener(new TesztActionListener());
		toolbar.setFacutltySortButtonListener(new TesztActionListener());
	}
	
	/**
	 * Teszteli, hogy a Sort By Name gomb lenyom�sa ut�n megfelel� m�don eljut-e a MainFramben l�v� actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener oszt�lyk�nt imit�lok.
	 */
	@Test
	public void testNameSortBtn() {
		toolbar.getNameSortB().doClick();
		Assert.assertEquals(true, reachedNameSortBtn);
	}
	
	/**
	 * Teszteli, hogy a Sort By Favulty gomb lenyom�sa ut�n megfelel� m�don eljut-e a MainFramben l�v� actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener oszt�lyk�nt imit�lok.
	 */
	@Test
	public void testFacultySortBtn() {
		toolbar.getFacultySortB().doClick();
		Assert.assertEquals(true, reachedFacultySortBtn);
	}
	
}
