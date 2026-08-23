/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicare;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baloy
 */
public class Ward {
   
   public static final int ROWS = 4;
    public static final int COLUMNS = 5;
    // calculates number of beds
    public static final int TOTAL_BEDS = ROWS * COLUMNS;

    private final String wardNumber;
    private final Bed[][] beds;
// constructor
    public Ward(String wardNumber) {
        if (wardNumber == null || wardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward number cannot be empty.");
        }
        this.wardNumber = wardNumber.trim();
        //2d array
        this.beds = new Bed[ROWS][COLUMNS];
        initializeBeds();
    }

    private void initializeBeds() {
        int counter = 1;
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                String bedNumber = String.format("B%02d", counter++);
                beds[row][col] = new Bed(bedNumber);
            }
        }
    }
// searches for bednumber
    private Bed findBed(String bedNumber) {
        if (bedNumber == null || bedNumber.trim().isEmpty()) {
            return null;
        }
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].getBedNumber().equalsIgnoreCase(bedNumber.trim())) {
                    return beds[row][col];
                }
            }
        }
        return null;
    }
// returning methods
    public String getWardNumber() {
        return wardNumber;
    }

    public int getTotalBeds() {
        return TOTAL_BEDS;
    }

    public int getOccupiedCount() {
        return getOccupiedBeds().size();
    }

    public int getAvailableCount() {
        return getAvailableBeds().size();
    }

    public double getOccupancyPercentage() {
        if (TOTAL_BEDS == 0) return 0.0;
        return (getOccupiedCount() * 100.0) / TOTAL_BEDS;
    }

    public void allocateBed(String bedNumber, Inpatient patient)
            throws BedNotAvailableException {

        if (patient == null) {
            throw new BedNotAvailableException("Patient cannot be available.");
        }

        if (getAvailableCount() == 0) {
            throw new BedNotAvailableException("No beds available in the ward.");
        }

        Bed bed = findBed(bedNumber);

        if (bed == null) {
            throw new BedNotAvailableException("Bed " + bedNumber + " does not exist.");
        }

        if (bed.isOccupied()) {
            throw new BedNotAvailableException("Bed " + bedNumber + " is already occupied.");
        }

        if (patient.hasBed()) {
            throw new BedNotAvailableException(
                "Patient " + patient.getPatientId() +
                " already occupies bed " + patient.getBedNumber() + "."
            );
        }

        bed.occupy(patient);
        patient.setBedNumber(bed.getBedNumber());
        patient.setWardNumber(wardNumber);
    }

    public void releaseBed(String bedNumber) throws BedNotAvailableException {
        Bed bed = findBed(bedNumber);

        if (bed == null) {
            throw new BedNotAvailableException("Bed " + bedNumber + " does not exist.");
        }

        if (!bed.isOccupied()) {
            throw new BedNotAvailableException("Bed " + bedNumber + " is already available.");
        }

        Inpatient patient = bed.getOccupant();
        bed.vacate();

        if (patient != null) {
            patient.setBedNumber("Not Allocated");
            patient.setWardNumber("Not Allocated");
        }
    }
// shows beds taht are currently available
    public List<Bed> getAvailableBeds() {
        List<Bed> available = new ArrayList<>();
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (!beds[row][col].isOccupied()) {
                    available.add(beds[row][col]);
                }
            }
        }
        return available;
    }

    public List<Bed> getOccupiedBeds() {
        List<Bed> occupied = new ArrayList<>();
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].isOccupied()) {
                    occupied.add(beds[row][col]);
                }
            }
        }
        return occupied;
    }
// displys the ward layout
    public void displayWardLayout() {
        System.out.println("\n--- Ward " + wardNumber +
                          " Layout (" + ROWS + "x" + COLUMNS + ") ---");
// shows the bed rows
        for (int row = 0; row < beds.length; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < beds[row].length; col++) {
                line.append(String.format("%-30s", beds[row][col].toString()));
            }
            System.out.println(line);
        }
    }
}