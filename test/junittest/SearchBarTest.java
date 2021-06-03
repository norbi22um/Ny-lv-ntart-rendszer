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
	 * A tesztel�shez alakotott action listener oszt�ly, ami implement�lja az ButtonListener interf�szt.
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
	 * A seachbarhoz hozz� adja listenernek az �n oszt�lyomat a tesztel�s kedv��rt.
	 */
	@Before
	public void setUp() {
		searchbar = new SearchBar();
		searchbar.setSearchBtnListener(new TesztActionListener());
		searchbar.setBackListener(new TesztActionListener());
	}
	
	/**
	 * Teszteli, hogy a Search gomb lenyom�sa ut�n megfelel� m�don eljut-e a MainFramben l�v� actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener oszt�lyk�nt imit�lok.
	 */
	@Test
	public void testSeachBtn() {
		searchbar.getSearchBtn().doClick();
		Assert.assertEquals(true, reachedSearchBtn);
	}
	
	/**
	 * Teszteli, hogy a Back gomb lenyom�sa ut�n megfelel� m�don eljut-e a MainFramben l�v� actionListeneremhez ennek a jele,
	 * amit itt a TesztActionListener oszt�lyk�nt imit�lok.
	 */
	@Test
	public void testBackBtn() {
		searchbar.getBackBtn().doClick();
		Assert.assertEquals(true, reachedBackBtn);
	}

}
