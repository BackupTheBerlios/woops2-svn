package view.breakdownelement.create;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.format.Controleur;

import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class AddBreakdownElementForm extends ActionForm{
	private static final long serialVersionUID = -3106613575264437257L;  /** Generated Serial ID */
	private String prefix ;
	private String name ;
	private String details ;
	private String kindId ;
	private String bkId;
	private String mode ;
	private String startDate ;
	
	/* Liste representant toutes les utilisateurs affectables au projet */
	private SimpleListControl userParticipationOptions = new SimpleListControl();	
	/* Liste representant les cles des utilisateurs selectionnes */
	private String[] usersParticipation = new String[0];	
	private SimpleListControl kindOptions = new SimpleListControl ();
	
	
	public ListDataModel getKindOptions() {
		return (ListDataModel) this.kindOptions.getDataModel();
	}
	
	public void setKindOptions(ListDataModel dataModel) {
		this.kindOptions.setDataModel(dataModel);
	}

	public ListDataModel getUserParticipationOptions() {
		return (ListDataModel) userParticipationOptions.getDataModel();
	}

	public void setUserParticipationOptions(
			ListDataModel dataModel) {
		this.userParticipationOptions.setDataModel(dataModel);
	}

	public String[] getUsersParticipation() {
		return usersParticipation;
	}

	public void setUsersParticipation(String[] usersParticipation) {
		this.usersParticipation = usersParticipation;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBkId() {
		return bkId;
	}

	public void setBkId(String bkId) {
		this.bkId = bkId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		
		if (Controleur.isVide(name)){
			errors.add("name", new ActionMessage("errors.champ.obligatoire","Nom"));
		}
		
		if (Controleur.isVide(prefix)){
			errors.add("prefix", new ActionMessage("errors.champ.obligatoire","Prefixe"));
		}
		
		if (Controleur.isVide(startDate)){
			errors.add("startDate", new ActionMessage("errors.champ.obligatoire","Date de Départ"));
		}
		
		return errors;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
