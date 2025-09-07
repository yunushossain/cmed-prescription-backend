package com.cmed.prescription.controller;

import com.cmed.prescription.dto.PrescriptionDTO;
import com.cmed.prescription.service.PrescriptionService;
import com.cmed.prescription.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prescriptions")
public class PrescriptionController {

  
    
    @Autowired
	private PrescriptionService prescriptionService;

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(
        @RequestParam(name = "startDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

        @RequestParam(name = "endDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getPrescriptions(startDate, endDate);

        Response response = new Response();
        response.setSuccess(true);
        response.setMessage("Data fetched successfully");
        response.setObj(prescriptions);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id") Long id) {
        PrescriptionDTO prescriptionDTO = prescriptionService.getPrescriptionById(id);

        Response response = new Response();
        if (prescriptionDTO != null) {
            response.setSuccess(true);
            response.setMessage("Prescription found");
            response.setObj(prescriptionDTO);
            return ResponseEntity.ok(response);
        } else {
            response.setSuccess(false);
            response.setMessage("Prescription not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO createdPrescription = prescriptionService.createPrescription(prescriptionDTO);

        Response response = new Response();
        response.setSuccess(true);
        response.setMessage("Prescription created successfully");
        response.setObj(createdPrescription);
        response.setId(createdPrescription.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(
        @PathVariable("id") Long id,
        @RequestBody PrescriptionDTO prescriptionDTO) {
        
        PrescriptionDTO updatedPrescription = prescriptionService.updatePrescription(id, prescriptionDTO);

        Response response = new Response();
        if (updatedPrescription != null) {
            response.setSuccess(true);
            response.setMessage("Prescription updated successfully");
            response.setObj(updatedPrescription);
            return ResponseEntity.ok(response);
        } else {
            response.setSuccess(false);
            response.setMessage("Prescription not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        prescriptionService.deletePrescription(id);

        Response response = new Response();
        response.setSuccess(true);
        response.setMessage("Prescription deleted successfully");
        response.setObj(id);  

        return ResponseEntity.ok(response); 
    }


}
