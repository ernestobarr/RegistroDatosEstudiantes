package ciclovidajpa;

import com.mycompany.registroacadweb.jpa.Credencial;
import com.mycompany.registroacadweb.jpa.Estudiante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistenceJPA {
    static Logger log = LogManager.getRootLogger();
    
    // Credencial
    public static void crearCredencial(Estudiante estudiante) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Credencial credencial = new Credencial("ernesto", "123", true);
        credencial.setIdestudiante(estudiante.getIdestudiante());
        //credencial.setEstudiante(estudiante);
        em.persist(credencial);
        em.getTransaction().commit();

        log.debug("Objeto persistido: " + credencial);
        em.close();
    }
    
    // Estudiante
    public static void crearEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
    
        // CREAR antes estudiante        

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Estudiante estudiante = new Estudiante("3", "Ernesto", "Barrientos");
        em.persist(estudiante);
        tx.commit();

        log.debug("Objeto persistido: " + estudiante);
        
        em.close();

        crearCredencial(estudiante);
    }
    
    public static void recuperarEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Estudiante estudiante = em.find(Estudiante.class, 4);
        
        tx.commit();
        
        log.debug("Objeto recuperado: " + estudiante);
        
        em.close();
    }
    
    public static void modificarEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();   
        
        Estudiante estudiante = em.find(Estudiante.class, 1);
        
        tx.commit();
        
        log.debug("Objeto persistido: " + estudiante);
        
        estudiante.setNombres("Pablo");
        estudiante.setApellidos("Gavidia");
        
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        tx2.commit();
        log.debug("Objeto guardado: " + estudiante);
        em.close();
    }
    
    public static void modficarEstudianteSesionLarga() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();   
        
        Estudiante estudiante = em.find(Estudiante.class, 1);
        log.debug("Objeto recuperado: " + estudiante);
        
        estudiante.setNombres("Alberto Perez");
        
        tx.commit();
        log.debug("Objeto modificado: " + estudiante);
        em.close();
    }
    
    public static void eliminarEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Estudiante estudiante = em.find(Estudiante.class, 4);
        em.remove(estudiante);
        log.debug("Objeto ELIMINADO: " + estudiante);
        em.getTransaction().commit();
        
        em.close();
    }
}