import java.util.Scanner;
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
                    realizarArriendo();
                    break;
                case 4:
                    realizarDevolucion();
                    break;
                case 5:
                    buscarCliente();
                    break;
                case 6:
                    buscarPelicula();
                    break;
                case 7:
                    eliminarCliente();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default: 
                    System.out.println("Opción inválida. Intente nuevamente.");                        
            }
        }while (opcion != 0);
    }
    private void mostrarMenu(){
        System.out.println("\n=== Video Club ===");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Agregar Película");
        System.out.println("3. Realizar Arriendo");
        System.out.println("4. Realizar Devolución");
        System.out.println("5. Buscar Cliente");
        System.out.println("6. Buscar Película");
        System.out.println("7. Eliminar Cliente");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
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
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Contacto: " + cliente.getContacto());
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
            System.out.println("ID: " + pelicula.getIdPelicula());
            System.out.println("Título: " + pelicula.getTitulo());
            System.out.println("Género: " + pelicula.getGenero());
            System.out.println("Año de estreno: " + pelicula.getFechaEstreno());
            System.out.println("Director: " + pelicula.getDirector());
            System.out.println("Stock disponible: " + pelicula.getStockDisponible());
        } catch (PeliculaNoDisponibleException e) {
            System.out.println("Error: Película no disponible.");
        }
    }
    private void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        videoClub.eliminarCliente(idCliente);
        System.out.println("Cliente eliminado exitosamente.");
    }
    private void eliminarPelicula() {
        System.out.print("ID de la película a eliminar: ");
        int idPelicula = scanner.nextInt();
        scanner.nextLine();
        videoClub.eliminarPelicula(idPelicula);
        System.out.println("Película eliminada exitosamente.");
    }
}    