public class Recomendacion {

    private Cliente cliente;
    private Pelicula pelicula;
    private boolean exitosa;

    public Recomendacion(Cliente cliente, Pelicula pelicula) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.exitosa = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public boolean isExitosa() {
        return exitosa;
    }
}
