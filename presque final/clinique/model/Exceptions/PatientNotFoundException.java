/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinique.model.Exceptions;

/**
 *
 * @author marwa
 */
public class PatientNotFoundException extends Exception {
    public PatientNotFoundException (String message ){
        super(message);
    }
}