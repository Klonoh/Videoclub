public class Recomendacion {

    private Cliente cliente;
    private Pelicula pelicula;
    private boolean exitosa;

    public Recomendacion(Cliente cliente, Pelicula pelicula) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.exitosa = false;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
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

    public void setExitosa(boolean exitosa) {
        this.exitosa = exitosa;
    }
}
