package business.activity.sequencetype;

import java.util.List;

import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceTypeDAO extends PersistentObjectDAO {
	
	public ActivitySequenceType getActivitySequenceTypeById(Integer activitySequenceTypeId)
	throws PersistanceException {
		List res = executeQuery("FROM ActivitySequenceType as actSeqTyp WHERE actSeqTyp.id = "+activitySequenceTypeId);
		return (ActivitySequenceType)res.get(0);
	}
	
	public ActivitySequenceType getActivitySequenceTypeByName(String activitySequenceTypeName)
	throws PersistanceException {
		List res = executeQuery("FROM ActivitySequenceType as actSeqTyp WHERE actSeqTyp.name = \""+activitySequenceTypeName+"\"");
		return (ActivitySequenceType)res.get(0);
	}
}