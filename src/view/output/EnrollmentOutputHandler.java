package view.output;

import model.Course;
import model.CourseEnrollment;
import model.DisciplineEnrollment;

public class EnrollmentOutputHandler {
    public static String generateCourseEnrollReport(CourseEnrollment courseEnrollment, Course course) {
        if(!course.getId().equals(courseEnrollment.getCourseId())) throw new IllegalArgumentException("Courses must be the same");
        var sb = new StringBuilder();
        sb.append("Enrollment Report:\n");
        sb.append("Student name: " + courseEnrollment.getStudent().getName() + "\n");
        sb.append("Course name: " + course.getName() + "\n");
        sb.append("Enrollment creation date: " + courseEnrollment.getCreationDate() + "\n");
        sb.append("Enrollment Status: " + courseEnrollment.getStatus() + "\n");
        sb.append(generateAllDisEnrollReport(courseEnrollment));
        return sb.toString();
    }

    public static String generateAllDisEnrollReport(CourseEnrollment courseEnrollment) {
        var sb = new StringBuilder();
        for(DisciplineEnrollment de : courseEnrollment.getDisciplineEnrollments())
            sb.append(generateDisEnrollReport(de));
        return sb.toString();
    }

    public static String generateDisEnrollReport(DisciplineEnrollment disciplineEnrollment) {
        var sb = new StringBuilder();
        sb.append("-------------------------\n");
        sb.append("Discipline: " + disciplineEnrollment.getDiscipline().getName() + "\n");
        if(disciplineEnrollment.isAverageAlreadySet()) {
            sb.append(generateAveragesText(disciplineEnrollment));
            sb.append("Status: " + disciplineEnrollment.getStatus() + "\n");
        }
        else sb.append("Averages not calculated yet...\n");
        return sb.toString();
    }
    
    public static String generateAveragesText(DisciplineEnrollment disciplineEnrollment) {
        var sb = new StringBuilder();
        sb.append("First average: " + disciplineEnrollment.getFirstAvg() + " | ");
        sb.append("Second average: " + disciplineEnrollment.getSecondAvg() + " | ");
        sb.append("Final average: " + disciplineEnrollment.getFinalAvg() + " | ");
        return sb.toString();
    }
}
