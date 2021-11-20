package lenyn.code.courseservice.course;

import java.util.List;

public interface CourseService {

    List<Course> listAll();

    Course getOne( Long id );

    Course addNew( Course course );

    Course update( Long id,
                   Course course );

    Course delete( Long id );
}
