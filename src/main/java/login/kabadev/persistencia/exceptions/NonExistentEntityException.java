package login.kabadev.persistencia.exceptions;

/**
 * Excepción lanzada cuando se intenta acceder o modificar una entidad que no existe en la base de datos.
 * 
 * <p>
 * Esta excepción se utiliza para indicar que una operación de persistencia
 * no puede completarse porque la entidad objetivo no se encuentra registrada.
 * </p>
 * 
 * @author kabal
 */
public class NonExistentEntityException extends Exception {

    /**
     * Crea una nueva instancia de NonexistentEntityException con un mensaje detallado y una causa.
     *
     * @param message Mensaje que describe el error.
     * @param cause   Causa original de la excepción.
     */
    public NonExistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva instancia de NonexistentEntityException con un mensaje detallado.
     *
     * @param message Mensaje que describe el error.
     */
    public NonExistentEntityException(String message) {
        super(message);
    }
}