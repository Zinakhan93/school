package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface  FacultyService {
    Faculty addFaculty (Faculty faculty);
    Faculty findFaculty (Long id);
    Faculty aditFaculty ( Faculty faculty);
    void deleteFaculty (Long id);



// дополнительные методы
   Collection<Faculty> findAll();
    void deleteAllFaculty();
    // ДЗ 2 базза данных
    public List<Faculty> findByNameOrColorIgnoreCase(String name, String color);
    //  параллельные стримы
    String getLongestFacultyName();
}
