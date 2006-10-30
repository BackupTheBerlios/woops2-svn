package view.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import business.activity.Activity;

public class ProcessControler {
	
	/**
	 * Recuperation des activites d'un processus defini dans un fichier DPE
	 * @param source : fichier DPE
	 * @return liste des activites du processus
	 * @throws FileParseException : Indique qu'une erreur s'est en parsant le fichier source
	 */
	public static Collection load (BufferedInputStream bis) throws FileParseException, FileNotFoundException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance() ;

		try
		{
			DocumentBuilder localDB = dbf.newDocumentBuilder() ;
			localDB.setErrorHandler(new org.xml.sax.ErrorHandler()
			{
				/*
				 * Meme si rien ne se passe une exception sera levee
				 * 
				 * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
				 */
				public void fatalError (SAXParseException e) throws SAXException
				{
				}

				/*
				 * On s'assure qu'une SAX exception es levee
				 * 
				 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
				 */
				public void error (SAXParseException e) throws SAXParseException
				{
					throw e ;
				}

				/*
				 * Les Warnings ne sont pas importants
				 * 
				 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
				 */
				public void warning (SAXParseException e) throws SAXParseException
				{

				}
			}) ;

			// Parse le document
			Document localDocument = localDB.parse(bis) ;
			/* Verifie le format, on s'assure que la racine obtenue correspond a la racine attendue "exportExecution" */
			if (!localDocument.getDocumentElement().getTagName().equalsIgnoreCase("exportExecution")) { throw new FileParseException() ; }


			/*
			 * Activites
			 */
			NodeList activitiesRoot = null;
			NodeList listActivitiesNode = null;
			Node activityNode = null;
			
			String activityName = null;
			Activity activity = null;
			List listActivities = null;
			
			// Recupere tous les noeuds "liste_activite" 
			activitiesRoot = localDocument.getElementsByTagName("liste_activite") ;
			// Verifie que ce noeud apparait qu'une seule fois et qu'il contient des noeuds c'est à dire des activites
			if (activitiesRoot.getLength() != 1 || activitiesRoot.item(0).getChildNodes().getLength() == 0) { 
				throw new FileParseException() ; 
			}
			// Recupere tous les noeuds fils de "liste_activite" 
			listActivitiesNode = activitiesRoot.item(0).getChildNodes() ;
			
			// Nombres de noeuds
			int nbActivities = listActivitiesNode.getLength() ;
			
			listActivities = new ArrayList();
			// Parcourt tous les noeuds
			for (int i = 0; i < nbActivities; i++ )
			{
				// On s'assure que le noeud obtenu est bien une activite
				if (listActivitiesNode.item(i).getNodeType() == Node.ELEMENT_NODE && listActivitiesNode.item(i).getNodeName().equalsIgnoreCase("activite"))
				{
					activityNode = listActivitiesNode.item(i) ;
					
					// On récupère les noeuds fils de "activite" c'est a dire les caracteristiques d'une activite
					NodeList activityInfos = activityNode.getChildNodes() ;
					int nbInfos = activityInfos.getLength() ;

					
					// Parcourt toutes les informations d'une activite et on recupere juste son nom
					for (int j = 0; j < nbInfos; j++ )
					{
						/*
						 * Le nom
						 */
						if (activityInfos.item(j).getNodeType() == Node.ELEMENT_NODE && activityInfos.item(j).getNodeName().equalsIgnoreCase("nom"))
						{
							try
							{
								activityName = activityInfos.item(j).getFirstChild().getNodeValue() ;
							}
							catch (NullPointerException exc)
							{}
						}
					}
					// Creation d'une activite avec le nom recupere
					if (activityName != null) {
						activity = new Activity();
						activity.setName(activityName);
					
						listActivities.add(activity) ;
					}
				}
			}

			return listActivities;
		} catch(FileNotFoundException fnfe) {
			throw new FileNotFoundException();
		}
		catch (Exception e) {
			throw new FileParseException() ;
		}
	}
}
