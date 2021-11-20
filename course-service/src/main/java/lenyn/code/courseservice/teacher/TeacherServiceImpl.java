package lenyn.code.courseservice.teacher;

import lenyn.code.courseservice.course.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> filterByCourse( Course course ) {
        return teacherRepository.findByCourse( course );
    }

    @Override
    public Teacher getOne( Long id ) {
        return teacherRepository.findById( id )
                                .orElse( null );
    }

    @Override
    public Teacher addNew( Teacher teacher ) {
        return teacherRepository.save( teacher );
    }

    @Override
    public Teacher update( Long id,
                           Teacher teacher ) {
        Teacher teacherDB = getOne( id );
        if ( teacherDB == null ) {
            return null;
        }
        teacherDB.setName( teacher.getName() );
        teacherDB.setSurname( teacher.getSurname() );
        teacherDB.setCourse( teacher.getCourse() );
        return teacherRepository.save( teacherDB );
    }

    @Override
    public Teacher delete( Long id ) {
        Teacher teacherDB = getOne( id );
        if ( teacherDB == null ) {
            return null;
        }
        teacherRepository.deleteById( id );
        return teacherDB;
    }
}
