package kg.kubatbekov.Hibernate.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;

    @Column(name = "group_name")
    private String group_name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public Group() {
    }

    public Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getGroup_id() {
        return group_id;
    }

    public Group(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    @Override
    public String toString() {
        return "Group: " +
                "group_id=" + group_id +
                ", group_name='" + group_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return group_id == group.group_id && Objects.equals(group_name, group.group_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group_id, group_name);
    }
}
