package manager;

import java.util.Collection;

import junit.framework.TestCase;
import business.BusinessConstantes;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

public class UserManagerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'business.user.UserManager.getUser(String)'
	 */
	public void testGetUser() {
		String login = new String("woops");
		User user = new User();
		
		try {
			
			user = UserManager.getInstance().getUser(login);
			
			// On v�rifie qu'on a r�cup�r� le bon user
			assertEquals(login, user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.user.UserManager.isLoginValid(String, String)'
	 */
	public void testIsLoginValid() {
		User user = new User();
		String login = new String("woops");
		String pass = new String("woops");
		try {
			
			user = UserManager.getInstance().isLoginValid(login,pass);
			
			// On v�rifie qu'on a r�cup�r� le bon user
			assertEquals(login, user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		User user = new User();
		UserRole ur = new UserRole();
		ur.setCode("dev");
		
		user.setLogin("nico");
		user.setPassword("nico");
		user.setFirstName("nico");
		user.setLastName("nico");
		user.setRole(ur);
		
		try {
			
			UserManager.getInstance().insert(user);
			//TODO V�rifier que le user a �t� ins�r� puis le supprimer pour que le test soit toujours valable
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.update(PersistentObject)'
	 */
	public void testUpdatePersistentObject() {
		User user = new User();
		
		try {
			UserManager.getInstance().getUser("nico");
			user.setPassword("nico7");
			UserManager.getInstance().update(user);
			//TODO V�rifier en BD les modifs
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.getList(String)'
	 */
	public void testGetList() {
		try {
			Collection coll = UserManager.getInstance().getList(BusinessConstantes.TABLE_USER);
			assertNotNull(coll);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
