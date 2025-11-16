/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rapport;


import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Record DossierMedical
public record DossierMedical(
        String idPatient,
        String nomPatient,
        String prenomPatient,
        LocalDate dateNaissance,
        List<String> allergies,       // Liste des allergies baddalha map
        List<String> antecedents,     
        List<String> traitements      // Traitements en cours baddalha set
) {

    public DossierMedical {
        if (idPatient == null || idPatient.isBlank()) {
            throw new IllegalArgumentException("L'ID patient est obligatoire");
        }
        if (nomPatient == null || nomPatient.isBlank()) {
            throw new IllegalArgumentException("Le nom du patient est obligatoire");
        }
        if (prenomPatient == null || prenomPatient.isBlank()) {
            throw new IllegalArgumentException("Le prénom du patient est obligatoire");
        }
        if (dateNaissance == null || dateNaissance.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date de naissance invalide");
        }
        if (allergies == null) allergies = new ArrayList<>();
        if (antecedents == null) antecedents = new ArrayList<>();
        if (traitements == null) traitements = new ArrayList<>();
    }

    public List<String> getAllergies() {
        return Collections.unmodifiableList(allergies);
    }

    public List<String> getAntecedents() {
        return Collections.unmodifiableList(antecedents);
    }

    public List<String> getTraitements() {
        return Collections.unmodifiableList(traitements);
    }

    public DossierMedical ajouterAllergie(String allergie) {
        List<String> nouvellesAllergies = new ArrayList<>(allergies);
        nouvellesAllergies.add(allergie);
        return new DossierMedical(idPatient, nomPatient, prenomPatient, dateNaissance,
                nouvellesAllergies, antecedents, traitements);
    }

    public DossierMedical ajouterAntecedent(String antecedent) {
        List<String> nouveauxAntecedents = new ArrayList<>(antecedents);
        nouveauxAntecedents.add(antecedent);
        return new DossierMedical(idPatient, nomPatient, prenomPatient, dateNaissance,
                allergies, nouveauxAntecedents, traitements);
    }

    public DossierMedical ajouterTraitement(String traitement) {
        List<String> nouveauxTraitements = new ArrayList<>(traitements);
        nouveauxTraitements.add(traitement);
        return new DossierMedical(idPatient, nomPatient, prenomPatient, dateNaissance,
                allergies, antecedents, nouveauxTraitements);
    }

    public String afficherRésumé() {
        return String.format(
                "Patient: %s %s (ID: %s)\nDate de naissance: %s\nAllergies: %s\nAntécédents: %s\nTraitements: %s",
                prenomPatient, nomPatient, idPatient, dateNaissance,
                allergies, antecedents, traitements
        );
    }
}
