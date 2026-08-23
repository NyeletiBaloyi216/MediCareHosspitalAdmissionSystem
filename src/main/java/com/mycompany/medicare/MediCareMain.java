/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.medicare;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author baloy
 */
public class MediCareMain {

    public static void main(String[] args) {
        
        boolean running = true;
        
        // scanner is used to get patient information
        Scanner scanner = new Scanner(System.in);
        // patient managermanages patient records
        PatientManager patientManager = new PatientManager();
        Ward ward = new Ward("Ward A");
// menu that has patient information
        while (running) {
            System.out.println("\n===== MediCare Hospital Patient Admission System =====");
            System.out.println("1.  Register New Patient");
            System.out.println("2.  Search Patient");
            System.out.println("3.  Display All Patients");
            System.out.println("4.  Update Patient");
            System.out.println("5.  Delete Patient");
            System.out.println("6.  Allocate Bed");
            System.out.println("7.  Release Bed");
            System.out.println("8.  Display Ward Layout");
            System.out.println("9.  Display Available Beds");
            System.out.println("10. Display Occupied Beds");
            System.out.println("11. Generate Reports");
            System.out.println("12. Sort Patients");
            System.out.println("0.  Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            // options the to choose from

            switch (choice) {
                case "1": registerPatient(scanner, patientManager); break;
                case "2": searchPatient(scanner, patientManager); break;
                case "3": patientManager.displayAllPatients(); break;
                case "4": updatePatient(scanner, patientManager); break;
                case "5": deletePatient(scanner, patientManager, ward); break;
                case "6": allocateBed(scanner, patientManager, ward); break;
                case "7": releaseBed(scanner, ward); break;
                case "8": ward.displayWardLayout(); break;
                case "9": displayAvailableBeds(ward); break;
                case "10": displayOccupiedBeds(ward); break;
                case "11": generateReports(patientManager, ward); break;
                case "12": sortPatients(scanner, patientManager); break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
// method to register new patient
    private static void registerPatient(Scanner scanner, PatientManager patientManager) {
        try {
            System.out.println("\n--- Register New Patient ---");
            System.out.print("Patient ID: ");
            String patientId = scanner.nextLine().trim();
            System.out.print("First Name: ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine().trim();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Gender: ");
            String gender = scanner.nextLine().trim();
            System.out.print("Medical Condition: ");
            String condition = scanner.nextLine().trim();

            System.out.println("Patient Category:");
            System.out.println("1. INPATIENT");
            System.out.println("2. OUTPATIENT");
            System.out.println("3. EMERGENCY");
            System.out.print("Select category: ");

            String categoryChoice = scanner.nextLine().trim();
            PatientCategory category;
// 3 types of patients to choose from
            switch (categoryChoice) {
                case "1": category = PatientCategory.INPATIENT; break;
                case "2": category = PatientCategory.OUTPATIENT; break;
                case "3": category = PatientCategory.EMERGENCY; break;
                default:
                    System.out.println("Invalid patient category.");
                    return;
            }

            MediCare patient;
            if (category == PatientCategory.INPATIENT) {
                System.out.print("Ward Number: ");
                String wardNumber = scanner.nextLine().trim();
                patient = new Inpatient(patientId, firstName, lastName, age, gender, condition, wardNumber);
            } else {
                patient = new MediCare(patientId, firstName, lastName, age, gender, condition, category);
            }

            patientManager.registerPatient(patient);
            System.out.println("Patient registered successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Age must be a valid number.");
        } catch (DuplicatePatientException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// search patient by id
    private static void searchPatient(Scanner scanner, PatientManager patientManager) {
        try {
            System.out.println("\n--- Search Patient ---");
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine().trim();
            MediCare patient = patientManager.searchPatient(patientId);
            System.out.println("\nPatient Found:");
            System.out.println(patient.displayDetails());
        } catch (PatientNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// updates patient details
    private static void updatePatient(Scanner scanner, PatientManager patientManager) {
        try {
            System.out.println("\n--- Update Patient ---");
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine().trim();
            patientManager.searchPatient(patientId);

            System.out.print("New First Name: ");
            String firstName = scanner.nextLine().trim();
            System.out.print("New Last Name: ");
            String lastName = scanner.nextLine().trim();
            System.out.print("New Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("New Gender: ");
            String gender = scanner.nextLine().trim();
            System.out.print("New Medical Condition: ");
            String condition = scanner.nextLine().trim();

            patientManager.updatePatient(patientId, firstName, lastName, age, gender, condition);
            System.out.println("Patient updated successfully!");
        } catch (PatientNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Age must be a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // this method delete patient from the hostpital system

    private static void deletePatient(Scanner scanner, PatientManager patientManager, Ward ward) {
        try {
            System.out.println("\n--- Delete Patient ---");
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine().trim();

            MediCare p = patientManager.findPatient(patientId);
            if (p instanceof Inpatient) {
                Inpatient ip = (Inpatient) p;
                if (ip.hasBed()) {
                    try { ward.releaseBed(ip.getBedNumber()); } catch (Exception e) {}
                }
            }

            patientManager.deletePatient(patientId);
            System.out.println("Patient deleted successfully!");
        } catch (PatientNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// allocate beds for inpatients
    private static void allocateBed(Scanner scanner, PatientManager patientManager, Ward ward) {
        try {
            System.out.println("\n--- Allocate Bed ---");
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine().trim();

            MediCare p = patientManager.findPatient(patientId);
            if (p == null) {
                System.out.println("Error: Patient not found.");
                return;
            }
            if (!(p instanceof Inpatient)) {
                System.out.println("Error: Only inpatients can be allocated beds.");
                return;
            }

            Inpatient patient = (Inpatient) p;
            if (patient.hasBed()) {
                System.out.println("Error: Patient already has bed " + patient.getBedNumber());
                return;
            }

            System.out.print("Enter Bed Number (e.g., B01): ");
            String bedNumber = scanner.nextLine().trim();

            ward.allocateBed(bedNumber, patient);
            System.out.println("Bed " + bedNumber + " allocated successfully!");
        } catch (BedNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void releaseBed(Scanner scanner, Ward ward) {
        try {
            System.out.println("\n--- Release Bed ---");
            System.out.print("Enter Bed Number to release (e.g., B01): ");
            String bedNumber = scanner.nextLine().trim();
            ward.releaseBed(bedNumber);
            System.out.println("Bed " + bedNumber + " released successfully!");
        } catch (BedNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// shows beds that are currently available
    private static void displayAvailableBeds(Ward ward) {
        List<Bed> available = ward.getAvailableBeds();
        System.out.println("\n--- Available Beds ---");
        if (available.isEmpty()) {
            System.out.println("No beds available.");
        } else {
            for (Bed bed : available) {
                System.out.println(bed.getBedNumber());
            }
        }
        System.out.println("Total Available: " + available.size());
    }
// shows occupied beds in the ward
    private static void displayOccupiedBeds(Ward ward) {
        List<Bed> occupied = ward.getOccupiedBeds();
        System.out.println("\n--- Occupied Beds ---");
        if (occupied.isEmpty()) {
            System.out.println("No occupied beds.");
        } else {
            for (Bed bed : occupied) {
                System.out.println(bed.getBedNumber() + " - Patient: " + bed.getOccupant().getPatientId());
            }
        }
        System.out.println("Total Occupied: " + occupied.size());
    }

    private static void generateReports(PatientManager patientManager, Ward ward) {
        System.out.println("\n========== REPORTS ==========");
        System.out.println("1. Patient Report");
        System.out.println("2. Bed Occupancy Report");
        System.out.println("==============================");
        System.out.print("Select report: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("\n--- PATIENT REPORT ---");
                System.out.println("Total Registered Patients: " + patientManager.getPatientCount());
                patientManager.displayAllPatients();
                break;
            case "2":
                System.out.println("\n--- BED OCCUPANCY REPORT ---");
                System.out.println("Total Beds: " + ward.getTotalBeds());
                System.out.println("Occupied Beds: " + ward.getOccupiedCount());
                System.out.println("Available Beds: " + ward.getAvailableCount());
                System.out.printf("Occupancy Percentage: %.2f%%\n", ward.getOccupancyPercentage());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
//Sort by Surname or id
    private static void sortPatients(Scanner scanner, PatientManager patientManager) {
        System.out.println("\n1. Sort by Surname");
        System.out.println("2. Sort by Patient ID");
        System.out.print("Select sort option: ");

        String choice = scanner.nextLine().trim();
        List<MediCare> sorted;
//Patients Sorted by Patient ID
        if (choice.equals("1")) {
            sorted = patientManager.getPatientsSortedBySurname();
            System.out.println("\n--- Patients Sorted by Surname ---");
        } else if (choice.equals("2")) {
            sorted = patientManager.getPatientsSortedById();
            System.out.println("\n--- Patients Sorted by Patient ID ---");
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        for (MediCare patient : sorted) {
            System.out.println(patient.getPatientId() + " - " + patient.getLastName() + ", " + patient.getFirstName());
        }
    }
}