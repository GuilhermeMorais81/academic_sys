package model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import model.enums.CourseEnrollmentStatus;
import model.enums.DisciplineEnrollmentStatus;

@Getter
public class CourseEnrollment extends Base {
    private UUID courseId;
    private Student student;
    private @Setter List<DisciplineEnrollment> disciplineEnrollments;
    private LocalDate creationDate;
    private CourseEnrollmentStatus status;

    public CourseEnrollment(UUID id, UUID courseId, Student student, LocalDate creationDate, CourseEnrollmentStatus status) {
        super(id);
        this.courseId = courseId;
        this.student = student;
        this.creationDate = creationDate;
        this.status = status;
    }

    public boolean isAverageAlreadySet() {
        return status.getNumber() != DisciplineEnrollmentStatus.IN_COURSE.getNumber(); 
    }

    private boolean isDisciplineEnrollmentsDefined() {
        for(DisciplineEnrollment des : disciplineEnrollments) {
            if(des.getStatus() == DisciplineEnrollmentStatus.IN_COURSE) return false;
        }
        return true;
    }

    public void setNewStatus() {
        if(!isDisciplineEnrollmentsDefined()) throw new IllegalStateException("Discipline Enrollments were not defined");
        for(DisciplineEnrollment des : disciplineEnrollments) {
            if(des.getStatus() == DisciplineEnrollmentStatus.NOT_APPROVED) {
                status = CourseEnrollmentStatus.NOT_APPROVED;
                return;
            }
        }
        status = CourseEnrollmentStatus.APPROVED;
    }
}
