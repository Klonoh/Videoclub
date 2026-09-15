public class Main {

    public static void main(String[] args) {

        VideoClub videoClub = new VideoClub();

        System.out.println("Sistema de VideoClub iniciado.");
        VistaConsola vista = new VistaConsola(videoClub);
        vista.iniciar();
    }
}

