package woops2.hibernate.activity;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.activity.Activity;

public class ActivityDao extends HibernateDaoSupport{
	
	public void saveOrUpdateActivity(Activity a) {
		this.getHibernateTemplate().saveOrUpdate(a);
	}

}
