package com.example.fifthhomeworkyetanrikulu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstructorDTO {

    @ApiModelProperty(hidden = true)
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private double fixedSalary;
    private double hourlySalary;
    private String type;

}
