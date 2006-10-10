package business.activity.sequencetype;

import business.hibernate.HistorizedObject;


public class ActivitySequenceType extends HistorizedObject {
	private static final long serialVersionUID = -3618392641200042938L; /** Generated Serial ID */
	private Integer id;
	private String name;
	
	/**
	 * R?cup?ration de l'identifiant du type de sequence d'activit? n?cessaire pour la persistence
	 * @return identifiant du type de sequence d'activit?
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant du type de sequence d'activit?
	 * @param id identifiant du type de sequence d'activit? ? modifier
	 */
	public void setId(Object id) {
		this.id = (Integer) id;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(String link) {
		return this.name.equals(link);
	}
	
	
	
	public boolean equals(Object obj) {
		boolean ok = ((ActivitySequenceType)obj).getId().equals(id);
	
		ok &= ((ActivitySequenceType)obj).getName().equals(name);
			
		return ok;
	}
}
