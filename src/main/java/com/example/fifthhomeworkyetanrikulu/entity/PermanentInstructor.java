package com.example.fifthhomeworkyetanrikulu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructor extends Instructor {
    private double fixedSalary;

    public PermanentInstructor(String name, String address, String phoneNumber, double fixedSalary) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setFixedSalary(fixedSalary);
    }
    @Override
    public double getSalary(){
        return this.fixedSalary;
    }
    @Override
    public double calculateNewSalary(double percent){
        this.fixedSalary = this.fixedSalary * (1+(percent/100));
        return this.fixedSalary;
    }

}

