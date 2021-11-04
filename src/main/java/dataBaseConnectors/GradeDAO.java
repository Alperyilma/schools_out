package dataBaseConnectors;

import model.Grade;

import javax.persistence.EntityManager;
import java.util.List;

public class GradeDAO {

    public Grade findGradeById(Long id){
        EntityManager em = EMFactory.getEMF ().createEntityManager ();
        return em.find (Grade.class, id);
    }

    public List<Grade> getAllGrades(){
        EntityManager em = EMFactory.getEMF ().createEntityManager ();
        List<Grade> grades = em.createQuery ("Select b From Grade b").getResultList ();
        return grades;
    }

    public void addGrade(Grade grade){
        EntityManager em = EMFactory.getEMF ().createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (grade);
        em.getTransaction ().commit ();
    }

    public void updateGrade(Grade grade){
        EntityManager em = EMFactory.getEMF ().createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (grade);
        em.getTransaction ().commit ();
    }

    public void deleteGrade(Grade grade){
        EntityManager em = EMFactory.getEMF ().createEntityManager ();
        em.getTransaction ().begin ();
        Grade foundGrade = em.find (Grade.class,grade.getId ());
        em.remove (foundGrade);
        em.getTransaction ().commit ();
    }
}
