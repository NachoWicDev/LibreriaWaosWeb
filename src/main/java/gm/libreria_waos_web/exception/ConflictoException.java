package gm.libreria_waos_web.exception;

public class ConflictoException extends BaseException {
    public ConflictoException(String message) {
        super("Conflicto: " + message);
    }
}
