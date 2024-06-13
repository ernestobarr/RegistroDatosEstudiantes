package com.mycompany.registroacadweb.jpql;

import com.mycompany.registroacadweb.jpa.Estudiante;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestJPQL {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        //buscarDatosEstudiante();
        //buscarEstudianteNombre("Ernesto Barrientos");
        //buscarEstudiantePorId(1);
        //buscarEstudiantePorIdEIn(1, 2);
        //buscarEstudiantes();
        //clausulaLike("%Er%");
    }
    
    private static void mostrarEstudiantes(List<Estudiante> estudiantes) {
        for (Estudiante e : estudiantes) {
            log.debug(e.toString());
        }
    }
    
    private static Estudiante buscarEstudiantePorId(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select e from Estudiante e where e.idestudiante = :id";
        Query q = em.createQuery(jpql).setParameter("id", id);
        
        Estudiante e = (Estudiante) q.getSingleResult();
        
        log.debug(e);
        return e;
    }
    
    private static List<Estudiante> buscarEstudiantePorIdEIn(Integer... ids) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String formatIds = Arrays.toString(ids)
                .replace('[', '(')
                .replace(']', ')');
        
        String jpql = "select e from Estudiante e where e.idestudiante in " + formatIds;
        
        List<Estudiante> e = em.createQuery(jpql).getResultList();
        
        mostrarEstudiantes(e);
        
        return e;
    }
    
    private static List<Estudiante> buscarEstudiantes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select e from Estudiante e";
        
        List<Estudiante> e = em.createQuery(jpql).getResultList();
        
        mostrarEstudiantes(e);
        
        return e;
    }
    
    private static List<Estudiante> buscarEstudianteNombre(String nombre) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select e from Estudiante e where e.nombres = '" + nombre + "'";
        
        List<Estudiante> e = em.createQuery(jpql).getResultList();
        
        mostrarEstudiantes(e);
        
        return e;
    }
    
    private static void buscarDatosEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select e.carnet, e.nombres from Estudiante e";
        
        Iterator iter = em.createQuery(jpql).getResultList().stream().iterator();
        Object[] tupla = null;
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String carnet = (String) tupla[0];
            String nombres = (String) tupla[1];
            log.debug("Carnet: " + carnet + " Nombres: " + nombres);
        }
    }
    
    private static void clausulaLike(String param) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select e from Estudiante e where upper(e.nombres) like (:parametro)";
        Query q = em.createQuery(jpql).setParameter("parametro", param);
        
        List<Estudiante> estudiantes = q.getResultList();
             
        mostrarEstudiantes(estudiantes);
    }
}