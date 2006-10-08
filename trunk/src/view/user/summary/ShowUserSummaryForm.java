package view.user.summary;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ShowUserSummaryForm extends FWActionForm{
	private static final long serialVersionUID = -8589436425695222935L; /** Generated Serial ID */
	private String userID ;
	private String firstName ;
	private String lastName ;
	private String login ;
	private String roleCode ;
	
	private SimpleListControl	bdeList = new SimpleListControl () ;	/** liste des bde d'un user */
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRole(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * @return Returns the predecessorsList.
	 */
	public SimpleListControl getBdeList() {
		return bdeList;
	}


	/**
	 * @param model The model to set to predecessorsList.
	 */
	public void setBdeList(ListDataModel model) {
		this.bdeList.setDataModel(model);
	}
}
