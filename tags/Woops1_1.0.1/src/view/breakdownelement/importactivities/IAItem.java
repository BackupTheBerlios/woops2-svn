package view.breakdownelement.importactivities;

import com.cc.framework.common.DisplayObject;

public class IAItem implements DisplayObject{
	String id ;
	String name ;
	String selectionne ; 
	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getSelectionne() {
		return selectionne;
	}

	public void setSelectionne(String selectionne) {
		this.selectionne = selectionne;
	}
}
