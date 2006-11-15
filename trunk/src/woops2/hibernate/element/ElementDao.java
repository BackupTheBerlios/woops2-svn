
package woops2.hibernate.element ;

import java.util.ArrayList ;
import java.util.List ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.element.Element ;

/**
 * ElementDao manage requests from the system to store Element to the database.
 * 
 * @author deder
 */
public class ElementDao extends HibernateDaoSupport {

	/**
	 * Save or update an element.
	 * 
	 * @param _element
	 */
	public void saveOrUpdateElement (Element _element) {
		this.getHibernateTemplate().saveOrUpdate(_element) ;
	}

	/**
	 * Return a list of elements.
	 * 
	 * @return
	 */
	public List <Element> getAllElements () {
		List <Element> loadAll = new ArrayList <Element>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Element.class)) ;
		return loadAll ;
	}

	/**
	 * Return the element which have the id _id.
	 * 
	 * @param _id
	 * @return
	 */
	public Element getElement (String _id) {
		return (Element) this.getHibernateTemplate().get(Element.class, _id) ;
	}

	/**
	 * Delete the element.
	 * 
	 * @param _element
	 */
	public void deleteElement (Element _element) {
		try {
			this.getHibernateTemplate().delete(_element) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting element into the db.
			logger.error("#### ERROR #### --- ElementDao => deleteElement : trying to delete unexisting object \n" + sose) ;
		}
	}
}
