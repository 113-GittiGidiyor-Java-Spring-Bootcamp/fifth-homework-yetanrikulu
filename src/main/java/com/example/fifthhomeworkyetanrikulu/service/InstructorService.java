package com.example.fifthhomeworkyetanrikulu.service;

import com.example.fifthhomeworkyetanrikulu.dto.InstructorDTO;
import com.example.fifthhomeworkyetanrikulu.entity.Instructor;
import com.example.fifthhomeworkyetanrikulu.entity.SalaryChangeTransaction;
import com.example.fifthhomeworkyetanrikulu.exception.InstructorIsAlreadyExistException;
import com.example.fifthhomeworkyetanrikulu.mapper.GlobalMapper;
import com.example.fifthhomeworkyetanrikulu.repository.InstructorRepository;
import com.example.fifthhomeworkyetanrikulu.repository.SalaryChangeTransactionRepository;
import com.example.fifthhomeworkyetanrikulu.util.DateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final GlobalMapper globalMapper;
    private final SalaryChangeTransactionRepository salaryChangeTransactionRepository;


    public List<Instructor> findAll() {
        List<Instructor> instructorList = new ArrayList<>();
        instructorRepository.findAll().forEach(instructorList::add);
        return instructorList;
    }

    public Instructor findById(long id) {
        return instructorRepository.findById(id).get();
    }

    public Instructor save(InstructorDTO instructorDTO) {

        if (instructorRepository.existsByPhoneNumber(instructorDTO.getPhoneNumber())) {
            throw new InstructorIsAlreadyExistException();
        }

        Instructor instructor = null;
        if (instructorDTO.getType().equals("PermanentInstructor")) {
            instructor = globalMapper.mapToPermanent(instructorDTO);
        }
        if (instructorDTO.getType().equals("VisitingResearcher")) {
            instructor = globalMapper.mapToVisiting(instructorDTO);
        }
        return instructor == null ? null : instructorRepository.save(instructor);
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


    public Instructor changeSalary(long id, double percent, HttpServletRequest request) {

        Instructor instructor = findById(id);

        SalaryChangeTransaction salaryChangeTransaction = new SalaryChangeTransaction();
        salaryChangeTransaction.setInstructorId(instructor.getId());
        salaryChangeTransaction.setClient(request.getRemoteAddr());
        salaryChangeTransaction.setPreviousSalary(instructor.getSalary());
        instructor.calculateNewSalary(percent);
        salaryChangeTransaction.setNewSalary(instructor.getSalary());
        salaryChangeTransaction.setChangePercent(percent);
        salaryChangeTransaction.setTime(LocalDate.now());

        salaryChangeTransactionRepository.save(salaryChangeTransaction);

        return instructor;
    }

    public List<SalaryChangeTransaction> findSalaryChangesByInstructorId(long id) {
        return salaryChangeTransactionRepository.findByInstructorId(id);
    }
    public List<SalaryChangeTransaction> findSalaryChangesByDate(String date) {
        LocalDate localDate = DateFormatter.convertStringToLocalDate(date);
        return salaryChangeTransactionRepository.findByTimeAfter(localDate);
    }

}

