package business.format;

import java.util.Comparator;

import com.cc.framework.common.SortOrder;

public abstract class ColumnComparator implements Comparator {

	/**
	 * Elément à comparer
	 */
	protected	String	column = "";
	
	/**
	 * Ordre de tri
	 */
	protected	SortOrder 	direction = SortOrder.NONE;

	/**
	 * Constructeur
	 * @param	column	colonne à trier
	 * @param	direction	Ordre de tri
	 */
	public ColumnComparator(String column, SortOrder direction) {
		super();
		
		this.column = column;
		this.direction = direction;
	}

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public abstract int compare(Object o1, Object o2);
}
