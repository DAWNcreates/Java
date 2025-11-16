
package Paiement;

import java.time.LocalDate;

public abstract class Paiement implements Payable {
    protected String idPaiement;
    protected double montant;
    protected LocalDate datePaiement;
    protected String statut;

    public  Paiement(String idPaiement, double montant, LocalDate datePaiement, String statut) {
        this.idPaiement = idPaiement;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.statut = statut;
    }

    @Override
    public void annulerPaiement() {
        statut = "Annulé";
        System.out.println("❌ Paiement " + idPaiement + " annulé.");
    }

    @Override
    public void afficherDetails() {
        System.out.println("Paiement ID: " + idPaiement);
        System.out.println("Montant: " + montant + " TND");
        System.out.println("Date: " + datePaiement);
        System.out.println("Statut: " + statut);
    }

    @Override
    public abstract void effectuerPaiement();

    public String getIdPaiement() { return idPaiement; }
    public double getMontant() { return montant; }
    public LocalDate getDatePaiement() { return datePaiement; }
    public String getStatut() { return statut; }
}
