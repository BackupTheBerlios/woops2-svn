package business.activity.sequence;

import business.activity.Activity;
import business.activity.sequencetype.ActivitySequenceType;
import business.hibernate.HistorizedObject;

public class ActivitySequence extends HistorizedObject {
	private static final long serialVersionUID = -2923306167381237289L; /** Generated Serial ID */
	private Integer id;
	private Activity successor;
	private Activity predecessor;
	private ActivitySequenceType linkType;
	
	
	/**
	 * R?cup?ration de l'identifiant de la s?quence d'activit? n?cessaire pour la persistence
	 * @return identifiant de la s?quence d'activit?
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Modification de l'identifiant de la s?quence d'activit?
	 * @param id identifiant de la s?quence d'activit? ? modifier
	 */
	public void setId(Object id) {
		this.id = (Integer) id;
	}
	
	public Activity getPredecessor() {
		return predecessor;
	}
	
	public void setPredecessor(Activity predecessor) {
		this.predecessor = predecessor;
	}
	
	public Activity getSuccessor() {
		return successor;
	}
	
	public void setSuccessor(Activity successor) {
		this.successor = successor;
	}
	
	public ActivitySequenceType getLinkType() {
		return linkType;
	}
	
	public void setLinkType(ActivitySequenceType linkType) {
		this.linkType = linkType;
	}

	public boolean equals(Object obj) {
		boolean ok = ((ActivitySequence)obj).getId().equals(id);
		
		ok &= ((ActivitySequence)obj).getPredecessor().equals(predecessor);
		
		ok &= ((ActivitySequence)obj).getSuccessor().equals(successor);
		
		ok &= ((ActivitySequence)obj).getLinkType().equals(linkType);
		
		return ok;
	}
	
	
	
}
