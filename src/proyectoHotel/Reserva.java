package proyectoHotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {

    // ****Atributos****
    private String idReserva;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private String estado;
    private double tarifaAplicada;
    private String[] serviciosAdicionales;
    private Temporada temporada;

    // ****Constructor****
    public Reserva(String idReserva, Huesped huesped, Habitacion habitacion,
                   LocalDate fechaCheckIn, LocalDate fechaCheckOut,
                   String estado, Temporada temporada) {
        this.idReserva = idReserva;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.estado = estado;
        this.temporada = temporada;
        this.tarifaAplicada = calcularTarifaTotal();
        this.serviciosAdicionales = new String[0];
    }

    // ****Métodos****

    // Calcular tarifa total según temporada y tipo de habitación
    private double calcularTarifaTotal() {
        double tarifaBase = habitacion.getPrecioBase();
        int noches = calcularNoches();
        double factorTemporada = obtenerFactorTemporada();
        
        return tarifaBase * noches * factorTemporada;
    }

    // Obtener factor de recargo según temporada
    private double obtenerFactorTemporada() {
        switch (temporada) {
            case ALTA:
                return 1.5; // 50% recargo
            case MEDIA:
                return 1.2; // 20% recargo
            case BAJA:
                return 1.0; // sin recargo
            default:
                return 1.0;
        }
    }

    // Calcular cantidad de noches
    private int calcularNoches() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(fechaCheckIn, fechaCheckOut);
    }

    // Agregar servicio adicional
    public void agregarServicio(String servicio) {
        String[] nuevosServicios = new String[serviciosAdicionales.length + 1];
        for (int i = 0; i < serviciosAdicionales.length; i++) {
            nuevosServicios[i] = serviciosAdicionales[i];
        }
        nuevosServicios[serviciosAdicionales.length] = servicio;
        serviciosAdicionales = nuevosServicios;
    }

    // Mostrar datos completos de la reserva
    public String obtenerDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder datos = new StringBuilder();
        
        datos.append("ID Reserva: ").append(idReserva).append("\n");
        datos.append("Huésped: ").append(huesped.getNombre()).append(" ").append(huesped.getApellido()).append("\n");
        datos.append("Habitación: ").append(habitacion.getNumero()).append("\n");
        datos.append("Check-in: ").append(fechaCheckIn.format(formatter)).append("\n");
        datos.append("Check-out: ").append(fechaCheckOut.format(formatter)).append("\n");
        datos.append("Noches: ").append(calcularNoches()).append("\n");
        datos.append("Temporada: ").append(temporada).append("\n");
        datos.append("Estado: ").append(estado).append("\n");
        datos.append("Tarifa aplicada: $").append(String.format("%.2f", tarifaAplicada)).append("\n");
        
        if (serviciosAdicionales.length > 0) {
            datos.append("Servicios adicionales: ");
            for (String servicio : serviciosAdicionales) {
                datos.append(servicio).append(", ");
            }
            datos.setLength(datos.length() - 2); // Eliminar última coma
            datos.append("\n");
        }
        
        return datos.toString();
    }

    // ****Getters y Setters****

    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public LocalDate getFechaCheckIn() { return fechaCheckIn; }
    public void setFechaCheckIn(LocalDate fechaCheckIn) { this.fechaCheckIn = fechaCheckIn; }

    public LocalDate getFechaCheckOut() { return fechaCheckOut; }
    public void setFechaCheckOut(LocalDate fechaCheckOut) { this.fechaCheckOut = fechaCheckOut; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getTarifaAplicada() { return tarifaAplicada; }
    public void setTarifaAplicada(double tarifaAplicada) { this.tarifaAplicada = tarifaAplicada; }

    public String[] getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(String[] serviciosAdicionales) { this.serviciosAdicionales = serviciosAdicionales; }

    public Temporada getTemporada() { return temporada; }
    public void setTemporada(Temporada temporada) { this.temporada = temporada; }
}
