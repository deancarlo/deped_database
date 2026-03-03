package com.github.deancarlo.deped_database.repository;

import com.github.deancarlo.deped_database.entity.DepedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DepedStudentRepository extends JpaRepository<DepedStudent, Long> {

    Optional<DepedStudent> findByLrnAndFirstNameAndLastNameAndDateOfBirth(
            Long lrn,
            String firstName,
            String lastName,
            LocalDate dateOfBirth
    );
}