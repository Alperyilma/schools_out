package dataBaseConnectors;

import model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class PersonDAO {
    private EntityManagerFactory emf;

    public PersonDAO(){
        emf = EMFactory.getEMF ();
    }

    public Person getPersonById(int id){
        EntityManager em = emf.createEntityManager ();
        return em.find (Person.class, id);
    }

    public List<Person> getAllPersons(){
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From Person", Person.class);
        List<Person> personList = query.getResultList ();
        return personList;
    }

    public void addPerson(Person person){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (person);
        em.getTransaction ().commit ();
    }

    public void updatePerson(Person person){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (person);
        em.getTransaction ().commit ();
    }

    public void deletePerson(Person person){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (Person.class,person.getId ()));
        em.getTransaction ().commit ();
    }

}
