package view.common;

import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.model.ListDataModel;

public abstract class WoopsListDataModel implements ListDataModel {
	/**
	 * Collection d'objets (= lignes) pour le contr�leur de la liste
	 */
	protected DisplayObject[] data = new DisplayObject[0];
	
	
	/**
	 * Constructeur par d�faut
	 */
	public WoopsListDataModel() {
		super();
	}
	

	/**
	 * Constructeur qui initialise la liste avec les �l�ments
	 * @param	elements	liste d'�l�ments
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
	 * La ListControl a besoin du cl� unique si le d�tail
	 * de la ligne doit �tre affich�.
	 * G�n�ralement la cl� correspond � la cl� primaire dans
	 * la base de donn�es.
	 * 
	 * @see com.cc.framework.ui.model.ListDataModel#getUniqueKey(int)
	 */
	public abstract String getUniqueKey(int index);
}
