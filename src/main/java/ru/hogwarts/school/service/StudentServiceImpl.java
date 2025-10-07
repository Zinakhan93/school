package ru.hogwarts.school.service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for getting total number of students");
      return   studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        logger.info("Was invoked method for finding student by id={}", id);
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student editStudent( Student student) {
        logger.info("Was invoked method for editing student with id={}", student.getId());
        if(!studentRepository.existsById(student.getId())){
            logger.error("Student with id={} not found for editing", student.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Was invoked method for deleting student with id={}", id);
        Student student = findStudent(id);
        if (student == null) {
            logger.warn("Attempt to delete non-existing student with id={}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: ");
        }
        studentRepository.deleteById(id);
    }
    @Override
    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for finding students by age={}", age);
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
        logger.info("Was invoked method for finding students between ages {} and {}", minAge, maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


}
