
package Signalment;

public class SignalementIa extends Signalement {

    private String typeAlerte;    
    private double niveauCriticite;  

    public SignalementIa(String idSignalement, String description, String auteur,
                         String typeAlerte, double niveauCriticite) {
        super(idSignalement, description, auteur);
        this.typeAlerte = typeAlerte;
        this.niveauCriticite = niveauCriticite;
    }

    public void analyserAlerte() {
        System.out.println("Analyse de l’alerte IA : " + typeAlerte 
                           + "  Niveau de criticité : " + niveauCriticite);
    }

    public void notifierManager() {
        System.out.println("Notification envoyée au manager pour l’alerte : " + typeAlerte);
    }

    @Override
    public void afficherSignalement() {
        super.afficherSignalement();  
        System.out.println("Type d’alerte IA : " + typeAlerte);
        System.out.println("Niveau de criticité : " + niveauCriticite);
    }

    public String getTypeAlerte() { return typeAlerte; }
    public void setTypeAlerte(String typeAlerte) { this.typeAlerte = typeAlerte; }

    public double getNiveauCriticite() { return niveauCriticite; }
    public void setNiveauCriticite(double niveauCriticite) { this.niveauCriticite = niveauCriticite; }
}
