package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;


    @Override
    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Long id, Student student) {
        if (!students.containsKey(id)){
            return null;
        }
        students.put(id,student);
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        students.remove(id);

    }
    @Override
    public List<Student> findByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
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
