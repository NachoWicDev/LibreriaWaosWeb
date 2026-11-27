package gm.libreria_waos_web.servicio;

import gm.libreria_waos_web.modelo.Autor;

import java.util.List;

public interface IAutorServicio {
    List<Autor> listarAutoresParaWeb();
    Autor buscarAutorPorIdWeb(Integer id);
    void listarAutores();
    void buscarAutorPorId();
    void agregarAutor();
    void modificarAutor();
    void eliminarAutor();
}
