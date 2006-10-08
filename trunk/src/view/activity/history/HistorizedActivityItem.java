package view.activity.history;

import java.util.Date;

import business.BusinessConstantes;
import business.format.Formatage;

import com.cc.framework.common.DisplayObject;


public class HistorizedActivityItem implements DisplayObject{
	private	String id;
	private String name;
	private String details;
	private String startDate = "";
	private String endDate = "";
	
	public HistorizedActivityItem() {
		super();
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		endDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		startDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT);
	}
}

