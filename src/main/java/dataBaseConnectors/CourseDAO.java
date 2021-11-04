package dataBaseConnectors;

import model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CourseDAO {
    private EntityManagerFactory emf;

    public CourseDAO(){
        emf = EMFactory.getEMF ();
    }

    public Course getCourseById(Long id){
        EntityManager em = emf.createEntityManager ();
        return em.find (Course.class, id);
    }

    public List<Course> getAllCourses(){
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From Course", Course.class);
        List<Course> courseList = query.getResultList ();
        return courseList;
    }

    public void addCourse(Course course){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (course);
        em.getTransaction ().commit ();
    }

    public void updateCourse(Course course){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (course);
        em.getTransaction ().commit ();
    }

    public void deleteCourse(Course course){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (Course.class, course.getId ()));
        em.getTransaction ().commit ();
    }
}
