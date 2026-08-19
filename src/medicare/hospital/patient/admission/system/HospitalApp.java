/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medicare.hospital.patient.admission.system;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class HospitalApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static PatientManager manager = new PatientManager();
    private static Ward ward = new Ward();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
             while(true){
            
            System.out.println(" MEDICARE HOSPITAL ADMISSION SYSTEM");
            
            System.out.println("1. Patient Management");
            System.out.println("2. Bed Management");
            System.out.println("3. Reports & Statistics");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
           int choice = getIntInput();
            switch (choice) {
                case 1: patientMenu(); break;
                case 2: bedMenu(); break;
                case 3: reportMenu(); break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Enter a number between 1 and 4.");
            }
        }
    }
    
    private static void patientMenu(){
        
        System.out.println("\n--- PATIENT MANAGEMENT ---");
        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients (Sorted by Surname)");
        System.out.print("Select an option: ");
        
        int choice = getIntInput();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                    
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                    
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                    
                        System.out.print("Enter Age: ");
                        int age = getIntInput();
                    
                        System.out.print("Enter Gender: ");
                        String gender = scanner.nextLine();
                    
                        System.out.print("Enter Medical Condition: ");
                        String condition = scanner.nextLine();

                        System.out.println("Category: 1. INPATIENT  2. OUTPATIENT  3. EMERGENCY");
                        int categoryChoice = getIntInput();
                        
                        PatientCategory category = PatientCategory.values()[Math.max(0, Math.min(2, categoryChoice - 1))];

                        if (category == PatientCategory.INPATIENT) {
                            Inpatient inpatient = new Inpatient(id, firstName, lastName, age, gender, condition, category, 1, null);
                            manager.registerPatient(inpatient);
                            ward.allocateBed(inpatient);
                            
                        } else {
                            Patient patient = new Patient(id, firstName, lastName, age, gender, condition, category);
                            manager.registerPatient(patient);
                        }
                        System.out.println("Patient registered successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Patient ID to Search: ");
                        Patient found = manager.findPatient(scanner.nextLine());
                        found.displayDetails();
                        break;

                    case 3:
                        System.out.print("Enter Patient ID to Update: ");
                        String updateId = scanner.nextLine();
                    
                        System.out.print("Enter New Medical Condition: ");
                        String updateCondition = scanner.nextLine();
                    
                        System.out.print("Enter New Age: ");
                        int updateAge = getIntInput();
                    
                        manager.updatePatient(updateId, updateCondition, updateAge);
                        System.out.println("Patient updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Patient ID to Delete: ");
                        String deleteId = scanner.nextLine();
                    
                        try { ward.releaseBed(deleteId); } catch (Exception ignored) {}
                        manager.deletePatient(deleteId);
                    
                        System.out.println("Patient deleted successfully!");
                        break;

                   case 5:
                        manager.sortBySurname();
                        manager.displayAllPatients();
                        break;

                    default:
                        System.out.println("Invalid selection.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    
    private static void bedMenu(){
         System.out.println("\n--- BED MANAGEMENT ---");
        System.out.println("1. View Ward Layout");
        System.out.println("2. Release Bed");
        System.out.print("Select an option: ");

        int choice = getIntInput();
        try {
            switch (choice) {
                case 1:
                    ward.displayWardLayout();
                    break;
                case 2:
                    System.out.print("Enter Patient ID to Release Bed: ");
                    String id = scanner.nextLine();
                    ward.releaseBed(id);
                    System.out.println("Bed released successfully.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private static void reportMenu(){
        System.out.println("");
        System.out.println("Total Registered Patients: " + manager.getTotalPatients());
        System.out.println("Occupied Beds: " + ward.getOccupiedCount());
        System.out.println("Available Beds: " + ward.getAvailableCount());
        System.out.printf("Ward Occupancy Rate: %.2f%%\n", ward.getOccupancyPercentage());
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid integer. Please try again: ");
            }
        }
    }
    
    
}
