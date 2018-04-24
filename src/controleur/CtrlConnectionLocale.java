/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoUtilisateur;
import modele.metier.Utilisateur;
import vue.VueConnectionLocale;

/**
 * Class to control the connection id to the database.
 */
public class CtrlConnectionLocale implements WindowListener, ActionListener {

    // To change this license header, choose License Headers in Project Properties.
    // To change this template file, choose Tools | Templates
    // and open the template in the editor.
    private vue.VueConnectionLocale connection;
    private CtrlPrincipal ctrlPrincipal;

    /**
     * Constructs the object.
     *
     * @param vue The vue
     * @param ctrl The control
     */
    public CtrlConnectionLocale(vue.VueConnectionLocale vue, CtrlPrincipal ctrl) {
        this.connection = vue;
        this.connection.addWindowListener((WindowListener) this);
        this.connection.getJButtonConnectLocal().addActionListener((ActionListener) this);
        this.ctrlPrincipal = ctrl;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ctrlPrincipal.quitterApplication();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    /**
     * Gets the vue connection.
     *
     * @return The connection.
     */
    public VueConnectionLocale getConnection() {
        return connection;
    }

    /**
     * When click on the connection button check if the id and password are the
     * same from the database
     *
     * @param e actionEvent from the vue
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Connection test to the app
        if (e.getSource().equals(this.connection.getJButtonConnectLocal())) {
            try {
                String identifiant = this.connection.getJTextFieldUtil().getText();
                String mdp = this.connection.getJPasswordField1().getText();
                Utilisateur lUtilisateur = null;
                lUtilisateur=new Utilisateur(identifiant, mdp);
                boolean found = ctrlPrincipal.testID(true, lUtilisateur);
                System.out.println(found);
                if (found) {
                    ctrlPrincipal.afficherLeMenu();
                }
                this.connection.getJTextFieldUtil().setText("");
                this.connection.getJPasswordField1().setText("");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CtrlConnectionLocale.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlConnectionLocale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
