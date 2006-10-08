package view.user;



import com.cc.framework.common.DisplayObject;


public class UserItem implements DisplayObject{
	private	String id;
	private String firstName;
	private String lastName;
	private String login;
	private String roleCode;

	public UserItem() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRole(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getName() {
		return lastName + " " + firstName;
	}
}
