package test;

import dao.impl.OdontologoDaoH2;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoServiceTest {
    private static Logger logger = Logger.getLogger(OdontologoServiceTest.class);

    @BeforeAll
    static void crearTablas(){
        logger.info("Creando tablas");
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/db_be_odontologia;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            logger.error("No se pudo cargar el driver", ex);
        } catch (SQLException e) {
            logger.error("No se pudo crear la tabla", e);
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error("No se pudo cerrar la conexión", ex);
            }
        }
    }

    @Test
    void testRegistrarOdontologo() {
        Odontologo odontologo = new Odontologo(1, "Juan", "Perez");
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo1 = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologo1);
    }

    @Test
    void testObtenerOdontologos() {
        Odontologo odontologo = new Odontologo(1, "Juan", "Perez");

        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertNotNull(odontologoService.obtenerOdontologos());
    }

}
