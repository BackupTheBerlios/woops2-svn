package view.common;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;

import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.def2.Def2PainterFactory;
import com.cc.framework.ui.painter.html.HtmlPainterFactory;
  
public class WoopsFrontController extends ActionServlet { 
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException { 
  	super.init(); 
  	/* Autorisation de l'internationalisation */ 
  	getServletContext().setAttribute( com.cc.framework.Globals.LOCALENAME_KEY, "true");
  	PainterFactory.registerApplicationPainter ( getServletContext (), Def2PainterFactory.instance());
  	PainterFactory.registerApplicationPainter ( getServletContext (), HtmlPainterFactory.instance()); 
  	}
} 