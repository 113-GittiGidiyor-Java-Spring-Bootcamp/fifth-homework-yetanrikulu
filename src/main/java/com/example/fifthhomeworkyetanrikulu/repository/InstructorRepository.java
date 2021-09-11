package com.example.fifthhomeworkyetanrikulu.repository;

import com.example.fifthhomeworkyetanrikulu.entity.Instructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    List<Instructor> findByNameContains(String name);

    @Transactional
    @Modifying
    @Query(value = "delete from Instructor i where i.name=:name")
    void deleteInstructorByName(String name);

    boolean existsByPhoneNumber(String phone);


}