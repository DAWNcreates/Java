/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import java.time.LocalDateTime;

public interface SessionManager {
    void demarrerSession();      
    void terminerSession();          
    void enregistrerSession();         
    boolean verifierDisponibilite(LocalDateTime date);  
}
