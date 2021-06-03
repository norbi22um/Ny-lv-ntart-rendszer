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
	 * A teszteléshez alakotott action listener osztály, ami implementálja az ButtonListener interfészt.
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
	 * A toolbarhoz hozzá adja listenernek az én osztályomat a tesztelés kedvéért.
	 */
	@Before
	public void setUp() {
		toolbar = new ToolBar();
		toolbar.setNameSortButtonListener(new TesztActionListener());
		toolbar.setFacutltySortButtonListener(new TesztActionListener());
	}
	
	/**
	 * Teszteli, hogy a Sort By Name gomb lenyomása után megfelelõ módon eljut-e a MainFramben lévõ actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener osztályként imitálok.
	 */
	@Test
	public void testNameSortBtn() {
		toolbar.getNameSortB().doClick();
		Assert.assertEquals(true, reachedNameSortBtn);
	}
	
	/**
	 * Teszteli, hogy a Sort By Favulty gomb lenyomása után megfelelõ módon eljut-e a MainFramben lévõ actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener osztályként imitálok.
	 */
	@Test
	public void testFacultySortBtn() {
		toolbar.getFacultySortB().doClick();
		Assert.assertEquals(true, reachedFacultySortBtn);
	}
	
}
