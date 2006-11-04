package view.common;


import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import business.hibernate.HibernateSessionFactory;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy(); 
	}

	public void init() throws ServletException {

		
		
		String prefix =  getServletContext().getRealPath("/");

		// Init Log4J
		String file = getInitParameter("log4jFile");
	    if(file != null) {
	      PropertyConfigurator.configure(prefix+file);
	    }

	    // Init hibernate
		String confFile = getInitParameter("hibernateConfFile");
		File f = new File(prefix + confFile);
		HibernateSessionFactory.init(f);
		
    
	}

}
