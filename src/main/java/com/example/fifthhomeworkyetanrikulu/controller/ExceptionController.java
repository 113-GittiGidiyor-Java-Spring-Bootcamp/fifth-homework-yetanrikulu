package com.example.fifthhomeworkyetanrikulu.controller;

import com.example.fifthhomeworkyetanrikulu.entity.ExceptionTransactionLogger;
import com.example.fifthhomeworkyetanrikulu.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/exceptions")
@RequiredArgsConstructor
public class ExceptionController {

    private final ExceptionService exceptionService;

    @GetMapping
    public ResponseEntity<List<ExceptionTransactionLogger>> getAllExceptionTransaction() {
        return new ResponseEntity<>(exceptionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/courseAlreadyExist")
    public ResponseEntity<List<ExceptionTransactionLogger>> getCourseAlreadyExistExceptionTransaction() {
        return new ResponseEntity<>(exceptionService.getCourseAlreadyExistExceptions(), HttpStatus.OK);
    }

    @GetMapping("/instructorAlreadyExist")
    public ResponseEntity<List<ExceptionTransactionLogger>> getInstructorAlreadyExistExceptionTransaction() {
        return new ResponseEntity<>(exceptionService.getInstructorAlreadyExistExceptions(), HttpStatus.OK);
    }

    @GetMapping("/studentAgeNotValid")
    public ResponseEntity<List<ExceptionTransactionLogger>> getStudentAgeNotValidExceptionTransaction() {
        return new ResponseEntity<>(exceptionService.getStudentAgeNotValidExceptions(), HttpStatus.OK);
    }

    @GetMapping("/studentNumberExceed")
    public ResponseEntity<List<ExceptionTransactionLogger>> getStudentNumberExceedExceptionTransaction() {
        return new ResponseEntity<>(exceptionService.getStudentNumberForOneCourseExceededException(), HttpStatus.OK);
    }

    @GetMapping("/getByDate")
    public ResponseEntity<List<ExceptionTransactionLogger>> getExceptionsByDate (@RequestParam String date){
        return new ResponseEntity<>(exceptionService.getExceptionsByDate(date), HttpStatus.OK);

    }


}

