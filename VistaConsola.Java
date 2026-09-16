import java.util.Scanner;
import java.util.ArrayList;

public class VistaConsola {
    private VideoClub videoClub;
    private Scanner scanner;
    public VistaConsola(VideoClub videoClub) {
        this.videoClub = videoClub;
        this.scanner = new Scanner(System.in);
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
    private void buscarCliente(){
        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
         try{
            Cliente cliente = videoClub.buscarCliente(idCliente);
            System.out.println("Cliente encontrado:");
            System.out.println(formatear(cliente));
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
            System.out.println(formatear(pelicula));
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
            System.out.println(formatear(cliente));
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
            System.out.println(formatear(pelicula));
        }
    }

    private void editarCliente() {

        System.out.print("ID del cliente a editar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        try {

            Cliente cliente = videoClub.buscarCliente(idCliente);

            System.out.println("Cliente actual:");
            System.out.println(formatear(cliente));

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
            System.out.println(formatear(pelicula));

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
}    