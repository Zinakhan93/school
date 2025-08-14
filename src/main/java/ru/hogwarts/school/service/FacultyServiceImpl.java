package ru.hogwarts.school.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.List;


@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
      return   facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty aditFaculty( Faculty faculty){
        if (!facultyRepository.existsById(faculty.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found with id: " + faculty.getId());
        }
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        Faculty faculty =  findFaculty(id);
        if (faculty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found with id: ");
        }
        facultyRepository.deleteById(id);
    }


 // Дополнительные методы для отображения всех и для удаления
    public Collection <Faculty> findAll(){
        return facultyRepository.findAll();
    }
    public void deleteAllFaculty(){
        facultyRepository.deleteAll();
    }

    @Override
    public List<Faculty> findByNameOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(name, color);
    }
}
