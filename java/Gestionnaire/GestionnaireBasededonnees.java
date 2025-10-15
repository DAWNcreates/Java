/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestionnaire;

/**
 *
 * @author pc
 */
public class GestionnaireBasededonnees {
  //private List<String> patients;
    //private List<String> therapeuttes;
    //private Map<String, String> sessions;      
    private boolean donneesChargees;
    private int nombreTotalSessions;            
    private String nomBaseDonnees;      
     public GestionnaireBasededonnees(String nomBaseDonnees) {
        this.nomBaseDonnees = nomBaseDonnees;
        //this.patients = new ArrayList<>();
        //this.therapeuttes = new ArrayList<>();
        //this.sessions = new HashMap<>();
        this.donneesChargees = false;
        this.nombreTotalSessions = 0;
    }

/*    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public List<String> getTherapeuttes() {
        return therapeuttes;
    }

    public void setTherapeuttes(List<String> therapeuttes) {
        this.therapeuttes = therapeuttes;
    }

    public Map<String, String> getSessions() {
        return sessions;
    }

    public void setSessions(Map<String, String> sessions) {
        this.sessions = sessions;
    }
*/

    public void setDonneesChargees(boolean donneesChargees) {
        this.donneesChargees = donneesChargees;
    }

    public int getNombreTotalSessions() {
        return nombreTotalSessions;
    }

    public void setNombreTotalSessions(int nombreTotalSessions) {
        this.nombreTotalSessions = nombreTotalSessions;
    }

    public String getNomBaseDonnees() {
        return nomBaseDonnees;
    }

    public void setNomBaseDonnees(String nomBaseDonnees) {
        this.nomBaseDonnees = nomBaseDonnees;
    }

/*
    public void ajouterPatient(String nom) {
        patients.add(nom);
        System.out.println("✅ Patient ajouté : " + nom);
    }

    public void supprimerPatient(String nom) {
        if (patients.remove(nom)) {
            System.out.println("🗑️ Patient supprimé : " + nom);
        } else {
            System.out.println("⚠️ Patient non trouvé : " + nom);
        }
    }

    public void afficherPatients() {
        System.out.println("👥 Liste des patients :");
        for (String p : patients) {
            System.out.println(" - " + p);
        }
    }

    public void trierPatients() {
        Collections.sort(patients);
        System.out.println(" Liste des patients triée par ordre alphabétique.");
    }

    public boolean rechercherPatient(String nom) {
        Iterator<String> it = patients.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(nom)) {
                System.out.println("🔍 Patient trouvé : " + nom);
                return true;
            }
        }
        System.out.println(" Patient non trouvé : " + nom);
        return false;
    }

    // 🔹 Fonctions utilitaires (thérapeutes)
    public void ajouterTherapeute(String nom) {
        therapeuttes.add(nom);
        System.out.println(" Thérapeute ajouté : " + nom);
    }

    public void supprimerTherapeute(String nom) {
        if (therapeuttes.remove(nom)) {
            System.out.println("️ Thérapeute supprimé : " + nom);
        } else {
            System.out.println("️ Thérapeute non trouvé : " + nom);
        }
    }

    public void afficherTherapeuttes() {
        System.out.println(" Liste des thérapeutes :");
        for (String t : therapeuttes) {
            System.out.println(" - " + t);
        }
    }

    public void ajouterSession(String idSession, String type) {
        sessions.put(idSession, type);
        nombreTotalSessions++;
        System.out.println(" Session ajoutée : ID=" + idSession + " | Type=" + type);
    }

    public void supprimerSession(String idSession) {
        if (sessions.remove(idSession) != null) {
            nombreTotalSessions--;
            System.out.println("️ Session supprimée : " + idSession);
        } else {
            System.out.println("️ Session non trouvée : " + idSession);
        }
    }

    public void afficherSessions() {
        System.out.println("Liste des sessions (" + nombreTotalSessions + " total) :");
        for (Map.Entry<String, String> entry : sessions.entrySet()) {
            System.out.println(" - ID: " + entry.getKey() + " | Type: " + entry.getValue());
        }
    }
*/


}
