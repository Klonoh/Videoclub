import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona las recomendaciones de películas y calcula sugerencias para
 * los clientes a partir de su historial y de recomendaciones exitosas.
 */
public class GestorRecomendaciones {

    private ArrayList<Recomendacion> recomendaciones;

    public GestorRecomendaciones() {
        recomendaciones = new ArrayList<>();
    }

    public ArrayList<Recomendacion> getRecomendaciones() {
        return new ArrayList<>(recomendaciones);
    }

    public void setRecomendaciones(ArrayList<Recomendacion> recomendaciones) {
        this.recomendaciones = new ArrayList<>(recomendaciones);
    }

    public void agregarRecomendacion(Recomendacion recomendacion) {
        recomendaciones.add(recomendacion);
    }

    /**
     * Calcula las preferencias de género contando los arriendos del cliente.
     *
     * @param cliente cliente cuyo historial será analizado
     * @return mapa con cada género y su cantidad de apariciones
     */
    public Map<String, Integer> obtenerPreferenciasGenero(Cliente cliente) {
        Map<String, Integer> preferencias = new HashMap<>();

        for (Arriendo arriendo : cliente.getHistorial()) {
            String genero = arriendo.getPelicula().getGenero();

            preferencias.put(
                    genero,
                    preferencias.getOrDefault(genero, 0) + 1
            );
        }

        return preferencias;
    }

    private int obtenerExitosGenero(int idCliente, String genero) {
        int exitos = 0;

        for (Recomendacion recomendacion : recomendaciones) {
            if (recomendacion.getCliente().getIdCliente() == idCliente
                    && recomendacion.isExitosa()
                    && recomendacion.getPelicula().getGenero().equals(genero)) {
                exitos++;
            }
        }

        return exitos;
    }

    private int obtenerExitosPelicula(int idPelicula) {
        int exitos = 0;

        for (Recomendacion recomendacion : recomendaciones) {
            if (recomendacion.getPelicula().getIdPelicula() == idPelicula
                    && recomendacion.isExitosa()) {
                exitos++;
            }
        }

        return exitos;
    }

    private boolean yaFueRecomendada(int idCliente, int idPelicula) {
        for (Recomendacion recomendacion : recomendaciones) {
            if (recomendacion.getCliente().getIdCliente() == idCliente
                    && recomendacion.getPelicula().getIdPelicula() == idPelicula
                    && recomendacion.isExitosa()) {
                return true;
            }
        }

        return false;
    }

    private void registrarRecomendacionSiNoExiste(Cliente cliente, Pelicula pelicula) {
        for (Recomendacion recomendacion : recomendaciones) {
            if (recomendacion.getCliente().getIdCliente() == cliente.getIdCliente()
                    && recomendacion.getPelicula().getIdPelicula() == pelicula.getIdPelicula()) {
                return;
            }
        }

        agregarRecomendacion(new Recomendacion(cliente, pelicula));
    }

    /**
     * Marca como exitosa una recomendación pendiente cuando el cliente
     * arrienda la película recomendada.
     *
     * @param idCliente identificador del cliente
     * @param idPelicula identificador de la película
     */
    public void marcarRecomendacionExitosa(int idCliente, int idPelicula) {
        for (Recomendacion recomendacion : recomendaciones) {
            if (recomendacion.getCliente().getIdCliente() == idCliente
                    && recomendacion.getPelicula().getIdPelicula() == idPelicula
                    && !recomendacion.estaFinalizada()) {

                recomendacion.setExitosa(true);
                break;
            }
        }
    }

    private ArrayList<Pelicula> generarRecomendacionesClienteNuevo(
            Cliente cliente,
            int cantidad,
            List<Pelicula> peliculas,
            GestorArriendos gestorArriendos) {

        Map<Pelicula, Integer> puntajes = new HashMap<>();

        for (Pelicula pelicula : peliculas) {
            if (!pelicula.hayStock()) {
                continue;
            }

            if (yaFueRecomendada(
                    cliente.getIdCliente(),
                    pelicula.getIdPelicula())) {
                continue;
            }

            int cantidadArriendos =
                    gestorArriendos.obtenerCantidadArriendos(
                            pelicula.getIdPelicula()
                    );

            int exitos =
                    obtenerExitosPelicula(pelicula.getIdPelicula());

            int puntaje = cantidadArriendos + (2 * exitos);

            puntajes.put(pelicula, puntaje);
        }

        ArrayList<Pelicula> candidatas =
                new ArrayList<>(puntajes.keySet());

        candidatas.sort(
                (p1, p2) -> Integer.compare(
                        puntajes.get(p2),
                        puntajes.get(p1)
                )
        );

        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0;
             i < candidatas.size() && i < cantidad;
             i++) {

            Pelicula pelicula = candidatas.get(i);

            resultado.add(pelicula);
            registrarRecomendacionSiNoExiste(cliente, pelicula);
        }

        return resultado;
    }

    /**
     * Genera recomendaciones considerando historial, géneros preferidos,
     * recomendaciones exitosas, stock y arriendos activos. Para clientes sin
     * historial utiliza la popularidad de arriendos y recomendaciones exitosas.
     *
     * @param cliente cliente para el cual se generan recomendaciones
     * @param cantidad cantidad máxima de películas a recomendar
     * @param peliculas películas disponibles en el videoclub
     * @param gestorArriendos gestor utilizado para consultar los arriendos
     * @return lista de películas recomendadas
     */
    public ArrayList<Pelicula> generarRecomendaciones(
            Cliente cliente,
            int cantidad,
            List<Pelicula> peliculas,
            GestorArriendos gestorArriendos) {

        Map<String, Integer> preferencias =
                obtenerPreferenciasGenero(cliente);

        if (preferencias.isEmpty()) {
            return generarRecomendacionesClienteNuevo(
                    cliente,
                    cantidad,
                    peliculas,
                    gestorArriendos
            );
        }

        Map<Pelicula, Integer> puntajes = new HashMap<>();

        for (Pelicula pelicula : peliculas) {
            if (!pelicula.hayStock()) {
                continue;
            }

            if (gestorArriendos.tienePeliculaArrendada(
                    cliente.getIdCliente(),
                    pelicula.getIdPelicula())) {
                continue;
            }

            if (yaFueRecomendada(
                    cliente.getIdCliente(),
                    pelicula.getIdPelicula())) {
                continue;
            }

            String genero = pelicula.getGenero();

            int puntajeHistorial =
                    preferencias.getOrDefault(genero, 0);

            int puntajeExitos =
                    obtenerExitosGenero(
                            cliente.getIdCliente(),
                            genero
                    );

            // las recomendaciones exitosas pesan el doble
            int puntaje =
                    puntajeHistorial + (2 * puntajeExitos);

            if (puntaje > 0) {
                puntajes.put(pelicula, puntaje);
            }
        }

        ArrayList<Pelicula> candidatas =
                new ArrayList<>(puntajes.keySet());

        candidatas.sort(
                (p1, p2) -> Integer.compare(
                        puntajes.get(p2),
                        puntajes.get(p1)
                )
        );

        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0;
             i < candidatas.size() && i < cantidad;
             i++) {

            Pelicula pelicula = candidatas.get(i);

            resultado.add(pelicula);
            registrarRecomendacionSiNoExiste(cliente, pelicula);
        }

        return resultado;
    }
}