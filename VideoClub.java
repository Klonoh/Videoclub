import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getIdCliente(), cliente);
    }

    public Cliente buscarCliente(int idCliente) {
        return clientes.get(idCliente);
    }

    public void registrarPelicula(Pelicula pelicula) {
        peliculas.put(pelicula.getIdPelicula(), pelicula);
    }

    public Pelicula buscarPelicula(int idPelicula) {
        return peliculas.get(idPelicula);
    }

    public boolean eliminarCliente(int idCliente) {

        Cliente cliente = buscarCliente(idCliente);

        if (cliente == null) {
            return false;
        }

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getCliente().getIdCliente() == idCliente && !arriendo.isDevuelto()) {

                return false;
            }
        }

        clientes.remove(idCliente);
        return true;
    }

    public boolean eliminarPelicula(int idPelicula) {

        Pelicula pelicula = buscarPelicula(idPelicula);

        if (pelicula == null) {
            return false;
        }

        for (Arriendo arriendo : arriendos) {

            if (arriendo.getPelicula().getIdPelicula() == idPelicula && !arriendo.isDevuelto()) {

                return false;
            }
        }

        peliculas.remove(idPelicula);
        return true;
    }

    public void agregarArriendo(Arriendo arriendo) {
        arriendos.add(arriendo);
    }

    public void agregarRecomendacion(Recomendacion recomendacion) {
        recomendaciones.add(recomendacion);
    }

    public void realizarArriendo(int idCliente, int idPelicula) {

        Cliente cliente = buscarCliente(idCliente);
        Pelicula pelicula = buscarPelicula(idPelicula);

        if (cliente != null && pelicula != null && pelicula.hayStock()) {

            Arriendo arriendo = new Arriendo(cliente, pelicula);

            arriendos.add(arriendo);
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

        ArrayList<Arriendo> historial = new ArrayList<>();

        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente().getIdCliente() == idCliente) {
                historial.add(arriendo);
            }
        }

        return historial;
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
}

