package com.cmed.prescription.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/rxnav")
public class RxNavController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/interactions/{rxcui}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> interactions(@PathVariable String rxcui) {
        if (rxcui == null || rxcui.isBlank()) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of("message", "Missing rxcui")
            );
        }

        String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=" + rxcui.trim();
        var external = restTemplate.getForEntity(URI.create(url), String.class);

        return ResponseEntity
                .status(external.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(external.getBody());
    }
}
