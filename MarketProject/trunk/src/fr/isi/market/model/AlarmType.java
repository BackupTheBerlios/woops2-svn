package fr.isi.market.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 */
public class AlarmType {

    private String label;

    public List<Alarm> alarms = new ArrayList<Alarm>();
    
    public String getLabel() {
		return label;
	}

	public void setLabel(String _label) {
		this.label = _label;
	}
	
	public List<Alarm> getAlarms() {
        return alarms;
    }

	public void setAlarms(List<Alarm> _alarms) {
		this.alarms = _alarms;
	}
	
 }
