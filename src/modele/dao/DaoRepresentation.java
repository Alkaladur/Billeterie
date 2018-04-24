/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Representation;
import modele.dao.DistantUpdater;

/**
 *
 * @author wquentel
 */
public class DaoRepresentation {

    /**
     * 
     * @param localDistant to see if the selected dtb is the distant one or the local one
     * @return lesRepresentation
     * @throws SQLException 
     */
    public static List<Representation> selectAll(boolean localDistant) throws SQLException {
        List<Representation> lesRepresentation = new ArrayList<Representation>();
        Representation uneRepresentation;
        ResultSet rs;
        PreparedStatement pstmt;
        // préparer la requête
        String requete = "SELECT R.id_rep AS id, date_rep, Lieu.nom AS Lieu, Groupe.nom AS Groupe, heure_deb,heure_fin, R.nbPlaceDispo AS places_dispo, Lieu.capacite AS places_total "
                + "FROM Representation R "
                + "INNER JOIN Groupe ON R.id_groupe=Groupe.id "
                + "INNER JOIN Lieu ON R.id_lieu= Lieu.id";
        if (localDistant) {
            JdbcLocal jdbc = JdbcLocal.getInstance();
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date_rep");
                String Lieu = rs.getString("Lieu");
                String Groupe = rs.getString("Groupe");
                String heureDebut = rs.getString("heure_deb");
                String heureFin = rs.getString("heure_fin");
                int placesDispo = rs.getInt("places_dispo");
                int placesTotal = rs.getInt("places_total");
                uneRepresentation = new Representation(id, date, Lieu, Groupe, heureDebut, heureFin, placesDispo, placesTotal);
                lesRepresentation.add(uneRepresentation);
            }
        } else {
            JdbcDistant jdbc = JdbcDistant.getInstance();
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date_rep");
                String Lieu = rs.getString("Lieu");
                String Groupe = rs.getString("Groupe");
                String heureDebut = rs.getString("heure_deb");
                String heureFin = rs.getString("heure_fin");
                int placesDispo = rs.getInt("places_dispo");
                int placesTotal = rs.getInt("places_total");
                uneRepresentation = new Representation(id, date, Lieu, Groupe, heureDebut, heureFin, placesDispo, placesTotal);
                lesRepresentation.add(uneRepresentation);
            }
        }
        return lesRepresentation;
    }

    /**
     * 
     * @param groupeChoix the selected band
     * @param localDistant  to see if the selected dtb is the distant one or the local one
     * @return uneRepresentation
     * @throws SQLException 
     */
    public static Representation selectRepresentationParGroupe(String groupeChoix, boolean localDistant) throws SQLException {
        Representation uneRepresentation = null;
        ResultSet rs;
        PreparedStatement pstmt;
        // préparer la requête  heure_deb 	heure_fin
        String requete = "SELECT R.id_rep AS id, date_rep, Lieu.nom AS Lieu, Groupe.nom AS Groupe,heure_deb,heure_fin, R.nbPlaceDispo AS places_dispo,Lieu.capacite AS places_total "
                + "FROM Representation R "
                + "INNER JOIN Groupe ON R.id_groupe=Groupe.id "
                + "INNER JOIN Lieu ON R.id_lieu= Lieu.id WHERE Groupe.nom LIKE ?";
        //test si l'on est connecté en local ou en distant
        if (localDistant) {
            JdbcLocal jdbc = JdbcLocal.getInstance();
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            pstmt.setString(1,groupeChoix);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date_rep");
                String Lieu = rs.getString("Lieu");
                String Groupe = rs.getString("Groupe");
                String heureDebut = rs.getString("heure_deb");
                String heureFin = rs.getString("heure_fin");
                int placesDispo = rs.getInt("places_dispo");
                int placesTotal = rs.getInt("places_total");
                uneRepresentation = new Representation(id, date, Lieu, Groupe, heureDebut, heureFin, placesDispo, placesTotal);
            }
        } else {
            JdbcDistant jdbc = JdbcDistant.getInstance();
            pstmt = jdbc.getConnexion().prepareStatement(requete);
            pstmt.setString(1,groupeChoix);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date_rep");
                String Lieu = rs.getString("Lieu");
                String Groupe = rs.getString("Groupe");
                String heureDebut = rs.getString("heure_deb");
                String heureFin = rs.getString("heure_fin");
                int placesDispo = rs.getInt("places_dispo");
                int placesTotal = rs.getInt("places_total");
                uneRepresentation = new Representation(id, date, Lieu, Groupe, heureDebut, heureFin, placesDispo, placesTotal);
            }
        }
        return uneRepresentation;

    }

    /**
     * Update the database when tickets are ordered
     *
     * @param idRepresentation The id of the Representation
     * @param place Number of tickets to order
     *
     * @throws SQLException
     */
    public static void ventePlace(int idRepresentation, int place) throws SQLException, IOException {
        Representation uneRepresentation = null;
        ResultSet rs;
        PreparedStatement pstmt;
        JdbcLocal jdbc = JdbcLocal.getInstance();
        // préparer la requête  heure_deb 	heure_fin
        String requete = "UPDATE `Representation` SET `nbPlaceDispo` = `nbPlaceDispo`-" + place + " WHERE `Representation`.`id_rep` =" + idRepresentation + ";";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.executeUpdate();
        DistantUpdater.writeFile(requete);
    }
    public static void updateDistant(String requete) throws SQLException, IOException {
        Representation uneRepresentation = null;
        ResultSet rs;
        PreparedStatement pstmt;
        JdbcDistant jdbc = JdbcDistant.getInstance();
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        pstmt.executeUpdate();
        DistantUpdater.writeFile(requete);
    }
}
