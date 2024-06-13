import ciclovidajpa.PersistenceJPA;
import com.mycompany.registroacadweb.jpa.Estudiante;

public class test {
    public static void main(String[] args) {
        PersistenceJPA.crearEstudiante();
        PersistenceJPA.recuperarEstudiante();
        PersistenceJPA.modificarEstudiante();
        PersistenceJPA.eliminarEstudiante();
    }
}