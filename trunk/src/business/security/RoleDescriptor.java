package business.security;

public interface RoleDescriptor {

	/**
	 * Vérifie si l'utilisateur a la permission requise
	 * 
	 * @param		droit	Permission requise
	 * @return		<code>true</code> Si l'utilisateur a cette permission
	 */
	public boolean hasRight(String right);

	/**
	 * Retourne le code interne pour ce rôle
	 *
	 * @return	Le code pour le rôle
	 */
	public String getCode();
}
