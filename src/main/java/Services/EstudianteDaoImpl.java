package Services;

import com.mycompany.registroacadweb.jpa.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EstudianteDaoImpl implements IEstudianteDao {
    
    @PersistenceContext(unitName="EstudiantePU")
    EntityManager em;

    @Override
    public List<Estudiante> buscarTodosEstudiante() {
        return em.createNamedQuery("Estudiante.findAll").getResultList();
    }

    @Override
    public Estudiante buscarEstudiantePorId(Estudiante estudiante) {
        return em.find(Estudiante.class, estudiante.getIdestudiante());
    }

    @Override
    public Estudiante buscarEstudiantePorCarnet(Estudiante estudiante) {
        Query query = em.createQuery("from Estudiante e where e.carnet=: carnet");
        query.setParameter("carnet", estudiante.getCarnet());
        return (Estudiante) query.getSingleResult();
    }

    @Override
    public void insertarEstudiante(Estudiante estudiante) {
        em.persist(estudiante);
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) {
        em.merge(estudiante);
    }

    @Override
    public void borrarEstudiante(Estudiante estudiante) {
        em.remove(em.merge(estudiante));
    }
    
}