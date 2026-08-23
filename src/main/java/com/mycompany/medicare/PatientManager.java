/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.medicare;
import java.util.ArrayList;
import java.util.Comparator;
// used comparator to compare objects so that it easy to sort them
import java.util.List;

/**
 *
 * @author baloy
 */
public class PatientManager {
    // stores all registered patients
    private final List<MediCare> patients;

    public PatientManager() {
        patients = new ArrayList<>();
    }
// method to add new patient
    public void registerPatient(MediCare patient) throws DuplicatePatientException {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (findPatient(patient.getPatientId()) != null) {
            throw new DuplicatePatientException(
                "A patient with ID " + patient.getPatientId() + " already exists."
            );
        }
        patients.add(patient);
    }

    public MediCare findPatient(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            return null;
        }
        for (MediCare patient : patients) {
            if (patient.getPatientId().equalsIgnoreCase(patientId.trim())) {
                return patient;
            }
        }
        return null;
    }

    public MediCare searchPatient(String patientId) throws PatientNotFoundException {
        MediCare patient = findPatient(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("No patient found with ID " + patientId);
        }
        return patient;
    }

    public void updatePatient(String patientId, String firstName, String lastName,
                              int age, String gender, String medicalCondition)
            throws PatientNotFoundException {

        MediCare patient = searchPatient(patientId);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
    }
//get method for returning methods
    public void deletePatient(String patientId) throws PatientNotFoundException {
        MediCare patient = searchPatient(patientId);
        patients.remove(patient);
    }

    public List<MediCare> getAllPatients() {
        return new ArrayList<>(patients);
    }
// return total number of registered 
    public int getPatientCount() {
        return patients.size();
    }
//sort by surname
    public List<MediCare> getPatientsSortedBySurname() {
        List<MediCare> sorted = new ArrayList<>(patients);
        sorted.sort(Comparator.comparing(MediCare::getLastName, String.CASE_INSENSITIVE_ORDER));
        return sorted;
    }
// sorts patients by id
    public List<MediCare> getPatientsSortedById() {
        List<MediCare> sorted = new ArrayList<>(patients);
        sorted.sort(Comparator.comparing(MediCare::getPatientId, String.CASE_INSENSITIVE_ORDER));
        return sorted;
    }
//shows all registered patients
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }
        // shows patient details
        System.out.println("\n--- All Registered Patients ---");
        for (MediCare patient : patients) {
            System.out.println(patient.displayDetails());
        }
    }
}