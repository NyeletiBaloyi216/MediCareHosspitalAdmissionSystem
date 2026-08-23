/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicare;

/**
 *
 * @author baloy
 */
public class Bed {
    //stores bed number
      private final String bedNumber;
    private boolean occupied;
    private Inpatient occupant;
//constructor
    public Bed(String bedNumber) {
        // cheecks if the bed is not empty
        if (bedNumber == null || bedNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Bed number cannot be empty.");
        }
        this.bedNumber = bedNumber.trim().toUpperCase();
        this.occupied = false;
        this.occupant = null;
    }

    public String getBedNumber() {
        return bedNumber;
    }
// checks if bed is occupied
    public boolean isOccupied() {
        return occupied;
    }

    public Inpatient getOccupant() {
        return occupant;
    }

    public void occupy(Inpatient patient) throws BedNotAvailableException {
        if (occupied) {
            throw new BedNotAvailableException("Bed " + bedNumber + " is already occupied.");
        }
        
        if (patient == null) {
            throw new BedNotAvailableException("Patient cannot be available.");
        }
        occupied = true;
        occupant = patient;
    }

    public void vacate() {
        occupied = false;
        occupant = null;
    }
// shows if the bed is available or not
    @Override
    public String toString() {
        if (occupied && occupant != null) {
            return bedNumber + " [OCCUPIED: " + occupant.getFirstName() + " " + occupant.getLastName() + "]";
        }
        return bedNumber + " [AVAILABLE]";
    }
}