package dataBaseConnectors;

import model.Exam;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class UserDAO {
    private EntityManagerFactory emf;

    public UserDAO(){
        emf = EMFactory.getEMF ();
    }

    public User getUserById(String login){
        EntityManager em = emf.createEntityManager ();
        return em.find (User.class, login);
    }

    public List<User> getAllUser(){
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From User", User.class);
        List<User> userList = query.getResultList ();
        return userList;
    }

    public void addUser(User user){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (user);
        em.getTransaction ().commit ();
    }

    public void updateUser(User user){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (user);
        em.getTransaction ().commit ();
    }

    public void deleteUser(User user){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (User.class, user.getLogin ()));
        em.getTransaction ().commit ();
    }
}
