/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import java.time.LocalDateTime;

public interface SessionManager {
    void demarrerSession();                 // Start the session
    void terminerSession();                 // End the session
    void enregistrerSession();              // Save session data to DB or file
    boolean verifierDisponibilite(LocalDateTime date);  // Check if the therapist is free
}
