/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.medicare.DuplicatePatientException;
import com.mycompany.medicare.MediCare;
import com.mycompany.medicare.PatientCategory;
import com.mycompany.medicare.PatientManager;
import com.mycompany.medicare.PatientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 *
 * @author baloy
 */
public class PatientManagerTest {
    
    private PatientManager manager;

    // This runs before every single test so they don't interfere with each other
    @BeforeEach
    public void setUp() {
        manager = new PatientManager();
    }

    @Test
    @DisplayName("Register patient successfully")
    public void testRegisterPatientSuccess() throws DuplicatePatientException {
        MediCare patient = new MediCare("P001", "HAPPY", "NENE", 30, "Male", "ADHD", PatientCategory.OUTPATIENT);

        manager.registerPatient(patient);

        assertEquals(1, manager.getPatientCount());
        assertNotNull(manager.findPatient("P001"));
    }

    @Test
    @DisplayName("Register duplicate patient ID throws DuplicatePatientException")
    public void testRegisterPatientDuplicateIdThrowsException() throws DuplicatePatientException {
        MediCare p1 = new MediCare("P001", "Happy ", "Nene", 30, "Male", "ADHD", PatientCategory.OUTPATIENT);
        manager.registerPatient(p1);

        MediCare p2 = new MediCare("P001", "Jane", "siminya", 30, "Female", "Cold", PatientCategory.OUTPATIENT);

        assertThrows(DuplicatePatientException.class, () -> {
            manager.registerPatient(p2);
        });
    }

    @Test
    @DisplayName("Find patient returns null when patient does not exist")
    public void testFindPatientNotFound() {
        assertNull(manager.findPatient("NONEXISTENT"));
    }

    @Test
    @DisplayName("Search patient throws PatientNotFoundException when not found")
    public void testSearchPatientNotFound() {
        assertThrows(PatientNotFoundException.class, () -> {
            manager.searchPatient("P999");
        });
    }

    @Test
    @DisplayName("Delete patient successfully")
    public void testDeletePatientSuccess() throws DuplicatePatientException, PatientNotFoundException {
        MediCare patient = new MediCare("P003", "Aleol", "sism", 28, "Female", "Fever", PatientCategory.EMERGENCY);
        manager.registerPatient(patient);

        assertEquals(1, manager.getPatientCount());

        manager.deletePatient("P003");

        assertEquals(0, manager.getPatientCount());
        assertNull(manager.findPatient("P003"));
    }

    @Test
    @DisplayName("Update patient details successfully")
    public void testUpdatePatientSuccess() throws DuplicatePatientException, PatientNotFoundException {
        MediCare patient = new MediCare("P004", "Baba", "Bud", 40, "Male", "Back Pain", PatientCategory.OUTPATIENT);
        manager.registerPatient(patient);

        manager.updatePatient("P004", "Robert", "siabnyoni", 41, "Male", "Recovered");

        MediCare updated = manager.findPatient("P004");
        assertEquals("Robert", updated.getFirstName());
        assertEquals(41, updated.getAge());
        assertEquals("Recovered", updated.getMedicalCondition());
    }

    @Test
    @DisplayName("Get all patients returns correct list")
    public void testGetAllPatients() throws DuplicatePatientException {
        MediCare p1 = new MediCare("P005", "Tom", "Hanks", 65, "Male", "Diabetes", PatientCategory.OUTPATIENT);
        MediCare p2 = new MediCare("P006", "Emma", "Stone", 35, "Female", "Asthma", PatientCategory.INPATIENT);

        manager.registerPatient(p1);
        manager.registerPatient(p2);

        List<MediCare> all = manager.getAllPatients();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Sort patients by surname")
    public void testSortBySurname() throws DuplicatePatientException {
        MediCare p1 = new MediCare("P007", "Zack", "Aaron", 20, "Male", "Cold", PatientCategory.OUTPATIENT);
        MediCare p2 = new MediCare("P008", "Amy", "Brown", 25, "Female", "Flu", PatientCategory.OUTPATIENT);
        MediCare p3 = new MediCare("P009", "Charlie", "Adams", 30, "Male", "Cough", PatientCategory.OUTPATIENT);

        manager.registerPatient(p1);
        manager.registerPatient(p2);
        manager.registerPatient(p3);

        List<MediCare> sorted = manager.getPatientsSortedBySurname();

        assertEquals("Aaron", sorted.get(0).getLastName());
        assertEquals("Adams", sorted.get(1).getLastName());
        assertEquals("Brown", sorted.get(2).getLastName());
    }

    @Test
    @DisplayName("Sort patients by patient ID")
    public void testSortById() throws DuplicatePatientException {
        MediCare p1 = new MediCare("P010", "Happy", "Nene", 35, "Male", "Adhd", PatientCategory.OUTPATIENT);
        MediCare p2 = new MediCare("P002", "kiki", "jones", 25, "Female", "depression", PatientCategory.OUTPATIENT);

        manager.registerPatient(p1);
        manager.registerPatient(p2);

        List<MediCare> sorted = manager.getPatientsSortedById();

        assertEquals("P002", sorted.get(0).getPatientId());
        assertEquals("P010", sorted.get(1).getPatientId());
    }

    @Test
    @DisplayName("Register null patient throws IllegalArgumentException")
    public void testRegisterNullPatientThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.registerPatient(null);
        });
    }
}
  