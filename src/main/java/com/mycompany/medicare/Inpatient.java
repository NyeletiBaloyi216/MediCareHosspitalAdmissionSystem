/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicare;

/**
 *
 * @author baloy
 */
 public class Inpatient extends MediCare{ 
     // used variables tostore inpatient information
     private String wardNumber;
    private String bedNumber;
//constructor
    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition,
                     String wardNumber) {

        super(patientId, firstName, lastName, age, gender,
              medicalCondition, PatientCategory.INPATIENT);
// validates  ward number
        if (wardNumber == null || wardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward number cannot be empty.");
        }

        this.wardNumber = wardNumber.trim();
        this.bedNumber = "Not Allocated";
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        if (wardNumber == null || wardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward number cannot be empty.");
        }
        this.wardNumber = wardNumber.trim();
    }
// returning method
    public String getBedNumber() {
        return bedNumber;
    }
//non returning method
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
// check if patient has a bed already
    public boolean hasBed() {
        return bedNumber != null && !bedNumber.equals("Not Allocated");
    }

    @Override
    public String displayDetails() {
        return super.displayDetails()
            + String.format(" Ward: %-8s Bed: %s", wardNumber, bedNumber);
    }
}