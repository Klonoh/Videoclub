import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;

public class VideoClub{
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Pelicula> peliculas;
    private ArrayList<Arriendo> arriendos;
    private ArrayList<Recomendacion> recomendaciones;

    public VideoClub() {
        clientes = new HashMap<>();
        peliculas = new HashMap<>();
        arriendos = new ArrayList<>();
        recomendaciones = new ArrayList<>();
    }

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<Integer, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Map<Integer, Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Map<Integer, Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public ArrayList<Arriendo> getArriendos() {
        return arriendos;
    }

    public void setArriendos(ArrayList<Arriendo> arriendos) {
        this.arriendos = arriendos;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getIdCliente(), cliente);
    }

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

    public Pelicula buscarPelicula(int idPelicula) throws PeliculaNoDisponibleException {
        Pelicula pelicula = peliculas.get(idPelicula);
        if (pelicula == null) {
            throw new PeliculaNoDisponibleException("No existe una pelicula con id " + idPelicula);
        }
        return pelicula;
    }

    /** Sobrecarga: busca por coincidencia parcial de titulo o genero (no lanza excepcion, retorna lista vacia si no hay). */
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

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getCliente().getIdCliente() == idCliente) {
                return false;
            }
        }

        for (Recomendacion recomendacion : recomendaciones) {

            if (recomendacion.getCliente().getIdCliente() == idCliente) {
                return false;
            }
        }

        clientes.remove(idCliente);
        return true;
    }

    public boolean eliminarPelicula(int idPelicula) throws PeliculaNoDisponibleException {

        buscarPelicula(idPelicula);

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getPelicula().getIdPelicula() == idPelicula) {
                return false;
            }
        }

        for (Recomendacion recomendacion : recomendaciones) {

            if (recomendacion.getPelicula().getIdPelicula() == idPelicula) {
                return false;
            }
        }

        peliculas.remove(idPelicula);
        return true;
    }

    public void agregarArriendo(Arriendo arriendo) {

        arriendos.add(arriendo);

        arriendo.getCliente().agregarArriendo(arriendo);
    }

    public void agregarRecomendacion(Recomendacion recomendacion) {
        recomendaciones.add(recomendacion);
    }

    public void realizarArriendo(int idCliente, int idPelicula) throws PeliculaNoDisponibleException, ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(idCliente);
        Pelicula pelicula = buscarPelicula(idPelicula);

        if (!pelicula.hayStock()) {
            throw new PeliculaNoDisponibleException("La pelicula no tiene stock disponible");
        }

        if (cliente != null && pelicula != null && pelicula.hayStock()) {

            Arriendo arriendo = new Arriendo(cliente, pelicula);

            agregarArriendo(arriendo);
            pelicula.disminuirStock();

            for (Recomendacion recomendacion : recomendaciones) {

                if (recomendacion.getCliente().getIdCliente() == idCliente &&
                    recomendacion.getPelicula().getIdPelicula() == idPelicula &&
                    !recomendacion.isExitosa()) {

                    recomendacion.setExitosa(true);
                    break;
                }
            }
        }
    }

    public void realizarDevolucion(int idCliente, int idPelicula) {
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente().getIdCliente() == idCliente && arriendo.getPelicula().getIdPelicula() == idPelicula && !arriendo.isDevuelto()) {
                arriendo.setDevuelto(true);
                arriendo.setFechaDevolucion(LocalDate.now());
                arriendo.getPelicula().setStockDisponible(arriendo.getPelicula().getStockDisponible() + 1);
                break;
            }
        }
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
        return recomendaciones;
    }

    public void setRecomendaciones(ArrayList<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    public ArrayList<Pelicula> listarPeliculas() {
        return new ArrayList<>(peliculas.values());
    }

    public ArrayList<Arriendo> listarArriendos() {
        return arriendos;
    }

    // Calcula las preferencias del cliente contando cuántas veces ha arrendado películas de cada género.
    public Map<String, Integer> obtenerPreferenciasGenero(int idCliente) {

        Map<String, Integer> preferencias = new HashMap<>();

        ArrayList<Arriendo> historial =
            obtenerHistorialCliente(idCliente);

        for (Arriendo arriendo : historial) {

            String genero =
                arriendo.getPelicula().getGenero();

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

            if (recomendacion.getCliente().getIdCliente() == idCliente &&
                recomendacion.isExitosa() &&
                recomendacion.getPelicula().getGenero().equals(genero)) {

                exitos++;
            }
        }

        return exitos;
    }

    private int obtenerCantidadArriendos(int idPelicula) {

        int cantidad = 0;

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getPelicula().getIdPelicula() == idPelicula) {
                cantidad++;
            }
        }

        return cantidad;
    }

    private int obtenerExitosPelicula(int idPelicula) {

        int exitos = 0;

        for (Recomendacion recomendacion : recomendaciones) {

            if (recomendacion.getPelicula().getIdPelicula() == idPelicula &&
                recomendacion.isExitosa()) {

                exitos++;
            }
        }

        return exitos;
    }

    private boolean tienePeliculaArrendada(int idCliente, int idPelicula) {

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getCliente().getIdCliente() == idCliente &&
                arriendo.getPelicula().getIdPelicula() == idPelicula &&
                !arriendo.isDevuelto()) {

                return true;
            }
        }

        return false;
    }

    private boolean yaFueRecomendada(int idCliente,int idPelicula) {

        for (Recomendacion recomendacion : recomendaciones) {

            if (recomendacion.getCliente().getIdCliente() == idCliente && recomendacion.getPelicula().getIdPelicula() == idPelicula) {

                return true;
            }
        }

        return false;
    }

    // Para clientes sin historial se priorizan peliculas con mayor cantidad de arriendos y exitos, sin importar el género.
    private ArrayList<Pelicula> generarRecomendacionesClienteNuevo(Cliente cliente,int cantidad) {

        Map<Pelicula, Integer> puntajes = new HashMap<>();

        for (Pelicula pelicula : peliculas.values()) {

            if (!pelicula.hayStock()) {
                continue;
            }

            if (yaFueRecomendada(cliente.getIdCliente(),pelicula.getIdPelicula())) {

                continue;
            }

            int cantidadArriendos = obtenerCantidadArriendos(pelicula.getIdPelicula());

            int exitos = obtenerExitosPelicula(pelicula.getIdPelicula());

            int puntaje = cantidadArriendos + (2 * exitos);

            puntajes.put(pelicula, puntaje);
        }

        ArrayList<Pelicula> candidatas = new ArrayList<>(puntajes.keySet());

        candidatas.sort((p1, p2) -> Integer.compare(puntajes.get(p2),puntajes.get(p1)));

        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0; i < candidatas.size() && i < cantidad; i++) {

            Pelicula pelicula = candidatas.get(i);

            resultado.add(pelicula);

            Recomendacion recomendacion = new Recomendacion(cliente, pelicula);

            agregarRecomendacion(recomendacion);
        }

        return resultado;
    }

    public ArrayList<Pelicula> generarRecomendaciones(int idCliente, int cantidad) throws ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(idCliente);

        Map<String, Integer> preferencias = obtenerPreferenciasGenero(idCliente);

        if (preferencias.isEmpty()) {
            return generarRecomendacionesClienteNuevo(cliente, cantidad);
        }

        Map<Pelicula, Integer> puntajes = new HashMap<>();

        for (Pelicula pelicula : peliculas.values()) {

            if (!pelicula.hayStock()) {
                continue;
            }

            if (tienePeliculaArrendada(idCliente,pelicula.getIdPelicula())) {

                continue;
            }

            if (yaFueRecomendada(idCliente,pelicula.getIdPelicula())) {

                continue;
            }

            String genero = pelicula.getGenero();

            int puntajeHistorial = preferencias.getOrDefault(genero, 0);

            int puntajeExitos = obtenerExitosGenero(idCliente, genero);

            // Las recomendaciones exitosas tienen doble peso respecto al historial normal del genero.
            int puntaje = puntajeHistorial + (2 * puntajeExitos);

            if (puntaje > 0) {
                puntajes.put(pelicula, puntaje);
            }
        }

        ArrayList<Pelicula> candidatas = new ArrayList<>(puntajes.keySet());

        candidatas.sort((p1, p2) -> Integer.compare(puntajes.get(p2),puntajes.get(p1)));

        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0; i < candidatas.size() && i < cantidad; i++) {

            Pelicula pelicula = candidatas.get(i);

            resultado.add(pelicula);

            Recomendacion recomendacion = new Recomendacion(cliente,pelicula);

            agregarRecomendacion(recomendacion);
        }

        return resultado;
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

        List<Arriendo> historial = cliente.getHistorial();

        if (numero < 1 || numero > historial.size()) {
            throw new IllegalArgumentException(
                "No existe un arriendo con ese numero."
            );
        }

        return historial.get(numero - 1);
    }

    public void editarArriendo(int idCliente, int numero, LocalDate nuevaFecha) throws ClienteNoEncontradoException {

        Arriendo arriendo = buscarArriendo(idCliente, numero);

        arriendo.setFechaArriendo(nuevaFecha);
    }

    public void eliminarArriendo(int idCliente, int numero) throws ClienteNoEncontradoException {

        Arriendo arriendo = buscarArriendo(idCliente, numero);

        if (!arriendo.isDevuelto()) {

            Pelicula pelicula = arriendo.getPelicula();

            pelicula.setStockDisponible(pelicula.getStockDisponible() + 1);
        }

        arriendos.remove(arriendo);

        arriendo.getCliente().getHistorial().remove(arriendo);
    }

}

