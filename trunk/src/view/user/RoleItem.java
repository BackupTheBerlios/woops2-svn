package view.user;

import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;

import com.cc.framework.common.DisplayObject;

public class RoleItem implements DisplayObject{
	private String id;
	private	String code;

	public RoleItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String id) {
		this.code = id;
	}
	
	public String getLabelCode() {
		return MessageResources.getMessageResources(PresentationConstantes.BASENAME).getMessage(code);
	}
}
