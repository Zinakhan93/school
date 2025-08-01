package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final HashMap <Long,Faculty> faculties = new HashMap<>();
    private long count =0;


    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(),faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty aditFaculty(Long id, Faculty faculty) {
        if(!faculties.containsKey(id)){
            return null;
        }
        faculties.put(id,faculty);
        return faculty;
    }

    @Override
    public void deleteFaculty(Long id) {
        faculties.remove(id);

    }
    @Override
    public List<Faculty> findByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
