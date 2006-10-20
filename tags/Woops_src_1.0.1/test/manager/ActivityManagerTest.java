package manager;

import java.util.Collection;
import java.util.Iterator;

import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.user.User;


public class ActivityManagerTest extends WoopsManagerTest {
	
	protected void setUp() throws Exception {
		super.setUp();
		mgr = ActivityManager.getInstance();
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivityById(Integer)'
	 */
	public void testGetActivityById() {
		final Integer activityId = new Integer(1);
		Activity activity = new Activity();
		
		try {
			// On s'assure que l'activité existe
			assertTrue(((ActivityManager)mgr).exist(Activity.class, activityId));
			
			// Récupération de l'activité
			activity = ActivityManager.getInstance().getActivityById(activityId);
			
			// On vérifie qu'on a récupéré la bonne activité
			assertEquals(activityId, activity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitiesByUser(Integer)'
	 */
	public void testGetActivitiesByUser() {
		final Integer userId = new Integer(1);
		final Integer bdeId = new Integer(1);
		Collection listActivities = null;
		
		try {
			// On s'assure que le participant existe
			assertTrue(((ActivityManager)mgr).exist(User.class, userId));
			
			listActivities = ActivityManager.getInstance().getRemainingActivitiesByUserByBDE(userId, bdeId);
		
			// On vérifie que toutes les activités appartiennent au participant voulu
			Iterator iter = listActivities.iterator();
			while (iter.hasNext()) {	
				assertEquals(userId, ((Activity)iter.next()).getUserId());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitiesHistoryByUser(Integer)'
	 */
	public void testGetActivitiesHistoryByUser() {
		
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getPossibleActivityPredecessors(Integer)'
	 */
	public void testGetPossibleActivityPredecessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getPredecessors(Integer)'
	 */
	public void testGetPredecessors() {
		final Integer activityId = new Integer(1);
		Activity activity = new Activity();
		Collection listPredecessors = null;
		Collection listSuccessors = null;
		
		try {
			// On s'assure que l'activité existe
			assertTrue(((ActivityManager)mgr).exist(Activity.class, activityId));
			
			// Récupération de l'activité
			activity = ActivityManager.getInstance().getActivityById(activityId);
			
			// Récupération des prédecesseurs l'activité
			listPredecessors = ActivityManager.getInstance().getPredecessors(activityId);
			
			/* On vérifie pour chaque prédecesseur que l'un des successeurs est l'activité passée  
			en paramètre */  
			Iterator iter = listPredecessors.iterator();
			while (iter.hasNext()) {
				// Récupération de la liste des successeurs
				listSuccessors = ActivityManager.getInstance().getSuccessors((Integer)((Activity)iter.next()).getId());
				assertTrue(listSuccessors.contains(activity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getSuccessors(Integer)'
	 */
	public void testGetSuccessors() {
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitySequencesPredecessors(Integer)'
	 */
	public void testGetActivitySequencesPredecessors() {
		final Integer activityId = new Integer(1);
		Activity activity = new Activity();
		Collection listActivitySequences = null;
		
		try {
			// On s'assure que l'activité existe
			assertTrue(((ActivityManager)mgr).exist(Activity.class, activityId));
			// Récupération de l'activité
			activity = ActivityManager.getInstance().getActivityById(activityId);
			
			// Récupération des prédecesseurs l'activité
			listActivitySequences = ActivityManager.getInstance().getActivitySequencesPredecessors(activityId);
			
			/* On vérifie pour chaque dépendance que le successeur correspond à l'activité pour 
			laquelle on recherche les prédecesseurs */  
			Iterator iter = listActivitySequences.iterator();
			while (iter.hasNext()) {	
				assertEquals(activity, ((ActivitySequence)iter.next()).getPredecessor());
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivitySequencesSuccessors(Integer)'
	 */
	public void testGetActivitySequencesSuccessors() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.saveActivityDependances(Integer, Collection, Collection)'
	 */
	public void testSaveActivityDependances() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.getActivityWithDependances(Integer)'
	 */
	public void testGetActivityWithDependances() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.verifChangeStateActivity(Activity)'
	 */
	public void testVerifChangeStateActivity() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.activitiesChangeState(Integer)'
	 */
	public void testActivitiesChangeState() {

	}

	/*
	 * Test method for 'business.activity.ActivityManager.deleteLinksFromActivity(Integer)'
	 */
	public void testDeleteLinksFromActivity() {

	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.insert(PersistentObject)'
	 */
	public void testInsertPersistentObject() {
		final Integer activityId = new Integer(1);
		final String activityName = "Test Création";
		Activity activity = new Activity();
		
		try {
			activity.setId(activityId);
			activity.setName(activityName);
			// On s'assure que le nom de l'activité n'existe pas
			/**
			 * TODO Test sur le nom
			 */
			//assertFalse(((ActivityManager)mgr).exist(Activity.class, activityId));
			
			// Création de l'activité et insertion en BD
			Activity act = new Activity();
			Integer actId = null;
			act.setId(activityId);
			act.setName(activityName);
			actId = (Integer) mgr.insert(act);
			
			// On s'assure que l'activité a été insérée en BD
			assertEquals(activityId, actId);
			assertTrue(((ActivityManager)mgr).exist(Activity.class, activityId));
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.update(PersistentObject)'
	 */
	public void testUpdatePersistentObject() {

	}

	/*
	 * Test method for 'business.hibernate.PersistentObjectManager.delete(PersistentObject)'
	 */
	public void testDeletePersistentObject() {

	}

}
