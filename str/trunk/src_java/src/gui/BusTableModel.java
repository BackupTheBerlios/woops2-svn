package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import model.Bus;

@SuppressWarnings("serial")
public class BusTableModel extends AbstractTableModel {

	private List<Bus> lBus = new ArrayList<Bus>();

	private Class[] columnClasses = { Integer.class, Integer.class,
			Boolean.class, Integer.class, Integer.class };

	public BusTableModel(Map<Integer, Bus> _busMap) {
		super();
		
		this.refreshModel(_busMap);
	}
	
	public void refreshModel(Map<Integer, Bus> _busMap){
		lBus.clear();
		for (Integer key : _busMap.keySet()) {
			lBus.add(_busMap.get(key));
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return lBus.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return this.lBus.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return this.lBus.get(rowIndex).getLine().getNumber();
		} else if (columnIndex == 2) {
			return this.lBus.get(rowIndex).getRepresentation().getIsRunning();
		} else if (columnIndex == 3) {
			return this.lBus.get(rowIndex).getRepresentation().getX();
		} else {
			return this.lBus.get(rowIndex).getRepresentation().getY();
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		return this.columnClasses[columnIndex];
	}

	public String getColumnName(int iColumnIndex) {
		// nom des colonnes
		if (iColumnIndex == 0) {
			return "Bus";
		} else if (iColumnIndex == 1) {
			return "Ligne";
		} else if (iColumnIndex == 2) {
			return "Démarré";
		} else if (iColumnIndex == 3) {
			return "X";
		} else {
			return "Y";
		}
	}
}
