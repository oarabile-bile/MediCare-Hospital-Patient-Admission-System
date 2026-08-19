/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicare.hospital.patient.admission.system;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author Student
 */
public class PatientManager {
    
    private ArrayList<Patient> patients;

    public PatientManager() {
        patients = new ArrayList<>();
    }

    public void registerPatient(Patient patient) throws DuplicatePatientException {
        if (findPatientOptional(patient.getPatientId()) != null) {
            throw new DuplicatePatientException("Patient ID " + patient.getPatientId() + " already exists.");
        }
        patients.add(patient);
    }

    public Patient findPatient(String patientId) throws PatientNotFoundException {
        Patient patient = findPatientOptional(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient ID " + patientId + " was not found.");
        }
        return patient;
    }

    private Patient findPatientOptional(String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equalsIgnoreCase(patientId)) return patient;
        }
        return null;
    }

    public void updatePatient(String patientId, String newCondition, int newAge) throws PatientNotFoundException {
        Patient patient = findPatient(patientId);
        patient.setMedicalCondition(newCondition);
        patient.setAge(newAge);
    }

    public void deletePatient(String patientId) throws PatientNotFoundException {
        Patient patient = findPatient(patientId);
        patients.remove(patient);
    }

    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No registered patients found.");
            return;
        }
        System.out.println("\n--- REGISTERED PATIENTS ---");
        for (Patient p : patients) {
            p.displayDetails();
            System.out.println("---------------------------");
        }
    }

    public void sortBySurname() {
        Collections.sort(patients, Comparator.comparing(Patient::getLastName));
    }

    public void sortById() {
        Collections.sort(patients, Comparator.comparing(Patient::getPatientId));
    }

    public ArrayList<Patient> getPatients() { return patients; }
    public int getTotalPatients() { return patients.size(); }
    
}
