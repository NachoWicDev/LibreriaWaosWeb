package gm.libreria_waos_web.controlador;

import gm.libreria_waos_web.modelo.Libro;
import gm.libreria_waos_web.servicio.ILibroServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
@ViewScoped
// @ViewScoped: Conserva los datos del controlador durante toda la vista actual (hasta que se recargue o cambie de página).
public class IndexControlador implements Serializable {
    @Autowired
    ILibroServicio libroServicio;
    private List<Libro> libros;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);
    private Libro libroSeleccionado;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.libros = this.libroServicio.listarLibrosParaWeb();
        for (Libro libro : this.libros) {
            logger.info("\n" + libro.toString());
        }
    }

    public void agregarLibro() {
        this.libroSeleccionado = new Libro();
    }
}
