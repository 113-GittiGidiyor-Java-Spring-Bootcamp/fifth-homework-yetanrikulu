package com.example.fifthhomeworkyetanrikulu.controller;

import com.example.fifthhomeworkyetanrikulu.dto.CourseDTO;
import com.example.fifthhomeworkyetanrikulu.dto.StudentDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Course;
import com.example.fifthhomeworkyetanrikulu.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable int id) {
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.save(courseDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.update(courseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        courseService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByObject(@RequestBody CourseDTO courseDTO) {
        courseService.deleteByObject(courseDTO);
    }

    @GetMapping("/nameInclude/{name}")
    public List<Course> findIncludeName(@PathVariable String name) {
        return courseService.findIncludeName(name);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteByName(@PathVariable String name) {
        courseService.deleteByName(name);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Course> addStudentToCourse(@RequestBody StudentDTO studentDTO, @RequestParam long courseId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentDTO, courseId), HttpStatus.OK);
    }


}
