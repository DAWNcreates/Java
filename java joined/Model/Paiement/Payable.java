package Paiement;

public interface Payable {
    void effectuerPaiement();    // effectuer le paiement
    void annulerPaiement();      // annuler le paiement
    void afficherDetails();      // afficher les infos du paiement
}
