package proyectoHotel;

public class Suite extends Habitacion {

    // ****Atributos****
    private String[] caracteristicasEspecificas;

    // ****Constructor****
    public Suite(int numero, EstadoHabitacion estado, double precioBase) {
        super(numero, estado, precioBase);
        this.caracteristicasEspecificas = new String[]{
            "Cama king size",
            "Baño de lujo",
            "TV 55\"",
            "Aire acondicionado",
            "Jacuzzi",
            "Vista a las sierras"
        };
    }

    // ****Métodos****

    @Override
    public double calcularTarifaSegunTemporada(Temporada temporada) {
        double factor;
        if (temporada == Temporada.ALTA) {
            factor = 2.0;
        } else if (temporada == Temporada.MEDIA) {
            factor = 1.5;
        } else {
            factor = 1.2;
        }
        return getPrecioBase() * factor;
    }

    @Override
    public String obtenerDatos() {
        StringBuilder datos = new StringBuilder();
        datos.append("Tipo: Suite\n");
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
