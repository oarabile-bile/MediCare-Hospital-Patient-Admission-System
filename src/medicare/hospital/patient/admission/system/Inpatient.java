/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicare.hospital.patient.admission.system;

/**
 *
 * @author Student
 */
public class Inpatient extends Patient {
    
    private int wardNumber;
    private String bedNumber;
    
    public Inpatient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition, PatientCategory category, int WardNumber, String bedNumber){
        
       super(patientId, firstName, lastName, age, gender, medicalCondition, category);
       
       this.wardNumber = wardNumber;
       this.bedNumber = bedNumber;
    }
    
    public int getWardNumber(){ return wardNumber; }
    public void setWardNumber(int wardNumber) { this.wardNumber = wardNumber; }
    
    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }
    
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Ward: " + wardNumber + " | Bed: " + (bedNumber != null ? bedNumber : "Unassigned"));
    }
    
}
