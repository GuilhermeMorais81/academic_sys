package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import model.enums.CourseEnrollmentStatus;
import model.enums.DisciplineEnrollmentStatus;

@Getter
public class CourseEnrollment extends Base {
    private Course course;
    private Student student;
    private List<DisciplineEnrollment> disciplineEnrollments;
    private LocalDate creationDate;
    private CourseEnrollmentStatus status;

    public CourseEnrollment(UUID id, Course course, Student student, LocalDate creationDate, CourseEnrollmentStatus status) {
        super(id);
        this.course = course;
        this.student = student;
        setNewDisciplineEnrollments();
        if(disciplineEnrollments == null) System.out.println(disciplineEnrollments);
        this.creationDate = creationDate;
        this.status = status;
    }

    private void setNewDisciplineEnrollments() {
        disciplineEnrollments = new ArrayList<>();
        for(Discipline discipline : course.getDisciplines()) {
            var disciplineEnrollment = new DisciplineEnrollment
            (
                UUID.randomUUID(),
                discipline, 
                null, 
                null, 
                null, 
                DisciplineEnrollmentStatus.IN_COURSE
            );
            disciplineEnrollments.add(disciplineEnrollment);
        }
    }

    @Override
    public String toString() {
        return course.getName() + " | " + student.getName() + creationDate + getStatus();
    }
}
