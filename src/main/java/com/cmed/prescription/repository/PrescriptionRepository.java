package com.cmed.prescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmed.prescription.dto.DayWiseReportDTO;
import com.cmed.prescription.entity.PrescriptionEntity;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {

    List<PrescriptionEntity> findByPrescriptionDateBetweenOrderByPrescriptionDateAsc(LocalDate start, LocalDate end);


    
    @Query("SELECT new com.cmed.prescription.dto.DayWiseReportDTO(p.prescriptionDate, COUNT(p)) " +
    	       "FROM PrescriptionEntity p " +
    	       "WHERE p.prescriptionDate BETWEEN :start AND :end " +
    	       "GROUP BY p.prescriptionDate ORDER BY p.prescriptionDate ASC")
    	List<DayWiseReportDTO> daywiseCountsWithDTO(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
