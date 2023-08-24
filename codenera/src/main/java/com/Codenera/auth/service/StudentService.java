package com.Codenera.auth.service;

import com.Codenera.auth.model.Student;

public interface StudentService {
	void save(Student student);
    Student findByUsername(String username);
}
