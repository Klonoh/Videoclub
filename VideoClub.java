import java.util.ArrayList;

public class VideoClub{
    private ArrayList<Cliente> clientes;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<Arriendo> arriendos;
    private ArrayList<Recomendacion> recomendaciones;

    public VideoClub() {
        clientes = new ArrayList<>();
        peliculas = new ArrayList<>();
        arriendos = new ArrayList<>();
        recomendaciones = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public void registrarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public Pelicula buscarPelicula(int idPelicula) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getIdPelicula() == idPelicula) {
                return pelicula;
            }
        }
        return null;
    }

    public void realizarArriendo(int idCliente, int idPelicula) {
        Cliente cliente = buscarCliente(idCliente);
        Pelicula pelicula = buscarPelicula(idPelicula);

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
}
