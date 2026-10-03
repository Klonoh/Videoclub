import java.io.IOException;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicación. Carga los datos y permite seleccionar
 * entre la interfaz de consola y la interfaz gráfica.
 */
public class Main {

    /**
     * Inicia la aplicación, carga los datos y solicita el modo de interfaz.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        VideoClub videoClub = new VideoClub();
        PersistenciaCSV persistencia = new PersistenciaCSV();

        try {

            persistencia.cargarDatos(videoClub);

        } catch (IOException e) {

            System.out.println(
                "Error al cargar los datos: " + e.getMessage()
            );
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Video Club ===");
        System.out.println("1. Usar Consola");
        System.out.println("2. Usar Ventanas");
        System.out.print("Seleccione modo: ");

        int modo = -1;

        while (modo != 1 && modo != 2) {

            String entrada = scanner.nextLine();

            try {
                modo = Integer.parseInt(entrada);

                if (modo != 1 && modo != 2) {
                    System.out.print("Opcion invalida. Ingrese 1 o 2: ");
                }

            } catch (NumberFormatException e) {
                System.out.print("Debe ingresar 1 o 2: ");
            }
        }

        if (modo == 1) {

            VistaConsola vista = new VistaConsola(videoClub);
            vista.iniciar();

            try {

                persistencia.guardarDatos(videoClub);

            } catch (IOException e) {

                System.out.println(
                    "Error al guardar los datos: "
                    + e.getMessage()
                );
            }

        } else if (modo == 2) {

            VistaGrafica vista =
                new VistaGrafica(videoClub);

            vista.iniciar();

        } else {

            System.out.println("Opcion invalida.");
        }
    }
}