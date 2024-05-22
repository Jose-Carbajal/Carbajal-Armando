package dao;

import model.Odontologo;

import java.util.List;

public interface IDao <T>{

    T registrar(Odontologo odontologo);
    List<T> obtenerOdontologos();
}
