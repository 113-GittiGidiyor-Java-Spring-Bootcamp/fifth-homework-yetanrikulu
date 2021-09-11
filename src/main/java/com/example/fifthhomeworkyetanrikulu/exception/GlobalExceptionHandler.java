package com.example.fifthhomeworkyetanrikulu.exception;

import com.example.fifthhomeworkyetanrikulu.entity.ExceptionTransactionLogger;
import com.example.fifthhomeworkyetanrikulu.repository.TransactionLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final TransactionLoggerRepository transactionLoggerRepository;

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(StudentAgeNotValidException exception) {
        ExceptionResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        ExceptionTransactionLogger exceptionTransactionLogger = prepareExceptionLogger(exception.getMessage());
        transactionLoggerRepository.save(exceptionTransactionLogger);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(CourseIsAlreadyExistException exception) {
        ExceptionResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        ExceptionTransactionLogger exceptionTransactionLogger = prepareExceptionLogger(exception.getMessage());
        transactionLoggerRepository.save(exceptionTransactionLogger);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(InstructorIsAlreadyExistException exception) {
        ExceptionResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        ExceptionTransactionLogger exceptionTransactionLogger = prepareExceptionLogger(exception.getMessage());
        transactionLoggerRepository.save(exceptionTransactionLogger);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(StudentNumberForOneCourseExceededException exception) {
        ExceptionResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        ExceptionTransactionLogger exceptionTransactionLogger = prepareExceptionLogger(exception.getMessage());
        transactionLoggerRepository.save(exceptionTransactionLogger);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private ExceptionResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        return response;
    }

    private ExceptionTransactionLogger prepareExceptionLogger(String message) {
        ExceptionTransactionLogger exceptionTransactionLogger = new ExceptionTransactionLogger();
        exceptionTransactionLogger.setLocalDate(LocalDate.now());
        exceptionTransactionLogger.setMessage(message);
        return exceptionTransactionLogger;
    }


}
