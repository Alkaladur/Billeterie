/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.JdbcLocal;
import modele.metier.Utilisateur;

/**
 *
 * @author wquentel
 */
public class testUtilisateur {
    public static void main(String args[]){
        try {
            JdbcLocal.creer("com.mysql.jdbc.Driver", "jdbc:mysql:", "//localhost/", "festival", "root", "joliverie");
            JdbcLocal.getInstance().connecter();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testDaoRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(testDaoRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("---------------------------------------------------");
        System.out.println("test de création d'un objet de type representation:");
        System.out.println("---------------------------------------------------");
        Utilisateur unUtilisateur = new Utilisateur("root","joliverie");
        System.out.println(unUtilisateur.getIdentifiant());
        System.out.println(unUtilisateur.getMdp());
        System.out.println(unUtilisateur.toString());
        System.out.println("test réussi");
    }
}
