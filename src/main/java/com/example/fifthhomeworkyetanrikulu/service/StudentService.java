package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.dto.StudentDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Student;
import com.example.fifthhomeworkyetanrikulu.exception.StudentAgeNotValidException;
import com.example.fifthhomeworkyetanrikulu.mapper.GlobalMapper;
import com.example.fifthhomeworkyetanrikulu.repository.StudentRepository;
import com.example.fifthhomeworkyetanrikulu.util.DateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GlobalMapper globalMapper;


    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
    }

    public Student findById(long id) {
        return studentRepository.findById(id).get();
    }

    public Student save(StudentDTO studentDTO) {

        Student student = globalMapper.mapFromStudentDTOtoStudent(studentDTO);
        LocalDate birthday = DateFormatter.convertStringToLocalDate(student.getBirthDate());
        long diff = ChronoUnit.YEARS.between(birthday, LocalDate.now());

        if (diff < 18 || diff > 40) {
            throw new StudentAgeNotValidException();
        }
        return studentRepository.save(student);
    }

    public Student update(StudentDTO studentDTO) {
        return save(studentDTO);
    }

    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    public void deleteByObject(StudentDTO studentDTO) {
        long id = studentDTO.getId();
        deleteById(id);
    }

    public List<Student> findIncludeName(String name) {
        return studentRepository.findByNameContains(name);
    }

    public List<?> groupByGender() {
        return studentRepository.groupByGender();
    }

    public void deleteByName(String name) {
        studentRepository.deleteStudentByName(name);
    }
}


