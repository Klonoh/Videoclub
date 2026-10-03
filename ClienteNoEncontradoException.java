/**
 * Excepción utilizada cuando se intenta operar con un cliente inexistente.
 */
public class ClienteNoEncontradoException extends Exception {

    /**
     * Crea la excepción indicando el identificador no encontrado.
     * @param idCliente identificador del cliente inexistente
     */
    public ClienteNoEncontradoException(int idCliente) {
        super("No se encontro un cliente con ID: " + idCliente);
    }
}
