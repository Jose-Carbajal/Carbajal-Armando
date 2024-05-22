package model;

import dao.IDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> {
    private Logger logger = Logger.getLogger(OdontologoEnMemoria.class);
    List<Odontologo> odontologos = new ArrayList<>();
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Integer id = odontologos.size() + 1;
        odontologo.setNumeroMatricula(id);
        logger.info("Odontologo encontrado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> obtenerOdontologos() {
        return odontologos;
    }
}
