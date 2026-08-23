/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.medicare.MediCare;
import com.mycompany.medicare.PatientCategory;
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
public class MediCareTest {
    
    public MediCareTest() {
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
 @Test
    @DisplayName("Create patient with valid data")
    public void testValidConstructor() {
        MediCare patient = new MediCare("P001", "John", "Doj", 30, "Male", "Flu", PatientCategory.OUTPATIENT);

        assertEquals("P001", patient.getPatientId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doj", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Flu", patient.getMedicalCondition());
        assertEquals(PatientCategory.OUTPATIENT, patient.getCategory());
    }

    @Test
    @DisplayName("Constructor trims whitespace from inputs")
    public void testConstructorTrimsWhitespace() {
        MediCare patient = new MediCare("  P002  ", "  Jane  ", "  Smith  ", 25, "  Female  ", "  stabbed ", PatientCategory.EMERGENCY);

        assertEquals("P002", patient.getPatientId());
        assertEquals("Jane", patient.getFirstName());
        assertEquals("Smith", patient.getLastName());
    }

    @Test
    @DisplayName("Constructor throws exception for empty patient ID")
    public void testConstructorEmptyPatientId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("", "John", "Doj", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for null patient ID")
    public void testConstructorNullPatientId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare(null, "John", "Doj", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for empty first name")
    public void testConstructorEmptyFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P003", "", "Doj", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for empty last name")
    public void testConstructorEmptyLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P004", "John", "", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for negative age")
    public void testConstructorNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P005", "John", "Doj", -1, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for age over 120")
    public void testConstructorAgeOver120() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P006", "John", "Doj", 121, "Male", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for empty gender")
    public void testConstructorEmptyGender() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P007", "John", "Doj", 30, "", "Flu", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for empty medical condition")
    public void testConstructorEmptyCondition() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P008", "John", "Doe", 30, "Male", "", PatientCategory.OUTPATIENT);
        });
    }

    @Test
    @DisplayName("Constructor throws exception for null category")
    public void testConstructorNullCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MediCare("P009", "John", "Doe", 30, "Male", "Flu", null);
        });
    }

    @Test
    @DisplayName("Setters update values correctly")
    public void testSetters() {
        MediCare patient = new MediCare("P010", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);

        patient.setFirstName("Jonathan");
        patient.setLastName("Smith");
        patient.setAge(31);
        patient.setGender("Female");
        patient.setMedicalCondition("Recovered");
        patient.setCategory(PatientCategory.INPATIENT);

        assertEquals("Jonathan", patient.getFirstName());
        assertEquals("Smith", patient.getLastName());
        assertEquals(31, patient.getAge());
        assertEquals("Female", patient.getGender());
        assertEquals("Recovered", patient.getMedicalCondition());
        assertEquals(PatientCategory.INPATIENT, patient.getCategory());
    }

    @Test
    @DisplayName("Setter throws exception for invalid age")
    public void testSetterInvalidAge() {
        MediCare patient = new MediCare("P011", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);

        assertThrows(IllegalArgumentException.class, () -> {
            patient.setAge(150);
        });
    }

    @Test
    @DisplayName("displayDetails returns formatted string")
    public void testDisplayDetails() {
        MediCare patient = new MediCare("P012", "Alice", "Wonder", 25, "Female", "Cold", PatientCategory.EMERGENCY);
        String details = patient.displayDetails();

        assertTrue(details.contains("P012"));
        assertTrue(details.contains("Alice Wonder"));
        assertTrue(details.contains("25"));
        assertTrue(details.contains("Female"));
        assertTrue(details.contains("EMERGENCY"));
        assertTrue(details.contains("Cold"));
    }

    @Test
    @DisplayName("toString calls displayDetails")
    public void testToString() {
        MediCare patient = new MediCare("P013", "Bob", "Marley", 35, "Male", "Fever", PatientCategory.OUTPATIENT);
        assertEquals(patient.displayDetails(), patient.toString());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
