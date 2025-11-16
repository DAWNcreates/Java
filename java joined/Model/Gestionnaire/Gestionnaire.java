
package Gestionnaire;

/**
 *
 * @author pc
 */
public class Gestionnaire {
      protected String nomGestionnaire ;
    protected int idGestionnaire ;
    protected String role;
    protected boolean estActif;
    protected int pointsBonus;

    public void GestionnaireFinances(String nomGestionnaire, int idGestionnaire, String role, boolean estactif, int pointsBonus) {
        this.nomGestionnaire = nomGestionnaire;
        this.idGestionnaire = idGestionnaire;
        this.role = role;
        this.estActif = estactif;
        this.pointsBonus = pointsBonus;
    }

    public String getNomGestionnaire() {
        return nomGestionnaire;
    }

    public void setNomGestionnaire(String nomGestionnaire) {
        this.nomGestionnaire = nomGestionnaire;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEstactif() {
        return estActif;
    }

    public void setEstactif(boolean estactif) {
        this.estActif = estactif;
    }

    public int getPointsBonus() {
        return pointsBonus;
    }

    public void setPointsBonus(int pointsBonus) {
        this.pointsBonus = pointsBonus;
    }
    
    public void ajouterPoints(){
        this.pointsBonus++;
        
    }
    public void activer() { this.estActif = true; }
    public void desactiver() { this.estActif = false; }
}
