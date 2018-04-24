/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 * Class for lieu.
 */
public class Lieu {
    
    private int id; 
    private String nom;
    private String adr;
    private String capacite;

    /**
     * Constructs the object.
     *
     * @param      id        The identifier
     * @param      nom       The nom
     * @param      adr       The address
     * @param      capacite  The capacite
     */
    public Lieu(int id, String nom, String adr, String capacite) {
        this.id = id;
        this.nom = nom;
        this.adr = adr;
        this.capacite = capacite;
    }
   /**
    * Returns a string representation of the object.
    *
    * @return     String representation of the object.
    */
    @Override
    public String toString() {
        return ("Lieu{nom: " + this.getNom() + "\tid: " + this.getId() + "\tadr: " + this.getAdr() + "\tcapacite: " + this.getCapacite()) + "}";
    }
    /**
     * Gets the identifier.
     *
     * @return     The identifier.
     */
     public int getId() {
        return id;
    }
    /**
     * Sets the identifier.
     *
     * @param      id    The identifier
     */
     public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets the nom.
     *
     * @return     The nom.
     */
     public String getNom() {
        return nom;
    }
    /**
     * Sets the nom.
     *
     * @param      Nom   The nom
     */
     public void setNom(String Nom) {
        this.nom = nom;
    }
    /**
     * Gets the address.
     *
     * @return     The address.
     */
     public String getAdr() {
        return adr;
    }
    /**
     * Sets the address.
     *
     * @param      Adr   The address
     */
     public void setAdr(String Adr) {
        this.adr = adr;
    }
    /**
     * Gets the capacite.
     *
     * @return     The capacite.
     */
     public String getCapacite() {
        return capacite;
    }
    /**
     * Sets the capacite.
     *
     * @param      Capacite  The capacite
     */
     public void setCapacite(String Capacite) {
        this.capacite = capacite;
    }       
}