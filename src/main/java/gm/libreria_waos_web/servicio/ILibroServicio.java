package gm.libreria_waos_web.servicio;

public interface ILibroServicio {
    void listarLibros();
    void listarMasVendidos();
    void listarPorNombre();
    void buscarLibroPorId();
    void comprarLibro();
    void recargarStock();
    void agregarLibro();
    void modificarLibro();
    void eliminarLibro();
}
