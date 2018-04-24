/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 * Class for groupe.
 */
public class Groupe {
    
    private int id; 
    private String nom;
    private String identiteResp;
    private String adr;
    private String nbPers;
    private String pays;
    private String hebergement;
    /**
     * Constructs the object.
     *
     * @param      id            The identifier
     * @param      nom           The nom
     * @param      identiteResp  The identite resp
     * @param      adr           The address
     * @param      nbPers        The number of pers
     * @param      pays          The pays
     * @param      hebergement   The hebergement
     */
    public Groupe(int id, String nom, String identiteResp, String adr, String nbPers, String pays, String hebergement) {
        this.id = id;
        this.nom = nom;
        this.identiteResp = identiteResp;
        this.adr = adr;
        this.nbPers = nbPers;
        this.pays = pays;
        this.hebergement = hebergement;
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
     * @param      nom   The nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Gets the identite resp.
     *
     * @return     The identite resp.
     */
    public String getIdentiteResp() {
        return identiteResp;
    }
    /**
     * Sets the identite resp.
     *
     * @param      identiteResp  The identite resp
     */
    public void setIdentiteResp(String identiteResp) {
        this.identiteResp = identiteResp;
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
     * @param      adr   The address
     */
    public void setAdr(String adr) {
        this.adr = adr;
    }
    /**
     * Gets the number of pers.
     *
     * @return     The number of pers.
     */
    public String getNbPers() {
        return nbPers;
    }
    /**
     * Sets the number of pers.
     *
     * @param      nbPers  The number of pers
     */
    public void setNbPers(String nbPers) {
        this.nbPers = nbPers;
    }
    /**
     * Gets the pays.
     *
     * @return     The pays.
     */
    public String getPays() {
        return pays;
    }
    /**
     * Sets the pays.
     *
     * @param      pays  The pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }
    /**
     * Gets the hebergement.
     *
     * @return     The hebergement.
     */
    public String getHebergement() {
        return hebergement;
    }
    /**
     * Sets the hebergement.
     *
     * @param      hebergement  The hebergement
     */
    public void setHebergement(String hebergement) {
        this.hebergement = hebergement;
    }

}
   
  
