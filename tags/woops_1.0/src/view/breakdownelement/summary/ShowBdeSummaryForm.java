package view.breakdownelement.summary;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ShowBdeSummaryForm extends FWActionForm{
	private static final long serialVersionUID = 7530202138513542655L;  /** Generated Serial ID */
	private String prefix ;
	private String name ;
	private String details ;
	private String startDate ;
	private String endDate ;
	private String kind ;
	
	private SimpleListControl	usersList = new SimpleListControl () ;	/** liste des bde d'un user */
	
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * @return Returns the predecessorsList.
	 */
	public SimpleListControl getUsersList() {
		return usersList;
	}


	/**
	 * @param model The model to set to predecessorsList.
	 */
	public void setUsersList(ListDataModel model) {
		this.usersList.setDataModel(model);
	}
	
}
