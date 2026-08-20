# MediCare Hospital Patient Admission System

A Java-based hospital management application designed to handle patient registration, category tracking, and ward bed allocations using object-oriented principles and custom exception handling.

## Features

- **Patient Management**: Register, search, update, remove outpatient and inpatient records.
- **Ward & Bed Allocation**: Track ward capacities and assign/release beds dynamically.
- **Custom Exceptions**: Robust error handling for edge cases such as duplicate patients, non-existent records and full ward capacities.
- **Unit Testing**: Automated test coverage using JUnit 4 for reliable verification of core logic.

## Project Structure

```text
src/
└── medicare/hospital/patient/admission/system/
    ├── Patient.java                  # Base patient entity model
    ├── Inpatient.java                # Child class extending Patient with ward details
    ├── PatientCategory.java          # Enum defining patient categories (e.g., INPATIENT, OUTPATIENT)
    ├── PatientManager.java           # Business logic for managing patient records
    ├── Ward.java                     # Bed allocation and capacity management logic
    ├── DuplicatePatientException.java# Custom exception for duplicate ID handling
    ├── PatientNotFoundException.java # Custom exception for missing record queries
    └── NoBedAvailableException.java  # Custom exception for ward capacity limits
test/
└── medicare/hospital/patient/admission/system/
    └── HospitalAppTest.java          # JUnit 4 test suite verifying system workflows
