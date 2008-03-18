package fr.isi.market.model;

import java.util.Date;

/**
 * 
 * 
 */
public class Action {

    private float buyPrice;

    private Date buyDate;

    public Wallet wallet;

    public ActionType type;

    public Wallet getWallet() {
        return wallet;
    }
    
    public void setWallet(Wallet _wallet) {
        this.wallet = _wallet;
    }

    public ActionType getType() {
        return type;
    }
    
    public void setType(ActionType _actionType) {
        this.type = _actionType;
    }
    
	public float getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(float _buyPrice) {
		this.buyPrice = _buyPrice;
	}
	
	public java.util.Date getBuyDate() {
		return buyDate;
	}
	
	public void setBuyDate(Date _buyDate) {
		this.buyDate = _buyDate;
	}
 }
