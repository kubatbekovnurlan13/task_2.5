package kg.kubatbekov.Hibernate.service;


import kg.kubatbekov.Hibernate.dao.StudentDAO;
import kg.kubatbekov.Hibernate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void save(Student student) {
        studentDAO.save(student);
    }

    public Optional<Student> getByName(String name) {
        return studentDAO.getByName(name);
    }

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public void update(Student student) {
        studentDAO.update(student);
    }

    public void deleteById(int student_id) {
        studentDAO.deleteById(student_id);
    }
}
