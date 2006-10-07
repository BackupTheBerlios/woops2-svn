package view;

/**
 * @author Nicolas Ricard
 * 
 */
public interface PresentationConstantes {
	
	public static final String BASENAME="ApplicationResources";
	public static final String YES="oui";
	public static final String NO="non";
	
	// Forward
	public static final String FORWARD_SUCCESS="success";
	public static final String FORWARD_ERROR="error";
	public static final String FORWARD_ACTION="action";
	public static final String FORWARD_SUPPRIMER="supprimer";	
	public static final String FORWARD_CREER="creer";
	public static final String FORWARD_RETOUR="retour";
    public static final String FORWARD_DECONNECT="deconnect";
    public static final String FORWARD_LOGOUT="logout"; 
    public static final String FORWARD_EDIT="edit";
    public static final String FORWARD_DELETE="delete";
    public static final String FORWARD_DELETE_USER="deleteUser";
    public static final String FORWARD_DELETE_BREAKDOWN="deleteBreakdown";
    public static final String FORWARD_COPY_BREAKDOWN="copy";
    public static final String FORWARD_DRILLDOWN="drillDown";
    public static final String FORWARD_BACK="back"; 
	public static final String FORWARD_INDEX="index"; 
	public static final String FORWARD_NOSESSION="nosession";
	public static final String FORWARD_SIGNAL="signal";
	
	
    public static final String FORWARD_NEXT="next"; 
    public static final String FORWARD_PREVIOUS="previous"; 
    public static final String FORWARD_PREVIOUS_FREE_ACTIVITY="previousFreeActivity"; 
    public static final String FORWARD_FINISH="finish"; 
    public static final String FORWARD_FINISH_FREE_ACTIVITIES="finishListFreeActivities";
    
	

	//Constantes de parametres de request
	public static final String PARAM_ACTION_SUBMIT="actionSubmit";
	public static final String PARAM_MODE="mode";
	public static final String PARAM_ACTIVITY_ID="activityId";
	public static final String PARAM_USER_ID="userId";
	public static final String PARAM_BREAKDOWN_ID="breakdownelementId";
	public static final String PARAM_EVENT_ID="eventId";
	public static final String PARAM_ACTIVITIES="listactivities";

	
	//constantes relatives aux Styles
	public static final String STYLE_FOND1="fond1";
	public static final String STYLE_FOND2="fond2";
	
	//constantes relatives aux styles des graphes
	public static final String COLOR_ACTIVITY_CREATED="green";
	public static final String COLOR_ACTIVITY_IN_PROGRESS="orange";
	public static final String COLOR_ACTIVITY_FINISHED="red";
	
	public static final String COLOR_FOCUS_USER_BKGRD="lightgrey";
	
	//constantes de cles en session
	public static final String KEY_FORM="FORM";
	public static final String KEY_USER="USER";
	public static final String KEY_OLD_DEPENDANCES_KEYS="OLD_DEPENDANCES_KEYS";
	public static final String KEY_POSSIBLE_DEPENDANCES_OPTIONS="POSSIBLE_DEPENDANCES_OPTIONS";
	public static final String KEY_DEPENDANCES_LIST = "KEY_DEPENDANCES_LIST";
	public static final String KEY_DEPENDANCES_LIST_MNGR = "KEY_DEPENDANCES_LIST_MNGR";
	public static final String KEY_ACTIVITY="ACTIVITY";
	public static final String KEY_ACTIVITIES_MAP="ACTIVITIES_MAP";
	public static final String KEY_EVENTS_MAP="EVENTS_MAP";
	public static final String KEY_ROLE_OPTIONS ="ROLE_OPTIONS";
	public static final String KEY_USER_BDES = "USER_BDES";
	
	public static final String LIBELLE_OUI="O";
	public static final String LIBELLE_NON="N";
	
	//Constantes permettant de modifier l'etat d'une activite
    public static final String ACTIVITY_START = "start";
    public static final String ACTIVITY_FINISH = "finish";
    public static final String ACTIVITY_AFFECT = "affect";
    
    //Constantes permettant de connaitre le mode d'un formulaire ( create ou update )
    public static final String INSERT_MODE = "insert_mode";
    public static final String UPDATE_MODE = "update_mode";
    public static final String DELETE_MODE = "delete_mode";
    public static final String COPY_MODE = "copy_mode";
    
    
    // Constantes forward pour admin
    public static final String FORWARD_ADMIN="admin";   
    public static final String FORWARD_EDIT_USER = "editUser";
    public static final String FORWARD_DRILLDOWN_USER="drillDownUser";
    public static final String FORWARD_EDIT_BREAKDOWN = "editBreakdown";
    public static final String FORWARD_DRILLDOWN_BREAKDOWN="drillDownBreakdown";;
    public static final String ADMIN_ROLE_CODE = "admin" ;
    public static final String FORWARD_IMPORT = "import";
    public static final String FORWARD_FINISH_BREAKDOWN="finishBde"; 
    
	public static final String KEY_USERS_MAP="USERS_MAP" ;
	public static final String KEY_BDE_MAP="BDE_MAP" ;
	
	// admin
	public static final String KEY_USER_ID="USER_ID" ;

}
