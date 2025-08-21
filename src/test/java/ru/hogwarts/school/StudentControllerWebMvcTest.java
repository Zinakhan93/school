package ru.hogwarts.school;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerWebMvcTest {
    private MockMvc mockMvc;
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Student testStudent = new Student(1L, "Harry Potter", 20);
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }
    @Test
    void getStudentInfo_shouldReturnStudentAndStatus200() throws Exception {
        when(studentService.findStudent(1L)).thenReturn(testStudent);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Harry Potter"))
                .andExpect(jsonPath("$.age").value(20));
    }
    @Test
    void getStudentInfo_shouldReturnStatus404WhenStudentNotFound() throws Exception {
        when(studentService.findStudent(anyLong())).thenReturn(null);

        mockMvc.perform(get("/student/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createStudent_shouldReturnStudentAndStatus200() throws Exception {
        when(studentService.addStudent(any(Student.class))).thenReturn(testStudent);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Harry Potter"))
                .andExpect(jsonPath("$.age").value(20));
    }
    @Test
    void updateStudent_shouldReturnUpdatedStudentAndStatus200() throws Exception {
        Student updatedStudent = new Student(1L, "Ron Weasley", 21);
        when(studentService.editStudent(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Ron Weasley"))
                .andExpect(jsonPath("$.age").value(21));
    }

    @Test
    void findStudents_shouldReturnStudentsByAgeAndStatus200() throws Exception {
        when(studentService.findByAge(20)).thenReturn(List.of(testStudent));

        mockMvc.perform(get("/student")
                        .param("age", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Harry Potter"))
                .andExpect(jsonPath("$[0].age").value(20));
    }

    @Test
    void findStudents_shouldReturnEmptyListWhenNoStudentsFound() throws Exception {
        when(studentService.findByAge(100)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/student")
                        .param("age", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }


}
