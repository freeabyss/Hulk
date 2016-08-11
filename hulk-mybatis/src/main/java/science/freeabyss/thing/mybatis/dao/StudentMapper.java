package science.freeabyss.thing.mybatis.dao;

import science.freeabyss.thing.mybatis.modul.Student;

import java.util.List;

/**
 * Created by abyss on 06/13/16.
 */
public interface StudentMapper {
    List<Student> findAllStudents();

    Student findStudentById(Integer id);

    void insertStudent(Student student);
}
