package fr.isi.market.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 */
public class AlarmType {

    private String label;

    public Set<Alarm> alarms = new HashSet<Alarm>();
    
    public String getLabel() {
		return label;
	}

	public void setLabel(String _label) {
		this.label = _label;
	}
	
	public Set<Alarm> getAlarms() {
        return alarms;
    }

	public void setAlarms(Set<Alarm> _alarms) {
		this.alarms = _alarms;
	}
	
 }
