import java.time.LocalDate;

public class Arriendo {

    private Cliente cliente;
    private Pelicula pelicula;
    private LocalDate fechaArriendo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    public Arriendo(Cliente cliente, Pelicula pelicula) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.fechaArriendo = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
    }

    public Arriendo(Cliente cliente, Pelicula pelicula, LocalDate fechaArriendo) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.fechaArriendo = fechaArriendo;
        this.fechaDevolucion = null;
        this.devuelto = false;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setFechaArriendo(LocalDate fechaArriendo) {
        this.fechaArriendo = fechaArriendo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public LocalDate getFechaArriendo() {
        return fechaArriendo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}