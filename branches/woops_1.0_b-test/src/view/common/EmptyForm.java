package view.common;

import com.cc.framework.adapter.struts.FWActionForm;

/**
 * Empty FormBean. Requise dans certains cas 
 * quand l'action ne fournit aucune information  
 */
public class EmptyForm extends FWActionForm {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut
	 */
	public EmptyForm() {
		super();
	}

}
