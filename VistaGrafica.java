import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Implementa la interacción con el sistema mediante ventanas Swing.
 */
public class VistaGrafica{
    private VideoClub videoClub;
    private JFrame ventana;
    private JTextArea areaResultados;
    public VistaGrafica(VideoClub videoClub){
        this.videoClub = videoClub; 
    }
    /**
     * Construye y muestra la ventana principal de la aplicación.
     */
    public void iniciar(){
        ventana  = new JFrame("Video Club");
        ventana.setSize(700,550);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.addWindowListener(
            new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosing(
                        java.awt.event.WindowEvent e) {

                    try {

                        PersistenciaCSV persistencia =
                            new PersistenciaCSV();

                        persistencia.guardarDatos(
                            videoClub
                        );

                        ventana.dispose();
                        System.exit(0);

                    } catch (IOException ex) {

                        mostrarError(
                            "No se pudieron guardar los datos: "
                            + ex.getMessage()
                        );
                    }
                }
            }
        );
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
            Integer id = pedirEntero("Ingrese el ID del cliente:");
            if (id == null) {
                return;
            }

            String nombre = JOptionPane.showInputDialog(ventana, "Nombre del cliente: ");
            if (nombre == null) {
                return;
            }

            String apellido = JOptionPane.showInputDialog(ventana, "Apellido del cliente: ");
            if (apellido == null) {
                return;
            }

            String contacto = JOptionPane.showInputDialog(ventana, "Contacto del cliente: ");
            if (contacto == null) {
                return;
            }
            Cliente cliente = new Cliente(id, nombre, apellido, contacto);
            videoClub.agregarCliente(cliente);
            mostrarMensaje("Cliente agregado exitosamente.");
        } catch (NumberFormatException e) {
            mostrarMensaje("ID inválido. Debe ser un número");
        } 
    }
    private void agregarPelicula() {

        try {
            Integer id = pedirEntero("ID de la película:");
            if (id == null) {
                return;
            }

            String titulo = JOptionPane.showInputDialog(
                    ventana, "Título de la película:"
            );
            if (titulo == null) {
                return;
            }

            String director = JOptionPane.showInputDialog(
                    ventana, "Director:"
            );
            if (director == null) {
                return;
            }

            String genero = JOptionPane.showInputDialog(
                    ventana, "Género:"
            );
            if (genero == null) {
                return;
            }

            Integer fechaEstreno = pedirEntero("Año de estreno:");
            if (fechaEstreno == null) {
                return;
            }

            Integer stockTotal = pedirEntero("Stock total:");
            if (stockTotal == null) {
                return;
            }

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

        Integer id = pedirEntero("ID del cliente:");

        if (id == null) {
            return;
        }

        try {

            Cliente cliente = videoClub.buscarCliente(id);

            areaResultados.setText(
                    "CLIENTE ENCONTRADO\n\n" +
                    "ID: " + cliente.getIdCliente() + "\n" +
                    "Nombre: " + cliente.getNombre() + "\n" +
                    "Apellido: " + cliente.getApellido() + "\n" +
                    "Contacto: " + cliente.getContacto()
            );

        } catch (ClienteNoEncontradoException e) {
            mostrarError("Cliente no encontrado.");
        }
    }
    private void buscarPelicula() {
        String[] opciones = {"ID", "Titulo o genero"};

        int opcion = JOptionPane.showOptionDialog(
            ventana,
            "Como desea buscar la pelicula?",
            "Buscar pelicula",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (opcion == 0) {
            try {
                String entrada = JOptionPane.showInputDialog(
                    ventana,
                    "ID de la pelicula:"
                );

                if (entrada == null) {
                    return;
                }

                int id = Integer.parseInt(entrada);
                Pelicula pelicula = videoClub.buscarPelicula(id);

                areaResultados.setText(
                    "PELICULA ENCONTRADA\n\n" +
                    "ID: " + pelicula.getIdPelicula() + "\n" +
                    "Titulo: " + pelicula.getTitulo() + "\n" +
                    "Director: " + pelicula.getDirector() + "\n" +
                    "Genero: " + pelicula.getGenero() + "\n" +
                    "Año: " + pelicula.getFechaEstreno() + "\n" +
                    "Stock disponible: " + pelicula.getStockDisponible()
                );

            } catch (NumberFormatException e) {
                mostrarError("El ID debe ser un numero.");
            } catch (PeliculaNoDisponibleException e) {
                mostrarError("Pelicula no encontrada.");
            }

        } else if (opcion == 1) {
            String criterio = JOptionPane.showInputDialog(
                ventana,
                "Ingrese titulo o genero:"
            );

            if (criterio == null) {
                return;
            }

            java.util.List<Pelicula> resultados =
                videoClub.buscarPelicula(criterio);

            if (resultados.isEmpty()) {
                areaResultados.setText(
                    "No se encontraron peliculas."
                );
                return;
            }

            StringBuilder texto = new StringBuilder(
                "PELICULAS ENCONTRADAS\n\n"
            );

            for (Pelicula pelicula : resultados) {
                texto.append("ID: ")
                    .append(pelicula.getIdPelicula())
                    .append("\nTitulo: ")
                    .append(pelicula.getTitulo())
                    .append("\nGenero: ")
                    .append(pelicula.getGenero())
                    .append("\n\n");
            }

            areaResultados.setText(texto.toString());
        }
    }

    private void realizarArriendo() {

        try {
            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer idPelicula = pedirEntero("ID de la película:");
            if (idPelicula == null) {
                return;
            }

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
            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer idPelicula = pedirEntero("ID de la película:");
            if (idPelicula == null) {
                return;
            }

            videoClub.realizarDevolucion(idCliente, idPelicula);

            mostrarMensaje("Devolución realizada correctamente.");

        } catch (NumberFormatException e) {
            mostrarError("Los IDs deben ser números.");

        } catch (PeliculaNoDisponibleException e) {
            mostrarError(e.getMessage());
        }
    }

    private void editarCliente() {

        try {

            Integer id = pedirEntero("ID del cliente a editar:");
            if (id == null) {
                return;
            }

            Cliente cliente =
                videoClub.buscarCliente(id);

            String nombre =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo nombre:",
                    cliente.getNombre()
                );
            if (nombre == null) {
                return;
            }

            String apellido =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo apellido:",
                    cliente.getApellido()
                );
            if (apellido == null) {
                return;
            }

            String contacto =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo contacto:",
                    cliente.getContacto()
                );
            if (contacto == null) {
                return;
            }

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
            Integer id = pedirEntero("ID del cliente a eliminar:");
            if (id == null) {
                return;
            }

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

            Integer id = pedirEntero("ID de la pelicula a editar:");
            if (id == null) {
                return;
            }

            Pelicula pelicula =
                videoClub.buscarPelicula(id);

            String titulo =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo titulo:",
                    pelicula.getTitulo()
                );
            if (titulo == null) {
                return;
            }

            String director =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo director:",
                    pelicula.getDirector()
                );
            if (director == null) {
                return;
            }

            String genero =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo genero:",
                    pelicula.getGenero()
                );
            if (genero == null) {
                return;
            }

            String entradaFechaEstreno =
                JOptionPane.showInputDialog(
                    ventana,
                    "Nuevo año de estreno:",
                    pelicula.getFechaEstreno()
                );

            if (entradaFechaEstreno == null) {
                return;
            }

            int fechaEstreno =
                Integer.parseInt(entradaFechaEstreno);

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
            Integer id = pedirEntero("ID de la película a eliminar:");
            if (id == null) {
                return;
            }

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
            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer cantidad = pedirEntero("¿Cuántas recomendaciones desea?");
            if (cantidad == null) {
                return;
            }

            if (cantidad <= 0) {
                mostrarError("La cantidad debe ser mayor a cero.");
                return;
            }

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

    private Integer pedirEntero(String mensaje) {
        String entrada = JOptionPane.showInputDialog(ventana, mensaje);

        //si cancela, simplemente vuelve al menu
        if (entrada == null) {
            return null;
        }

        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            mostrarError("Debe ingresar un numero valido.");
            return null;
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

                Integer idCliente = pedirEntero("ID del cliente:");
                if (idCliente == null) {
                    return;
                }

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

            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer numero = pedirEntero("Numero del arriendo:");
            if (numero == null) {
                return;
            }

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

            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer numero = pedirEntero("Numero del arriendo:");
            if (numero == null) {
                return;
            }

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
            if (fecha == null) {
                return;
            }

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

            Integer idCliente = pedirEntero("ID del cliente:");
            if (idCliente == null) {
                return;
            }

            Integer numero = pedirEntero("Numero del arriendo:");
            if (numero == null) {
                return;
            }

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

    public VideoClub getVideoClub() {
        return videoClub;
    }

    public void setVideoClub(VideoClub videoClub) {
        this.videoClub = videoClub;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public JTextArea getAreaResultados() {
        return areaResultados;
    }

    public void setAreaResultados(JTextArea areaResultados) {
        this.areaResultados = areaResultados;
    }

}