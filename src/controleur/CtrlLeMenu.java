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
import modele.dao.JdbcDistant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import modele.dao.DistantUpdater;
import modele.dao.JdbcLocal;
import vue.VueMenu;

/**
 * Class for control le menu to select if you want to look for the band list or
 * buy ticket
 */
public class CtrlLeMenu implements ActionListener, WindowListener {

    private vue.VueMenu leMenu;
    private CtrlPrincipal ctrlPrincipal;
    //boolean pour l'affichage de VueConnectionDistante
    //permet de ne pas avoir à s'identifier à chaque fois
    private boolean connectedToDtbDistant = false;

    public void setConnectedToDtbDistant(boolean connectedToDtbDistant) {
        this.connectedToDtbDistant = connectedToDtbDistant;
    }

    /**
     * Constructs the object.
     *
     * @param vue The vue
     * @param ctrl The control
     */
    public CtrlLeMenu(vue.VueMenu vue, CtrlPrincipal ctrl) {
        this.leMenu = vue;
        this.ctrlPrincipal = ctrl;
        this.leMenu.addWindowListener(this);
        leMenu.getjBoutonBilleterie().addActionListener(this);
        leMenu.getjBoutonRepresentation().addActionListener(this);
        leMenu.getjButtonDeconnection().addActionListener(this);
        leMenu.getjRadioButtonDistance().addActionListener(this);
        leMenu.getjRadioButtonLocale().addActionListener(this);
        leMenu.getJButtonSynchronisation().addActionListener(this);
        this.leMenu.setJRationButtonLocale();
        this.leMenu.setJButtonSynchronisation();
    }

    public VueMenu getLeMenu() {
        return leMenu;
    }

    /**
     * Diplay either VueBilleterie or VueRepresentation
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leMenu.getjBoutonBilleterie()) {
            ctrlPrincipal.afficherLaBilleterie();
        }
        if (e.getSource() == leMenu.getjBoutonRepresentation()) {
            ctrlPrincipal.afficherLesRepresentations();
        }
        if (e.getSource() == leMenu.getJButtonSynchronisation()) {

            DistantUpdater.sqlScriptCreator();
            DistantUpdater.pushToDistant();

        }
        //si l'on se déconnecte repasse le JradioButtonLocale en selectionné
        if (e.getSource() == leMenu.getjButtonDeconnection()) {
            ctrlPrincipal.afficherConnection();
            ctrlPrincipal.getCtrlMenu().getLeMenu().getjRadioButtonLocale().setSelected(true);
            ctrlPrincipal.getCtrlMenu().getLeMenu().getjBoutonBilleterie().setEnabled(true);
            ctrlPrincipal.getCtrlMenu().getLeMenu().getJButtonSynchronisation().setEnabled(false);
            if (!ctrlPrincipal.getConnection()) {
                try {
                    JdbcDistant.getInstance().deconnecter();
                    ctrlPrincipal.getCtrlMenu().setConnectedToDtbDistant(false);
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlLeMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == leMenu.getjRadioButtonLocale()) {
            ctrlPrincipal.setConnection(true);
            ctrlPrincipal.getCtrlMenu().getLeMenu().getjBoutonBilleterie().setEnabled(true);
            ctrlPrincipal.getCtrlRepresentation().getReserv().getJButtonCommander().setEnabled(true);
            ctrlPrincipal.getCtrlRepresentation().getReserv().getJButtonUpdate().setEnabled(false);
        }
        if (e.getSource() == leMenu.getjRadioButtonDistance()) {
            //vérifie si l'on est connecté à la dtb distante
            if (ctrlPrincipal.getConnection()) {
                if (!this.connectedToDtbDistant) {
                    ctrlPrincipal.afficherConnectionDistante();
                }
                //désactive l'option de commander sur la base distante
                ctrlPrincipal.getCtrlMenu().getLeMenu().getJButtonSynchronisation().setEnabled(true);
                ctrlPrincipal.setConnection(false);
                ctrlPrincipal.getCtrlMenu().getLeMenu().getjBoutonBilleterie().setEnabled(false);
                ctrlPrincipal.getCtrlRepresentation().getReserv().getJButtonCommander().setEnabled(false);
                ctrlPrincipal.getCtrlRepresentation().getReserv().getJButtonUpdate().setEnabled(true);
            }
        }

    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        leMenu.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

    public VueMenu getMenu() {
        return leMenu;
    }

}
