package view.breakdownelement.importactivities;

import java.util.Arrays;

import view.common.WoopsListDataModel;
import business.format.StringColumnComparator;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

public class ListIAModel extends WoopsListDataModel{
	public ListIAModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste des projets
	 * pour le contr�leur c'est � dire les lignes de la liste qui 
	 * sera affich�e au participant
	 */
	public ListIAModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ( (IAItem)data[index] ).getId();
	}
	
	/**
	 * Trie la liste � partir de la colonne selectionn�e  
	 * @param column		propri�t� qui d�finie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new StringColumnComparator(column, direction));
	}
}
