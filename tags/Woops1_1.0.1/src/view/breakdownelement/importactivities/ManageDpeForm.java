package view.breakdownelement.importactivities;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDpeForm extends FWActionForm{
	private static final long serialVersionUID = -6093888440247162830L; /** Generated Serial ID */
	private SimpleListControl listActivities = new SimpleListControl () ;
	
	
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	public void setListActivities(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}
}
