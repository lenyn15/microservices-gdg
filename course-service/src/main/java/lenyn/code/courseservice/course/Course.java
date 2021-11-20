package lenyn.code.courseservice.course;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.*;

@Table( name = "Curso" )
@Entity( name = "Course" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    @Id
    @GeneratedValue( strategy = AUTO )
    private Long id;

    @NotEmpty( message = "Ingrese el nombre del curso" )
    @Column( name = "nombre",
             length = 45,
             nullable = false )
    private String name;
}
