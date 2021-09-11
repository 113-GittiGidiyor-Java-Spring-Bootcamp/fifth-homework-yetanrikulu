package com.example.fifthhomeworkyetanrikulu.exception;

public class StudentNumberForOneCourseExceededException extends RuntimeException {
    public StudentNumberForOneCourseExceededException() {
        super("Maximum of 20 students can take a course at the same time.");
    }
}
