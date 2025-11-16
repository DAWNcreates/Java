/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paiement;

import java.time.LocalDate;

public class PaiementParCarte extends Paiement {
    private String numeroCarte;
    private String nomTitulaire;
    private String typeCarte;

    public PaiementParCarte(String idPaiement, double montant, LocalDate datePaiement, String statut,
                            String numeroCarte, String nomTitulaire, String typeCarte) {
        super(idPaiement, montant, datePaiement, statut);
        this.numeroCarte = numeroCarte;
        this.nomTitulaire = nomTitulaire;
        this.typeCarte = typeCarte;
    }

    @Override
    public void effectuerPaiement() {
        System.out.println("💳 Traitement du paiement par carte...");
        System.out.println("Titulaire: " + nomTitulaire + " | Type: " + typeCarte);
        statut = "Effectué";
        System.out.println("✅ Paiement " + idPaiement + " validé avec succès.");
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Type de carte: " + typeCarte);
        System.out.println("Titulaire: " + nomTitulaire);
    }
}
