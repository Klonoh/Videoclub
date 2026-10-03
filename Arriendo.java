import java.time.LocalDate;

/**
 * Representa el arriendo de una película realizado por un cliente.
 * Mantiene las fechas del arriendo y su estado de devolución.
 */
public class Arriendo extends InteraccionClientePelicula {

    private LocalDate fechaArriendo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    public Arriendo(Cliente cliente, Pelicula pelicula) {
        super(cliente, pelicula);
        this.fechaArriendo = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
    }

    public Arriendo(Cliente cliente, Pelicula pelicula, LocalDate fechaArriendo) {
        super(cliente, pelicula);
        this.fechaArriendo = fechaArriendo;
        this.fechaDevolucion = null;
        this.devuelto = false;
    }

    public void setFechaArriendo(LocalDate fechaArriendo) {
        this.fechaArriendo = fechaArriendo;
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

    /**
     * En un arriendo, la interacción finaliza cuando la película fue devuelta.
     *
     * @return true si el arriendo fue devuelto
     */
    @Override
    public boolean estaFinalizada() {
        return devuelto;
    }

    @Override
    public String toString() {
        return "Cliente: " + getCliente().getIdCliente() +
            " | Pelicula: " + getPelicula().getTitulo() +
            " (" + getPelicula().getIdPelicula() + ")" +
            " | Fecha arriendo: " + fechaArriendo +
            " | Fecha devolucion: " +
            (fechaDevolucion == null ? "Pendiente" : fechaDevolucion) +
            " | Estado: " +
            (devuelto ? "Devuelto" : "Activo");
    }
}