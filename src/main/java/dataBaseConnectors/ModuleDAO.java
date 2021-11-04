package dataBaseConnectors;

import model.Course;
import model.Module;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {
    private EntityManagerFactory emf;

    public ModuleDAO(){
        emf = EMFactory.getEMF ();
    }

    public Module getModuleById(Long id){
        EntityManager em = emf.createEntityManager ();
        return em.find (Module.class, id);
    }

    public List<Module> getAllModules(){
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From Module", Module.class);
        List<Module> moduleList = query.getResultList ();
        return moduleList;
    }

    public void addModule(Module module){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (module);
        em.getTransaction ().commit ();
    }

    public void updateModule(Module module){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (module);
        em.getTransaction ().commit ();
    }

    public void deleteModule(Module module){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (Module.class, module.getId ()));
        em.getTransaction ().commit ();
    }
}
