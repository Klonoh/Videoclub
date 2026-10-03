/**
 * Representa una película recomendada a un cliente y registra si la
 * recomendación terminó siendo exitosa.
 */
public class Recomendacion extends InteraccionClientePelicula{

    private boolean exitosa;

    public Recomendacion(Cliente cliente, Pelicula pelicula) {
        super(cliente, pelicula);
        this.exitosa = false;
    }

    public boolean isExitosa() {
        return exitosa;
    }

    public void setExitosa(boolean exitosa) {
        this.exitosa = exitosa;
    }

    /**
     * En una recomendación, la interacción finaliza cuando esta fue exitosa.
     *
     * @return true si la recomendación fue exitosa
     */
    @Override
    public boolean estaFinalizada() {
        return exitosa;
    }
}
