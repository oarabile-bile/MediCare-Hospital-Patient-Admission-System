/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package medicare.hospital.patient.admission.system;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Student
 */

public class HospitalAppTest {

    private PatientManager manager;
    private Ward ward;

    @Before
    public void setUp() {
        manager = new PatientManager();
        ward = new Ward();
    }

    @Test
    public void testRegisterAndFindPatient() throws DuplicatePatientException, PatientNotFoundException {
        Patient p = new Patient("P001", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        manager.registerPatient(p);

        Patient result = manager.findPatient("P001");
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test(expected = DuplicatePatientException.class)
    public void testDuplicatePatientException() throws DuplicatePatientException {
        Patient p1 = new Patient("P001", "John", "Doe", 30, "Male", "Flu", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "Jane", "Smith", 25, "Female", "Cold", PatientCategory.OUTPATIENT);

        manager.registerPatient(p1);
        manager.registerPatient(p2);
    }

    @Test
    public void testBedAllocationAndRelease() throws NoBedAvailableException, PatientNotFoundException {
        Inpatient inp = new Inpatient("P002", "Alice", "Brown", 45, "Female", "Surgery", PatientCategory.INPATIENT, 1, "Bed-1");

        assertTrue(ward.allocateBed(inp));
        assertEquals(1, ward.getOccupiedCount());

        assertTrue(ward.releaseBed("P002"));
        assertEquals(0, ward.getOccupiedCount());
    }

    @Test(expected = NoBedAvailableException.class)
    public void testWardCapacityLimit() throws NoBedAvailableException {
        for (int i = 1; i <= 20; i++) {
            Inpatient p = new Inpatient("P" + i, "Test", "User", 20, "M", "Condition", PatientCategory.INPATIENT, 1, "Bed-" + i);
            ward.allocateBed(p);
        }

        assertEquals(20, ward.getOccupiedCount());
        Inpatient extra = new Inpatient("P21", "Overflow", "User", 20, "M", "Condition", PatientCategory.INPATIENT, 1, "Bed-21");
        ward.allocateBed(extra);
    }
}