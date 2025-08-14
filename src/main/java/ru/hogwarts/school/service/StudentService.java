package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent (Student student);
    Student findStudent (Long id);
    Student editStudent (Student student);
     void deleteStudent (Long id);
    List<Student> findByAge(int age);


// Добавленные методы
   void deleteAllStudent();

    Collection<Student> findAllStudent();

    //ДЗ 2 по базам данных
    List<Student> findByAgeBetween(int minAge, int maxAge);


}
