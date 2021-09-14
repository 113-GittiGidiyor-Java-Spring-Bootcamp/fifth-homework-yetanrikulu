package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.dto.CourseDTO;
import com.example.fifthhomeworkyetanrikulu.dto.StudentDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Course;
import com.example.fifthhomeworkyetanrikulu.entity.Student;
import com.example.fifthhomeworkyetanrikulu.exception.CourseIsAlreadyExistException;
import com.example.fifthhomeworkyetanrikulu.exception.StudentNumberForOneCourseExceededException;
import com.example.fifthhomeworkyetanrikulu.mapper.GlobalMapper;
import com.example.fifthhomeworkyetanrikulu.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private GlobalMapper globalMapper;

    @InjectMocks
    private CourseService courseService;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;

    @Test
    void findAll() {

        //given
        Course course1 = new Course("Math", "Mat101", 5);
        Course course2 = new Course("Phy", "Phy101", 4);
        Course course3 = new Course("Chem", "Chem101", 4);
        Mockito.when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2, course3));

        //when
        List<Course> courseList = courseService.findAll();

        //then
        assertEquals(3, courseList.size());

    }

    @Test
    void findById() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

        //when
        Course course1 = courseService.findById(1);

        //then
        assertEquals(course.getCourseCode(), course1.getCourseCode());

    }

    @Test
    void save_CourseIsAlreadyExists() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        CourseDTO courseDTO = new CourseDTO(1, "Math", "Mat101", 5, null, null);
        Mockito.when(globalMapper.mapFromCourseDTOtoCourse(courseDTO)).thenReturn(course);
        Mockito.when(courseRepository.existsByCourseCode(Mockito.anyString())).thenReturn(true);

        //when
        RuntimeException exception = assertThrows(CourseIsAlreadyExistException.class, () -> courseService.save(courseDTO));

        //then
        assertEquals("Course is already exist.", exception.getMessage());

    }

    @Test
    void save_CourseIsNOTAlreadyExists() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        CourseDTO courseDTO = new CourseDTO(1, "Math", "Mat101", 5, null, null);
        Mockito.when(globalMapper.mapFromCourseDTOtoCourse(courseDTO)).thenReturn(course);
        Mockito.when(courseRepository.existsByCourseCode(Mockito.anyString())).thenReturn(false);

        //when
        Course course1 = courseService.save(courseDTO);

        //then
        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());
        Course capturedCourse = courseArgumentCaptor.getValue();
        assertEquals(course.getCourseName(), capturedCourse.getCourseName());
        assertEquals(course.getCourseCode(), capturedCourse.getCourseCode());
        assertEquals(course.getCreditScore(), capturedCourse.getCreditScore());

    }

    @Test
    void update() {
        //given
        Course course = new Course("Math", "Mat101", 5);
        CourseDTO courseDTO = new CourseDTO(1, "Math", "Mat101", 5, null, null);
        Mockito.when(globalMapper.mapFromCourseDTOtoCourse(courseDTO)).thenReturn(course);

        //when
        Course course1 = courseService.update(courseDTO);

        //then
        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());
        Course capturedCourse = courseArgumentCaptor.getValue();
        assertEquals(course.getCourseName(), capturedCourse.getCourseName());
        assertEquals(course.getCourseCode(), capturedCourse.getCourseCode());
        assertEquals(course.getCreditScore(), capturedCourse.getCreditScore());

    }

    @Test
    void findIncludeName() {
        //given
        Course course1 = new Course("Math1", "Mat101", 5);
        Course course2 = new Course("Math2", "Mat102", 5);
        Mockito.when(courseRepository.findByCourseNameContains("Mat")).thenReturn(Arrays.asList(course1, course2));

        //when
        List<Course> courseList = courseService.findIncludeName("Mat");

        //then
        assertEquals(2, courseList.size());

    }

    @Test
    void addStudentToCourse_20StudentCourse() {
        //given
        Student student = new Student("ogr", "adr", "1995-03-03", "Male");
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            studentList.add(student);
        }
        Course course = new Course("Math1", "Mat101", 5, studentList);
        StudentDTO studentDTO = new StudentDTO(1l,"og2","adres2","2001-04-04","Female",null);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

        //when
        RuntimeException exception = assertThrows(StudentNumberForOneCourseExceededException.class, () -> courseService.addStudentToCourse(studentDTO, Mockito.anyLong()));


        //then
        assertEquals("Maximum of 20 students can take a course at the same time.", exception.getMessage());


    }

    @Test
    void addStudentToCourse_NOT20StudentCourse() {
        //given
        Student student = new Student("ogr", "adr", "1995-03-03", "Male");
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            studentList.add(student);
        }
        Course course = new Course("Math1", "Mat101", 5, studentList);
        StudentDTO studentDTO = new StudentDTO(1l,"og2","adres2","2001-04-04","Female",null);
        Mockito.when(globalMapper.mapFromStudentDTOtoStudent(studentDTO)).thenReturn(student);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

        //when
        Course actualCourse = courseService.addStudentToCourse(studentDTO,Mockito.anyLong());


        //then
        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());
        Course capturedCourse = courseArgumentCaptor.getValue();
        assertEquals(4,capturedCourse.getStudentList().size());

    }


}
