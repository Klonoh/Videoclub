import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class VistaGrafica{
    private VideoClub videoClub;
    private JFrame ventana;
    private JTextArea areaResultados;
    public VistaGrafica(VideoClub videoClub){
        this.videoClub = videoClub; 
    }
    public void iniciar(){
        ventana  = new JFrame("Video Club");
        ventana.setSize(700,550);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        JPanel panelPrincipal = new JPanel(new BorderLayout(10,10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        JLabel titulo = new JLabel("Bienvenido al Video Club", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        JPanel panelBotones = new JPanel(new GridLayout(4,3,10,10));

        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnAgregarPelicula = new JButton("Agregar Película");
        JButton btnBuscarCliente = new JButton("Buscar Cliente");
        JButton btnBuscarPelicula = new JButton("Buscar Película");
        JButton btnArriendo = new JButton("Realizar Arriendo");
        JButton btnDevolucion = new JButton("Realizar Devolución");
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        JButton btnEliminarPelicula = new JButton("Eliminar Película");
        JButton btnRecomendaciones = new JButton("Recomendaciones");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnListarPeliculas = new JButton("Listar Películas");
        JButton btnGuardar = new JButton("Guardar Datos");
        
        panelBotones.add(btnAgregarCliente);
        panelBotones.add(btnAgregarPelicula);
        panelBotones.add(btnBuscarCliente);

        panelBotones.add(btnBuscarPelicula);
        panelBotones.add(btnArriendo);
        panelBotones.add(btnDevolucion);

        panelBotones.add(btnEliminarCliente);
        panelBotones.add(btnEliminarPelicula);
        panelBotones.add(btnRecomendaciones);

        panelBotones.add(btnListarClientes);
        panelBotones.add(btnListarPeliculas);
        panelBotones.add(btnGuardar);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        
          areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));

        panelPrincipal.add(scroll, BorderLayout.SOUTH);
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        btnAgregarPelicula.addActionListener(e -> agregarPelicula());
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        btnBuscarPelicula.addActionListener(e -> buscarPelicula());
        btnArriendo.addActionListener(e -> realizarArriendo());
        btnDevolucion.addActionListener(e -> realizarDevolucion());
        btnEliminarCliente.addActionListener(e -> eliminarCliente());
        btnEliminarPelicula.addActionListener(e -> eliminarPelicula());
        btnRecomendaciones.addActionListener(e -> generarRecomendaciones());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnListarPeliculas.addActionListener(e -> listarPeliculas());
        btnGuardar.addActionListener(e -> guardarDatos());
        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }
    private void agregarCliente() {
        try{
            int id = Integer.parseInt(JOptionPane.showInputDialog(ventana, "Ingrese el ID del cliente:"));
            String nombre = JOptionPane.showInputDialog(ventana, "Nombre del cliente: ");
            String apellido = JOptionPane.showInputDialog(ventana, "Apellido del cliente: ");
            String contacto = JOptionPane.showInputDialog(ventana, "Contacto del cliente: ");
            Cliente cliente = new Cliente(id, nombre, apellido, contacto);
            videoClub.agregarCliente(cliente);
            mostrarMensaje("Cliente agregado exitosamente.");
        } catch (NumberFormatException e) {
            mostrarMensaje("ID inválido. Debe ser un número");
        } 
    }
    private void agregarPelicula() {

        try {
            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID de la película:")
            );

            String titulo = JOptionPane.showInputDialog(
                    ventana, "Título de la película:"
            );

            String director = JOptionPane.showInputDialog(
                    ventana, "Director:"
            );

            String genero = JOptionPane.showInputDialog(
                    ventana, "Género:"
            );

            int fechaEstreno = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "Año de estreno:")
            );

            int stockTotal = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "Stock total:")
            );

            Pelicula pelicula = new Pelicula(
                    id,
                    titulo,
                    director,
                    genero,
                    fechaEstreno,
                    stockTotal
            );

            videoClub.registrarPelicula(pelicula);

            mostrarMensaje("Película agregada correctamente.");

        } catch (NumberFormatException e) {
            mostrarError("Los valores numéricos no son válidos.");
        }
    }
    private void buscarCliente() {

        try {
            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID del cliente:")
            );

            Cliente cliente = videoClub.buscarCliente(id);

            areaResultados.setText(
                    "CLIENTE ENCONTRADO\n\n" +
                    "ID: " + cliente.getIdCliente() + "\n" +
                    "Nombre: " + cliente.getNombre() + "\n" +
                    "Apellido: " + cliente.getApellido() + "\n" +
                    "Contacto: " + cliente.getContacto()
            );

        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número.");

        } catch (ClienteNoEncontradoException e) {
            mostrarError("Cliente no encontrado.");
        }
    }
     private void buscarPelicula() {

        try {
            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID de la película:")
            );

            Pelicula pelicula = videoClub.buscarPelicula(id);

            areaResultados.setText(
                    "PELÍCULA ENCONTRADA\n\n" +
                    "ID: " + pelicula.getIdPelicula() + "\n" +
                    "Título: " + pelicula.getTitulo() + "\n" +
                    "Director: " + pelicula.getDirector() + "\n" +
                    "Género: " + pelicula.getGenero() + "\n" +
                    "Año: " + pelicula.getFechaEstreno() + "\n" +
                    "Stock disponible: " + pelicula.getStockDisponible()
            );

        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número.");

        } catch (PeliculaNoDisponibleException e) {
            mostrarError("Película no encontrada.");
        }
    }

    private void realizarArriendo() {

        try {
            int idCliente = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID del cliente:")
            );

            int idPelicula = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID de la película:")
            );

            videoClub.realizarArriendo(idCliente, idPelicula);

            mostrarMensaje("Arriendo realizado correctamente.");

        } catch (NumberFormatException e) {
            mostrarError("Los IDs deben ser números.");

        } catch (ClienteNoEncontradoException e) {
            mostrarError("Cliente no encontrado.");

        } catch (PeliculaNoDisponibleException e) {
            mostrarError("La película no está disponible.");
        }
    }

    private void realizarDevolucion() {

        try {
            int idCliente = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID del cliente:")
            );

            int idPelicula = Integer.parseInt(
                    JOptionPane.showInputDialog(ventana, "ID de la película:")
            );

            videoClub.realizarDevolucion(idCliente, idPelicula);

            mostrarMensaje("Devolución realizada correctamente.");

        } catch (NumberFormatException e) {
            mostrarError("Los IDs deben ser números.");
        }
    }

    private void eliminarCliente() {

        try {
            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            ventana,
                            "ID del cliente a eliminar:"
                    )
            );

            boolean eliminado = videoClub.eliminarCliente(id);

            if (eliminado) {
                mostrarMensaje("Cliente eliminado correctamente.");
            } else {
                mostrarError(
                        "No se puede eliminar el cliente porque tiene "
                        + "arriendos o recomendaciones asociadas."
                );
            }

        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número.");

        } catch (ClienteNoEncontradoException e) {
            mostrarError("Cliente no encontrado.");
        }
    }

    private void eliminarPelicula() {

        try {
            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            ventana,
                            "ID de la película a eliminar:"
                    )
            );

            boolean eliminada = videoClub.eliminarPelicula(id);

            if (eliminada) {
                mostrarMensaje("Película eliminada correctamente.");
            } else {
                mostrarError(
                        "No se puede eliminar la película porque tiene "
                        + "arriendos o recomendaciones asociadas."
                );
            }

        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número.");

        } catch (PeliculaNoDisponibleException e) {
            mostrarError("Película no encontrada.");
        }
    }

    private void listarClientes() {

        ArrayList<Cliente> clientes = videoClub.listarClientes();

        StringBuilder texto = new StringBuilder();

        texto.append("CLIENTES REGISTRADOS\n");
        texto.append("====================\n\n");

        if (clientes.isEmpty()) {
            texto.append("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                texto.append("ID: ")
                        .append(cliente.getIdCliente())
                        .append(" | ")
                        .append(cliente.getNombre())
                        .append(" ")
                        .append(cliente.getApellido())
                        .append(" | ")
                        .append(cliente.getContacto())
                        .append("\n");
            }
        }

        areaResultados.setText(texto.toString());
    }

    private void listarPeliculas() {

        ArrayList<Pelicula> peliculas = videoClub.listarPeliculas();

        StringBuilder texto = new StringBuilder();

        texto.append("PELÍCULAS REGISTRADAS\n");
        texto.append("=====================\n\n");

        if (peliculas.isEmpty()) {
            texto.append("No hay películas registradas.");
        } else {
            for (Pelicula pelicula : peliculas) {
                texto.append("ID: ")
                        .append(pelicula.getIdPelicula())
                        .append(" | ")
                        .append(pelicula.getTitulo())
                        .append(" | Género: ")
                        .append(pelicula.getGenero())
                        .append(" | Stock: ")
                        .append(pelicula.getStockDisponible())
                        .append("/")
                        .append(pelicula.getStockTotal())
                        .append("\n");
            }
        }

        areaResultados.setText(texto.toString());
    }

    private void generarRecomendaciones() {

        try {
            int idCliente = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            ventana,
                            "ID del cliente:"
                    )
            );

            int cantidad = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            ventana,
                            "¿Cuántas recomendaciones desea?"
                    )
            );

            ArrayList<Pelicula> recomendaciones =
                    videoClub.generarRecomendaciones(idCliente, cantidad);

            StringBuilder texto = new StringBuilder();

            texto.append("RECOMENDACIONES\n");
            texto.append("================\n\n");

            if (recomendaciones.isEmpty()) {
                texto.append("No hay recomendaciones disponibles.");
            } else {
                for (Pelicula pelicula : recomendaciones) {
                    texto.append("- ")
                            .append(pelicula.getTitulo())
                            .append(" | Género: ")
                            .append(pelicula.getGenero())
                            .append(" | Stock: ")
                            .append(pelicula.getStockDisponible())
                            .append("\n");
                }
            }

            areaResultados.setText(texto.toString());

        } catch (NumberFormatException e) {
            mostrarError("Los valores ingresados deben ser números.");

        } catch (ClienteNoEncontradoException e) {
            mostrarError("Cliente no encontrado.");
        }
    }

    private void guardarDatos() {

        try {
            PersistenciaCSV persistencia = new PersistenciaCSV();

            persistencia.guardarDatos(videoClub);

            mostrarMensaje("Datos guardados correctamente.");

        } catch (IOException e) {
            mostrarError(
                    "No se pudieron guardar los datos: "
                    + e.getMessage()
            );
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
                ventana,
                mensaje,
                "VideoClub",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                ventana,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}