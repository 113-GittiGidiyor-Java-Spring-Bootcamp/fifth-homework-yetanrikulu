package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.dto.InstructorDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Instructor;
import com.example.fifthhomeworkyetanrikulu.exception.InstructorIsAlreadyExistException;
import com.example.fifthhomeworkyetanrikulu.mapper.GlobalMapper;
import com.example.fifthhomeworkyetanrikulu.repository.InstructorRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final GlobalMapper globalMapper;


    public List<Instructor> findAll() {
        List<Instructor> instructorList = new ArrayList<>();
        instructorRepository.findAll().forEach(instructorList::add);
        return instructorList;
    }

    public Instructor findById(long id) {
        return instructorRepository.findById(id).get();
    }

    public Instructor save(InstructorDTO instructorDTO) {

        if (instructorRepository.existsByPhoneNumber(instructorDTO.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException();
        }

        Instructor instructor = null;
        if (instructorDTO.getType().equals("PermanentInstructor")){
            instructor = globalMapper.mapToPermanent(instructorDTO);
        }
        if (instructorDTO.getType().equals("VisitingResearcher")){
            instructor = globalMapper.mapToVisiting(instructorDTO);
        }
        return instructor==null? null:instructorRepository.save(instructor);
    }

    public Instructor update(InstructorDTO instructorDTO) {
        return save(instructorDTO);
    }

    public void deleteById(long id) {
        instructorRepository.deleteById(id);
    }

    public void deleteByObject(InstructorDTO instructorDTO) {
        long id = instructorDTO.getId();
        deleteById(id);
    }

    public List<Instructor> findIncludeName(String name) {
        return instructorRepository.findByNameContains(name);
    }

    public void deleteByName(String name) {
        instructorRepository.deleteInstructorByName(name);
    }

}

