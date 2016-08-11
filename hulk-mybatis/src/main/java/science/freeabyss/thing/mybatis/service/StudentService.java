package science.freeabyss.thing.mybatis.service;

import science.freeabyss.thing.mybatis.dao.StudentMapper;
import science.freeabyss.thing.mybatis.modul.Student;
import org.apache.ibatis.session.SqlSession;
import science.freeabyss.thing.mybatis.util.MyBatisSqlSessionFactory;

import java.util.List;

/**
 * Created by abyss on 06/13/16.
 */
public class StudentService {

//    private Logger logger = Logger.getLogger(getClass());

    public List<Student> findAllStudents() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findAllStudents();
        } finally {
            sqlSession.close();
        }
    }

    public Student findStudentById(Integer studId) {
//      studId  if (logger.isDebugEnabled()) {
//            logger.debug("Select Student by ID:" + studId);
//        }

        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findStudentById(studId);
        } finally {
            sqlSession.close();
        }
    }

    public void createStudent(Student student) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 不通过Mapper接口执行映射语句
     */
    public Student findStudentById1(Integer studId) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        return sqlSession.selectOne("dao.StudentMapper.findStudentById", studId);
    }


    public static void main(String[] args) {
        new StudentService().findAllStudents();
    }
}
