package view.breakdownelement.importactivities;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.struts.upload.FormFile;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import view.util.FileParseException;
import view.util.ProcessControler;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;

public class ImportFileAction extends WoopsCCAction{

	public void doExecute(ActionContext context) throws Exception {
		context.forwardToInput();
	}
	
	public void import_onClick(FormActionContext context) {
		if (!context.hasErrors()){
			ImportFileForm leForm = (ImportFileForm)context.form() ;
			FormFile ff = leForm.getPathFile() ;
			
			InputStream is;
			
			try {
				is = ff.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				Collection list = ProcessControler.load(bis) ;
				if (list.isEmpty())
				{
					context.addGlobalError("admin.manageDpe.emptyList") ;
					context.forwardByName(PresentationConstantes.FORWARD_ERROR);
				}
				else
				{
					context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITIES, list);
					context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
				}
			} catch (FileNotFoundException fnfe) {
				context.addGlobalError("error.file.find", ff.getFileName()) ;
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			} catch (IOException e1) {
				context.addGlobalError("error.file.read", ff.getFileName()) ;
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			} catch (FileParseException e) {
				context.addGlobalError("error.file.parse", ff.getFileName()) ;
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			}
		}
	}

}
