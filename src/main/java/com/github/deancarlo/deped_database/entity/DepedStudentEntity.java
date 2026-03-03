package com.github.deancarlo.deped_database.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deped_student_database")
public class DepedStudent {

    @Id
    @Column(name = "lrn")
    private Long lrn;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "grade_level", nullable = false)
    private Integer gradeLevel;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "status", nullable = false)
    private String status = "Active";

    @Column(name = "gender", nullable = false)
    private String gender;

    // Constructors
    public DepedStudent() {}

    public DepedStudent(Long lrn, String firstName, String lastName,
                        LocalDate dateOfBirth, Integer gradeLevel,
                        String schoolName, String status, String gender) {
        this.lrn = lrn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gradeLevel = gradeLevel;
        this.schoolName = schoolName;
        this.status = status;
        this.gender = gender;
    }

    // Getters and Setters
    public Long getLrn() { return lrn; }
    public void setLrn(Long lrn) { this.lrn = lrn; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Integer getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}