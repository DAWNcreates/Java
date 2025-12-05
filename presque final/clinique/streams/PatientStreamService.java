package clinique.streams;

import clinique.model.Personnes.Patient;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service utilitaire utilisant les Streams et Lambda pour filtrer, trier, 
 * et transformer des collections de patients.
 * Respecte la contrainte : « utiliser les streams et expressions Lambda ».
 */
public class PatientStreamService {

    /**
     * Filtre les patients selon un critère donné (ex: âge, genre, satisfaction).
     * @param patients La liste de patients à traiter
     * @param condition Un prédicat Lambda (ex: p -> p.getAge() > 30)
     * @return Une nouvelle liste filtrée
     */
    public static List<Patient> filtrerPatients(List<Patient> patients, 
                                                Predicate<Patient> condition) {
        return patients.stream()
                       .filter(condition)   // ✅ Lambda
                       .collect(Collectors.toList());
    }

    /**
     * Exemple d'utilisation : patients satisfaits (note ≥ 4)
     */
    public static List<Patient> patientsSatisfaits(List<Patient> patients) {
        return filtrerPatients(patients, p -> p.getNoteSatisfaction() >= 4);
    }
}