package business.format;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.cc.framework.common.SortOrder;

public class DateColumnComparator extends ColumnComparator {
		
	public DateColumnComparator(String column, SortOrder direction) {
		super(column, direction);
	}
		
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		int result = 0;
		
		try {	
			String sDate1 = (String) PropertyUtils.getProperty(o1, column);
			String sDate2 = (String) PropertyUtils.getProperty(o2, column);
	
			Date date1 = Formatage.stringToDate(sDate1);
			Date date2 = Formatage.stringToDate(sDate2);
			
			if (direction.equals(SortOrder.ASCENDING) && date1 != null) {
				// La date 1 est après la date 2 donc on retourne un nombre positif
				if (date2 == null) {
					return 1;
				} else {
					result = date1.compareTo(date2);
				}
			} else if (direction.equals(SortOrder.DESCENDING) && date2 != null) {
				// La date 1 est après la date 2 donc on retourne un nombre positif
				if (date1 == null) {
					return 1;
				} else {
					result = date2.compareTo(date1);
				}
			}
		} catch (Throwable t) {}
		
		return result;
	}
}