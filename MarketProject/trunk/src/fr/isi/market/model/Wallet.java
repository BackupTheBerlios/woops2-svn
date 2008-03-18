package fr.isi.market.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 */
public class Wallet {

    private float cash;

    public User user;

    public List<Action> actions = new ArrayList<Action>();

    public List<Alarm> alarms = new ArrayList<Alarm>();

    public User getUser() {
        return user;
    }
    
    public void setUser(User _user) {
        this.user = _user;
    }

	public float getCash() {
		return cash;
	}

	public void setCash(float _cash) {
		cash = _cash;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> _actions) {
		actions = _actions;
	}

	public List<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarm> _alarms) {
		alarms = _alarms;
	}
    
 }
