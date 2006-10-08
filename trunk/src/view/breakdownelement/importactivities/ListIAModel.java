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
	 * pour le contrôleur c'est à dire les lignes de la liste qui 
	 * sera affichée au participant
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
	 * Trie la liste à partir de la colonne selectionnée  
	 * @param column		propriété qui définie la colonne
	 * @param direction		ordre de tri : ascendant ou descendant
	 */	
	public void sortByColumn(String column, SortOrder direction) {
		Arrays.sort(data, new StringColumnComparator(column, direction));
	}
}
