package proyectoHotel;

public class Empleado extends Persona {

    // ****Atributos****
    private String legajo;
    private String cargo;

    // ****Constructor****
    public Empleado(String nombre, String apellido, String DNI,
                    String legajo, String cargo) {
        super(nombre, apellido, DNI);
        this.legajo = legajo;
        this.cargo = cargo;
    }

    // ****Métodos****

    // Mostrar datos completos del empleado
    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() +
               "\nLegajo: " + this.legajo +
               "\nCargo: " + this.cargo;
    }

    // ****Getters y Setters****

    public String getLegajo() { return legajo; }
    public void setLegajo(String legajo) { this.legajo = legajo; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}