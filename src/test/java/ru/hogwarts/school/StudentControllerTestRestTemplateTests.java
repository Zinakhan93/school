package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerTestRestTemplateTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private Student testStudent;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/student";
        testStudent = new Student();
        testStudent.setName("Harry Potter");
        testStudent.setAge(20);
    }
    @Test
    void createStudent_shouldReturnStudentAndStatus200() {
        ResponseEntity<Student> response = restTemplate.postForEntity(baseUrl, testStudent, Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Harry Potter", response.getBody().getName());
        assertEquals(20, response.getBody().getAge());
    }
    @Test
    void getStudentInfo_shouldReturnStatus404WhenStudentNotFound() {
        ResponseEntity<Student> response = restTemplate.getForEntity(baseUrl + "/999", Student.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void updateStudent_shouldReturnUpdatedStudentAndStatus200() {
        Student createdStudent = restTemplate.postForObject(baseUrl, testStudent, Student.class);
        createdStudent.setName("Ron Weasley");
        createdStudent.setAge(21);

        restTemplate.put(baseUrl, createdStudent);
        ResponseEntity<Student> response = restTemplate.getForEntity(
                baseUrl + "/" + createdStudent.getId(), Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ron Weasley", response.getBody().getName());
        assertEquals(21, response.getBody().getAge());
    }
    @Test
    void deleteStudent_shouldReturnStatus200() {
        Student createdStudent = restTemplate.postForObject(baseUrl, testStudent, Student.class);

        restTemplate.delete(baseUrl + "/" + createdStudent.getId());
        ResponseEntity<Student> response = restTemplate.getForEntity(
                baseUrl + "/" + createdStudent.getId(), Student.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findStudents_shouldReturnStudentsByAgeAndStatus200() {
        restTemplate.postForObject(baseUrl, testStudent, Student.class);

        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                baseUrl + "?age=20", Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void findStudents_shouldReturnEmptyListWhenNoStudentsFound() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                baseUrl + "?age=100", Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }


}
