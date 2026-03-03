package com.github.deancarlo.deped_database.controller;

import com.github.deancarlo.deped_database.repository.DepedStudentRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/deped/api/v1")
public class DepedStudentController {

    private final DepedStudentRepository depedStudentRepository;

    public DepedStudentController(DepedStudentRepository depedStudentRepository) {
        this.depedStudentRepository = depedStudentRepository;
    }

    // ✅ API Health Check
    @GetMapping("/")
    public String healthCheck() {
        return "API is working";
    }

    // ✅ Check LRN Endpoint
    @GetMapping("/check-lrn")
    public String checkLrn(
            @RequestParam Long lrn,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String date_of_birth
    ) {

        LocalDate dob = LocalDate.parse(date_of_birth);

        return depedStudentRepository
                .findByLrnAndFirstNameAndLastNameAndDateOfBirth(
                        lrn,
                        firstname,
                        lastname,
                        dob
                )
                .isPresent()
                ? "Exist"
                : "Student not exist";
    }
}