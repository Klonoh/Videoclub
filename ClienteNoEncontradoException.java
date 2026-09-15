public class ClienteNoEncontradoException extends Exception {

    public ClienteNoEncontradoException(int idCliente) {
        super("No se encontro un cliente con ID: " + idCliente);
    }
}
