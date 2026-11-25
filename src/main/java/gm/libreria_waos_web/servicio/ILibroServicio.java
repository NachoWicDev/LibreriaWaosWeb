package gm.libreria_waos_web.servicio;

import gm.libreria_waos_web.modelo.Libro;
import java.util.List;

public interface ILibroServicio {
    void listarLibros();
    List<Libro> listarLibrosParaWeb();
    void listarMasVendidos();
    void listarPorNombre();
    void buscarLibroPorId();
    void comprarLibro();
    void recargarStock();
    void agregarLibro();
    void modificarLibro();
    void eliminarLibro();
}
