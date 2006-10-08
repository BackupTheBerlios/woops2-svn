package business;

public interface BusinessConstantes {

	public static final String OUI = "O";
	public static final String NON = "N";
    
    public static final String LINK_TYPE_FINISH_TO_START = "finishToStart";
    public static final String LINK_TYPE_FINISH_TO_FINISH = "finishToFinish";
    public static final String LINK_TYPE_START_TO_START = "startToStart";
    public static final String LINK_TYPE_START_TO_FINISH = "startToFinish";
    
    public static final String ACTIVITY_STATE_CREATED = "created";
    public static final String ACTIVITY_STATE_IN_PROGRESS = "inProgress";
    public static final String ACTIVITY_STATE_FINISHED = "finished";
    
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DATE_FORMAT_BDE = "dd/MM/yyyy";
    
    // constantes de nom de table
	public static final String TABLE_USER = "User";
	public static final String TABLE_USERROLE = "UserRole" ;
	public static final String TABLE_BREAKDOWN = "BreakdownElement";
	public static final String TABLE_BREAKDOWN_KIND = "BreakdownElementKind";
	public static final String TABLE_ACTIVITY = "Activity";
	public static final String TABLE_ACTIVITY_SEQUENCE = "ActivitySequence";
}
