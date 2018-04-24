/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoUtilisateur;
import modele.dao.JdbcDistant;
import modele.dao.JdbcLocal;
import modele.metier.Utilisateur;

/**
 * Class for control principal.
 */
public class CtrlPrincipal {

    private CtrlRepresentation ctrlLesRepresentations;
    private CtrlLeMenu ctrlLeMenu;
    private CtrlLaBilleterie ctrlLaBilleterie;
    private CtrlConnectionLocale ctrlConnectionLocale;
    private CtrlConnectionDistante ctrlConnectionDistante;
    //boolean permettant de savoir si la connection est en locale ou distante
    private boolean connection=true;

    /**
     * Diplay VueConnectionLocale
     */
    public void afficherConnection() {
        this.ctrlConnectionLocale.getConnection().setVisible(true);
        this.ctrlLeMenu.getMenu().setVisible(false);
        this.ctrlLesRepresentations.getReserv().setVisible(false);
        this.ctrlLaBilleterie.getBilleterie().setVisible(false);
    }
    /**
     * Display VueConnectionDistante
     */
    public void afficherConnectionDistante() {
        this.ctrlConnectionDistante.getConnection().setVisible(true);

    }

    /**
     * Display VueMenu
     */
    public void afficherLeMenu() {
        this.ctrlConnectionLocale.getConnection().setVisible(false);
        this.ctrlLeMenu.getMenu().setVisible(true);
        this.ctrlLesRepresentations.getReserv().setVisible(false);
        this.ctrlLaBilleterie.getBilleterie().setVisible(false);
    }

    /**
     * Display VueRepresentation
     */
    public void afficherLesRepresentations() {
        this.ctrlLeMenu.getMenu().setVisible(false);
        this.ctrlLesRepresentations.getReserv().setVisible(true);
        this.ctrlLaBilleterie.getBilleterie().setVisible(false);
    }

    /**
     * Display VueBilleterie
     */
    public void afficherLaBilleterie() {
        this.ctrlLeMenu.getMenu().setVisible(false);
        this.ctrlLesRepresentations.getReserv().setVisible(false);
        this.ctrlLaBilleterie.getBilleterie().setVisible(true);
    }

    /**
     * Quit the app
     */
    public void quitterApplication() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(null, "Quitter l'application\nEtes-vous sûr(e) ?", "Representation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            System.exit(0);
        }
        if (rep == JOptionPane.NO_OPTION) {

        }
    }

    public CtrlConnectionLocale getCtrlConnectionLocale() {
        return ctrlConnectionLocale;
    }

    public CtrlConnectionDistante getCtrlConnectionDistante() {
        return ctrlConnectionDistante;
    }

    public CtrlLeMenu getCtrlMenu() {
        return ctrlLeMenu;
    }

    public void setCtrlMenu(CtrlLeMenu ctrlLeMenu) {
        this.ctrlLeMenu = ctrlLeMenu;
    }

    public CtrlLaBilleterie getCtrlLaBilleterie() {
        return ctrlLaBilleterie;
    }

    public void setCtrlLaConnectionLocale(CtrlConnectionLocale ctrlConnection) {
        this.ctrlConnectionLocale = ctrlConnection;
    }

    public void setCtrlLaConnectionDistante(CtrlConnectionDistante ctrlConnection) {
        this.ctrlConnectionDistante = ctrlConnection;
    }

    public void setCtrlLaBilleterie(CtrlLaBilleterie ctrlLaBilleterie) {
        this.ctrlLaBilleterie = ctrlLaBilleterie;
    }

    public CtrlRepresentation getCtrlRepresentation() {
        return ctrlLesRepresentations;
    }

    public void setCtrlRepresentation(CtrlRepresentation ctrlLesRepresentations) {
        this.ctrlLesRepresentations = ctrlLesRepresentations;
    }
    
    /**
     * Gets the connection.
     *
     * @return  connection   The connection.
     */
    public boolean getConnection() {
        return connection;
    }
    /**
     * Sets the connection.
     *
     * @param      connection  The connection
     */
    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    /**
     * Test if the login and the password are correct
     *
     * @param localDistant
     * @param lUtilisateur
     * @return found to see if the login and password were correct
     */
    public boolean testID(boolean localDistant, Utilisateur lUtilisateur) throws ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        InputStream input = null;
        boolean found = false;
        Utilisateur unUtilisateur = new Utilisateur();
        connection = localDistant;
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
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        //Connection test if will be either local connection or distant
        if (connection) {
            //vérifie que les identifiants passés en paramètre sont les mêmes que dans le properties
            String identifiant = prop.getProperty("identifiant");
            String mdp = prop.getProperty("mdp");
            unUtilisateur.setIdentifiant(identifiant);
            unUtilisateur.setMdp(mdp);
            System.out.println("id: " + lUtilisateur.getIdentifiant() + "--|--" + identifiant + " mdp: " + lUtilisateur.getMdp() + "--|--" + mdp);
            if (lUtilisateur.getIdentifiant().equals(unUtilisateur.getIdentifiant()) && lUtilisateur.getMdp().equals(unUtilisateur.getMdp())) {
                found = true;
            }
        } else {
            //vérifie que les identifiants passés en paramètre sont les mêmes que dans la table Utilisateur en se connectant à la base distante
            JdbcDistant.creer(prop.getProperty("jdbcDriver"), prop.getProperty("typeBdd"), prop.getProperty("localisationDistante"), prop.getProperty("databaseDistante"), prop.getProperty("dbuserDistant"), prop.getProperty("dbpasswordDistant"));
            try {
                JdbcDistant.getInstance().connecter();   
            } catch (SQLException ex) {
                Logger.getLogger(CtrlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (DaoUtilisateur.verifConnection(lUtilisateur, connection)) {
                    found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Mauvais identifiants !");
        }        
        return found;
    }
}
