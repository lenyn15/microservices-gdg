package lenyn.code.courseservice.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getOne( Long id ) {
        return courseRepository.findById( id )
                               .orElse( null );
    }

    @Override
    public Course addNew( Course course ) {
        return courseRepository.save( course );
    }

    @Override
    public Course update( Long id,
                          Course course ) {
        Course courseDB = getOne( id );
        if ( courseDB == null ) {
            return null;
        }
        courseDB.setName( course.getName() );
        return courseRepository.save( courseDB );
    }

    @Override
    public Course delete( Long id ) {
        Course courseDB = getOne( id );
        if ( courseDB == null ) {
            return null;
        }
        courseRepository.deleteById( id );
        return courseDB;
    }
}
