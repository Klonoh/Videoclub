import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorArriendos {

    private ArrayList<Arriendo> arriendos;

    public GestorArriendos() {
        arriendos = new ArrayList<>();
    }

    public ArrayList<Arriendo> getArriendos() {
        return new ArrayList<>(arriendos);
    }

    public void setArriendos(ArrayList<Arriendo> arriendos) {
        this.arriendos = new ArrayList<>(arriendos);
    }

    public void agregarArriendo(Arriendo arriendo) {
        arriendos.add(arriendo);
        arriendo.getCliente().agregarArriendo(arriendo);
    }

    public void realizarArriendo(Cliente cliente, Pelicula pelicula)
            throws PeliculaNoDisponibleException {

        if (!pelicula.hayStock()) {
            throw new PeliculaNoDisponibleException(
                    "La pelicula no tiene stock disponible"
            );
        }

        Arriendo arriendo = new Arriendo(cliente, pelicula);

        agregarArriendo(arriendo);
        pelicula.disminuirStock();
    }

    public void realizarDevolucion(int idCliente, int idPelicula) {
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente().getIdCliente() == idCliente
                    && arriendo.getPelicula().getIdPelicula() == idPelicula
                    && !arriendo.estaFinalizada()) {

                arriendo.setDevuelto(true);
                arriendo.setFechaDevolucion(LocalDate.now());

                Pelicula pelicula = arriendo.getPelicula();
                pelicula.setStockDisponible(
                        pelicula.getStockDisponible() + 1
                );

                break;
            }
        }
    }

    public ArrayList<Arriendo> listarArriendos() {
        return new ArrayList<>(arriendos);
    }

    public Arriendo buscarArriendo(Cliente cliente, int numero) {
        List<Arriendo> historial = cliente.getHistorial();

        if (numero < 1 || numero > historial.size()) {
            throw new IllegalArgumentException(
                    "No existe un arriendo con ese numero."
            );
        }

        return historial.get(numero - 1);
    }

    public void editarArriendo(
            Cliente cliente,
            int numero,
            LocalDate nuevaFecha) {

        Arriendo arriendo = buscarArriendo(cliente, numero);
        arriendo.setFechaArriendo(nuevaFecha);
    }

    public void eliminarArriendo(Cliente cliente, int numero) {
        Arriendo arriendo = buscarArriendo(cliente, numero);

        if (!arriendo.isDevuelto()) {
            Pelicula pelicula = arriendo.getPelicula();

            pelicula.setStockDisponible(
                    pelicula.getStockDisponible() + 1
            );
        }

        arriendos.remove(arriendo);
        cliente.eliminarArriendo(arriendo);
    }

    public int obtenerCantidadArriendos(int idPelicula) {
        int cantidad = 0;

        for (Arriendo arriendo : arriendos) {
            if (arriendo.getPelicula().getIdPelicula() == idPelicula) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public boolean tienePeliculaArrendada(int idCliente, int idPelicula) {
        for (Arriendo arriendo : arriendos) {
            if (arriendo.getCliente().getIdCliente() == idCliente
                    && arriendo.getPelicula().getIdPelicula() == idPelicula
                    && !arriendo.estaFinalizada()) {
                return true;
            }
        }

        return false;
    }
}