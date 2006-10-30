package business.security;

import java.util.Hashtable;

import business.user.User;
import business.user.UserRole;

public final class SecurityConfig {
	// Liste des r�le reconnus par le syst�me
	private Hashtable roles = new Hashtable();
	
	/**
	 * Constructeur
	 */
	public SecurityConfig() {
		super();
		roles.put(Roles.GUEST_ROLE, new UserRole(Roles.GUEST_ROLE));
		roles.put(Roles.ADMINISTRATOR_ROLE, new UserRole(Roles.ADMINISTRATOR_ROLE));
		roles.put(Roles.DEVELOPER_ROLE, new UserRole(Roles.DEVELOPER_ROLE));
	}
	
	/**
	 * R�cup�ration d'un UserRole � partir du code
	 * @param 	code	code interne du r�le
	 * @return	Le r�le correspondant au code
	 * @throws IllegalArgumentException	Le code n'est pas reconnu par l'application
	 */
	public UserRole parseRole(String code) throws IllegalArgumentException {
		if (code == null) {
			// Cr�ation d'un r�le "Invit�"
			return new UserRole(Roles.GUEST_ROLE);
		}

		String c = code.trim();
		if ("".equals(c)) {
			// Cr�ation d'un r�le "Invit�"
			return new UserRole(Roles.GUEST_ROLE);
		} else {
			UserRole role = (UserRole) roles.get(c);

			if (role == null) {
				throw new IllegalArgumentException("userrole.invalid.user");
			}
		
			return role;
		}
	}
	
	/**
	 * V�rifie si l'utilisateur est administrateur
	 * @param 	user 	utilisateur
	 * @return <code>true</code> Si l'utilisateur est administrateur
	 */
	public static boolean isAdmin(User user) {
		RoleDescriptor userRole = user.getRole();
		return userRole.getCode().equals(Roles.ADMINISTRATOR_ROLE);
	}
}
