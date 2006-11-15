package woops2.application.console;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import woops2.hibernate.activity.ActivityDao;
import woops2.model.activity.Activity;
import woops2.model.breakdownelement.BreakdownElement;

/**
 * Application for testing hibernate and spring configuration with small model
 * @author garwind
 */
public class DAOConsoleTest {

	
	public static void main(String[] args) {
		// Getback the application context from the spring configuration file
		Resource res = new FileSystemResource("src/applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		// Show what is in the factory
		System.out.println("factory => "+factory);
		// Getback the hibernateTemplate bean
		org.springframework.orm.hibernate3.HibernateTemplate hibTempl = (org.springframework.orm.hibernate3.HibernateTemplate) factory.getBean("hibernateTemplate");
		System.out.println("HibTemplate => "+hibTempl);
		// Get the ActivityDao Singleton for managing Activity data
		ActivityDao dao = (ActivityDao) factory.getBean("ActivityDao");
		
		/*
		// Create empty Activity
		Activity a = new Activity();
		a.setPrefix("a_test");
		// Save it
		dao.saveOrUpdateActivity(a);
		
		Activity b = new Activity();
		b.setPrefix("b_test");
		dao.saveOrUpdateActivity(b);
		
		a.getBreakDownElements().add(b);
		dao.saveOrUpdateActivity(a);
		*/
		
		/*
		Activity a = dao.getActivityByPrefix("a_test");
		System.out.println("Activity a recuperée : "+a.getBreakDownElements().size());
		Set liste = a.getBreakDownElements();
		Iterator it = liste.iterator();
		while (it.hasNext()){
			Activity tmp = (Activity) it.next(); 
			System.out.println("==> "+tmp.getPrefix()+"\n");
			tmp.setPrefix(tmp.getPrefix()+" test2");
			dao.saveOrUpdateActivity(tmp);
		}
		
		Activity b = dao.getActivityByPrefix("b_test test2 test2");
		System.out.println("==> b = "+b);
		*/
		/*
		 BreakdownElement bde = new BreakdownElement();
		bde.setPrefix("bde_test");
		dao.getHibernateTemplate().save(bde);

		 */
		/*
		Activity a = dao.getActivityByPrefix("a_test");
		BreakdownElement bde = (BreakdownElement) dao.getHibernateTemplate().find("from BreakdownElement as b where b.prefix = ?", "bde_test").get(0);
		a.getBreakDownElements().add(bde);
		dao.saveOrUpdateActivity(a);
		*/
		/*
		Activity c = new Activity();
		dao.saveOrUpdateActivity(c);
		
		Activity b = dao.getActivityByPrefix("b_test");
		
		b.getBreakDownElements().add(b);
		dao.saveOrUpdateActivity(b);
		
		System.out.println("Activity b recup "+b.getBreakDownElements().size());
		*/
		
		Activity a = dao.getActivityByPrefix("a_test");
		System.out.println("Activity a recuperée : "+a.getBreakDownElements().size());
		Set liste = a.getBreakDownElements();
		Iterator it = liste.iterator();
		while (it.hasNext()){
			//Activity tmp = (Activity) it.next(); 
			Object o = it.next();
			System.out.println("==> "+o+"\n");
			
			if (o instanceof Activity){
				Activity tmp = (Activity) o;
				System.out.println(" --> Activity "+tmp.getPrefix());
				System.out.println("----> collections ="+tmp.getBreakDownElements().size());
				System.out.println("---> "+tmp);
				//tmp.setPrefix("b_test");
				//dao.saveOrUpdateActivity(tmp);
			}
			else if (o instanceof BreakdownElement) System.out.println(" --> Bde ");
		}
	}

}
