package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import junit.framework.TestCase;
import view.util.ProcessControler;

public class ProcessControlerTest extends TestCase {
	private File dpe;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'view.util.ProcessControler.load(File)'
	 */
	public void testLoad() {
		try {
			dpe = new File("test/util/processus.dpe");
			// On s'assure que le fichier existe 
			assertTrue(dpe.exists());
			assertTrue(dpe.canRead());
			
			// On créé un flux d'octets
			FileInputStream fis = new FileInputStream(dpe.getAbsolutePath());
			Collection listActivities = ProcessControler.load(new BufferedInputStream(fis));
			
			// On verifie que la recuperation s'est bien effectuee
			assertNotNull(listActivities);
			assertTrue(!listActivities.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}
