package ru.hogwarts.school.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping ("/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyService facultyService, FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty( @RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.aditFaculty(faculty);
        return ResponseEntity.ok(updateFaculty);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<Void>deleteFaculty (@PathVariable Long id ){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    // Дополнительные Контрьллеры для списка всех и удаления всех
    @DeleteMapping ("/All")
    public ResponseEntity<Void> deleteAllFaculty(){
       facultyService.deleteAllFaculty();
        return  ResponseEntity.ok().build();
    }
    @GetMapping("/All")
    public ResponseEntity <Collection<Faculty>>getAllFaculty(){
        return ResponseEntity.ok(facultyService.findAll());
    }

// дз 2 база данных
@GetMapping("/search")
public ResponseEntity<List<Faculty>> searchFaculties(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String color) {
    List<Faculty> faculties = facultyService.findByNameOrColorIgnoreCase(name, color);
    return ResponseEntity.ok(faculties);
}
    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getFacultyStudents(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty.getStudents());
    }

}


