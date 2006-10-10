package business.activity.state;

import java.util.Date;
import java.util.Iterator;

import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.sequence.ActivitySequence;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class InProgressActivityState extends IActivityState {
	private static final long serialVersionUID = 1595751762726689156L; /** Generated Serial ID */
	private Integer progress;	/** Indique la progression de l'activité */
	
	public InProgressActivityState() {
		super(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS);
	}
	
	public boolean process(Activity activity) {
		boolean result = false;
		if (checkBeforeChange(activity)) {
			FinishedActivityState finished = new FinishedActivityState();
			activity.setState(finished);
			activity.setEndDate(new Date());
			result = true;
		}
		return result;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public boolean checkBeforeChange(Activity activity) {
		/* Si l'activité n'a aucun prédecesseur, elle peut commencer */
		boolean result = true;
		
		Iterator iter = activity.getListPredecessors().iterator();

		ActivitySequence actSeq = null;
		while ((iter.hasNext())&&(result==true)) {
			actSeq = (ActivitySequence)iter.next();
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_FINISH_TO_FINISH)) {
				if (!(actSeq.getPredecessor().getState() instanceof FinishedActivityState))
					result = false;
			}
			
			if (actSeq.getLinkType().equals(BusinessConstantes.LINK_TYPE_START_TO_FINISH)) {
				if (!(actSeq.getPredecessor().getState() instanceof InProgressActivityState))
					result = false;
			}
		}
		
		return result;
	}
}