package kg.kubatbekov.Hibernate.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id",insertable = false)
    private Group group;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public Student() {
    }


    public Student(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Student(int student_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }


    public Student(int student_id, String first_name, String last_name, Group group, List<Course> courses) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.group = group;
        this.courses = courses;
    }


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return student_id == student.student_id && Objects.equals(first_name, student.first_name) && Objects.equals(last_name, student.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, first_name, last_name);
    }
}