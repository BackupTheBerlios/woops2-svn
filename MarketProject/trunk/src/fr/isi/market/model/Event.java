package fr.isi.market.model;

import java.util.Date;

/**
 * 
 * 
 */
public class Event {

    private Date date;

    private float price;

    public ActionType actionType;

    public Date getDate() {
		return date;
	}

	public void setDate(Date _date) {
		this.date = _date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float _price) {
		this.price = _price;
	}

	public ActionType getActionType() {
        return actionType;
    }
    
    public void setActionType(ActionType _actionType) {
        this.actionType = _actionType;
    }
 }
