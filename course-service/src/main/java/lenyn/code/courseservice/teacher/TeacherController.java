package lenyn.code.courseservice.teacher;

import lenyn.code.courseservice.course.Course;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.GONE;

@RestController
@RequestMapping( "/teacher" )
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> findTeachers( @RequestParam( name = "course_id",
                                                                      required = false ) Long courseId ) {
        List<Teacher> teachers = courseId == null ?
                                 teacherService.listAll() :
                                 teacherService.filterByCourse( Course.builder()
                                                                      .id( courseId )
                                                                      .build() );
        if ( teachers.isEmpty() ) {
            return ResponseEntity.noContent()
                                 .build();
        }

        return ResponseEntity.ok( teachers );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Teacher> findTeacher( @PathVariable( "id" ) Long idTeacher ) {
        Teacher teacher = teacherService.getOne( idTeacher );
        if ( teacher == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok( teacher );
    }

    @PostMapping( "/new" )
    public ResponseEntity<Teacher> createTeacher( @Valid @RequestBody Teacher teacher ) {
        Teacher newTeacher = teacherService.addNew( teacher );
        return ResponseEntity.status( CREATED )
                             .body( newTeacher );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Teacher> updateTeacher( @PathVariable( "id" ) Long idTeacher,
                                                  @Valid @RequestBody Teacher teacher ) {
        Teacher teacherDB = teacherService.update( idTeacher, teacher );
        if ( teacherDB == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok( teacherDB );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Teacher> deleteTeacher( @PathVariable( "id" ) Long idTeacher ) {
        Teacher teacherDeleted = teacherService.delete( idTeacher );
        if ( teacherDeleted == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }

        return ResponseEntity.status( GONE )
                             .body( teacherDeleted );
    }
}