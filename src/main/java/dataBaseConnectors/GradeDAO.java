package dataBaseConnectors;

import model.Grade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class GradeDAO {
    private EntityManagerFactory emf;

    public GradeDAO(){
        emf = EMFactory.getEMF();
    }

    public Grade getGradeById(Long id){
        EntityManager em = emf.createEntityManager();
        return em.find(Grade.class, id);
    }

    public List<Grade> getAllGrade(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("From Grade", Grade.class);
        List<Grade> gradeList = query.getResultList();
        return gradeList;
    }

    public void addGrade(Grade grade){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

    public void updateGrade(Grade grade){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    public void deleteGrade(Grade grade){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Grade.class,grade.getId()));
        em.getTransaction().commit();
    }
}
