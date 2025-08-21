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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
public class FacultyControllerWebMvcTest {
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Faculty testFaculty = new Faculty(1L, "Gryffindor", "Red");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(facultyController).build();
    }
    @Test
    void getFacultyInfo_shouldReturnFacultyAndStatus200() throws Exception {
        when(facultyService.findFaculty(1L)).thenReturn(testFaculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void getFacultyInfo_shouldReturnStatus404WhenFacultyNotFound() throws Exception {
        when(facultyService.findFaculty(anyLong())).thenReturn(null);

        mockMvc.perform(get("/faculty/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createFaculty_shouldReturnFacultyAndStatus200() throws Exception {
        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(testFaculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testFaculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void searchFaculties_shouldReturnFacultiesByNameAndStatus200() throws Exception {
        when(facultyService.findByNameOrColorIgnoreCase(anyString(), isNull()))
                .thenReturn(List.of(testFaculty));

        mockMvc.perform(get("/faculty/search")
                        .param("name", "Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(jsonPath("$[0].color").value("Red"));
    }

    @Test
    void searchFaculties_shouldReturnEmptyListWhenNoFacultiesFound() throws Exception {
        when(facultyService.findByNameOrColorIgnoreCase(anyString(), isNull()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/faculty/search")
                        .param("name", "Slytherin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
