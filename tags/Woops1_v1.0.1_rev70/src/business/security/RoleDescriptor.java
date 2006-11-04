package business.security;

public interface RoleDescriptor {

	/**
	 * V�rifie si l'utilisateur a la permission requise
	 * 
	 * @param		droit	Permission requise
	 * @return		<code>true</code> Si l'utilisateur a cette permission
	 */
	public boolean hasRight(String right);

	/**
	 * Retourne le code interne pour ce r�le
	 *
	 * @return	Le code pour le r�le
	 */
	public String getCode();
}
