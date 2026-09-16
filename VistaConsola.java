import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class VistaConsola {
    private VideoClub videoClub;
    private Scanner scanner;
    private Formateador formateador;
    public VistaConsola(VideoClub videoClub) {
        this.videoClub = videoClub;
        this.scanner = new Scanner(System.in);
        this.formateador = new Formateador();
    }

    public void iniciar(){
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {

                case 1:
                    agregarCliente();
                    break;

                case 2:
                    agregarPelicula();
                    break;

                case 3:
                    listarClientes();
                    break;

                case 4:
                    listarPeliculas();
                    break;

                case 5:
                    buscarCliente();
                    break;

                case 6:
                    buscarPelicula();
                    break;

                case 7:
                    editarCliente();
                    break;

                case 8:
                    editarPelicula();
                    break;
                    
                case 9:
                    eliminarCliente();
                    break;

                case 10:
                    eliminarPelicula();
                    break;

                case 11:
                    realizarArriendo();
                    break;

                case 12:
                    realizarDevolucion();
                    break;

                case 13:
                    generarRecomendaciones();
                    break;

                case 14:
                    listarArriendos();
                    break;

                case 15:
                    buscarArriendo();
                    break;

                case 16:
                    editarArriendo();
                    break;

                case 17:
                    eliminarArriendo();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }while (opcion != 0);
    }
    private void mostrarMenu() {

        System.out.println("\n=== Video Club ===");

        System.out.println("1. Agregar Cliente");
        System.out.println("2. Agregar Pelicula");

        System.out.println("3. Listar Clientes");
        System.out.println("4. Listar Peliculas");

        System.out.println("5. Buscar Cliente");
        System.out.println("6. Buscar Pelicula");

        System.out.println("7. Editar Cliente");
        System.out.println("8. Editar Pelicula");

        System.out.println("9. Eliminar Cliente");
        System.out.println("10. Eliminar Pelicula");

        System.out.println("11. Realizar Arriendo");
        System.out.println("12. Realizar Devolucion");
        System.out.println("13. Generar Recomendaciones");

        System.out.println("14. Listar Arriendos");
        System.out.println("15. Buscar Arriendo");
        System.out.println("16. Editar Arriendo");
        System.out.println("17. Eliminar Arriendo");

        System.out.println("0. Salir");

        System.out.print("Seleccione una opcion: ");
    }
    private void agregarCliente(){
        System.out.print("ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Contacto del cliente: ");
        String contacto = scanner.nextLine();
        Cliente cliente = new Cliente(id, nombre, apellido, contacto);
        videoClub.agregarCliente(cliente);
        System.out.println("Cliente agregado exitosamente.");
    }
    private void agregarPelicula(){
        System.out.print("ID de la película: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título de la película: ");
        String titulo = scanner.nextLine();
        System.out.print("Director de la película: ");
        String director = scanner.nextLine();
        System.out.print("Género de la película: ");
        String genero = scanner.nextLine();
        System.out.print("Año de estreno: ");
        int fechaEstreno = scanner.nextInt();
        System.out.print("Stock total: ");
        int stockTotal = scanner.nextInt();
        scanner.nextLine();

        Pelicula pelicula = new Pelicula(id, titulo, director, genero, fechaEstreno, stockTotal);
        videoClub.registrarPelicula(pelicula);
        System.out.println("Película agregada exitosamente.");
    }
    private void realizarArriendo() {
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID de la película: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();
        try{
            videoClub.realizarArriendo(idCliente, idPelicula);
            System.out.println("Arriendo realizado exitosamente.");
        } catch(ClienteNoEncontradoException e) {
            System.out.println("Error: Cliente no encontrado.");
        } catch(PeliculaNoDisponibleException e) {
            System.out.println("Error: Película no disponible."); 
        }
       
    }
    private void realizarDevolucion() {
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        System.out.print("ID de la película: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();
        videoClub.realizarDevolucion(idCliente, idPelicula);
        System.out.println("Devolución realizada exitosamente.");
    }

    private void generarRecomendaciones() {

        try {

            System.out.print("ID del cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Cantidad de recomendaciones: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a cero.");
                return;
            }

            Cliente cliente = videoClub.buscarCliente(idCliente);

            ArrayList<Pelicula> recomendadas =
                    videoClub.generarRecomendaciones(idCliente, cantidad);

            System.out.println("\n=== Recomendaciones para " + cliente.getNombre() + " ===");

            if (recomendadas.isEmpty()) {
                System.out.println("No hay peliculas para recomendar en este momento.");
                return;
            }

            for (Pelicula pelicula : recomendadas) {
                System.out.println(formateador.formatear(pelicula));
            }

        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe ingresar un numero valido.");
            scanner.nextLine();
        }
    }

    private void buscarCliente(){
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
         try{
            Cliente cliente = videoClub.buscarCliente(idCliente);
            System.out.println("Cliente encontrado:");
             System.out.println(formateador.formatear(cliente));
         } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: Cliente no encontrado.");
         }
    }
    private void buscarPelicula(){
        System.out.print("ID de la película: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();
        try{
            Pelicula pelicula = videoClub.buscarPelicula(idPelicula);
            System.out.println("Película encontrada:");
            System.out.println(formateador.formatear(pelicula));
        } catch (PeliculaNoDisponibleException e) {
            System.out.println("Error: Película no disponible.");
        }
    }
    private void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        try {
            boolean eliminado = videoClub.eliminarCliente(idCliente);

            if (eliminado) {
                System.out.println("Cliente eliminado exitosamente.");
            } else {
                System.out.println("No se puede eliminar el cliente porque posee historial asociado.");
            }

        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void eliminarPelicula() {
        System.out.print("ID de la película a eliminar: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();
        
        try {
            boolean eliminado = videoClub.eliminarPelicula(idPelicula);

            if (eliminado) {
                System.out.println("Película eliminada exitosamente.");
            } else {
                System.out.println("No se puede eliminar la película porque posee historial asociado.");
            }

        } catch (PeliculaNoDisponibleException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarClientes() {

        System.out.println("\n=== Lista de Clientes ===");

        ArrayList<Cliente> clientes = videoClub.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(formateador.formatear(cliente));
        }
    }

    private void listarPeliculas() {

        System.out.println("\n=== Lista de Peliculas ===");

        ArrayList<Pelicula> peliculas = videoClub.listarPeliculas();

        if (peliculas.isEmpty()) {
            System.out.println("No hay peliculas registradas.");
            return;
        }

        for (Pelicula pelicula : peliculas) {
            System.out.println(formateador.formatear(pelicula));
        }
    }

    private void editarCliente() {

        System.out.print("ID del cliente a editar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        try {

            Cliente cliente = videoClub.buscarCliente(idCliente);

            System.out.println("Cliente actual:");
            System.out.println(formateador.formatear(cliente));

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Nuevo apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Nuevo contacto: ");
            String contacto = scanner.nextLine();

            videoClub.editarCliente(idCliente,nombre,apellido,contacto);

            System.out.println("Cliente modificado exitosamente.");

        } catch (ClienteNoEncontradoException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    private void editarPelicula() {

        System.out.print("ID de la pelicula a editar: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();

        try {

            Pelicula pelicula =
                videoClub.buscarPelicula(idPelicula);

            System.out.println("Pelicula actual:");
            System.out.println(formateador.formatear(pelicula));

            System.out.print("Nuevo titulo: ");
            String titulo = scanner.nextLine();

            System.out.print("Nuevo director: ");
            String director = scanner.nextLine();

            System.out.print("Nuevo genero: ");
            String genero = scanner.nextLine();

            System.out.print("Nuevo año de estreno: ");
            int fechaEstreno = scanner.nextInt();
            scanner.nextLine();

            videoClub.editarPelicula(idPelicula,titulo,director,genero,fechaEstreno);

            System.out.println("Pelicula modificada exitosamente.");

        } catch (PeliculaNoDisponibleException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarArriendos() {

        try {

            System.out.print("ID del cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Arriendo> historial =
                videoClub.listarArriendosCliente(idCliente);

            if (historial.isEmpty()) {

                System.out.println(
                    "El cliente no posee arriendos."
                );

                return;
            }

            System.out.println(
                "\n=== Historial de Arriendos ==="
            );

            for (int i = 0; i < historial.size(); i++) {

                System.out.println(
                    (i + 1) + ". " +
                    formateador.formatear(
                        historial.get(i)
                    )
                );
            }

        } catch (ClienteNoEncontradoException e) {

            System.out.println(
                "Error: " + e.getMessage()
            );

        } catch (java.util.InputMismatchException e) {

            System.out.println(
                "Error: debe ingresar un numero valido."
            );

            scanner.nextLine();
        }
    }

    private void buscarArriendo() {

        try {

            System.out.print("ID del cliente: ");
            int idCliente = scanner.nextInt();

            System.out.print("Numero del arriendo: ");
            int numero = scanner.nextInt();

            scanner.nextLine();

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            System.out.println(
                "\nArriendo encontrado:"
            );

            System.out.println(
                formateador.formatear(arriendo)
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            System.out.println(
                "Error: " + e.getMessage()
            );

        } catch (java.util.InputMismatchException e) {

            System.out.println(
                "Error: debe ingresar un numero valido."
            );

            scanner.nextLine();
        }
    }

    private void editarArriendo() {

        try {

            System.out.print("ID del cliente: ");
            int idCliente = scanner.nextInt();

            System.out.print("Numero del arriendo: ");
            int numero = scanner.nextInt();

            scanner.nextLine();

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            System.out.println(
                "Arriendo actual:"
            );

            System.out.println(
                formateador.formatear(arriendo)
            );

            System.out.print(
                "Nueva fecha de arriendo (AAAA-MM-DD): "
            );

            String fecha = scanner.nextLine();

            LocalDate nuevaFecha =
                LocalDate.parse(fecha);

            videoClub.editarArriendo(
                idCliente,
                numero,
                nuevaFecha
            );

            System.out.println(
                "Arriendo editado exitosamente."
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            System.out.println(
                "Error: " + e.getMessage()
            );

        } catch (java.time.format.DateTimeParseException e) {

            System.out.println(
                "Error: fecha invalida. Use AAAA-MM-DD."
            );
        }
    }

    private void eliminarArriendo() {

        try {

            System.out.print("ID del cliente: ");
            int idCliente = scanner.nextInt();

            System.out.print("Numero del arriendo: ");
            int numero = scanner.nextInt();

            scanner.nextLine();

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            System.out.println(
                "Arriendo a eliminar:"
            );

            System.out.println(
                formateador.formatear(arriendo)
            );

            videoClub.eliminarArriendo(
                idCliente,
                numero
            );

            System.out.println(
                "Arriendo eliminado exitosamente."
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            System.out.println(
                "Error: " + e.getMessage()
            );

        } catch (java.util.InputMismatchException e) {

            System.out.println(
                "Error: debe ingresar un numero valido."
            );

            scanner.nextLine();
        }
    }

    public VideoClub getVideoClub() {
        return videoClub;
    }

    public void setVideoClub(VideoClub videoClub) {
        this.videoClub = videoClub;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Formateador getFormateador() {
        return formateador;
    }

    public void setFormateador(Formateador formateador) {
        this.formateador = formateador;
    }
}    