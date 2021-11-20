package lenyn.code.courseservice.teacher;

import lenyn.code.courseservice.course.Course;

import java.util.List;

public interface TeacherService {

    List<Teacher> listAll();

    List<Teacher> filterByCourse( Course course );

    Teacher getOne( Long id );

    Teacher addNew( Teacher teacher );

    Teacher update( Long id,
                    Teacher teacher );

    Teacher delete( Long id );
}
