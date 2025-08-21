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
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FacultyControllerTestRestTemplateTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private Faculty testFaculty;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/faculty";
        testFaculty = new Faculty();
        testFaculty.setName("Gryffindor");
        testFaculty.setColor("Red");
    }
    @Test
    void createFaculty_shouldReturnFacultyAndStatus200() {
        ResponseEntity<Faculty> response = restTemplate.postForEntity(baseUrl, testFaculty, Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Gryffindor", response.getBody().getName());
        assertEquals("Red", response.getBody().getColor());
    }
    @Test
    void getFacultyInfo_shouldReturnFacultyAndStatus200() {
        Faculty createdFaculty = restTemplate.postForObject(baseUrl, testFaculty, Faculty.class);

        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                baseUrl + "/" + createdFaculty.getId(), Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdFaculty.getId(), response.getBody().getId());
        assertEquals("Gryffindor", response.getBody().getName());
    }
    @Test
    void getFacultyInfo_shouldReturnStatus404WhenFacultyNotFound() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(baseUrl + "/200", Faculty.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void searchFaculties_shouldReturnFacultiesByNameAndStatus200() {
        restTemplate.postForObject(baseUrl, testFaculty, Faculty.class);

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(
                baseUrl + "/search?name=Gryffindor", Faculty[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void searchFaculties_shouldReturnEmptyListWhenNoFacultiesFound() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(
                baseUrl + "/search?name=Slytherin", Faculty[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }



}
