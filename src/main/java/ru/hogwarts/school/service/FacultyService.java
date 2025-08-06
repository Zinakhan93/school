package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface  FacultyService {
    Faculty addFaculty (Faculty faculty);
    Faculty findFaculty (Long id);
    Faculty aditFaculty ( Faculty faculty);
    void deleteFaculty (Long id);
    List<Faculty> findByColorIgnoreCase(String color);



}
