import java.io.IOException;
import java.util.Scanner;

public class Main {

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

        int modo = scanner.nextInt();

        if (modo == 1) {

            VistaConsola vista = new VistaConsola(videoClub);
            vista.iniciar();

        } else if (modo == 2) {

            VistaGrafica vista = new VistaGrafica(videoClub);
            vista.iniciar();

        } else {

            System.out.println("Opcion invalida.");
        }
        try {

            persistencia.guardarDatos(videoClub);

        } catch (IOException e) {

            System.out.println(
                "Error al guardar los datos: " + e.getMessage()
            );
        }
    }
}

