package com.cmed.prescription.service;

import com.cmed.prescription.dto.DayWiseReportDTO;
import com.cmed.prescription.repository.PrescriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
	
	@Autowired
    private  PrescriptionRepository prescriptionRepository;

    public ReportService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    // Method to fetch day-wise prescription count using DTO
    public List<DayWiseReportDTO> getDayWiseReport(LocalDate startDate, LocalDate endDate) {
        return prescriptionRepository.daywiseCountsWithDTO(startDate, endDate);  // Return DTO
    }
}  