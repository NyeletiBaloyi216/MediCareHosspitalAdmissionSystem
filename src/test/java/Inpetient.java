/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.medicare.Inpatient;
import com.mycompany.medicare.PatientCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author baloy
 */
public class Inpetient {
    
    public Inpetient() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
        @Test
    @DisplayName("Create inpatient with valid data")
    public void testValidConstructor() {
        Inpatient patient = new Inpatient("I001", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");

        assertEquals("I001", patient.getPatientId());
        assertEquals("Thabo", patient.getFirstName());
        assertEquals("Hlolo", patient.getLastName());
        assertEquals("Ward A", patient.getWardNumber());
        assertEquals("Not Allocated", patient.getBedNumber());
        assertEquals(PatientCategory.INPATIENT, patient.getCategory());
        assertFalse(patient.hasBed());
    }

    @Test
    @DisplayName("Constructor throws exception for empty ward number")
    public void testConstructorEmptyWardNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Inpatient("I002", "Thabo", "Hlolo", 65, "Male", "Surgery", "");
        });
    }

    @Test
    @DisplayName("Constructor throws exception for null ward number")
    public void testConstructorNullWardNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Inpatient("I003", "Thabo", "Hlolo", 65, "Male", "Surgery", null);
        });
    }

    @Test
    @DisplayName("Set ward number updates correctly")
    public void testSetWardNumber() {
        Inpatient patient = new Inpatient("I004", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        patient.setWardNumber("Ward B");

        assertEquals("Ward B", patient.getWardNumber());
    }

    @Test
    @DisplayName("Set bed number updates correctly")
    public void testSetBedNumber() {
        Inpatient patient = new Inpatient("I005", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        patient.setBedNumber("B05");

        assertEquals("B05", patient.getBedNumber());
        assertTrue(patient.hasBed());
    }

    @Test
    @DisplayName("hasBed returns false when bed is not allocated")
    public void testHasBedFalse() {
        Inpatient patient = new Inpatient("I006", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        assertFalse(patient.hasBed());
    }

    @Test
    @DisplayName("hasBed returns true when bed is allocated")
    public void testHasBedTrue() {
        Inpatient patient = new Inpatient("I007", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        patient.setBedNumber("B10");
        assertTrue(patient.hasBed());
    }

    @Test
    @DisplayName("hasBed returns false when bed number is null")
    public void testHasBedNull() {
        Inpatient patient = new Inpatient("I008", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        patient.setBedNumber(null);
        assertFalse(patient.hasBed());
    }

    @Test
    @DisplayName("displayDetails includes ward and bed info")
    public void testDisplayDetails() {
        Inpatient patient = new Inpatient("I009", "Thabo", "Hlolo", 65, "Male", "Surgery", "Ward A");
        String details = patient.displayDetails();

        assertTrue(details.contains("Ward A"));
        assertTrue(details.contains("Not Allocated"));
    }

}
