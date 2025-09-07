package com.cmed.prescription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cmed.prescription.entity.User;
import com.cmed.prescription.repository.UserRepository;

@SpringBootApplication
public class PrescriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrescriptionApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(encoder.encode("password"));
                u.setRole("ROLE_ADMIN");
                userRepo.save(u);
            }
            if (userRepo.findByUsername("doctor").isEmpty()) {
                User u = new User();
                u.setUsername("doctor");
                u.setPassword(encoder.encode("password"));
                u.setRole("ROLE_USER");
                userRepo.save(u);
            }
        };
    }
}
