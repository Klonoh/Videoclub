public class Pelicula{
    private int idPelicula;
    private String titulo;
    private String director;
    private String genero;
    private int fechaEstreno;
    private int stockTotal;
    private int stockDisponible;

    public Pelicula(int idPelicula, String titulo, String director, String genero, int fechaEstreno, int stockTotal) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.fechaEstreno = fechaEstreno;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockTotal; 
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public int getFechaEstreno() {
        return fechaEstreno;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFechaEstreno(int fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public boolean hayStock() {
        return stockDisponible > 0;
    }

    public void disminuirStock() {
        stockDisponible--;
    }
}
