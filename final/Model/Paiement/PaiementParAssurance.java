
package Paiement;

import java.time.LocalDate;

public class PaiementParAssurance extends Paiement {
    private String nomAssurance;
    private double tauxCouverture;

    public  PaiementParAssurance(String idPaiement, double montant, LocalDate datePaiement, String statut,
                                String nomAssurance, double tauxCouverture) {
        super(idPaiement, montant, datePaiement, statut);
        this.nomAssurance = nomAssurance;
        this.tauxCouverture = tauxCouverture;
    }

    @Override
    public void effectuerPaiement() {
        double remboursement = montant * (tauxCouverture / 100);
        System.out.println("🧾 Envoi du dossier à " + nomAssurance);
        System.out.println("Montant remboursable: " + remboursement + " TND");
        statut = "En attente de remboursement";
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Assurance: " + nomAssurance);
        System.out.println("Taux de couverture: " + tauxCouverture + "%");
    }
}
