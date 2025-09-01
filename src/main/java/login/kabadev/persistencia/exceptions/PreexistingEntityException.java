package login.kabadev.persistencia.exceptions;

/**
 * Excepción lanzada cuando se intenta crear una entidad que ya existe en la base de datos.
 * 
 * <p>
 * Esta excepción se utiliza para señalar que una operación de persistencia
 * no puede completarse porque la entidad que se intenta crear ya está presente.
 * </p>
 * 
 * @author kabal
 */
public class PreexistingEntityException extends Exception {

    /**
     * Crea una nueva instancia de PreexistingEntityException con un mensaje detallado y una causa.
     *
     * @param message Mensaje que describe el error.
     * @param cause   Causa original de la excepción.
     */
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva instancia de PreexistingEntityException con un mensaje detallado.
     *
     * @param message Mensaje que describe el error.
     */
    public PreexistingEntityException(String message) {
        super(message);
    }
}