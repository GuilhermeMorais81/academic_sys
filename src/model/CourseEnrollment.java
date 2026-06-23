package model;

import java.time.LocalDate;
import java.util.UUID;

import model.enums.CourseEnrollmentStatus;

public class CourseEnrollment extends Base {
    private Course course;
    private Student student;
    private DisciplineEnrollment disciplineEnrollments;
    private LocalDate creationDate;
    private CourseEnrollmentStatus status;

    public CourseEnrollment(UUID id, Course course, Student student, DisciplineEnrollment disciplineEnrollments,
            LocalDate creationDate, CourseEnrollmentStatus status) {
        super(id);
        this.course = course;
        this.student = student;
        this.disciplineEnrollments = disciplineEnrollments;
        this.creationDate = creationDate;
        this.status = status;
    }
}
