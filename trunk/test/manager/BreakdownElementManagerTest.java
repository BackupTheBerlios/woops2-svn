package manager;

import java.util.ArrayList;

import business.BusinessConstantes;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementKind;
import business.breakdownelement.BreakdownElementManager;

public class BreakdownElementManagerTest extends WoopsManagerTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		BreakdownElement bde = new BreakdownElement();
		bde.setPrefix("p");
		bde.setName("projet");
		bde.setDetails("super projet");
		BreakdownElementKind bdek = new BreakdownElementKind();
		bdek.setId(new Integer(1));
		bde.setKind(bdek);
		
		try {
			
			Integer bdeId = (Integer)BreakdownElementManager.getInstance().insert(bde);
			assertNotNull(bdeId);
			
			bde.setId(bdeId);
			BreakdownElementManager.getInstance().delete(bde);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetList() {

		try {
			ArrayList list = (ArrayList)BreakdownElementManager.getInstance().getList(BusinessConstantes.TABLE_BREAKDOWN);
			assertFalse(list.isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testCopyBreakdownElement() {
		try {
			// création d'un bde copie
			BreakdownElement destBde = new BreakdownElement();
			destBde.setPrefix("c");
			destBde.setName("copie de test");
			destBde.setDetails("copie de test");
			destBde.setKind(new BreakdownElementKind(new Integer(1)));
			destBde.setId(BreakdownElementManager.getInstance().copyBreakdownElement(new Integer(1),destBde));
			
			assertNotNull(destBde.getId());
			
			
			// suppression de bde
			//BreakdownElementManager.getInstance().delete(destBde);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
