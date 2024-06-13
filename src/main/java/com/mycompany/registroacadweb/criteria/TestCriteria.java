package com.mycompany.registroacadweb.criteria;

import com.mycompany.registroacadweb.jpa.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        recuperarEstudiante();
        recuperarPorCriterios();
    }
    
    private static void mostrarEstudiantes(List<Estudiante> estudiantes) {
        for (Estudiante e : estudiantes) {
            log.debug(e.toString());
        }
    }
    
    public static void recuperarEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory ("EstudiantePU");
        EntityManager em = emf.createEntityManager ();
        
        //1. Con EM crear una instancia de CriteriaBuilder
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        //2. Se crea un objeto CriteriaQuery
        CriteriaQuery<Estudiante> criteriaQuery = criteriaBuilder.createQuery(Estudiante.class);
        //3. Se crea un objeto Raiz de query
        Root<Estudiante> fromEstudiante = criteriaQuery.from(Estudiante.class);
        //4. Seleccionamos del from
        criteriaQuery.select(fromEstudiante);
        //5. Se crea un query tupesafe
        TypedQuery query = em.createQuery(criteriaQuery);
        //6. Ejecutamos la consulta
        List<Estudiante> estudiantes = query. getResultList();
        
        mostrarEstudiantes(estudiantes);
    }
    
    public static void recuperarPorCriterios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Estudiante> cg = cb.createQuery(Estudiante.class);
        Root<Estudiante> fromEstudiante = cg.from(Estudiante.class);
        cg.select(fromEstudiante);
        List<Predicate> criterios = new ArrayList<Predicate>(); //Creando lista de criterios
        
        //Evaluamos criterios
        Integer idEstudiante = 1;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idestudiante");
        criterios.add(cb.equal(fromEstudiante.get("idestudiante"),parameter));
        if (criterios.isEmpty()) {
            throw new RuntimeException("No hay criterios"); //si no hay criterios
        } else if (criterios.size() == 1) {
            cg.where(criterios.get(0));
            //si solo hay un criterio
        } else {
            cg.where(cb.and(criterios.toArray(new Predicate[0]))); // Varios criterios con AND
        }
        TypedQuery query = em.createQuery(cg);
        query.setParameter("idestudiante", idEstudiante);
        Estudiante estudiante = (Estudiante) query.getSingleResult();
        log.debug(estudiante);
    }
}