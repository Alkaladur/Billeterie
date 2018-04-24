/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.JdbcLocal;
import modele.dao.DaoRepresentation;
import modele.metier.Representation;

/**
 *
 * @author pboucher
 */
public class testRepresentation {
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
        Representation laRepresentation = new Representation(1,"2018-06-11","Parc Hellfest, Clisson ","Hellfest 2018","14:00:00","23:00:00",5000,5000);
        System.out.println(laRepresentation.toString());
        System.out.println("test réussi");
    }
}