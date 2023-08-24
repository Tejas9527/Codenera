package com.Codenera.auth.validator;

import com.Codenera.auth.model.Student;
import com.Codenera.auth.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Autowired
    private StudentService studentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (student.getUsername().length() < 6 || student.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.studentForm.username");
        }
        if (studentService.findByUsername(student.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.studentForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (student.getPassword().length() < 8 || student.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.studentForm.password");
        }

        
    }
}

