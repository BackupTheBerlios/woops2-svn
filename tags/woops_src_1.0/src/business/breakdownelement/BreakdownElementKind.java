package business.breakdownelement;

public class BreakdownElementKind {
	private Integer id;
	private String name;
	
	
	public BreakdownElementKind() {
		super();
		this.id = null;
		this.name = null;
		
	}

	public BreakdownElementKind(Integer id) {
		super();
		this.id = id;
		this.name = null;
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}	
}
