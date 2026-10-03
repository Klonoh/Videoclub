/**
 * Excepción utilizada cuando una película no existe o no puede participar
 * en la operación solicitada.
 */
public class PeliculaNoDisponibleException extends RuntimeException {
    /**
     * Crea la excepción con el detalle del problema encontrado.
     * @param message mensaje descriptivo del error
     */
    public PeliculaNoDisponibleException(String message) {
        super(message);
    }
}
