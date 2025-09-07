package com.cmed.prescription.service;

import com.cmed.prescription.dto.PrescriptionDTO;
import com.cmed.prescription.entity.PrescriptionEntity;
import com.cmed.prescription.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	
	
	 @Autowired
     private PrescriptionRepository prescriptionRepository;


    @Override
    public List<PrescriptionDTO> getPrescriptions(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            YearMonth now = YearMonth.now();
            startDate = now.atDay(1); // Default to 1st of current month
            endDate = now.atEndOfMonth(); // Default to the last day of current month
        }

        List<PrescriptionEntity> prescriptionEntities = prescriptionRepository
                .findByPrescriptionDateBetweenOrderByPrescriptionDateAsc(startDate, endDate);

        return prescriptionEntities.stream().map(entity -> {
            PrescriptionDTO dto = new PrescriptionDTO();
            dto.setId(entity.getId());
            dto.setPrescriptionDate(entity.getPrescriptionDate());
            dto.setPatientName(entity.getPatientName());
            dto.setPatientAge(entity.getPatientAge());
            dto.setPatientGender(entity.getPatientGender());
            dto.setDiagnosis(entity.getDiagnosis());
            dto.setMedicines(entity.getMedicines());
            dto.setNextVisitDate(entity.getNextVisitDate());
            dto.setCreatedBy(entity.getCreatedBy());
	        dto.setCreatedOn(entity.getCreatedOn());
	        dto.setModifiedBy(entity.getModifiedBy());
	        dto.setModifiedOn(entity.getModifiedOn());
	        dto.setVersion(entity.getVersion());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO getPrescriptionById(Long id) {
        PrescriptionEntity entity = prescriptionRepository.findById(id).orElse(null);
        if (entity == null) return null;

        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setId(entity.getId());
        dto.setPrescriptionDate(entity.getPrescriptionDate());
        dto.setPatientName(entity.getPatientName());
        dto.setPatientAge(entity.getPatientAge());
        dto.setPatientGender(entity.getPatientGender());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setMedicines(entity.getMedicines());
        dto.setNextVisitDate(entity.getNextVisitDate());
        dto.setCreatedBy(entity.getCreatedBy());
	    dto.setCreatedOn(entity.getCreatedOn());
	    dto.setModifiedBy(entity.getModifiedBy());
	    dto.setModifiedOn(entity.getModifiedOn());
	    dto.setVersion(entity.getVersion());
        return dto;
    }

    @Override
    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
        PrescriptionEntity entity = new PrescriptionEntity();
        entity.setPrescriptionDate(prescriptionDTO.getPrescriptionDate());
        entity.setPatientName(prescriptionDTO.getPatientName());
        entity.setPatientAge(prescriptionDTO.getPatientAge());
        entity.setPatientGender(prescriptionDTO.getPatientGender());
        entity.setDiagnosis(prescriptionDTO.getDiagnosis());
        entity.setMedicines(prescriptionDTO.getMedicines());
        entity.setNextVisitDate(prescriptionDTO.getNextVisitDate());

        // Set createdBy and createdOn
        entity.setCreatedBy(prescriptionDTO.getCreatedBy());  // Assuming createdBy comes from DTO
        entity.setCreatedOn(LocalDateTime.now());  // Set current date and time for createdOn

        PrescriptionEntity savedEntity = prescriptionRepository.save(entity);

        // Set the fields in DTO (including audit fields)
        prescriptionDTO.setId(savedEntity.getId());
        prescriptionDTO.setCreatedBy(savedEntity.getCreatedBy());
        prescriptionDTO.setCreatedOn(savedEntity.getCreatedOn());
        prescriptionDTO.setModifiedBy(savedEntity.getModifiedBy());
        prescriptionDTO.setModifiedOn(savedEntity.getModifiedOn());
        prescriptionDTO.setVersion(savedEntity.getVersion());

        return prescriptionDTO;
    }



    @Override
    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
        PrescriptionEntity existingEntity = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        existingEntity.setPrescriptionDate(prescriptionDTO.getPrescriptionDate());
        existingEntity.setPatientName(prescriptionDTO.getPatientName());
        existingEntity.setPatientAge(prescriptionDTO.getPatientAge());
        existingEntity.setPatientGender(prescriptionDTO.getPatientGender());
        existingEntity.setDiagnosis(prescriptionDTO.getDiagnosis());
        existingEntity.setMedicines(prescriptionDTO.getMedicines());
        existingEntity.setNextVisitDate(prescriptionDTO.getNextVisitDate());
        existingEntity.setModifiedBy(prescriptionDTO.getModifiedBy());
        existingEntity.setModifiedOn(LocalDateTime.now());

        PrescriptionEntity updatedEntity = prescriptionRepository.save(existingEntity);

        prescriptionDTO.setId(updatedEntity.getId());
        prescriptionDTO.setCreatedBy(updatedEntity.getCreatedBy());
        prescriptionDTO.setCreatedOn(updatedEntity.getCreatedOn());
        prescriptionDTO.setModifiedBy(updatedEntity.getModifiedBy());
        prescriptionDTO.setModifiedOn(updatedEntity.getModifiedOn());
        prescriptionDTO.setVersion(updatedEntity.getVersion());
        return prescriptionDTO;
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
