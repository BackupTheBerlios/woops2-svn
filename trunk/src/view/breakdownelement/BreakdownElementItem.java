package view.breakdownelement;

import java.util.Date;

import business.BusinessConstantes;
import business.format.Formatage;

import com.cc.framework.common.DisplayObject;

public class BreakdownElementItem implements DisplayObject {
	private	String id;
	private String prefix;
	private String name;
	private String details;
	private String startDate;
	private String endDate;
	private String kind;
	private boolean finished;
	
	public BreakdownElementItem() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		endDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT_BDE);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		startDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT_BDE );
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getLabel() {
		return prefix + " : " + name;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public boolean isNotFinished() {
		return !finished;
	}

}
