/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicare.hospital.patient.admission.system;

/**
 *
 * @author Student
 */
public class Ward {
    
    private static final int ROWS = 4;
    private static final int COLUMNS = 5;
    private static final int TOTAL_BEDS = ROWS * COLUMNS;
    private Patient[][] beds;
    
    public Ward() {
        beds = new Patient[ROWS][COLUMNS];
    }

    public String getBedId(int row, int column) {
        int bedNumber = (row * COLUMNS) + column + 1;
        return String.format("B%02d", bedNumber);
    }

    public boolean allocateBed(Inpatient patient) throws NoBedAvailableException {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                if (beds[r][c] == null) {
                    beds[r][c] = patient;
                    String bedId = getBedId(r, c);
                    patient.setBedNumber(bedId);
                    return true;
                }
            }
        }
        throw new NoBedAvailableException("Allocation failed: All beds in the ward are fully occupied.");
    }
    
    public boolean releaseBed(String patientId) throws PatientNotFoundException {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                if (beds[r][c] != null && beds[r][c].getPatientId().equalsIgnoreCase(patientId)) {
                    if (beds[r][c] instanceof Inpatient) {
                        ((Inpatient) beds[r][c]).setBedNumber(null);
                    }
                    beds[r][c] = null;
                    return true;
                }
            }
        }
        throw new PatientNotFoundException("Release failed: Patient ID " + patientId + " is not assigned to any bed.");
    }
    
    //r=rows/ c=column
    public void displayWardLayout() {
        System.out.println("\n--- WARD LAYOUT ---");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                String bedId = getBedId(r, c);
                String status = (beds[r][c] == null) ? "[AVAIL]" : "[OCCUPY]";
                System.out.print(bedId + " " + status + "\t");
            }
            System.out.println();
        }
    }
    
    
    public int getOccupiedCount() {
        int count = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                if (beds[r][c] != null) count++;
            }
        }
        return count;
    }

    public int getAvailableCount() { return TOTAL_BEDS - getOccupiedCount(); }

    public double getOccupancyPercentage() {
        return ((double) getOccupiedCount() / TOTAL_BEDS) * 100;
    }
    
}
