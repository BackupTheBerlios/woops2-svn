package business.format;

import org.apache.commons.beanutils.PropertyUtils;

import com.cc.framework.common.SortOrder;

public class StringColumnComparator extends ColumnComparator {
	
	public StringColumnComparator(String column, SortOrder direction) {
		super(column, direction);
	}
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		int result = 0;
		
		try {	
			String str1 = (String) PropertyUtils.getProperty(o1, column);
			String str2 = (String) PropertyUtils.getProperty(o2, column);
	
			if (direction.equals(SortOrder.ASCENDING)) {
				result = str1.compareTo(str2);
			} else if (direction.equals(SortOrder.DESCENDING)) {
				result = str2.compareTo(str1);
			}
		} catch (Throwable t) {}
		
		return result;
	}
}
