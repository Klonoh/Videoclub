/**
 * Representa una interacción entre un cliente y una película.
 * Sirve como abstracción común para arriendos y recomendaciones.
 */
public abstract class InteraccionClientePelicula {

    private Cliente cliente;
    private Pelicula pelicula;

    public InteraccionClientePelicula(Cliente cliente, Pelicula pelicula) {
        this.cliente = cliente;
        this.pelicula = pelicula;
    }

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public Pelicula getPelicula() {return pelicula;}

    public void setPelicula(Pelicula pelicula) {this.pelicula = pelicula;}

    /**
     * Indica si la interacción ya se considera finalizada según su tipo.
     *
     * @return true si la interacción está finalizada; false en caso contrario
     */
    public abstract boolean estaFinalizada();
}
