package view.activity;

import view.common.WoopsListDataModel;

import com.cc.framework.common.DisplayObject;

public class ActivitySequencesModel extends WoopsListDataModel {

	public ActivitySequencesModel(DisplayObject[] elements) {
		super(elements);
	}

	public String getUniqueKey(int index) {
		return ((ActivitySequenceItem)data[index]).getId();
	}
	
	public ActivitySequenceItem[] getData(){
	
		return (ActivitySequenceItem[]) data;
	}

}
