package com.example.fifthhomeworkyetanrikulu.repository;

import com.example.fifthhomeworkyetanrikulu.entity.SalaryChangeTransaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalaryChangeTransactionRepository extends CrudRepository<SalaryChangeTransaction,Long> {

    List<SalaryChangeTransaction> findByInstructorId(long id);

    List<SalaryChangeTransaction> findByTimeAfter(LocalDate date);
}
