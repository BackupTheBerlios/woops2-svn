package business.activity.state;

import business.BusinessConstantes;
import business.activity.Activity;

/**
 * Implements a behavior associated with a state of the Context.
 */

public class FinishedActivityState extends IActivityState {
	private static final long serialVersionUID = 2094251057686511116L; /** Generated Serial ID */

	public FinishedActivityState() {
		super(BusinessConstantes.ACTIVITY_STATE_FINISHED);
	}

	public boolean process(Activity activity) {
		return false;
	}

	public boolean checkBeforeChange(Activity activity) {
		return false;
		// TODO Auto-generated method stub
	}
}