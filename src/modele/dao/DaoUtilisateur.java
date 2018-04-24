/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;
import controleur.CtrlConnectionLocale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.metier.Utilisateur;
/**
 * Class for dao utilisateur.
 */
public class DaoUtilisateur {
    /**
     * { function_description }
     *
     * @param      lUtilisateur  Login of the database
     *
     * @return     trouve Boolean return true if the login and the password are found in the database
     *
     * @throws     SQLException  
     */
    public static boolean verifConnection(Utilisateur lUtilisateur, boolean activeDtb) throws SQLException{
        boolean trouve=false;
        String identifiant= lUtilisateur.getIdentifiant();
        String mdp=lUtilisateur.getMdp();
        ResultSet rs;
        PreparedStatement pstmt;
        JdbcLocal jdbc = JdbcLocal.getInstance();
        String requete = "SELECT * FROM Utilisateur WHERE identifiant LIKE '"+identifiant+"' AND mdp='"+mdp+"';";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        System.out.println(requete);
        if (rs.next()) {
            trouve=true;
        }            
        return trouve;
    }
    
}
