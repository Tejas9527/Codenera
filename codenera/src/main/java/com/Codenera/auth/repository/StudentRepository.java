package com.Codenera.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Codenera.auth.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
