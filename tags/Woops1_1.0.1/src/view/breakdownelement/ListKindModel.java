package view.breakdownelement;

import view.common.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ListKindModel extends WoopsListDataModel {

	
	/**
	 * Constructeur par defaut
	 */
	public ListKindModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activites
	 * pour le controleur c'est a dire les lignes de la liste qui 
	 * sera affichee au participant
	 */
	public ListKindModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((KindItem)data[index]).getId();
	}

}
