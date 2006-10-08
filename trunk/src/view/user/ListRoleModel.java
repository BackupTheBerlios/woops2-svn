package view.user;

import view.common.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ListRoleModel extends WoopsListDataModel {

	/**
	 * Constructeur par défaut
	 */
	public ListRoleModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activités
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
	 */
	public ListRoleModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((RoleItem)data[index]).getId();
	}

}
