package business.format;

import java.util.Date;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


public class Controleur {
	 /**
     * Controle si une chaine est vide
     * @param chaine
     * @return
     */
	public static boolean isVide(String chaine ){
		boolean ret = false;
		if (chaine==null || chaine.trim().length()<1)
			ret = true;
		return ret;
	}
	
	/**
	 * Controle si une chaine est un Integer valide
	 * @param chaine
	 * @return
	 */
	public static boolean isInteger(String chaine ){
		boolean ret = false;
		if (!isVide(chaine)){
	        try {
                Integer.valueOf(chaine);
                ret = true;
	        } catch (NumberFormatException e) {
	            ret = false;
	        }		    
		}
		return ret;
	}	
	
	/**
	 * Controle si une chaine est un Integer valide
	 * @param chaine
	 * @return
	 */
	public static boolean isLong(String chaine ){
		boolean ret = false;
		if (!isVide(chaine)){
	        try {
                Long.valueOf(chaine);
                ret = true;
	        } catch (NumberFormatException e) {
	            ret = false;
	        }		    
		}
		return ret;
	}	
	
	
	/**
	 * Controle si une chaine est un Double valide
	 * @param chaine
	 * @return
	 */
	public static boolean isDouble(String chaine ){
		boolean ret = false;
		if (!isVide(chaine)){
	        try {
                Double.valueOf(chaine);
                ret = true;
	        } catch (NumberFormatException e) {
	            ret = false;
	        }		    
		}
		return ret;
	}	
	
	/**
	 * Controle si une chaine est une date valide
	 * @param chaine
	 * @return
	 */
	public static boolean isDate(String chaine ){
		boolean ret = false;
		if (Formatage.stringToDate(chaine)!=null)
		    ret = true;
		return ret;
	}	
	

	/**
	 * Controle si un champ est obligatoire sinon insere un message d'erreur
	 * @param champ
	 * @param libChamp
	 * @param errors
	 */
	public static void champObligatoire(String champ, String libChamp, ActionErrors errors){
		if (isVide(champ)){
			errors.add("erreur",new ActionMessage("errors.champ.obligatoire",libChamp));
		}
	}

	/**
	 * Controle si un champ est une date valide sinon insere un message d'erreur
	 * @param champ
	 * @param libChamp
	 * @param errors
	 */
	public static void champDate(String champ, String libChamp, ActionErrors errors){
		if (!isVide(champ) && !isDate(champ)){
			errors.add("erreur",new ActionMessage("errors.champ.date",libChamp));
		}
	}

	/**
	 * Controle si un champ est numérique sinon insere un message d'erreur
	 * @param champ
	 * @param libChamp
	 * @param errors
	 */
	public static void champNumerique(String champ, String libChamp, ActionErrors errors){
		if (!isVide(champ) && !isLong(champ)){
			errors.add("erreur",new ActionMessage("errors.champ.numerique",libChamp));
		}
	}
	
	/**
	 * Controle si un champ est numérique sinon insere un message d'erreur
	 * @param champ
	 * @param libChamp
	 * @param errors
	 */
	public static void champDouble(String champ, String libChamp, ActionErrors errors){
		if (!isVide(champ) && !isDouble(champ)){
			errors.add("erreur",new ActionMessage("errors.champ.numerique",libChamp));
		}
	}
	
	
	/**
	 * Controle si la date1 est superieure à la date 2 sinon insere un message d'erreur
	 * @param date1 date valide en String
	 * @param date2 date valide en String
	 * @param lib1
	 * @param lib2
	 * @param errors
	 */
	public static void dateSuperieure(String date1, String date2, String lib1, String lib2, ActionMessages errors){
		if (!isVide(date1) && !isVide(date2)){
		    Date d1 = Formatage.stringToDate(date1);
		    Date d2 = Formatage.stringToDate(date2);
			if (!dateAfter(d1,d2)){
				errors.add("erreur",new ActionMessage("errors.date.superieure",lib1,lib2));
			}		    
		}
	}
    /**
     * Controle si la date1 est superieure à la date 2 sinon insere un message d'erreur
     * @param date1 date valide en String
     * @param date2 date valide en String
     * @param lib1
     * @param lib2
     * @param errors
     */
    public static void dateSuperieureEgale(String date1, String date2, String lib1, String lib2, ActionMessages errors){
        if (!isVide(date1) && !isVide(date2)){
            Date d1 = Formatage.stringToDate(date1);
            Date d2 = Formatage.stringToDate(date2);
            if (dateBefore(d1,d2)){
                errors.add("erreur",new ActionMessage("errors.date.superieure",lib1,lib2));
            }           
        }
    }    

    public static boolean dateAfter(Date d1, Date d2){
        boolean ret = false;
        if ( d1!=null && d2!= null && d1.after(d2)){
            ret = true;
        }           
        return ret;
    }
    public static boolean dateBefore(Date d1, Date d2){
        boolean ret = false;
        if ( d1!=null && d2!= null && d1.before(d2)){
            ret = true;
        }           
        return ret;
    }
    
    
	public static void main(String[] args) {
        Date d1 = Formatage.stringToDate("01/01/2001");
        Date d2 = Formatage.stringToDate("02/01/2001");
        System.out.println(dateAfter(d1,d2));
        System.out.println(!dateBefore(d1,d2));        
    }
}
