package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent (Student student);
    Student findStudent (Long id);
    Student editStudent (Long id,Student student);
     void deleteStudent (Long id);
    List<Student> findByAge(int age);

}
