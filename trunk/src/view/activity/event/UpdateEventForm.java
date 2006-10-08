/**
 * 
 */
package view.activity.event;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

/**
 * @author Simon REGGIANI
 *
 */
public class UpdateEventForm extends FWActionForm {
	private static final long serialVersionUID = 6090433996487352208L;

	private String eventId;
	private String name;
	private String details;
	private SimpleListControl possibleActivities;
	
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the possibleActivities.
	 */
	public ListDataModel getPossibleActivities() {
		return (ListDataModel)possibleActivities.getDataModel();
	}
	/**
	 * @param possibleActivities The possibleActivities to set.
	 */
	public void setPossibleActivities(ListDataModel dataModel) {
		this.possibleActivities.setDataModel(dataModel);
	}
	
	
}
