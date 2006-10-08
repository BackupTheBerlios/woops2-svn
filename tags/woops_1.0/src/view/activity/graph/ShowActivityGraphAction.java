package view.activity.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;


/**
 * @author Simon REGGIANI
 * ShowActivityGraphAction : permet de voir le graphe des activit?s du projet
 */
public class ShowActivityGraphAction extends WoopsCCAction {
	/**
	 * Constructeur vide
	 *
	 */
	public ShowActivityGraphAction() {
		super();
	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		ShowActivityGraphForm form = (ShowActivityGraphForm) context.form();
		
		//Recuperation de l'identifiant du participant connect?
    	User sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
		try {
			// D?claration des managers utilis?s
			ActivityManager activityManager = ActivityManager.getInstance();
			UserManager userManager = UserManager.getInstance();
			ActivitySequenceManager activitySequenceManager = ActivitySequenceManager.getInstance();
			
			// On utilise la classe GraphViz comme API
			GraphViz gv = new GraphViz();
			
			// On ?crit le debut de graph
			gv.addln(gv.start_graph());
            
			
			// On r?cup?re la liste des utilisateur du projet
            Collection listUsers = userManager.getUsersByBDE(sessionUser.getDefaultBDEId());
            
            // Pour chaque utilisateur, on va afficher son cadre et ses activit?s
            Iterator iterUsers = listUsers.iterator();
            int i = 0;
            while(iterUsers.hasNext()) {
            	User user = (User)iterUsers.next();
            	
            	// Affichage du cadre ( "cluster" )
            	gv.addln("\tsubgraph cluster"+i+" {");
            	if ( user.getId().equals(sessionUser.getId()) ) {
            		gv.addln("\t\tstyle=filled;");
            		gv.addln("\t\tcolor="+PresentationConstantes.COLOR_FOCUS_USER_BKGRD+";");
            	}
            		
            	gv.addln("\t\tlabel = \""+user.getFirstName()+" "+user.getLastName()+"\";");
            	
            	// R?cup?ration des activit?s de l'utilisateur
				ArrayList listActivities = (ArrayList) activityManager.getAllActivitiesByUserByBDE((Integer)user.getId(),sessionUser.getDefaultBDEId());
				
				// Pour chaque activit?, on va l'afficher
				Iterator iterActivities = listActivities.iterator();
				while(iterActivities.hasNext()){
					Activity act = (Activity)iterActivities.next();
					String label = "label=\""+act.getName()+"\",";
					String color="";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_CREATED))
						color="color="+PresentationConstantes.COLOR_ACTIVITY_CREATED+",";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS))
						color="color="+PresentationConstantes.COLOR_ACTIVITY_IN_PROGRESS+",";
					if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED))
						color="color="+PresentationConstantes.COLOR_ACTIVITY_FINISHED+",";
					String style="style=filled";
					gv.addln("\t\t"+act.getId().toString()+"["+label+" "+color+" "+style+"];");
				}
				
				// On ferme le cadre
				gv.addln("\t}");
				
				i++;
            }
			
            
            // On va afficher ?galement les activit?s libres du projet ( dans un cadre a part )
            
            
            //Affichage du cadre ( "cluster" )
        	gv.addln("\tsubgraph cluster"+(i)+" {");
        	String nameFreeLocalized = MessageResources.getMessageResources(PresentationConstantes.BASENAME).getMessage("graph.freeActivity");
        	gv.addln("\t\tlabel = \""+nameFreeLocalized+"\";");
        	
      
            // R?cup?ration de la liste des activit?s libres du projet
            ArrayList listFreeActivities = (ArrayList) activityManager.getFreeActivities(sessionUser.getDefaultBDEId());
            
            //Pour chaque activit? libre, on va l'afficher
			Iterator iterFreeActivities = listFreeActivities.iterator();
			while(iterFreeActivities.hasNext()){
				Activity act = (Activity)iterFreeActivities.next();
				String label = "label=\""+act.getName()+"\",";
				String color="";
				if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_CREATED))
					color="color="+PresentationConstantes.COLOR_ACTIVITY_CREATED+",";
				if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS))
					color="color="+PresentationConstantes.COLOR_ACTIVITY_IN_PROGRESS+",";
				if (act.getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED))
					color="color="+PresentationConstantes.COLOR_ACTIVITY_FINISHED+",";
				String style="style=filled";
				gv.addln("\t\t"+act.getId().toString()+"["+label+" "+color+" "+style+"];");
			}
			
            // On ferme le cadre
			gv.addln("\t}");
            
            
            // On r?cup?re les d?pendances entre les activit?s
			ArrayList listAllActivitySequences = (ArrayList) activitySequenceManager.getActivitySequencesByBDE(sessionUser.getDefaultBDEId());
			
			// Pour chaque d?pendances, on les affiche
			Iterator iterActivitySequences = listAllActivitySequences.iterator();
			while(iterActivitySequences.hasNext()){
				ActivitySequence actSeq = (ActivitySequence)iterActivitySequences.next();
				
				String predecessorId = actSeq.getPredecessor().getId().toString();
				String successorId = actSeq.getSuccessor().getId().toString();
				String linkType = actSeq.getLinkType().getName();
			
				linkType = MessageResources.getMessageResources(PresentationConstantes.BASENAME).getMessage(linkType);
				
				gv.addln("\t"+predecessorId+" -> "+successorId+"[label=\""+linkType+"\"];");
			}
			
			
			// On affiche la fin de graph
			gv.addln(gv.end_graph());
			
			// On r?cup?re le dossier "graph" dans lequel se trouveront les images
			String graphRealPath = getServlet().getServletContext().getRealPath("/") + "graph" + File.separator;
			
			// Parametrage de la classe GraphViz
			GraphViz.setDOT("dot");
			GraphViz.setTEMP_DIR(graphRealPath);
			
			// Cr?ation du fichier image : graph + id du user + id du projet
			String imageFile = "graph"+sessionUser.getId().toString()+sessionUser.getDefaultBDEId().toString()+".jpg";
			File out = new File(graphRealPath+imageFile);
			
			
			byte[] imgBytes = gv.getGraph(gv.getDotSource());
			if ( imgBytes != null ) {
				gv.writeGraphToFile(imgBytes, out);
				
				// On met ? jour l'attribut du form qui contient le path de l'image g?n?r?e
				String graphPath = context.request().getContextPath()+"/graph/";
				form.setImageFilePath(graphPath+imageFile);
				
				context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
			}
			else {
				form.setImageFilePath("");
				context.addGlobalError("errors.graph.dotNotInstalled");
				context.forwardByName(PresentationConstantes.FORWARD_ERROR);
			}
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		
	}
}
