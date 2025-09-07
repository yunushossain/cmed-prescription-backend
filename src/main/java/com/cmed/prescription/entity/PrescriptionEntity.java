package com.cmed.prescription.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import com.cmed.prescription.base.BaseEntity;

@Entity
@Table(name = "prescriptions") // Custom table name
@Getter
@Setter
public class PrescriptionEntity extends BaseEntity {  // Inheriting from BaseEntity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Prescription date is required")
    @Column(name = "prescription_date", nullable = false)
    private LocalDate prescriptionDate;

    @NotBlank(message = "Patient name is required")
    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @NotNull(message = "Patient age is required")
    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 130, message = "Age must be <= 130")
    @Column(name = "patient_age", nullable = false)
    private Integer patientAge;

    @NotNull(message = "Patient gender is required")
    @Column(name = "patient_gender", nullable = false)
    private String patientGender; // Gender select box (must not be null)

    @Column(name = "diagnosis", length = 2000)
    private String diagnosis; // Optional

    @Column(name = "medicines", length = 2000)
    private String medicines; // Optional

    @Column(name = "next_visit_date")
    private LocalDate nextVisitDate; // Optional field
}
