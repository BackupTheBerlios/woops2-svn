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
public class ListEventsForm extends FWActionForm {
	private static final long serialVersionUID = -1784602049701215496L; /** Generated Serial ID */
	private SimpleListControl listEventsNotOccured = new SimpleListControl();
	private SimpleListControl listEventsOccured = new SimpleListControl();

	/**
	 * @return Returns the dataModel of listEventsNotOccured.
	 */
	public SimpleListControl getListEventsNotOccured() {
		return listEventsNotOccured;
	}

	/**
	 * @param dataModel the dataModel of listEventsNotOccured to set.
	 */
	public void setListEventsNotOccured(ListDataModel dataModel) {
		this.listEventsNotOccured.setDataModel(dataModel);
	}
	
	/**
	 * @return Returns the dataModel of listEventsOccured.
	 */
	public SimpleListControl getListEventsOccured() {
		return listEventsOccured;
	}

	/**
	 * @param dataModel the dataModel of listEventsOccured to set.
	 */
	public void setListEventsOccured(ListDataModel dataModel) {
		this.listEventsOccured.setDataModel(dataModel);
	}
	
	
}
