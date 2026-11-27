package gm.libreria_waos_web.controlador;

import gm.libreria_waos_web.exception.ConflictoException;
import gm.libreria_waos_web.exception.ValidacionException;
import gm.libreria_waos_web.modelo.Autor;
import gm.libreria_waos_web.modelo.Libro;
import gm.libreria_waos_web.servicio.AutorServicio;
import gm.libreria_waos_web.servicio.IAutorServicio;
import gm.libreria_waos_web.servicio.ILibroServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Named("indexControlador")
@Data
@ViewScoped
@ToString(exclude = "libroSeleccionado")
// @ViewScoped: Conserva los datos del controlador durante toda la vista actual (hasta que se recargue o cambie de página).
public class IndexControlador implements Serializable {
    @Autowired
    private IAutorServicio autorServicio;
    private List<Autor> autores;
    @Autowired
    private ILibroServicio libroServicio;
    private List<Libro> libros;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);
    private Libro libroSeleccionado = new Libro(); // Inicializado para evitar NullPointerException
    private Integer idAutorSeleccionado = null;

    @PostConstruct
    public void init() {
        cargarDatos();
        this.autores = autorServicio.listarAutoresParaWeb();
    }

    public void cargarDatos() {
        this.libros = this.libroServicio.listarLibrosParaWeb();
        for (Libro libro : this.libros) {
            logger.info("\n" + libro.toString());
        }
    }

    public void agregarLibro() {
        this.libroSeleccionado = new Libro();
        this.idAutorSeleccionado = null;
    }

    public void guardarLibro() {
        logger.info("Iniciando método guardarLibro desde la web...");
        try {
            // 1. Buscar el autor completo a partir del ID que llegó de la vista.
            if (idAutorSeleccionado != null) {
                Autor autorCompleto = autorServicio.buscarAutorPorIdWeb(idAutorSeleccionado);
                this.libroSeleccionado.setAutor(autorCompleto);
            }

            // 2. Valida si el libro es nuevo para guardarlo, si ya existe, lo modifica.
            if (this.libroSeleccionado.getId() == null) {
                this.libroServicio.guardarLibroWeb(this.libroSeleccionado);
            } else {
                // Modificar libro, WIP.
            }

            // 2. Recargar tabla en la web.
            cargarDatos();

            // 3. Limpiar el formulario.
            this.libroSeleccionado = new Libro();
            this.idAutorSeleccionado = null;

            // 4. Mensaje de éxito opcional.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Libro guardado"));
        } catch (ValidacionException | ConflictoException e) {
            // Si hay error, el mensaje de error se muestra en el growl.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
}
