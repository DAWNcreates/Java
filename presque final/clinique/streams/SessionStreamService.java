package clinique.streams;

import clinique.model.Sessions.Session;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service pour les opérations Stream sur les sessions.
 */
public class SessionStreamService {

    /**
     * Compte le nombre de sessions longues (>60 min) avec une méthode référence.
     */
    public static long compterSessionsLongues(List<Session> sessions) {
        return sessions.stream()
                       .filter(Session::estLongue)   // ✅ Référence de méthode
                       .count();
    }
}