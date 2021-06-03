package junittest;

import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import View.ButtonListener;
import View.SearchBar;

public class SearchBarTest {

	SearchBar searchbar;
	boolean reachedSearchBtn = false;
	boolean reachedBackBtn = false;
	
	/**
	 * A teszteléshez alakotott action listener osztály, ami implementálja az ButtonListener interfészt.
	 */
	class TesztActionListener implements ButtonListener{
		@Override
		public void btnPerformed(String text, ActionEvent e) {
			if(e.getActionCommand().equals("search")) {
				reachedSearchBtn = true;
			} else if(e.getActionCommand().equals("back")) {
				reachedBackBtn = true;
			}	
		}
	}
	
	/**
	 * A seachbarhoz hozzá adja listenernek az én osztályomat a tesztelés kedvéért.
	 */
	@Before
	public void setUp() {
		searchbar = new SearchBar();
		searchbar.setSearchBtnListener(new TesztActionListener());
		searchbar.setBackListener(new TesztActionListener());
	}
	
	/**
	 * Teszteli, hogy a Search gomb lenyomása után megfelelõ módon eljut-e a MainFramben lévõ actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener osztályként imitálok.
	 */
	@Test
	public void testSeachBtn() {
		searchbar.getSearchBtn().doClick();
		Assert.assertEquals(true, reachedSearchBtn);
	}
	
	/**
	 * Teszteli, hogy a Back gomb lenyomása után megfelelõ módon eljut-e a MainFramben lévõ actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener osztályként imitálok.
	 */
	@Test
	public void testBackBtn() {
		searchbar.getBackBtn().doClick();
		Assert.assertEquals(true, reachedBackBtn);
	}

}
