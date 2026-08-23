/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicare;

/**
 *
 * @author baloy
 */
public class PatientNotFoundException extends Exception{
      public PatientNotFoundException(String message) {
          // used when the patient cannot be found in the system
        super(message);
    }
      //10 commit
}