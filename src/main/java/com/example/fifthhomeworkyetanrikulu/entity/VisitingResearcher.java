package com.example.fifthhomeworkyetanrikulu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcher extends Instructor {
    private double hourlySalary;

    public VisitingResearcher(String name, String address, String phoneNumber, double hourlySalary) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setHourlySalary(hourlySalary);
    }
    @Override
    public double getSalary(){
        return this.hourlySalary;
    }
    @Override
    public double calculateNewSalary(double percent){
        this.hourlySalary = this.hourlySalary * (1+(percent/100));
        return this.hourlySalary;
    }

}

