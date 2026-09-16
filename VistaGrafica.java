import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        JPanel panelBotones = new JPanel(new GridLayout(6,3,10,10));

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
        JButton btnEditarCliente = new JButton("Editar Cliente");
        JButton btnEditarPelicula = new JButton("Editar Pelicula");
        JButton btnListarArriendos = new JButton("Listar Arriendos");
        JButton btnBuscarArriendo = new JButton("Buscar Arriendo");
        JButton btnEditarArriendo = new JButton("Editar Arriendo");
        JButton btnEliminarArriendo = new JButton("Eliminar Arriendo");
        
        panelBotones.add(btnAgregarCliente);
        panelBotones.add(btnBuscarCliente);
        panelBotones.add(btnEditarCliente);

        panelBotones.add(btnEliminarCliente);
        panelBotones.add(btnListarClientes);
        panelBotones.add(btnAgregarPelicula);

        panelBotones.add(btnBuscarPelicula);
        panelBotones.add(btnEditarPelicula);
        panelBotones.add(btnEliminarPelicula);

        panelBotones.add(btnListarPeliculas);
        panelBotones.add(btnArriendo);
        panelBotones.add(btnDevolucion);

        panelBotones.add(btnListarArriendos);
        panelBotones.add(btnBuscarArriendo);
        panelBotones.add(btnEditarArriendo);

        panelBotones.add(btnEliminarArriendo);
        panelBotones.add(btnRecomendaciones);
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
        btnEditarCliente.addActionListener(e -> editarCliente());
        btnEditarPelicula.addActionListener(e -> editarPelicula());
        btnListarArriendos.addActionListener(e -> listarArriendos());
        btnBuscarArriendo.addActionListener(e -> buscarArriendo());
        btnEditarArriendo.addActionListener(e -> editarArriendo());
        btnEliminarArriendo.addActionListener(e -> eliminarArriendo());
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

    private void editarCliente() {

        try {

            int id = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "ID del cliente a editar:"
                )
            );

            Cliente cliente =
                videoClub.buscarCliente(id);

            String nombre =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo nombre:",
                    cliente.getNombre()
                );

            String apellido =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo apellido:",
                    cliente.getApellido()
                );

            String contacto =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo contacto:",
                    cliente.getContacto()
                );

            videoClub.editarCliente(
                id,
                nombre,
                apellido,
                contacto
            );

            mostrarMensaje(
                "Cliente editado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarError(
                "El ID debe ser un numero."
            );

        } catch (ClienteNoEncontradoException e) {

            mostrarError(
                "Cliente no encontrado."
            );
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

    private void editarPelicula() {

        try {

            int id = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "ID de la pelicula a editar:"
                )
            );

            Pelicula pelicula =
                videoClub.buscarPelicula(id);

            String titulo =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo titulo:",
                    pelicula.getTitulo()
                );

            String director =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo director:",
                    pelicula.getDirector()
                );

            String genero =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo genero:",
                    pelicula.getGenero()
                );

            int fechaEstreno =
                Integer.parseInt(
                    JOptionPane.showInputDialog(
                        ventana,
                        "Nuevo año de estreno:",
                        pelicula.getFechaEstreno()
                    )
                );

            videoClub.editarPelicula(
                id,
                titulo,
                director,
                genero,
                fechaEstreno
            );

            mostrarMensaje(
                "Pelicula editada correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarError(
                "Los valores numericos no son validos."
            );

        } catch (PeliculaNoDisponibleException e) {

            mostrarError(
                "Pelicula no encontrada."
            );
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

    private void listarArriendos() {

            try {

                int idCliente = Integer.parseInt(
                    JOptionPane.showInputDialog(
                        ventana,
                        "ID del cliente:"
                    )
                );

                ArrayList<Arriendo> historial =
                    videoClub.listarArriendosCliente(
                        idCliente
                    );

                StringBuilder texto =
                    new StringBuilder();

                texto.append("HISTORIAL DE ARRIENDOS\n");
                texto.append("======================\n\n");

                if (historial.isEmpty()) {

                    texto.append(
                        "El cliente no posee arriendos."
                    );

                } else {

                    for (int i = 0;
                        i < historial.size();
                        i++) {

                        texto.append(i + 1)
                            .append(". ")
                            .append(
                                historial.get(i).toString()
                            )
                            .append("\n");
                    }
                }

                areaResultados.setText(
                    texto.toString()
                );

            } catch (NumberFormatException e) {

                mostrarError(
                    "El ID debe ser un numero."
                );

            } catch (ClienteNoEncontradoException e) {

                mostrarError(
                    "Cliente no encontrado."
                );
            }
        }

        private void buscarArriendo() {

        try {

            int idCliente = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "ID del cliente:"
                )
            );

            int numero = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "Numero del arriendo:"
                )
            );

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            areaResultados.setText(
                "ARRIENDO ENCONTRADO\n\n"
                + arriendo.toString()
            );

        } catch (NumberFormatException e) {

            mostrarError(
                "Los valores deben ser numericos."
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            mostrarError(
                e.getMessage()
            );
        }
    }

    private void editarArriendo() {

        try {

            int idCliente = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "ID del cliente:"
                )
            );

            int numero = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "Numero del arriendo:"
                )
            );

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            String fecha =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nueva fecha (AAAA-MM-DD):",
                    arriendo.getFechaArriendo()
                );

            LocalDate nuevaFecha =
                LocalDate.parse(fecha);

            videoClub.editarArriendo(
                idCliente,
                numero,
                nuevaFecha
            );

            mostrarMensaje(
                "Arriendo editado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarError(
                "Los valores deben ser numericos."
            );

        } catch (DateTimeParseException e) {

            mostrarError(
                "Fecha invalida. Use AAAA-MM-DD."
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            mostrarError(
                e.getMessage()
            );
        }
    }

    private void eliminarArriendo() {

        try {

            int idCliente = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "ID del cliente:"
                )
            );

            int numero = Integer.parseInt(
                JOptionPane.showInputDialog(
                    ventana,
                    "Numero del arriendo:"
                )
            );

            Arriendo arriendo =
                videoClub.buscarArriendo(
                    idCliente,
                    numero
                );

            int respuesta =
                JOptionPane.showConfirmDialog(
                    ventana,
                    "¿Eliminar este arriendo?\n\n"
                    + arriendo.toString(),
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION
                );

            if (respuesta !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            videoClub.eliminarArriendo(
                idCliente,
                numero
            );

            mostrarMensaje(
                "Arriendo eliminado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarError(
                "Los valores deben ser numericos."
            );

        } catch (ClienteNoEncontradoException |
                IllegalArgumentException e) {

            mostrarError(
                e.getMessage()
            );
        }
    }
}