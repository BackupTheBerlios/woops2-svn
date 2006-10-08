package manager;

import java.io.File;

import junit.framework.TestCase;
import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectManager;

public class WoopsManagerTest extends TestCase {
	protected File cfg; 
	protected PersistentObjectManager mgr;
	
	protected void setUp() throws Exception {
		super.setUp();
		cfg = new File("test/manager/hibernate.cfg.xml");
		HibernateSessionFactory.init(cfg);
	}
}
