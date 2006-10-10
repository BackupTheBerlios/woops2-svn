package business.security;

import java.util.HashSet;
import java.util.Set;

public class Permission {

	/**
	 * Permissions littérales
	 */
	private Set literals = new HashSet();

	/**
	 * Constructeur
	 */
	public Permission() {
		super();
	}

	/**
	 * Vérifie le permission de l'utilisateur
	 * 
	 * @param	permission 	La permission de l'utilisateur
	 * @return	<code>true</code> si la permission est valable
	 */
	public boolean isGranted(String permission) {
		// Vérification des permissions littérales
		if (literals.contains(permission)) {
			return true;
		}

		// Pas de permission
		return false;
	}
}

