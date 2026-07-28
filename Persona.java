package proyectoHotel;

public class Persona {

    // ****Atributos****
    private String nombre;
    private String apellido;
    private String DNI;

    // ****Constructor****
    public Persona(String nombre, String apellido, String DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
    }

    // ****Métodos****

    // Mostrar datos básicos de la persona
    public String obtenerDatos() {
        return "Nombre: " + this.nombre +
               "\nApellido: " + this.apellido +
               "\nDNI: " + this.DNI;
    }

    // ****Getters y Setters****

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDNI() { return DNI; }
    public void setDNI(String DNI) { this.DNI = DNI; }
}
