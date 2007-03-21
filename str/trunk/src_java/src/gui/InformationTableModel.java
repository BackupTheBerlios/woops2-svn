package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.table.AbstractTableModel;

import model.Information;

@SuppressWarnings("serial")
public class InformationTableModel extends AbstractTableModel {

	private List<Information> lInformation = new ArrayList<Information>();

	private Class[] columnClasses = { Integer.class, String.class };

	public InformationTableModel(Queue<Information> linf) {
		super();

		this.refreshModel(linf);
	}

	public void refreshModel(Queue<Information> linf) {
		lInformation.clear();
		for (Information i : linf) {
			lInformation.add(i);
			linf.remove();
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return lInformation.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return this.lInformation.get(rowIndex).getRecipient().getId();
		} else {
			return this.lInformation.get(rowIndex).getMessage();
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		return this.columnClasses[columnIndex];
	}

	public String getColumnName(int iColumnIndex) {
		// nom des colonnes
		if (iColumnIndex == 0) {
			return "N° Arret";
		} else {
			return "Informations";
		}
	}
}
