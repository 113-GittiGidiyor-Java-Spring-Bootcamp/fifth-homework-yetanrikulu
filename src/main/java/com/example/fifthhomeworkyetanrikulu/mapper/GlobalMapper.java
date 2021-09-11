package com.example.fifthhomeworkyetanrikulu.mapper;

import com.example.fifthhomeworkyetanrikulu.dto.CourseDTO;
import com.example.fifthhomeworkyetanrikulu.dto.InstructorDTO;
import com.example.fifthhomeworkyetanrikulu.dto.StudentDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Course;
import com.example.fifthhomeworkyetanrikulu.entity.PermanentInstructor;
import com.example.fifthhomeworkyetanrikulu.entity.Student;
import com.example.fifthhomeworkyetanrikulu.entity.VisitingResearcher;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

@Mapper
public interface GlobalMapper {

    Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);


    @InheritConfiguration(name = "Instructor")
    PermanentInstructor mapToPermanent(InstructorDTO instructorDTO);

    @InheritConfiguration(name = "Instructor")
    VisitingResearcher mapToVisiting(InstructorDTO instructorDTO);


}

