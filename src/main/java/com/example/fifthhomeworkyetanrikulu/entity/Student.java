package com.example.fifthhomeworkyetanrikulu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    private String birthDate;
    private String gender;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Course> courseList = new ArrayList<>();

    public Student(String name, String address, String birthDate, String gender) {
        setName(name);
        setAddress(address);
        setBirthDate(birthDate);
        setGender(gender);
    }

}

