package ru.hogwarts.school.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
      return   studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student editStudent( Student student) {
        if(!studentRepository.existsById(student.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findStudent(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: ");
        }
        studentRepository.deleteById(id);
    }
    @Override
    public List<Student> findByAge(int age) {
       return studentRepository.findByAge(age);
    }


    // Дополнительные методы для отображения всех и для удаления
    @Override
    public Collection<Student> findAllStudent(){
        return studentRepository.findAll();
    }
    @Override
    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }

    // ДЗ 2 по базам данных

    @Override
    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


}
