package clinique.model.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service utilitaire pour charger des données à partir d'un fichier texte.
 * Utilise try-with-resources pour gérer automatiquement la fermeture du fichier.
 */
public class DataLoader {

    /**
     * Charge une liste de patients depuis un fichier texte.
     * @param filename Le nom du fichier à charger
     * @return Une liste de chaînes représentant les lignes du fichier
     * @throws IOException Si une erreur survient lors de la lecture du fichier
     */
    public static List<String> loadLinesFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        
        // ✅ Utilisation de try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } // 🔚 Le fichier est fermé automatiquement ici
        
        return lines;
    }
}