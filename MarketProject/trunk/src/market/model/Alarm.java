package market.model;

/**
 * 
 * 
 */
public class Alarm {

    private int id;

    private String name;

    public Wallet wallet;

    public AlarmType type;

    public ActionType actionType;

    public int getId() {
		return id;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet _wallet) {
        this.wallet = _wallet;
    }

    public AlarmType getType() {
        return type;
    }
    
    public void setType(AlarmType _alarmType) {
        this.type = _alarmType;
    }

    public ActionType getActionType() {
        return actionType;
    }
    
    public void setActionType(ActionType _actionType) {
        this.actionType = _actionType;
    }
 }
