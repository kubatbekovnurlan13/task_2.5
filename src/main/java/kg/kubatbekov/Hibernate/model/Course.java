package kg.kubatbekov.Hibernate.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;
    @Column(name = "course_name")
    private String course_name;
    @Column(name = "course_description")
    private String course_description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public Course() {
    }

    public Course(String course_name, String course_description) {
        this.course_name = course_name;
        this.course_description = course_description;
    }

    public Course(int course_id, String course_name, String course_description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_description = course_description;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    @Override
    public String toString() {
        return "Course: " +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", course_description='" + course_description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return course_id == course.course_id && Objects.equals(course_name, course.course_name) && Objects.equals(course_description, course.course_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, course_name, course_description);
    }
}
