package view.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.format.Controleur;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class AddUserForm extends ActionForm{
	private static final long serialVersionUID = 4324731707888397420L; /** Generated Serial ID */
	private String firstName ;
	private String lastName ;
	private String login ;
	private String password ;
	private String password2 ;
	private String roleId ; /** * Role sélectionné pour l'utilisateur */
	private String mode ;
	private String userId ;
	private SimpleListControl roleOptions = new SimpleListControl ();
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	// TODO voir si login existe deja ??? dans action ?
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if (Controleur.isVide(lastName)){
			errors.add("lastName",new ActionMessage("errors.champ.obligatoire","Nom"));
		}
		if (Controleur.isVide(firstName)){
			errors.add("firstName",new ActionMessage("errors.champ.obligatoire","Prenom"));
		}
		if (Controleur.isVide(login)){
			errors.add("login",new ActionMessage("errors.champ.obligatoire","login"));
		}
		if (Controleur.isVide(password)){
			errors.add("password",new ActionMessage("errors.champ.obligatoire","mot de passe"));
		}
		if (!password.equals(password2)){
			errors.add("password2",new ActionMessage("admin.error.password","mot de passe"));
		}
		return errors;
	}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public ListDataModel getRoleOptions() {
		return (ListDataModel) this.roleOptions.getDataModel();
	}
	
	public void setRoleOptions(ListDataModel dataModel) {
		this.roleOptions.setDataModel(dataModel);
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
