package business.user;

import java.util.Collection;

import net.sf.hibernate.collection.Set;
import business.hibernate.HistorizedObject;

import com.cc.framework.security.Principal;

public class User extends HistorizedObject implements Principal {

	private static final long serialVersionUID = -526715186666675789L; 	/** Generated Serial ID */

	private Integer id;

	private String login;

	private String password;

	private String firstName;

	private String lastName;
	
	private Integer defaultBDEId; /** Entite par defaut du participant */
	
	private UserRole role; /** Role de l'utilisateur */
	
	private Set bdes;

	/**
	 * @link aggregation
	 * @associates business.activity.Activity
	 * @directed directed
	 * @supplierCardinality 0..*
	 */
	private Collection linkToActivity = null;

	/**
	 * Constructeur
	 */
	public User() {
		super();
		this.defaultBDEId = null;
		this.bdes = null;
	}
	
	public Collection getLinkToActivity() {
		return linkToActivity;
	}

	public void setLinkToActivity(Collection linkToActivity) {
		this.linkToActivity = linkToActivity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Object getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Récupération du rôle
	 * @return UserRole
	 */
	public UserRole getRole() {
		return role;
	}
	
	/**
	 * Modification du rôle
	 * @param role Le rôle à modifier
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
//	 ==============================
	// Principal
	// ==============================

	/**
	 * @see com.cc.framework.security.Principal#hasRight(java.lang.String)
	 */
	public boolean hasRight(String checkForRight) {
		if (role == null) {
			return false;
		} else {
			return role.hasRight(checkForRight);
		}
	}

	/**
	 * @see com.cc.framework.security.Principal#isInRole(java.lang.String)
	 */
	public boolean isInRole(String checkForRole) {
		if (role == null) {
			return false;
		} else {
			return role.equals(checkForRole);
		}
	}

	public Set getBdes() {
		return bdes;
	}

	public void setBdes(Set bdes) {
		this.bdes = bdes;
	}

	public Integer getDefaultBDEId() {
		return defaultBDEId;
	}

	public void setDefaultBDEId(Integer defaultBDEId) {
		this.defaultBDEId = defaultBDEId;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return id.equals(((User)obj).getId());
		}
		return super.equals(obj);
	}
}
