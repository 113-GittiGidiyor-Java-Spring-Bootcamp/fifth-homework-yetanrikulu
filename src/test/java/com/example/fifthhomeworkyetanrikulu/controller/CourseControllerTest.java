package com.example.fifthhomeworkyetanrikulu.controller;

import com.example.fifthhomeworkyetanrikulu.dto.CourseDTO;
import com.example.fifthhomeworkyetanrikulu.dto.StudentDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Course;
import com.example.fifthhomeworkyetanrikulu.entity.Student;
import com.example.fifthhomeworkyetanrikulu.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void findAll() {
        //given
        Course course1 = new Course("Math", "Mat101", 5);
        Course course2 = new Course("Phy", "Phy101", 4);
        Course course3 = new Course("Chem", "Chem101", 4);
        Mockito.when(courseService.findAll()).thenReturn(Arrays.asList(course1,course2,course3));

        //when
        ResponseEntity<List<Course>> responseEntity = courseController.findAll();

        //then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(3,responseEntity.getBody().size());
    }

    @Test
    void findById() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        Mockito.when(courseService.findById(Mockito.anyLong())).thenReturn(course);

        //when
        ResponseEntity<Course> responseEntity = courseController.findById(3L);

        //then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(course.getCourseCode(),responseEntity.getBody().getCourseCode());
    }

    @Test
    void save() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        Mockito.when(courseService.save(Mockito.any())).thenReturn(course);

        //when
        ResponseEntity<Course> responseEntity = courseController.save(new CourseDTO(1L,"Math","Mat101",5,null,null));

        //then
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals(course.getCourseCode(),responseEntity.getBody().getCourseCode());
    }

    @Test
    void update() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        Mockito.when(courseService.update(Mockito.any())).thenReturn(course);

        //when
        ResponseEntity<Course> responseEntity = courseController.update(new CourseDTO(1L,"Math","Mat101",5,null,null));

        //then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(course.getCourseCode(),responseEntity.getBody().getCourseCode());
    }

    @Test
    void addStudentToCourse(){
        //given
        Student student = new Student("Ahmet","adres","2002-11-11","Male");
        Course course = new Course("Math", "Mat101", 5,Arrays.asList(student));
        Mockito.when(courseService.addStudentToCourse(Mockito.any(),Mockito.anyLong())).thenReturn(course);

        //when
        ResponseEntity<Course> responseEntity = courseController.addStudentToCourse(Mockito.any(),Mockito.anyLong());

        //then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(Arrays.asList(student),responseEntity.getBody().getStudentList());

    }

}
