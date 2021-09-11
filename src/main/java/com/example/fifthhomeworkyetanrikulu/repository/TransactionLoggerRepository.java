package com.example.fifthhomeworkyetanrikulu.repository;

import com.example.fifthhomeworkyetanrikulu.entity.ExceptionTransactionLogger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionLoggerRepository extends CrudRepository<ExceptionTransactionLogger,Long> {

    List<ExceptionTransactionLogger> findExceptionTransactionLoggerByMessage(String message);

    List<ExceptionTransactionLogger> findExceptionTransactionLoggerByLocalDateAfter(LocalDate localDate);

}
