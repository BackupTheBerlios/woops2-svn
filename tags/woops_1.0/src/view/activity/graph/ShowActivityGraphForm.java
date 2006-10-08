package view.activity.graph;

import com.cc.framework.adapter.struts.FWActionForm;

/**
 * @author Simon REGGIANI
 */

public class ShowActivityGraphForm extends FWActionForm {
	private static final long serialVersionUID = 9152697665576557516L; /** Generated Serial ID */
	private String imageFilePath;

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	
}
