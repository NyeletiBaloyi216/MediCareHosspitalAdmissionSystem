/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicare;

/**
 *
 * @author baloy
 */
public class DuplicatePatientException extends Exception{
    // this exception is used when a patientid is already registred in the system
    public DuplicatePatientException (String message){
        super(message);
    }
    
}
