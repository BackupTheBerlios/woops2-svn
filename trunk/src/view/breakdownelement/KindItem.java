package view.breakdownelement;

import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;

import com.cc.framework.common.DisplayObject;

public class KindItem implements DisplayObject {
	private	String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabelName() {
		return MessageResources.getMessageResources(PresentationConstantes.BASENAME).getMessage(name);
	}
}
