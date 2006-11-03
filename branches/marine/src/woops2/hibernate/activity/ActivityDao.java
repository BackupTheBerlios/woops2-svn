package woops2.hibernate.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.activity.Activity;

public class ActivityDao extends HibernateDaoSupport{
	
	public void saveOrUpdateActivity(Activity a) {
		this.getHibernateTemplate().saveOrUpdate(a);
	}
	
	
	public List<Activity> getAllActivities(){
		List<Activity> loadAll = new ArrayList<Activity>();
		loadAll.addAll(this.getHibernateTemplate().loadAll(Activity.class));
		return loadAll;
	}
}
