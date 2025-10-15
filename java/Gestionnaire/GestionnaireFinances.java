
package Gestionnaire;


public class GestionnaireFinances {


    private double totalRevenus;
    private double totalDepenses;
    //private Map<String, Double> paiements = new HashMap<>();
    //private Map<String, String> factures = new HashMap<>();
    private boolean rapportsGeneres;

    public GestionnaireFinances() {
    }

    public void ajouterPaiement(String idPaiement, double montant) {
    }

    public void supprimerPaiement(String idPaiement) {
    }

    public void ajouterFacture(String idFacture, String statut) {
    }

    public void supprimerFacture(String idFacture) {
    }

    public void genererRapport() {
    }

    public void afficherResume() {
    }

    public double getTotalRevenus() { return totalRevenus; }
    public void setTotalRevenus(double totalRevenus) { this.totalRevenus = totalRevenus; }

    public double getTotalDepenses() { return totalDepenses; }
    public void setTotalDepenses(double totalDepenses) { this.totalDepenses = totalDepenses; }

    public boolean isRapportsGeneres() { return rapportsGeneres; }
    public void setRapportsGeneres(boolean rapportsGeneres) { this.rapportsGeneres = rapportsGeneres; }

}

