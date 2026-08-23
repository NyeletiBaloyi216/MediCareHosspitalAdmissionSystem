/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.medicare.Bed;
import com.mycompany.medicare.BedNotAvailableException;
import com.mycompany.medicare.Inpatient;
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
public class BedTest {
    
    public BedTest() {
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
    @DisplayName("Create bed with valid number")
    public void testValidConstructor() {
        Bed bed = new Bed("B01");

        assertEquals("B01", bed.getBedNumber());
        assertFalse(bed.isOccupied());
        assertNull(bed.getOccupant());
    }

    @Test
    @DisplayName("Constructor converts bed number to uppercase")
    public void testConstructorUppercase() {
        Bed bed = new Bed("b02");
        assertEquals("B02", bed.getBedNumber());
    }

    @Test
    @DisplayName("Constructor throws exception for empty bed number")
    public void testConstructorEmptyBedNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bed("");
        });
    }

    @Test
    @DisplayName("Constructor throws exception for null bed number")
    public void testConstructorNullBedNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bed(null);
        });
    }

    @Test
    @DisplayName("Occupy bed with patient successfully")
    public void testOccupySuccess() throws BedNotAvailableException {
        Bed bed = new Bed("B03");
        Inpatient patient = new Inpatient("I020", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");

        bed.occupy(patient);

        assertTrue(bed.isOccupied());
        assertEquals(patient, bed.getOccupant());
    }

    @Test
    @DisplayName("Occupy bed throws exception when already occupied")
    public void testOccupyAlreadyOccupied() throws BedNotAvailableException {
        Bed bed = new Bed("B04");
        Inpatient p1 = new Inpatient("I021", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");
        Inpatient p2 = new Inpatient("I022", "Bob", "Jonasi", 50, "Male", "Flu", "Ward A");

        bed.occupy(p1);

        assertThrows(BedNotAvailableException.class, () -> {
            bed.occupy(p2);
        });
    }

    @Test
    @DisplayName("Occupy bed throws exception when patient is null")
    public void testOccupyNullPatient() {
        Bed bed = new Bed("B05");

        assertThrows(BedNotAvailableException.class, () -> {
            bed.occupy(null);
        });
    }

    @Test
    @DisplayName("Vacate bed clears occupant")
    public void testVacate() throws BedNotAvailableException {
        Bed bed = new Bed("B06");
        Inpatient patient = new Inpatient("I023", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");

        bed.occupy(patient);
        assertTrue(bed.isOccupied());

        bed.vacate();

        assertFalse(bed.isOccupied());
        assertNull(bed.getOccupant());
    }

    @Test
    @DisplayName("toString shows available when empty")
    public void testToStringAvailable() {
        Bed bed = new Bed("B07");
        assertEquals("B07 [AVAILABLE]", bed.toString());
    }

    @Test
    @DisplayName("toString shows occupied with patient name")
    public void testToStringOccupied() throws BedNotAvailableException {
        Bed bed = new Bed("B08");
        Inpatient patient = new Inpatient("I024", "Alice", "Sese", 40, "Female", "Broken Leg", "Ward A");

        bed.occupy(patient);

        String result = bed.toString();
        assertTrue(result.contains("OCCUPIED"));
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Sese"));
    }

}
