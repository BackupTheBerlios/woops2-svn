package view.common;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

public abstract class WoopsListDataModel implements ListDataModel {
	/**
	 * Collection d'objets (= lignes) pour le contrôleur de la liste
	 */
	protected DisplayObject[] data = new DisplayObject[0];
	
	
	/**
	 * Constructeur par défaut
	 */
	public WoopsListDataModel() {
		super();
	}
	

	/**
	 * Constructeur qui initialise la liste avec les éléments
	 * @param	elements	liste d'éléments
	 */
	public WoopsListDataModel(DisplayObject[] elements) {
		this.data = elements; 
	}

	/**
	 * @see com.cc.framework.ui.model.ListDataModel#getElementAt(int)
	 */
	public Object getElementAt(int index) {
		return data[index];
	}
	
	/**
	 * @see com.cc.framework.ui.model.ListDataModel#size()
	 */
	public int size() {
		return data.length;
	}
	
	/**
	 * La ListControl a besoin du clé unique si le détail
	 * de la ligne doit être affiché.
	 * Généralement la clé correspond à la clé primaire dans
	 * la base de données.
	 * 
	 * @see com.cc.framework.ui.model.ListDataModel#getUniqueKey(int)
	 */
	public abstract String getUniqueKey(int index);
}
