package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO ODONTOLOGOS (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
    private static String SQL_SELECT = "SELECT * FROM ODONTOLOGOS";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Odontologo odontologoARetornar = null;
        try {
            conn = H2Connection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Integer numeroMatricula = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(numeroMatricula, odontologo.getNombre(), odontologo.getApellido());

            }
            logger.info("Odontólogo registrado en H2" + odontologo);
            conn.commit();
            conn.setAutoCommit(true);

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    logger.error("Error al hacer rollback en H2", ex);
                    ex.printStackTrace();
                }
            }
            logger.info("Error al registrar odontólogo en H2", e);
            e.printStackTrace();
        }
        finally {
            try{
                conn.close();

            } catch (SQLException e) {
                logger.info("Error al cerrar la conexión", e);
                e.printStackTrace();
            }
        }

        logger.info("Registrando odontólogo en H2 con id: " + odontologo.getNumeroMatricula());
        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> obtenerOdontologos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Odontologo> listaOdontologos = null;
        try {
            conn = H2Connection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()) {
                Integer numeroMatricula = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String apellido = resultSet.getString(3);
                Odontologo odontologo = new Odontologo(numeroMatricula, nombre, apellido);
                listaOdontologos.add(odontologo);
            }
        } catch (Exception e) {
            logger.info("Error al obtener odontólogos en H2", e);
            e.printStackTrace();
        }
        finally {
            try{
                conn.close();

            } catch (SQLException e) {
                logger.info("Error al cerrar la conexión", e);
                e.printStackTrace();
            }
        }
        logger.info("Obteniendo odontólogos de H2" + listaOdontologos);
        return listaOdontologos;
    }
}
