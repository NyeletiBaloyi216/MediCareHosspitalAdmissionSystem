/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.medicare.Bed;
import com.mycompany.medicare.BedNotAvailableException;
import com.mycompany.medicare.Inpatient;
import com.mycompany.medicare.Ward;
import java.util.List;
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
public class WardTest {
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
        private Ward ward;

    @BeforeEach
    public void setUp() {
        ward = new Ward("Ward A");
    }

    @Test
    @DisplayName("Ward initializes with correct number of beds")
    public void testTotalBeds() {
        assertEquals(20, ward.getTotalBeds());
    }

    @Test
    @DisplayName("Ward starts with all beds available")
    public void testInitiallyAllBedsAvailable() {
        assertEquals(20, ward.getAvailableCount());
        assertEquals(0, ward.getOccupiedCount());
        assertEquals(0.0, ward.getOccupancyPercentage());
    }

    @Test
    @DisplayName("Constructor throws exception for empty ward number")
    public void testConstructorEmptyWardNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ward("");
        });
    }

    @Test
    @DisplayName("Get ward number")
    public void testGetWardNumber() {
        assertEquals("Ward A", ward.getWardNumber());
    }

    @Test
    @DisplayName("Allocate bed to inpatient successfully")
    public void testAllocateBedSuccess() throws BedNotAvailableException {
        Inpatient patient = new Inpatient("I010", "Alice", "Smith", 40, "Female", "Broken Leg", "Ward A");

        ward.allocateBed("B01", patient);

        assertTrue(patient.hasBed());
        assertEquals("B01", patient.getBedNumber());
        assertEquals("Ward A", patient.getWardNumber());
        assertEquals(1, ward.getOccupiedCount());
        assertEquals(19, ward.getAvailableCount());
    }

    @Test
    @DisplayName("Allocate bed throws exception when bed does not exist")
    public void testAllocateBedNotFound() {
        Inpatient patient = new Inpatient("I011", "Bob", "Jones", 50, "Male", "Flu", "Ward A");

        assertThrows(BedNotAvailableException.class, () -> {
            ward.allocateBed("B99", patient);
        });
    }

    @Test
    @DisplayName("Allocate bed throws exception when bed already occupied")
    public void testAllocateBedAlreadyOccupied() throws BedNotAvailableException {
        Inpatient p1 = new Inpatient("I012", "Alice", "Smith", 40, "Female", "Broken Leg", "Ward A");
        Inpatient p2 = new Inpatient("I013", "Bob", "Jones", 50, "Male", "Flu", "Ward A");

        ward.allocateBed("B02", p1);

        assertThrows(BedNotAvailableException.class, () -> {
            ward.allocateBed("B02", p2);
        });
    }

    @Test
    @DisplayName("Allocate bed throws exception when patient already has a bed")
    public void testAllocateBedPatientAlreadyHasBed() throws BedNotAvailableException {
        Inpatient patient = new Inpatient("I014", "Alice", "Smith", 40, "Female", "Broken Leg", "Ward A");

        ward.allocateBed("B03", patient);

        assertThrows(BedNotAvailableException.class, () -> {
            ward.allocateBed("B04", patient);
        });
    }

    @Test
    @DisplayName("Allocate bed throws exception when patient is null")
    public void testAllocateBedNullPatient() {
        assertThrows(BedNotAvailableException.class, () -> {
            ward.allocateBed("B05", null);
        });
    }

    @Test
    @DisplayName("Release bed successfully")
    public void testReleaseBedSuccess() throws BedNotAvailableException {
        Inpatient patient = new Inpatient("I015", "Alice", "Smith", 40, "Female", "Broken Leg", "Ward A");

        ward.allocateBed("B06", patient);
        assertTrue(patient.hasBed());

        ward.releaseBed("B06");

        assertFalse(patient.hasBed());
        assertEquals("Not Allocated", patient.getBedNumber());
        assertEquals("Not Allocated", patient.getWardNumber());
        assertEquals(20, ward.getAvailableCount());
    }

    @Test
    @DisplayName("Release bed throws exception when bed does not exist")
    public void testReleaseBedNotFound() {
        assertThrows(BedNotAvailableException.class, () -> {
            ward.releaseBed("B99");
        });
    }

    @Test
    @DisplayName("Release bed throws exception when bed already available")
    public void testReleaseBedAlreadyAvailable() {
        assertThrows(BedNotAvailableException.class, () -> {
            ward.releaseBed("B07");
        });
    }

    @Test
    @DisplayName("Get available beds returns correct list")
    public void testGetAvailableBeds() throws BedNotAvailableException {
        Inpatient patient = new Inpatient("I016", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");

        List<Bed> availableBefore = ward.getAvailableBeds();
        assertEquals(20, availableBefore.size());

        ward.allocateBed("B08", patient);

        List<Bed> availableAfter = ward.getAvailableBeds();
        assertEquals(19, availableAfter.size());
    }

    @Test
    @DisplayName("Get occupied beds returns correct list")
    public void testGetOccupiedBeds() throws BedNotAvailableException {
        Inpatient patient = new Inpatient("I017", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");

        List<Bed> occupiedBefore = ward.getOccupiedBeds();
        assertEquals(0, occupiedBefore.size());

        ward.allocateBed("B09", patient);

        List<Bed> occupiedAfter = ward.getOccupiedBeds();
        assertEquals(1, occupiedAfter.size());
        assertEquals("B09", occupiedAfter.get(0).getBedNumber());
    }

    @Test
    @DisplayName("Occupancy percentage calculates correctly")
    public void testOccupancyPercentage() throws BedNotAvailableException {
        Inpatient p1 = new Inpatient("I018", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");
        Inpatient p2 = new Inpatient("I019", "Bob", "Jonasi", 50, "Male", "Flu", "Ward A");

        assertEquals(0.0, ward.getOccupancyPercentage());

        ward.allocateBed("B10", p1);
        assertEquals(5.0, ward.getOccupancyPercentage()); 

        ward.allocateBed("B11", p2);
        assertEquals(10.0, ward.getOccupancyPercentage()); 
    }

    @Test
    @DisplayName("Display ward layout does not throw exception")
    public void testDisplayWardLayout() {
       
        assertDoesNotThrow(() -> ward.displayWardLayout());
    }
}


