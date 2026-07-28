package proyectoHotel;

public class HabitacionDoble extends Habitacion {

    // ****Atributos****
    private String[] caracteristicasEspecificas;

    // ****Constructor****
    public HabitacionDoble(int numero, EstadoHabitacion estado, double precioBase) {
        super(numero, estado, precioBase);
        this.caracteristicasEspecificas = new String[]{
            "Dos camas individuales",
            "Baño privado",
            "TV",
            "Aire acondicionado"
        };
    }

    // ****Métodos****

    @Override
    public double calcularTarifaSegunTemporada(Temporada temporada) {
        double factor;
        if (temporada == Temporada.ALTA) {
            factor = 1.6;
        } else if (temporada == Temporada.MEDIA) {
            factor = 1.3;
        } else {
            factor = 1.0;
        }
        return getPrecioBase() * factor;
    }

    @Override
    public String obtenerDatos() {
        StringBuilder datos = new StringBuilder();
        datos.append("Tipo: Habitación Doble\n");
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
