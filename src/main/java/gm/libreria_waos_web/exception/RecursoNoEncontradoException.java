package gm.libreria_waos_web.exception;

public class RecursoNoEncontradoException extends BaseException {
    public RecursoNoEncontradoException(String message) {
        super("Recurso no encontrado: " + message);
    }
}
