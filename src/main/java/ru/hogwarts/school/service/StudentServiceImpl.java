package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;

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
    public Student editStudent(Long id, Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);

    }
    @Override
    public List<Student> findByAge(int age) {
       return studentRepository.findByAge(age);
    }
    /*Фильтрация по возрасту ( второй способ )
	public List<Student> findByAge(int age) {
	    List<Student> result = new ArrayList<>();
	    for (Student s : students.values()) {
	        if (s.getAge() == age) result.add(s);
	    }
	    return result;
	}*/
}
