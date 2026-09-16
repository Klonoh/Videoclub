import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PersistenciaCSV {
    private static final String ARCHIVO_CLIENTES = "clientes.csv";
    private static final String ARCHIVO_PELICULAS = "peliculas.csv";
    private static final String ARCHIVO_ARRIENDOS = "arriendos.csv";
    private static final String ARCHIVO_RECOMENDACIONES = "recomendaciones.csv";

    public void cargarDatos(VideoClub videoClub) throws IOException {
        File fClientes = new File(ARCHIVO_CLIENTES);
        File fPeliculas = new File(ARCHIVO_PELICULAS);
        File fArriendos = new File(ARCHIVO_ARRIENDOS);
        File fRecomendaciones = new File(ARCHIVO_RECOMENDACIONES);

        boolean primeraEjecucion = !fClientes.exists() && !fPeliculas.exists() && !fArriendos.exists() && !fRecomendaciones.exists();

        if (primeraEjecucion) {

            cargarDatosIniciales(videoClub);
            guardarDatos(videoClub);
            return;
        }

        if (fClientes.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fClientes))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(",", -1);
                    Cliente c = new Cliente(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3]);
                    videoClub.agregarCliente(c);
                }
            }
        }

        if (fPeliculas.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fPeliculas))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(",", -1);
                    Pelicula p = new Pelicula(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3],
                            Integer.parseInt(partes[4]), Integer.parseInt(partes[5]));
                    p.setStockDisponible(Integer.parseInt(partes[6]));
                    videoClub.registrarPelicula(p);
                }
            }
        }

        
        if (fArriendos.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fArriendos))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(",", -1);
                    int idCliente = Integer.parseInt(partes[0]);
                    int idPelicula = Integer.parseInt(partes[1]);
                    LocalDate fechaArriendo = LocalDate.parse(partes[2]);
                    boolean devuelto = Boolean.parseBoolean(partes[4]);

                    try {

                        Cliente cliente = videoClub.buscarCliente(idCliente);
                        Pelicula pelicula = videoClub.buscarPelicula(idPelicula);

                        Arriendo a = new Arriendo(cliente, pelicula, fechaArriendo);

                        a.setDevuelto(devuelto);

                        if (devuelto && !partes[3].equals("null")) {
                            a.setFechaDevolucion(LocalDate.parse(partes[3]));
                        }

                        videoClub.agregarArriendo(a);

                    } catch (ClienteNoEncontradoException | PeliculaNoDisponibleException e) {
                        System.out.println("No se pudo cargar el arriendo: " + e.getMessage());
                    }
                }
            }
        }


        if (fRecomendaciones.exists()) {
            try (BufferedReader br =
                    new BufferedReader(new FileReader(fRecomendaciones))) {

                String linea;

                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;

                    String[] partes = linea.split(",", -1);

                    int idCliente = Integer.parseInt(partes[0]);
                    int idPelicula = Integer.parseInt(partes[1]);
                    boolean exitosa = Boolean.parseBoolean(partes[2]);

                    try {

                        Cliente cliente = videoClub.buscarCliente(idCliente);
                        Pelicula pelicula = videoClub.buscarPelicula(idPelicula);

                        Recomendacion r = new Recomendacion(cliente, pelicula);

                        r.setExitosa(exitosa);

                        videoClub.agregarRecomendacion(r);

                    } catch (ClienteNoEncontradoException | PeliculaNoDisponibleException e) {
                        System.out.println("No se pudo cargar la recomendacion: " + e.getMessage());
                    }
                }
            }
        }

    }

    public void guardarDatos(VideoClub videoClub) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            for (Cliente c : videoClub.listarClientes()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getApellido() + "," + c.getContacto());
                bw.newLine();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PELICULAS))) {
            for (Pelicula p : videoClub.listarPeliculas()) {
                bw.write(p.getIdPelicula() + "," + p.getTitulo() + "," + p.getDirector() + "," + p.getGenero() + ","
                        + p.getFechaEstreno() + "," + p.getStockTotal() + "," + p.getStockDisponible());
                bw.newLine();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ARRIENDOS))) {
            for (Arriendo a : videoClub.listarArriendos()) {
                String fechaDevolucion = (a.getFechaDevolucion() == null) ? "null" : a.getFechaDevolucion().toString();

                bw.write(a.getCliente().getIdCliente() + "," + a.getPelicula().getIdPelicula() + "," + a.getFechaArriendo() + "," + fechaDevolucion + "," + a.isDevuelto());
                bw.newLine();
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RECOMENDACIONES))) {

            for (Recomendacion r : videoClub.getRecomendaciones()) {

                bw.write(r.getCliente().getIdCliente() + "," +r.getPelicula().getIdPelicula() + "," +r.isExitosa());
                bw.newLine();
            }
        }
    }

    private void cargarDatosIniciales(VideoClub videoClub) {
        videoClub.agregarCliente(new Cliente(1, "Sebastian", "Soto", "sebastian@correo.com"));
        videoClub.agregarCliente(new Cliente(2, "Camila", "Perez", "camila@correo.com"));
        videoClub.agregarCliente(new Cliente(3, "Diego", "Rojas", "diego@correo.com"));

        videoClub.registrarPelicula(new Pelicula(101, "Inception", "C. Nolan", "Ciencia Ficcion", 2010, 3));
        videoClub.registrarPelicula(new Pelicula(102, "Terminator 2", "J. Cameron", "Accion", 1991, 2));
        videoClub.registrarPelicula(new Pelicula(103, "Coco", "Lee Unkrich", "Animacion", 2017, 4));
        videoClub.registrarPelicula(new Pelicula(104, "Mad Max: Fury Road", "G. Miller", "Accion", 2015, 2));
        videoClub.registrarPelicula(new Pelicula(105, "El Padrino II", "F. Coppola", "Drama", 1974, 2));
    }
}
