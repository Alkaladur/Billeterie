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
 * @author wquentel
 */
public class testDaoRepresentation {
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
        System.out.println("test de la méthode selectAll():");
        System.out.println("---------------------------------------------------");
        try {
            List<Representation> lesRepresentation = new ArrayList<Representation>();
            lesRepresentation = DaoRepresentation.selectAll(true);
            for(Representation uneRepresentation : lesRepresentation){
                System.out.println(uneRepresentation.toString());
            }
            System.out.println("test de selectAll() réussi");
        } catch (SQLException ex) {
            System.out.println("test de selectAll() échoué");
        }
        System.out.println("---------------------------------------------------");
        System.out.println("test de la méthode selectRepresentationParGroupe():");
        System.out.println("---------------------------------------------------");
        try {
            Representation RecupParGroupe;
            RecupParGroupe = DaoRepresentation.selectRepresentationParGroupe("Boxty",true);
            System.out.println(RecupParGroupe.toString());
            System.out.println("test de selectRepresentationParGroupe() réussi");
        } catch (SQLException ex) {
            System.out.println("test de selectRepresentationParGroupe() échoué"+ex+"");
        }
        
    }
}
