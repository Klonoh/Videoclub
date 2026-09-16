import java.io.IOException;

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

        VistaGrafica vista = new VistaGrafica(videoClub);

        vista.iniciar();

        try {

            persistencia.guardarDatos(videoClub);

        } catch (IOException e) {

            System.out.println(
                "Error al guardar los datos: " + e.getMessage()
            );
        }
    }
}

