package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);
    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByAgeBetween(int minAge, int maxAge);


    //   Запросы напрямую в базу данных через URL
    //   JPQL: подсчитать количество студентов
    @Query("SELECT COUNT(s) FROM Student s")
    long countAllStudents();
    //
    @Query ("SELECT AVG (age) FROM Student")
    long avgStudentAge();
    // 3) Получение пяти последних студентов (по id DESC) через нативный SQL (LIMIT 5)
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLatestFiveNative();




}
