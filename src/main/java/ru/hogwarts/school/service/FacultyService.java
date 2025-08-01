package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty (Faculty faculty);
    Faculty findFaculty (Long id);
    Faculty aditFaculty (Long id, Faculty faculty);
    void deleteFaculty (Long id);
    List<Faculty> findByColor(String color);

}
