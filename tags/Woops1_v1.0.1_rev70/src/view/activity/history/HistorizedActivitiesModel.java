package view.activity.history;

import java.util.Arrays;

import view.common.WoopsListDataModel;
import business.format.DateColumnComparator;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

public class HistorizedActivitiesModel extends WoopsListDataModel {

	/**
	 * Constructeur par d�faut
	 */
	public HistorizedActivitiesModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activit�s
	 * pour le contr�leur c'est � dire les lignes de la liste qui 
	 * sera affich�e au participant
	 */
	public HistorizedActivitiesModel(DisplayObject[] elements) {
		super(elements);
	}
	
	/**
	 * @see view.common.WoopsListDataModel#getUniqueKey(int)
	 */
	public String getUniqueKey(int index) {
		return ((HistorizedActivityItem)data[index]).getId();
	}
	
	/**
	 * Trie la liste � partir de la colonne selectionn�e  
	 * @param column		propri�t� qui d�finie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new DateColumnComparator(column, direction));
	}

}
