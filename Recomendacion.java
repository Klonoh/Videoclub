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

    //en una recomendación la interacción se considera finalizada cuando fue exitosa
    @Override
    public boolean estaFinalizada() {
        return exitosa;
    }
}
