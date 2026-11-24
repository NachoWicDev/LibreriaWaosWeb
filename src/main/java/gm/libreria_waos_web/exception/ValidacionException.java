package gm.libreria_waos_web.exception;

public class ValidacionException extends BaseException {
    public ValidacionException(String message) {
        super("Error de validación: " + message);
    }
}
