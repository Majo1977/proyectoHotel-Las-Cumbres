public abstract class Habitacion {

    // ****Atributos****
    private int numero;
    private EstadoHabitacion estado;
    private double precioBase;

    // ****Constructor****
    public Habitacion(int numero, EstadoHabitacion estado, double precioBase) {
        this.numero = numero;
        this.estado = estado;
        this.precioBase = precioBase;
    }

    // ****Métodos****

    // Consultar estado de la habitación
    public String consultarEstado() {
        return "Habitación " + this.numero + " → Estado: " + this.estado;
    }

    // Mostrar datos de la habitación
    public String obtenerDatos() {
        return "Número: " + this.numero +
               "\nEstado: " + this.estado +
               "\nPrecio base: $" + String.format("%.2f", this.precioBase);
    }

    // Método abstracto que cada subclase implementa según su tipo
    public abstract double calcularTarifaSegunTemporada(Temporada temporada);

    // ****Getters y Setters****

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public EstadoHabitacion getEstado() { return estado; }
    public void setEstado(EstadoHabitacion estado) { this.estado = estado; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
}