package business.security;

import java.util.HashSet;
import java.util.Set;

public class Permission {

	/**
	 * Permissions litt�rales
	 */
	private Set literals = new HashSet();

	/**
	 * Constructeur
	 */
	public Permission() {
		super();
	}

	/**
	 * V�rifie le permission de l'utilisateur
	 * 
	 * @param	permission 	La permission de l'utilisateur
	 * @return	<code>true</code> si la permission est valable
	 */
	public boolean isGranted(String permission) {
		// V�rification des permissions litt�rales
		if (literals.contains(permission)) {
			return true;
		}

		// Pas de permission
		return false;
	}
}

