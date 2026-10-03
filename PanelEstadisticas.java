import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Componente grafico que muestra estadisticas de arriendos por genero.
 */
public class PanelEstadisticas extends JPanel {

    private Map<String, Integer> arriendosPorGenero;

    public PanelEstadisticas(Map<String, Integer> arriendosPorGenero) {
        setArriendosPorGenero(arriendosPorGenero);
        setPreferredSize(new Dimension(650, 400));
    }

    public Map<String, Integer> getArriendosPorGenero() {
        return new HashMap<>(arriendosPorGenero);
    }

    public void setArriendosPorGenero(Map<String, Integer> arriendosPorGenero) {
        this.arriendosPorGenero = new HashMap<>(arriendosPorGenero);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Arial", Font.BOLD, 22));
        g2.drawString("Arriendos por genero", 30, 35);

        if (arriendosPorGenero.isEmpty()) {
            g2.setFont(new Font("Arial", Font.PLAIN, 16));
            g2.drawString("No hay arriendos registrados.", 30, 80);
            return;
        }

        int maximo = 1;

        for (int cantidad : arriendosPorGenero.values()) {
            if (cantidad > maximo) {
                maximo = cantidad;
            }
        }

        int y = 80;
        int altoBarra = 35;
        int separacion = 60;
        int inicioBarra = 170;
        int anchoMaximo = 400;

        g2.setFont(new Font("Arial", Font.PLAIN, 15));

        for (Map.Entry<String, Integer> entrada : arriendosPorGenero.entrySet()) {

            String genero = entrada.getKey();
            int cantidad = entrada.getValue();

            int anchoBarra =
                    (int) ((double) cantidad / maximo * anchoMaximo);

            g2.drawString(genero, 25, y + 23);

            g2.setColor(new Color(70, 130, 180));
            g2.fillRect(
                    inicioBarra,
                    y,
                    anchoBarra,
                    altoBarra
            );

            g2.setColor(Color.BLACK);
            g2.drawRect(
                    inicioBarra,
                    y,
                    anchoBarra,
                    altoBarra
            );

            g2.drawString(
                    String.valueOf(cantidad),
                    inicioBarra + anchoBarra + 10,
                    y + 23
            );

            y += separacion;
        }
    }
}