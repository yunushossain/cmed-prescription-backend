package com.cmed.prescription.service;

import com.cmed.prescription.dto.PrescriptionDTO;
import java.time.LocalDate;
import java.util.List;

public interface PrescriptionService {
    List<PrescriptionDTO> getPrescriptions(LocalDate startDate, LocalDate endDate);
    PrescriptionDTO getPrescriptionById(Long id);
    PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO);
    PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO);
    void deletePrescription(Long id);
}
