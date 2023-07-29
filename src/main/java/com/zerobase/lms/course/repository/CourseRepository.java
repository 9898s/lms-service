package com.zerobase.lms.course.repository;

import com.zerobase.lms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findByCategoryId(long categoryId);
}
