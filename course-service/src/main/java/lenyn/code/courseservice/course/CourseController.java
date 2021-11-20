package lenyn.code.courseservice.course;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.GONE;

@RestController
@RequestMapping( "/course" )
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.listAll();
        if ( courses.isEmpty() ) {
            return ResponseEntity.noContent()
                                 .build();
        }
        return ResponseEntity.ok( courses );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Course> getCourse( @PathVariable( "id" ) Long idCourse ) {
        Course course = courseService.getOne( idCourse );
        if ( course == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }

        return ResponseEntity.ok( course );
    }

    @PostMapping( "/new" )
    public ResponseEntity<Course> createCourse( @Valid @RequestBody Course course ) {
        Course newCourse = courseService.addNew( course );
        return ResponseEntity.status( CREATED )
                             .body( newCourse );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Course> updateCourse( @PathVariable( "id" ) Long idCourse,
                                                @Valid @RequestBody Course course ) {
        Course courseDB = courseService.update( idCourse, course );
        if ( course == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok( courseDB );
    }

    public ResponseEntity<Course> deleteCourse( @PathVariable( "id" ) Long idCourse ) {
        Course courseDeleted = courseService.delete( idCourse );
        if ( courseDeleted == null ) {
            return ResponseEntity.notFound()
                                 .build();
        }

        return ResponseEntity.status( GONE )
                             .body( courseDeleted );
    }
}
