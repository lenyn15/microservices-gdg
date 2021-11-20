package lenyn.code.courseservice.teacher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lenyn.code.courseservice.course.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.*;

@Table( name = "Docente" )
@Entity( name = "Teacher" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue( strategy = AUTO )
    private Long id;

    @NotEmpty( message = "Ingrese el nombre del docente" )
    @Column( name = "nombre",
             length = 45,
             nullable = false )
    private String name;

    @NotEmpty( message = "Ingrese el apellido del docente" )
    @Column( name = "apellido",
             length = 45,
             nullable = false )
    private String surname;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "course_id" )
    @JsonIgnoreProperties( {
            "hibernateLazyInitializer",
            "handler"
    } )
    private Course course;
}
