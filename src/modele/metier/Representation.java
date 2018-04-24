/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 * Class for representation.
 */
public class Representation {
    private int id;
    private String groupe;
    private String lieux;
    private String date;
    private String heureD;
    private String heureF;
    private int placesDispo;
    private int placesTotal;
    
        
    
    
    /**
     * Constructs the object.
     *
     * @param      id           The identifier
     * @param      date         The date
     * @param      Lieu         The lieu
     * @param      Groupe       The groupe
     * @param      heureDebut   The heure debut
     * @param      heureFin     The heure fin
     * @param      placesDispo  The places dispo
     * @param      placesTotal  The places total
     */
    public Representation(int id,String date,String Lieu,String Groupe,String heureDebut,String heureFin,int placesDispo, int placesTotal){
    
    this.id=id;
    this.date=date;
    this.lieux=Lieu;
    this.groupe=Groupe;
    this.heureD=heureDebut;
    this.heureF=heureFin;
    this.placesDispo=placesDispo;
    this.placesTotal=placesTotal;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    @Override
    public String toString() {
        return "<html>Groupe=" + groupe +"<br> date=" + date + "<br> Lieu=" + lieux + "<br> heureDebut=" + heureD + "<br> heureFin=" + heureF + "<br> placesDispo=" + placesDispo + "/"+placesTotal+"</html>";
    }
    /**
     * Gets the places dispo.
     *
     * @return     The places dispo.
     */
    public int getPlacesDispo() {
        return placesDispo;
    }
    /**
     * Sets the places dispo.
     *
     * @param      placesDispo  The places dispo
     */
    public void setPlacesDispo(int placesDispo) {
        this.placesDispo = placesDispo;
    }
    
    /**
     * Gets the identifier.
     *
     * @return     The identifier.
     */
    public int getId(){
        return id;
    }
    /**
     * Gets the date.
     *
     * @return     The date.
     */
    public String getDate() {
        return date;
    }
    /**
     * Gets the lieu.
     *
     * @return     The lieu.
     */
    public String getLieu() {
        return lieux;
    }
    /**
     * Gets the groupe.
     *
     * @return     The groupe.
     */
    public String getGroupe() {
        return groupe;
    }
    /**
     * Gets the heure debut.
     *
     * @return     The heure debut.
     */
    public String getHeureDebut() {
        return heureD;
    }
    /**
     * Gets the heure fin.
     *
     * @return     The heure fin.
     */
    public String getHeureFin() {
        return heureF;
    }
    
    
    
    
    
    //mutateur
 

    public void setDate(String date) {
        this.date = date;
    }

    public void setLieu(String lieu) {
        this.lieux = lieu;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureD= heureDebut;
    }

    public void setHeureFin(String heureFin) {
        this.heureF= heureFin;
    }
}
