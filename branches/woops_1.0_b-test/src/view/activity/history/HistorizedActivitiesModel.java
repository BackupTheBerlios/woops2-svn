package view.activity.history;

import java.util.Arrays;

import view.common.WoopsListDataModel;
import business.format.DateColumnComparator;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;

public class HistorizedActivitiesModel extends WoopsListDataModel {

	/**
	 * Constructeur par défaut
	 */
	public HistorizedActivitiesModel() {
		super();
	}
	
	/**
	 * Constructeur permettant d'initialiser la liste d'activités
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
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
	 * Trie la liste à partir de la colonne selectionnée  
	 * @param column		propriété qui définie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new DateColumnComparator(column, direction));
	}

}
