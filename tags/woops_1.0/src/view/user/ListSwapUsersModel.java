package view.user;

import java.util.Arrays;

import view.common.WoopsListDataModel;
import business.format.StringColumnComparator;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

public class ListSwapUsersModel extends WoopsListDataModel {

	/**
	 * Constructeur par defaut
	 */
	public ListSwapUsersModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activites
	 * pour le contreleur c'est a dire les lignes de la liste qui 
	 * sera affichee au participant
	 */
	public ListSwapUsersModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((UserItem)data[index]).getId();
	}
	
	/**
	 * Trie la liste a partir de la colonne selectionnee  
	 * @param column		propriete qui definie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new StringColumnComparator(column, direction));
	}
	
	

}
