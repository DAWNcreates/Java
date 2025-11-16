package Rapport;

import java.time.LocalDate;

// Record Facture
public record Facture(
        String idFacture,
        String idPatient,
        double montant,
        String methodePaiement, // "Carte", "Assurance"
        LocalDate dateEmission
) {

    public Facture {
        if (idFacture == null || idFacture.isBlank()) {
            throw new IllegalArgumentException("L'ID de la facture ne peut pas être vide");
        }
        if (idPatient == null || idPatient.isBlank()) {
            throw new IllegalArgumentException("L'ID du patient est obligatoire");
        }
        if (montant < 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        if (methodePaiement == null || methodePaiement.isBlank()) {
            throw new IllegalArgumentException("La méthode de paiement est obligatoire");
        }
        if (dateEmission == null) {
            throw new IllegalArgumentException("La date d'émission est obligatoire");
        }
    }



    public Facture appliquerReduction(double pourcentage) {
        if (pourcentage < 0 || pourcentage > 100) {
            throw new IllegalArgumentException("Pourcentage invalide");
        }
        double montantReduit = montant * (1 - pourcentage / 100.0);
        return new Facture(idFacture, idPatient, montantReduit, methodePaiement, dateEmission);
    }

    public double calculerTVA(double taux) {
        if (taux < 0) taux = 0;
        return montant * taux / 100.0;
    }
}
