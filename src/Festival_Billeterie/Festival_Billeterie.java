/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Festival_Billeterie;

import controleur.CtrlConnectionDistante;
import controleur.CtrlConnectionLocale;
import controleur.CtrlPrincipal;
import controleur.CtrlRepresentation;
import controleur.CtrlLeMenu;
import controleur.CtrlLaBilleterie;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import vue.VueRepresentation;
import vue.VueMenu;
import vue.VueBilleterie;
import vue.VueConnectionLocale;
import modele.dao.JdbcLocal;
import vue.VueConnectionDistante;

/**
 * Class for festival billeterie as know as main
 */
public class Festival_Billeterie {

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;
        
        // Get the lines in config.properties to be able to connect to the local dataBase
        try {

            input = new FileInputStream("properties/config.properties");

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JdbcLocal.creer(prop.getProperty("jdbcDriver"), prop.getProperty("typeBdd"), prop.getProperty("localisation"), prop.getProperty("database"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
        try {
            //instanciation de tout les controleurs et connection à la base locale
            JdbcLocal.getInstance().connecter();
            CtrlPrincipal leControleurPrincipal = new CtrlPrincipal();

            VueConnectionLocale laVueLaConnectionLocale = new VueConnectionLocale();
            CtrlConnectionLocale leControleurLaConnectionLocale = new CtrlConnectionLocale(laVueLaConnectionLocale, leControleurPrincipal);

            VueConnectionDistante laVueLaConnectionDistante=new VueConnectionDistante();
            CtrlConnectionDistante leControleurLaConnectionDistante = new CtrlConnectionDistante(laVueLaConnectionDistante, leControleurPrincipal);

            VueMenu laVueLeMenu = new VueMenu();
            CtrlLeMenu leControleurLeMenu = new CtrlLeMenu(laVueLeMenu, leControleurPrincipal);

            VueRepresentation laVueLesRepresentation = new VueRepresentation();
            CtrlRepresentation leControleurLesRepresentation = new CtrlRepresentation(laVueLesRepresentation, leControleurPrincipal);

            VueBilleterie laVueLaBilleterie = new VueBilleterie();
            CtrlLaBilleterie leControleurLaBilleterie = new CtrlLaBilleterie(laVueLaBilleterie, leControleurPrincipal);

            leControleurPrincipal.setCtrlLaConnectionLocale(leControleurLaConnectionLocale);
            leControleurPrincipal.setCtrlLaConnectionDistante(leControleurLaConnectionDistante);
            leControleurPrincipal.setCtrlLaBilleterie(leControleurLaBilleterie);
            leControleurPrincipal.setCtrlRepresentation(leControleurLesRepresentation);
            leControleurPrincipal.setCtrlMenu(leControleurLeMenu);
            leControleurPrincipal.setCtrlLaBilleterie(leControleurLaBilleterie);

            laVueLaConnectionLocale.setVisible(true);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Main - classe JDBC non trouvée");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Main - échec de connexion " + ex.getMessage());
        }

    }

}
