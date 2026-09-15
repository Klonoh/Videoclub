import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getIdCliente(), cliente);
    }

    public Cliente buscarCliente(int idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = clientes.get(idCliente);

        if (cliente == null) {
            throw new ClienteNoEncontradoException(idCliente);
        }
        return clientes.get(idCliente);
    }

    public void registrarPelicula(Pelicula pelicula) {
        peliculas.put(pelicula.getIdPelicula(), pelicula);
    }

    public Pelicula buscarPelicula(int idPelicula) throws PeliculaNoDisponibleException {
        Pelicula pelicula = peliculas.get(idPelicula);

        if (pelicula == null) {
            throw new PeliculaNoDisponibleException("No se encontro una pelicula con ID: " + idPelicula);
        }

        return peliculas.get(idPelicula);
    }

    public void eliminarCliente(int idCliente) {
        clientes.remove(idCliente);
    }

    public void eliminarPelicula(int idPelicula) {
        peliculas.remove(idPelicula);
    }

    public void realizarArriendo(int idCliente, int idPelicula) throws PeliculaNoDisponibleException, ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(idCliente);
        Pelicula pelicula = buscarPelicula(idPelicula);

        if (!pelicula.hayStock()) {
            throw new PeliculaNoDisponibleException("La pelicula no tiene stock disponible");
        }

        if (cliente != null && pelicula != null && pelicula.hayStock()) {
            Arriendo arriendo = new Arriendo(cliente, pelicula);
            arriendos.add(arriendo);
            pelicula.disminuirStock();
        }
    }

    public void realizarDevolucion(int idCliente, int idPelicula) {
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente().getIdCliente() == idCliente && arriendo.getPelicula().getIdPelicula() == idPelicula && !arriendo.isDevuelto()) {
                arriendo.setDevuelto(true);
                arriendo.getPelicula().setStockDisponible(arriendo.getPelicula().getStockDisponible() + 1);
                break;
            }
        }
    }
    public ArrayList<Recomendacion> getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(ArrayList<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
}
