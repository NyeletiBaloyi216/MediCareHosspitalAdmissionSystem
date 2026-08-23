/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.medicare.BedNotAvailableException;
import com.mycompany.medicare.DuplicatePatientException;
import com.mycompany.medicare.PatientNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author baloy
 */
public class ExceptionTest {
    
    public ExceptionTest() {
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
    @DisplayName("DuplicatePatientException stores message")
    public void testDuplicatePatientException() {
        DuplicatePatientException ex = new DuplicatePatientException("Duplicate ID found");
        assertEquals("Duplicate ID found", ex.getMessage());
    }

    @Test
    @DisplayName("PatientNotFoundException stores message")
    public void testPatientNotFoundException() {
        PatientNotFoundException ex = new PatientNotFoundException("Patient missing");
        assertEquals("Patient missing", ex.getMessage());
    }

    @Test
    @DisplayName("BedNotAvailableException stores message")
    public void testBedNotAvailableException() {
        BedNotAvailableException ex = new BedNotAvailableException("Bed taken");
        assertEquals("Bed taken", ex.getMessage());
    }

    @Test
    @DisplayName("Exceptions extend Exception class")
    public void testExceptionHierarchy() {
        assertTrue(new DuplicatePatientException("test") instanceof Exception);
        assertTrue(new PatientNotFoundException("test") instanceof Exception);
        assertTrue(new BedNotAvailableException("test") instanceof Exception);
    }

}
