package woops2.model.element;

/**
 * @author Mathieu BENOIT
 *
 * This class represents ... TODO
 *
 */
public class Element {
	
	private String id;
	
	private String name;
	
	private String description;

	/**
	 * Getter of description.
	 *
	 * @return the description.
	 */
	public String getDescription () {
		return this.description ;
	}

	/**
	 * Setter of description.
	 *
	 * @param _description The description to set.
	 */
	public void setDescription (String _description) {
		this.description = _description ;
	}

	/**
	 * Getter of id.
	 *
	 * @return the id.
	 */
	public String getId () {
		return this.id ;
	}

	/**
	 * Setter of id.
	 *
	 * @param _id The id to set.
	 */
	public void setId (String _id) {
		this.id = _id ;
	}

	/**
	 * Getter of name.
	 *
	 * @return the name.
	 */
	public String getName () {
		return this.name ;
	}

	/**
	 * Setter of name.
	 *
	 * @param _name The name to set.
	 */
	public void setName (String _name) {
		this.name = _name ;
	}

}
