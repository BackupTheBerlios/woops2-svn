package view.activity.performing;

import view.breakdownelement.ListBreakDownElementsModel;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ListActivitiesForm extends FWActionForm {
	private static final long serialVersionUID = -8539904306657854815L; /** Generated Serial ID */
	
	/** * Liste de contrôle des activites que l'on souhaite afficher au participant */ 
	private SimpleListControl listActivities = new SimpleListControl();
	
	/** * Liste de contrôle des projets que l'on souhaite afficher au participant */ 
	private SimpleListControl listBDEs = new SimpleListControl();
	
	private String bdeId; /** * Entité par défaut */
	
	/** Retourne la liste des activités. 
	 * 	Cet accesseur permet à la JSP d'accéder à la liste
	 ** @return liste du contrôleur */
	public SimpleListControl getListActivities() {
		return listActivities;
	}
	
	/** * Modifie les données de la liste des activités du participant
	 * @param dataModel modèle de données pour le contrôleur */
	public void setDataModel(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}


	/** Retourne la liste des entites. 
	 * 	Cet accesseur permet à la JSP d'afficher une liste deroulante d'entites
	 ** @return liste du contrôleur */
	public ListDataModel getListBDEs() {
		return (ListBreakDownElementsModel)listBDEs.getDataModel();
	}

	/** * Modifie les données de la liste des entites du participant
	 * @param dataModel modèle de données pour le contrôleur */
	public void setBDEDataModel(ListDataModel dataModel) {
		listBDEs.setDataModel(dataModel);
	}
	
	/**
	 * Modifie l'entite selectionnee par defaut dans la liste
	 * @param uniqueKey identifiant de l'entite
	 */
	public void setBDEDefault(String uniqueKey) {
		this.bdeId = uniqueKey;
	}

	public String getBdeId() {
		return bdeId;
	}

	public void setBdeId(String bdeId) {
		this.bdeId = bdeId;
	}
}
