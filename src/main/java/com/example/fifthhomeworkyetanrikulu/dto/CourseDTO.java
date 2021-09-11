package com.example.fifthhomeworkyetanrikulu.dto;

import com.example.fifthhomeworkyetanrikulu.entity.Instructor;
import com.example.fifthhomeworkyetanrikulu.entity.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Course name is mandatory.")
    private String courseName;
    @NotBlank (message = "Course code is mandatory.")
    private String courseCode;
    @NotNull(message = "Credit score is mandatory.")
    private int creditScore;

    private Instructor instructor;

    private List<Student> studentList = new ArrayList<>();

}
