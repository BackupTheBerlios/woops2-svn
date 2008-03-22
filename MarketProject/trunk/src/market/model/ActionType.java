package market.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 */
public class ActionType {

    private String code;

    private String label;

    private java.util.Date introductionDate;

    private float introductionPrice;

    private int quantity;

    private float currentPrice;

    public List<Action> actions = new ArrayList<Action>();

    public List<Alarm> alarms = new ArrayList<Alarm>();

    public List<Event> events = new ArrayList<Event>();

	public String getCode() {
		return code;
	}

	public void setCode(String _code) {
		code = _code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String _label) {
		label = _label;
	}

	public java.util.Date getIntroductionDate() {
		return introductionDate;
	}

	public void setIntroductionDate(java.util.Date _introductionDate) {
		introductionDate = _introductionDate;
	}

	public float getIntroductionPrice() {
		return introductionPrice;
	}

	public void setIntroductionPrice(float _introductionPrice) {
		introductionPrice = _introductionPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int _quantity) {
		quantity = _quantity;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float _currentPrice) {
		currentPrice = _currentPrice;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> _events) {
		events = _events;
	}

 }
