package com.example.fifthhomeworkyetanrikulu.repository;

import com.example.fifthhomeworkyetanrikulu.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByCourseNameContains(String name);

    @Transactional
    @Modifying
    @Query(value = "delete from Course c where c.courseName=:name")
    void deleteCourseByCourseName(String name);

    boolean existsByCourseCode(String courseCode);

}
