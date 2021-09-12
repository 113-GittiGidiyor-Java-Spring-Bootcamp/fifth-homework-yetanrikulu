package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.mapper.GlobalMapper;
import com.example.fifthhomeworkyetanrikulu.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;
    @Mock
    GlobalMapper globalMapper;

    @InjectMocks
    CourseService courseService;

    @Test
    void findAll(){

    }


}
