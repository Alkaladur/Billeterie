/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modele.dao.DaoRepresentation;
import modele.metier.Representation;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import vue.VueBilleterie;



/**
 * Class for control la billeterie.
 */
public class CtrlLaBilleterie implements WindowListener,MouseListener,ActionListener{
    private vue.VueBilleterie billeterie;
    private ArrayList<Representation> lesRepresentations;
    private CtrlPrincipal ctrlPrincipal;
    private int nbPlaceDispo;
    private int idRepresentation;
    private String nomGroupe;
    private Boolean concertDispo;
    
    public CtrlLaBilleterie(vue.VueBilleterie vue, CtrlPrincipal ctrl){
        this.billeterie=vue;
        this.billeterie.addWindowListener(this);
        this.billeterie.getjTable1().addMouseListener(this);
        this.ctrlPrincipal = ctrl;
        afficheLesReserv();
        billeterie.getJButtonCommander().addActionListener(this);
        billeterie.getJButtonRetour().addActionListener(this);
        billeterie.getJButtonCommander().setEnabled(false);
    }
    /**
     * Display the band list and on click on one of them display the whole
     * information such as where they gonna play, date, time and how much ticket left
     */
    private void afficheLesReserv() {
        try {
            lesRepresentations= (ArrayList<Representation>) DaoRepresentation.selectAll(true);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTable jtable1 = this.billeterie.getjTable1();
        DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
        
        for (Representation uneRepresentation : lesRepresentations){
            model.addRow(new Object[]{uneRepresentation.getGroupe()});
        }
        
        this.billeterie.setjTable1(jtable1);
    }
    private void afficheLesReserv(boolean active) {
        try {
            lesRepresentations= (ArrayList<Representation>) DaoRepresentation.selectAll(active);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTable jtable1 = this.billeterie.getjTable1();
        DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
        
        for (Representation uneRepresentation : lesRepresentations){
            model.addRow(new Object[]{uneRepresentation.getGroupe()});
        }
        
        this.billeterie.setjTable1(jtable1);
    }
    /**
     * When you try to order ticket and  performe an action on the JButtonCommander
     * check if there is enough ticket left and if the number of tickets is a number or above 0 
     *
     * @param      e     
     */
    public void actionPerformed(ActionEvent e) {
        int nbPlace = 0;
        boolean active =ctrlPrincipal.getConnection();
        if (e.getSource() == billeterie.getJButtonCommander()) {
            try{
                nbPlace = Integer.parseInt(billeterie.getJTextFieldNbPlace().getText());
                billeterie.getJTextFieldNbPlace().setText("");
            }
            catch(NumberFormatException a){
                billeterie.getjLabelCommande().setText("entrez un nombre");
                billeterie.getJTextFieldNbPlace().setText("");
            }
            if (nbPlace<0){
                billeterie.getjLabelCommande().setText("nombre<0");
            }
            else if(nbPlace>nbPlaceDispo){
                billeterie.getjLabelCommande().setText("Pas assez de place");
            }
            else{
                try{
                    DaoRepresentation.ventePlace(idRepresentation,nbPlace);
                    billeterie.getjLabelCommande().setText("Commande de "+nbPlace+" place(s)");
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlLaBilleterie.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CtrlLaBilleterie.class.getName()).log(Level.SEVERE, null, ex);
                }

                int row = billeterie.getjTable1().getSelectedRow();
                String groupeChoisis = (String) billeterie.getjTable1().getValueAt(row, 0);
                String groupeChoisisRes = null;
                try {
                    groupeChoisisRes = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).toString();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Date currentTime = new Date();
                    String dateDebut = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getDate();
                    String heureDebut = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getHeureDebut();
                    int annee = Integer.parseInt(dateDebut.substring(0,4));
                    int mois = Integer.parseInt(dateDebut.substring(5,7));
                    int jour = Integer.parseInt(dateDebut.substring(8,10));
                    int heure = Integer.parseInt(heureDebut.substring(0,2));
                    int minutes = Integer.parseInt(heureDebut.substring(3,5));
            
                    Date dateConcert = new Date(annee-1900, mois, jour,heure,minutes);
                    System.out.println(annee + " " +mois + " " +jour);
                    System.out.println(currentTime);
                    System.out.println(dateConcert);
                    billeterie.getjLabel3().setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
                }
                billeterie.getjLabel2().setText(groupeChoisisRes);
            }
        }
        if (e.getSource() == billeterie.getJButtonRetour()) {
             ctrlPrincipal.afficherLeMenu() ;
             billeterie.getjLabelCommande().setText("");
        }
    }
    
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        billeterie.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
     * Display the list of band
     *
     * @param      e     onClick on the list
     */
    public void mouseClicked(MouseEvent e) {
        boolean active =ctrlPrincipal.getConnection();
        int row = billeterie.getjTable1().getSelectedRow();
        String groupeChoisis = (String) billeterie.getjTable1().getValueAt(row, 0);
        String groupeChoisisRes = null;
        nomGroupe=groupeChoisis;
        billeterie.getjLabelCommande().setText("");
        try {
            groupeChoisisRes = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).toString();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date currentTime = new Date();
            String dateDebut = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getDate();
            String heureDebut = DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getHeureDebut();
            int annee = Integer.parseInt(dateDebut.substring(0,4));
            int mois = Integer.parseInt(dateDebut.substring(5,7));
            int jour = Integer.parseInt(dateDebut.substring(8,10));
            int heure = Integer.parseInt(heureDebut.substring(0,2));
            int minutes = Integer.parseInt(heureDebut.substring(3,5));
            
            Date dateConcert = new Date(annee-1900, mois, jour,heure,minutes);
            System.out.println(annee + " " +mois + " " +jour);
            System.out.println(currentTime);
            System.out.println(dateConcert);
            idRepresentation=DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getId();
            nbPlaceDispo=DaoRepresentation.selectRepresentationParGroupe(groupeChoisis,active).getPlacesDispo();
            concertDispo=dateConcert.before(currentTime);
            
            if(nbPlaceDispo==0 && concertDispo){
                billeterie.getjLabel3().setText("Le concert est passé");
                billeterie.getJButtonCommander().setEnabled(false);
            }else if(concertDispo){
                billeterie.getjLabel3().setText("Le concert est passé");
                billeterie.getJButtonCommander().setEnabled(false);
            }else if(nbPlaceDispo==0){
                billeterie.getjLabel3().setText("Il n'y a plus de places");
                billeterie.getJButtonCommander().setEnabled(false);
            }
            else{
                billeterie.getjLabel3().setText("");
                billeterie.getJButtonCommander().setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        billeterie.getjLabel2().setText(groupeChoisisRes);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public VueBilleterie getBilleterie() {
        return billeterie;
    }
}
