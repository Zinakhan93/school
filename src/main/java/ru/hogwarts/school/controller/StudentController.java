package ru.hogwarts.school.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> aditStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.editStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteStudent (@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) Integer age) {
        if (age != null) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    //Дополнительные методы для отображения всех и для удаления;
    @GetMapping ("/All")
    public ResponseEntity<Collection<Student>>findAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudent());
    }
    @DeleteMapping ("/All")
    public ResponseEntity<Void>deleteAllStudents(){
        studentService.deleteAllStudent();
        return ResponseEntity.ok().build();
    }
    // Дз 2 по баззам данных
    @GetMapping("/age-between")
    public ResponseEntity<List<Student>> getStudentsByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        List<Student> students = studentService.findByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null || student.getFaculty() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.getFaculty());

    }
    @GetMapping("/count")
    public long getCount() {
        return studentRepository.countAllStudents();
    }
    @GetMapping("/average-age")
    public long gerAverageAge(){
        return studentRepository.avgStudentAge();
    }

    @GetMapping("/latest5")
    public List<Student> getLatestFive() {
        // Можно использовать либо findLatestFiveNative, либо метод с Pageable/derived query
        return studentRepository.findLatestFiveNative();
    }

}


