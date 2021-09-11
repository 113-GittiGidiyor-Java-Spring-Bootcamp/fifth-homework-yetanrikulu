package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.entity.ExceptionTransactionLogger;
import com.example.fifthhomeworkyetanrikulu.repository.TransactionLoggerRepository;
import com.example.fifthhomeworkyetanrikulu.util.DateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExceptionService {

    private final TransactionLoggerRepository transactionLoggerRepository;


    public List<ExceptionTransactionLogger> getAll() {
        return (List<ExceptionTransactionLogger>) transactionLoggerRepository.findAll();
    }

    public List<ExceptionTransactionLogger> getCourseAlreadyExistExceptions(){
        return transactionLoggerRepository.findExceptionTransactionLoggerByMessage("Course is already exist.");
    }

    public List<ExceptionTransactionLogger> getInstructorAlreadyExistExceptions(){
        return transactionLoggerRepository.findExceptionTransactionLoggerByMessage("Instructor is already exist");
    }
    public List<ExceptionTransactionLogger> getStudentAgeNotValidExceptions(){
        return transactionLoggerRepository.findExceptionTransactionLoggerByMessage("Student age should be between 18 - 40");
    }
    public List<ExceptionTransactionLogger> getStudentNumberForOneCourseExceededException(){
        return transactionLoggerRepository.findExceptionTransactionLoggerByMessage("Maximum of 20 students can take a course at the same time.");
    }

    public List<ExceptionTransactionLogger> getExceptionsByDate(String date){
        LocalDate localDate = DateFormatter.convertStringToLocalDate(date);
        return transactionLoggerRepository.findExceptionTransactionLoggerByLocalDateAfter(localDate);
    }

}
