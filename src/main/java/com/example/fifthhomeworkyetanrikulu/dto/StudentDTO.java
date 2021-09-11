package com.example.fifthhomeworkyetanrikulu.dto;

import com.example.fifthhomeworkyetanrikulu.entity.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Name is mandatory.")
    private String name;
    private String address;

    @ApiModelProperty(example = "yyyy-mm-dd")
    @NotNull(message = "Birthdate is mandatory.")
    private String birthDate;

    @NotBlank(message = "Gender is mandatory.")
    private String gender;
    private List<Course> courseList;

}
