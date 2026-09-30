package proyectoHotel;

public class HabitacionSimple extends Habitacion {

    // ****Atributos****
    private String[] caracteristicasEspecificas;

    // ****Constructor****
    public HabitacionSimple(int numero, EstadoHabitacion estado, double precioBase) {
        super(numero, estado, precioBase);
        this.caracteristicasEspecificas = new String[]{
            "Cama individual",
            "Baño privado",
            "TV"
        };
    }

    // ****Métodos****

    @Override
    public double calcularTarifaSegunTemporada(Temporada temporada) {
        return getPrecioBase() * temporada.getFactorRecargo();
    }

    @Override
    public String obtenerTipo() {
        return "Simple";
    }

    @Override
    public String obtenerDatos() {
        StringBuilder datos = new StringBuilder();
        datos.append("Tipo: Habitación Simple\n");
        datos.append(super.obtenerDatos());
        datos.append("\nCaracterísticas:");
        for (int i = 0; i < caracteristicasEspecificas.length; i++) {
            datos.append("\n  - " + caracteristicasEspecificas[i]);
        }
        return datos.toString();
    }

    // ****Getters y Setters****

    public String[] getCaracteristicasEspecificas() { return caracteristicasEspecificas; }
    public void setCaracteristicasEspecificas(String[] caracteristicasEspecificas) {
        this.caracteristicasEspecificas = caracteristicasEspecificas;
    }
}