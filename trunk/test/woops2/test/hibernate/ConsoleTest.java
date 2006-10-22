package woops2.test.hibernate;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import woops2.hibernate.activity.ActivityDao;
import woops2.model.activity.Activity;

public class ConsoleTest {

	
	public static void main(String[] args) {
		Resource res = new FileSystemResource("src/applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		System.out.println("factory => "+factory);
		
		org.springframework.orm.hibernate3.HibernateTemplate hibTempl = (org.springframework.orm.hibernate3.HibernateTemplate) factory.getBean("hibernateTemplate");
		System.out.println("HibTemplate => "+hibTempl);
		
		ActivityDao dao = (ActivityDao) factory.getBean("ActivityDao");
		Activity a = new Activity();
		dao.saveOrUpdateActivity(a);
	}

}
