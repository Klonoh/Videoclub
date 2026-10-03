import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase principal del dominio que coordina clientes, películas, arriendos
 * y recomendaciones, delegando las operaciones especializadas a sus gestores.
 */
public class VideoClub{
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Pelicula> peliculas;
    private GestorArriendos gestorArriendos;
    private GestorRecomendaciones gestorRecomendaciones;

    public VideoClub() {
        clientes = new HashMap<>();
        peliculas = new HashMap<>();
        gestorArriendos = new GestorArriendos();
        gestorRecomendaciones = new GestorRecomendaciones();
    }

    public Map<Integer, Cliente> getClientes() {
        return new HashMap<>(clientes);
    }

    public void setClientes(Map<Integer, Cliente> clientes) {
        this.clientes = new HashMap<>(clientes);
    }

    public Map<Integer, Pelicula> getPeliculas() {
        return new HashMap<>(peliculas);
    }

    public void setPeliculas(Map<Integer, Pelicula> peliculas) {
        this.peliculas = new HashMap<>(peliculas);
    }

    public GestorArriendos getGestorArriendos() {
        return gestorArriendos;
    }

    public void setGestorArriendos(GestorArriendos gestorArriendos) {
        this.gestorArriendos = gestorArriendos;
    }

    public GestorRecomendaciones getGestorRecomendaciones() {
        return gestorRecomendaciones;
    }

    public void setGestorRecomendaciones(GestorRecomendaciones gestorRecomendaciones) {
        this.gestorRecomendaciones = gestorRecomendaciones;
    }

    public ArrayList<Arriendo> getArriendos() {
        return gestorArriendos.getArriendos();
    }

    public void setArriendos(ArrayList<Arriendo> arriendos) {
        gestorArriendos.setArriendos(arriendos);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getIdCliente(), cliente);
    }

    /**
     * Busca un cliente por su identificador.
     * @param idCliente identificador del cliente
     * @return cliente encontrado
     * @throws ClienteNoEncontradoException si el cliente no existe
     */
    public Cliente buscarCliente(int idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = clientes.get(idCliente);

        if (cliente == null) {
            throw new ClienteNoEncontradoException(idCliente);
        }
        return cliente;
    }

    public void registrarPelicula(Pelicula pelicula) {
        peliculas.put(pelicula.getIdPelicula(), pelicula);
    }

    /**
     * Busca una película por su identificador.
     * @param idPelicula identificador de la película
     * @return película encontrada
     * @throws PeliculaNoDisponibleException si la película no existe
     */
    public Pelicula buscarPelicula(int idPelicula) throws PeliculaNoDisponibleException {
        Pelicula pelicula = peliculas.get(idPelicula);
        if (pelicula == null) {
            throw new PeliculaNoDisponibleException("No existe una pelicula con id " + idPelicula);
        }
        return pelicula;
    }

    /**
     * Busca películas por coincidencia parcial de título o género.
     * Esta sobrecarga permite realizar búsquedas mediante texto.
     *
     * @param textoCriterio texto utilizado como criterio de búsqueda
     * @return lista de películas coincidentes; puede estar vacía
     */
    public List<Pelicula> buscarPelicula(String textoCriterio) {
        List<Pelicula> resultado = new ArrayList<>();
        String criterio = textoCriterio.toLowerCase();
        for (Pelicula p : peliculas.values()) {
            if (p.getTitulo().toLowerCase().contains(criterio) || p.getGenero().toLowerCase().contains(criterio)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean eliminarCliente(int idCliente) throws ClienteNoEncontradoException {

        buscarCliente(idCliente);

        for (Arriendo arriendo : gestorArriendos.getArriendos()) {

            if (arriendo.getCliente().getIdCliente() == idCliente) {
                return false;
            }
        }

        for (Recomendacion recomendacion : gestorRecomendaciones.getRecomendaciones()) {

            if (recomendacion.getCliente().getIdCliente() == idCliente) {
                return false;
            }
        }

        clientes.remove(idCliente);
        return true;
    }

    public boolean eliminarPelicula(int idPelicula) throws PeliculaNoDisponibleException {

        buscarPelicula(idPelicula);

        for (Arriendo arriendo : gestorArriendos.getArriendos()) {

            if (arriendo.getPelicula().getIdPelicula() == idPelicula) {
                return false;
            }
        }

        for (Recomendacion recomendacion : gestorRecomendaciones.getRecomendaciones()) {

            if (recomendacion.getPelicula().getIdPelicula() == idPelicula) {
                return false;
            }
        }

        peliculas.remove(idPelicula);
        return true;
    }

    public void agregarArriendo(Arriendo arriendo) {
        gestorArriendos.agregarArriendo(arriendo);
    }

    public void agregarRecomendacion(Recomendacion recomendacion) {
        gestorRecomendaciones.agregarRecomendacion(recomendacion);
    }

    /**
     * Coordina el arriendo de una película y actualiza una recomendación
     * pendiente si corresponde.
     *
     * @param idCliente identificador del cliente
     * @param idPelicula identificador de la película
     * @throws PeliculaNoDisponibleException si la película no existe o no tiene stock
     * @throws ClienteNoEncontradoException si el cliente no existe
     */
    public void realizarArriendo(int idCliente, int idPelicula) throws PeliculaNoDisponibleException, ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(idCliente);
        Pelicula pelicula = buscarPelicula(idPelicula);

        gestorArriendos.realizarArriendo(cliente, pelicula);
        gestorRecomendaciones.marcarRecomendacionExitosa(idCliente, idPelicula);
    }

    public void realizarDevolucion(int idCliente, int idPelicula) {
        gestorArriendos.realizarDevolucion(idCliente, idPelicula);
    }

    public ArrayList<Arriendo> obtenerHistorialCliente(int idCliente) {

        try {

            Cliente cliente = buscarCliente(idCliente);

            return new ArrayList<>(cliente.getHistorial());

        } catch (ClienteNoEncontradoException e) {

            return new ArrayList<>();
        }
    }

    public ArrayList<Recomendacion> getRecomendaciones() {
        return gestorRecomendaciones.getRecomendaciones();
    }

    public void setRecomendaciones(ArrayList<Recomendacion> recomendaciones) {
        gestorRecomendaciones.setRecomendaciones(recomendaciones);
    }

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    public ArrayList<Pelicula> listarPeliculas() {
        return new ArrayList<>(peliculas.values());
    }

    public ArrayList<Arriendo> listarArriendos() {return gestorArriendos.listarArriendos();}

    // Calcula las preferencias del cliente contando cuántas veces ha arrendado películas de cada género.
    public Map<String, Integer> obtenerPreferenciasGenero(int idCliente) {
        try {
            Cliente cliente = buscarCliente(idCliente);
            return gestorRecomendaciones.obtenerPreferenciasGenero(cliente);
        } catch (ClienteNoEncontradoException e) {
            return new HashMap<>();
        }
    }

    /**
     * Genera recomendaciones para un cliente delegando el cálculo al gestor.
     *
     * @param idCliente identificador del cliente
     * @param cantidad cantidad máxima de recomendaciones
     * @return películas recomendadas
     * @throws ClienteNoEncontradoException si el cliente no existe
     */
    public ArrayList<Pelicula> generarRecomendaciones(int idCliente, int cantidad) throws ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(idCliente);
        return gestorRecomendaciones.generarRecomendaciones(cliente, cantidad, new ArrayList<>(peliculas.values()), gestorArriendos);
    }
    
    public void editarCliente(int idCliente,String nombre,String apellido,String contacto) throws ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(idCliente);

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setContacto(contacto);
    }

    public void editarPelicula(int idPelicula, String titulo, String director, String genero, int fechaEstreno) throws PeliculaNoDisponibleException {

        Pelicula pelicula = buscarPelicula(idPelicula);

        pelicula.setTitulo(titulo);
        pelicula.setDirector(director);
        pelicula.setGenero(genero);
        pelicula.setFechaEstreno(fechaEstreno);
    }

    public ArrayList<Arriendo> listarArriendosCliente(int idCliente) throws ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(idCliente);

        return new ArrayList<>(cliente.getHistorial());
    }

    public Arriendo buscarArriendo(int idCliente, int numero) throws ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(idCliente);
        return gestorArriendos.buscarArriendo(cliente, numero);
    }

    public void editarArriendo(int idCliente, int numero, LocalDate nuevaFecha) throws ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(idCliente);
        gestorArriendos.editarArriendo(cliente, numero, nuevaFecha);
    }

    public void eliminarArriendo(int idCliente, int numero) throws ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(idCliente);
        gestorArriendos.eliminarArriendo(cliente, numero);
    }

    /**
     * Obtiene la cantidad de arriendos agrupados por genero.
     *
     * @return estadisticas de arriendos por genero
     */
    public Map<String, Integer> obtenerArriendosPorGenero() {
        return gestorArriendos.obtenerArriendosPorGenero();
    }
}

