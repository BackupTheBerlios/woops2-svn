package business.user;

import business.security.Permission;
import business.security.RoleDescriptor;

public class UserRole implements RoleDescriptor, Comparable {
	/**
	 * Id du rôle en BD
	 */	
	private Integer id;
	
	/**
	 * Nom interne
	 */
	private String code;

	/**
	 * Permissions du rôle
	 */
	private Permission permission = new Permission();

	/**
	 * Construteur par défaut
	 */
	public UserRole() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param		code code interne
	 */
	public UserRole(String code) {
		super();
		/* L'id est fournit par la BD */
		this.code	= code;
	}

	/**
	 * récupération du code.
	 * 
	 * @return		String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Modification du code
	 * 
	 * @param		code Le code à modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}

	
	/**
	 * @see business.security.RoleDescriptor#hasRight(String)
	 */
	public boolean hasRight(String right) {
		return permission.isGranted(right);
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(Object o) {
			
		if (o instanceof UserRole) {
			return code.compareTo(((UserRole) o).code);
		}
		return 0;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return code.equals(obj);
		}
		return super.equals(obj);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
