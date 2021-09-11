package com.example.fifthhomeworkyetanrikulu.exception;

public class CourseIsAlreadyExistException extends RuntimeException {
    public CourseIsAlreadyExistException() {
        super("Course is already exist.");
    }
}
