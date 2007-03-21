package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.table.AbstractTableModel;

import controler.ClientControler;

import model.Information;

@SuppressWarnings("serial")
public class InformationTableModel extends AbstractTableModel {

	private List<Information> lInformation = new ArrayList<Information>();

	private Class[] columnClasses = { Integer.class, String.class };

	public InformationTableModel() {
		super();
		this.refreshModel();
	}

	public void refreshModel() {
		ArrayList<Information> lInf = new  ArrayList<Information>(); //ClientControler.getInstance().getInformationsQueue();
		lInf.addAll(ClientControler.getInstance().getInformationsQueue());
		lInformation.clear();
		for (Information i : lInf) {
			lInformation.add(i);
		}
	}

	public int getColumnCount() {
		return 2;
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
