
package woops2.hibernate.breakdownelement ;

import java.util.ArrayList ;
import java.util.List ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.breakdownelement.BreakdownElement ;

/**
 * BreakdownElementDao manage requests from the system to store BreakdownElement to the database.
 * 
 * @author deder
 */
public class BreakdownElementDao extends HibernateDaoSupport {

	/**
	 * Save or update an bde.
	 * 
	 * @param _bde
	 */
	public void saveOrUpdateBreakdownElement (BreakdownElement _bde) {
		this.getHibernateTemplate().saveOrUpdate(_bde) ;
	}

	/**
	 * Return a list of bdes.
	 * 
	 * @return
	 */
	public List <BreakdownElement> getAllBreakdownElements () {
		List <BreakdownElement> loadAll = new ArrayList <BreakdownElement>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(BreakdownElement.class)) ;
		return loadAll ;
	}

	/**
	 * Return the bde which have the id _id.
	 * 
	 * @param _id
	 * @return
	 */
	public BreakdownElement getBreakdownElement (String _id) {
		return (BreakdownElement) this.getHibernateTemplate().get(BreakdownElement.class, _id) ;
	}

	/**
	 * Delete the bde.
	 * 
	 * @param _bde
	 */
	public void deleteBreakdownElement (BreakdownElement _bde) {
		try {
			this.getHibernateTemplate().delete(_bde) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting element into the db.
			logger.error("#### ERROR #### --- BreakdownElementdao => deleteBreakdownElement : trying to delete unexisting object \n" + sose) ;
		}
	}
}
