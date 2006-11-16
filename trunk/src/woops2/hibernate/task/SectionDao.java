
package woops2.hibernate.task ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.task.Section;
import woops2.model.workbreakdownelement.WorkBreakdownElement;

/**
 * TODO finir commentaires
 * @author garwind
 *
 */
public class SectionDao extends HibernateDaoSupport {

	
	public void saveOrUpdateSection (Section _section) {
		this.getHibernateTemplate().saveOrUpdate(_section) ;
	}

	public List <Section> getAllSection() {
		List <Section> loadAll = new ArrayList <Section>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Section.class)) ;
		return loadAll ;
	}

	
	public Section getSection (String _id) {
		return (Section) this.getHibernateTemplate().get(Section.class, _id) ;
	}

	
	public void deleteSection (Section _section) {
		try {
			this.getHibernateTemplate().delete(_section) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting activity into
			// the db.
			logger.error("#### ERROR #### --- SectionDao => deleteSection : trying to delete unexisting object \n" + sose) ;
		}
	}
}
