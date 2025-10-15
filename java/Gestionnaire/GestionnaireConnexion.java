
package Gestionnaire;

import java.util.Date;


public class GestionnaireConnexion {
    private String identifiant;             
    private String motDePasseCrypte;         
    private boolean sessionActive;           
    private Date derniereConnexion;  
    private int tentativesEchouees;          
    private boolean estBloque;                
    private String role;     

    public GestionnaireConnexion(String identifiant, String motDePasseCrypte, boolean sessionActive, Date derniereConnexion, int tentativesEchouees, boolean estBloque, String role) {
        this.identifiant = identifiant;
        this.motDePasseCrypte = motDePasseCrypte;
        this.sessionActive = sessionActive;
        this.derniereConnexion = derniereConnexion;
        this.tentativesEchouees = tentativesEchouees;
        this.estBloque = estBloque;
        this.role = role;
    }

    
    
    
    
    
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasseCrypte() {
        return motDePasseCrypte;
    }

    public void setMotDePasseCrypte(String motDePasseCrypte) {
        this.motDePasseCrypte = motDePasseCrypte;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public Date getDerniereConnexion() {
        return derniereConnexion;
    }

    public void setDerniereConnexion(Date derniereConnexion) {
        this.derniereConnexion = derniereConnexion;
    }

    public int getTentativesEchouees() {
        return tentativesEchouees;
    }

    public void setTentativesEchouees(int tentativesEchouees) {
        this.tentativesEchouees = tentativesEchouees;
    }

    public boolean isEstBloque() {
        return estBloque;
    }

    public void setEstBloque(boolean estBloque) {
        this.estBloque = estBloque;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
 
    
    public boolean verifierIdentifiants(String id, String motDePasse) {
        return false;
    }

    public void demarrerSession() {
    }

    public void terminerSession() {
    }

    public void reinitialiserMotDePasse(String nouveauMotDePasse) {
    }

    private String crypterMotDePasse(String motDePasse) {
        return null;
    }

    public void debloquerCompte() {
    }
}
