/**
 * Centraliza el formato textual de los principales objetos del sistema.
 * Sus métodos sobrecargados permiten formatear distintos tipos de objetos.
 */
public class Formateador {

    /**
     * Obtiene la representación textual de un cliente.
     * @param cliente cliente a formatear
     * @return texto representativo del cliente
     */
    public String formatear(Cliente cliente) {
        return cliente.toString();
    }

    /**
     * Obtiene la representación textual de una película.
     * @param pelicula película a formatear
     * @return texto representativo de la película
     */
    public String formatear(Pelicula pelicula) {
        return pelicula.toString();
    }

    /**
     * Obtiene la representación textual de un arriendo.
     * @param arriendo arriendo a formatear
     * @return texto representativo del arriendo
     */
    public String formatear(Arriendo arriendo) {
        return arriendo.toString();
    }
}
