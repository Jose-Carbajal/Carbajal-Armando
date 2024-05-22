package service;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoDao.registrar(odontologo);
    }

    public List<Odontologo> obtenerOdontologos() {
        return odontologoDao.obtenerOdontologos();
    }
}
