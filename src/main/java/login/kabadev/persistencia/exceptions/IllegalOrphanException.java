package login.kabadev.persistencia.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Excepción lanzada cuando una operación de persistencia provocaría la existencia de entidades huérfanas ilegales.
 * 
 * <p>
 * Esta excepción se utiliza para señalar que una acción no puede completarse porque dejaría entidades relacionadas
 * sin su referencia obligatoria, violando las restricciones de integridad referencial.
 * </p>
 * 
 * @author kabal
 */
public class IllegalOrphanException extends Exception {
    private List<String> messages;

    /**
     * Crea una nueva instancia de IllegalOrphanException con una lista de mensajes detallados.
     *
     * @param messages Lista de mensajes que describen los motivos de la excepción.
     */
    public IllegalOrphanException(List<String> messages) {
        super((messages != null && messages.size() > 0 ? messages.get(0) : null));
        if (messages == null) {
            this.messages = new ArrayList<String>();
        } else {
            this.messages = messages;
        }
    }

    /**
     * Devuelve la lista de mensajes asociados a la excepción.
     *
     * @return Lista de mensajes de error.
     */
    public List<String> getMessages() {
        return messages;
    }
}