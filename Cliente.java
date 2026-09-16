import java.util.ArrayList;
import java.util.List;

public class Cliente{
    private int idCliente;
    private String nombre;
    private String apellido;
    private String contacto;

    private List<Arriendo> historial;

    public Cliente(int idCliente, String nombre, String apellido, String contacto) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;

        this.historial = new ArrayList<>();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContacto() {
        return contacto;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<Arriendo> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Arriendo> historial) {
        this.historial = historial;
    }

    public void agregarArriendo(Arriendo arriendo) {
        historial.add(arriendo);
    }

    @Override
    public String toString() {
        return "ID: " + idCliente +
            " | Nombre: " + nombre + " " + apellido +
            " | Contacto: " + contacto;
    }

}
