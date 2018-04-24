/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import controleur.CtrlConnectionLocale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for utilisateur.
 */
public class Utilisateur {
    private String identifiant;
    private String mdp;
    
    public Utilisateur(String identifiant, String mdp){
        
        this.identifiant=md5Converter(identifiant);
        this.mdp=md5Converter(mdp);
    }
    public Utilisateur(){
    //overide method to allow us to compare mdp and id 
    }
    
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    @Override
    public String toString() {
        return "Utilisateur{" + "identifiant=" + identifiant + ", mdp=" + mdp + '}';
    }
    public String getIdentifiant() {
        return identifiant;
    }
    /**
     * Sets the identifiant.
     *
     * @param      identifiant  The identifiant
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
    /**
     * Gets the mdp.
     *
     * @return     The mdp.
     */
    public String getMdp() {
        return mdp;
    }
    /**
     * Sets the mdp.
     *
     * @param      mdp   The mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    /**
     * Convert the String in parameters into MD5 type
     *
     * @param      ligne  The String to convert 
     *
     * @return     lConvertie The String of the Converted parameter
     */
    public static String md5Converter(String ligne){
        String lConvertie="";
        try {
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(ligne.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        lConvertie=sb.toString();
        
        } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CtrlConnectionLocale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lConvertie;
    }
    
}
