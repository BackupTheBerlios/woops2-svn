package business.breakdownelement;

import java.util.Date;
import java.util.Set;

import business.hibernate.HistorizedObject;

public class BreakdownElement extends HistorizedObject{	
	private static final long serialVersionUID = 2763309763407190586L; /** Generated Serial ID */
	private	Integer					id;
	private String 					prefix;
	private String 					name;
	private String					details;
	private Date 					startDate;
	private Date 					endDate;
	private BreakdownElementKind 	kind;
	private Set						users;
	
	public BreakdownElement() {
		super();
		this.id = null;
		this.prefix = null;
		this.name = null;
		this.details = null;
		this.startDate = null;
		this.endDate = null;
		this.kind = null;
		this.users = null;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = (Integer)id;
	}

	public BreakdownElementKind getKind() {
		return kind;
	}

	public void setKind(BreakdownElementKind kind) {
		this.kind = kind;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}	
}
