package validation2.Model.Gestionnaire;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gestionnaire {
    protected String nomGestionnaire;
    protected int idGestionnaire;
    protected String role;
    protected boolean estActif;
    protected int pointsBonus;

    public void GestionnaireFinances(String nomGestionnaire, int idGestionnaire, String role, boolean estactif, int pointsBonus) {
        this.nomGestionnaire = nomGestionnaire;
        this.idGestionnaire = idGestionnaire;
        this.role = role;
        this.estActif = estactif;
        this.pointsBonus = pointsBonus;
    }

    public String getNomGestionnaire() {
        return nomGestionnaire;
    }

    public void setNomGestionnaire(String nomGestionnaire) {
        this.nomGestionnaire = nomGestionnaire;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEstactif() {
        return estActif;
    }

    public void setEstactif(boolean estactif) {
        this.estActif = estactif;
    }

    public int getPointsBonus() {
        return pointsBonus;
    }

    public void setPointsBonus(int pointsBonus) {
        this.pointsBonus = pointsBonus;
    }

    public void ajouterPoints() {
        this.pointsBonus++;
    }

    public void activer() {
        this.estActif = true;
    }

    public void desactiver() {
        this.estActif = false;
    }

    private static List<Gestionnaire> gestionnaires = new ArrayList<>();

    public static void ajouterGestionnaire(Gestionnaire g) {
        gestionnaires.add(g);
    }

    public static List<Gestionnaire> getGestionnairesActifs() {
        return gestionnaires.stream()
                .filter(Gestionnaire::isEstactif)
                .collect(Collectors.toList());
    }

    public static Stream<String> nomsDesGestionnaires() {
        return gestionnaires.stream()
                .map(Gestionnaire::getNomGestionnaire);
    }

    public static void afficherNomsActifs() {
        gestionnaires.stream()
                .filter(Gestionnaire::isEstactif)
                .map(Gestionnaire::getNomGestionnaire)
                .forEach(System.out::println);
    }

    public static int totalPointsBonus() {
        return gestionnaires.stream()
                .mapToInt(Gestionnaire::getPointsBonus)
                .sum();
    }

    public static List<Gestionnaire> trierParPointsBonus() {
        return gestionnaires.stream()
                .sorted((g1, g2) -> Integer.compare(g2.getPointsBonus(), g1.getPointsBonus()))
                .collect(Collectors.toList());
    }
}
