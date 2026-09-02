public class Arriendo{
    private Cliente cliente;
    private Pelicula pelicula;
    private boolean devuelto;

    public Arriendo(Cliente cliente, Pelicula pelicula) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.devuelto = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}

