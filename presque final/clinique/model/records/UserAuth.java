package clinique.model.records;

/**
 * Record immuable représentant les identifiants d'un utilisateur.
 * Utilisé pour encapsuler les données sensibles (login/mot de passe) de façon sécurisée.
 */
public record UserAuth(String username, String password) {
    // Le constructeur compact est implicite : public UserAuth(String username, String password)
}