package proyectoHotel;

public class Huesped extends Persona {

    // ****Atributos****
    private String idHuesped;
    private int diasEstadia;
    private String formaPago;

    // ****Constructor****
    public Huesped(String nombre, String apellido, String DNI,
                   String idHuesped, int diasEstadia, String formaPago) {
        super(nombre, apellido, DNI);
        this.idHuesped = idHuesped;
        this.diasEstadia = diasEstadia;
        this.formaPago = formaPago;
    }

    // ****Métodos****

    // Calcula el costo total de la estadía según tarifa y temporada
    public double calcularCostoEstadia(double tarifaPorNoche) {
        double costoTotal = tarifaPorNoche * this.diasEstadia;
        return aplicarDescuento(costoTotal);
    }

    // Aplica descuento según forma de pago o duración de estadía
    public double aplicarDescuento(double costoTotal) {
        if (this.formaPago.equalsIgnoreCase("anticipado")) {
            costoTotal = costoTotal * 0.85; // 15% de descuento
        } else if (this.diasEstadia > 7) {
            costoTotal = costoTotal * 0.90; // 10% de descuento
        }
        return costoTotal;
    }

    // Mostrar datos completos del huésped
    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() +
               "\nID Huésped: " + this.idHuesped +
               "\nDías de estadía: " + this.diasEstadia +
               "\nForma de pago: " + this.formaPago;
    }

    // ****Getters y Setters****

    public String getIdHuesped() { return idHuesped; }
    public void setIdHuesped(String idHuesped) { this.idHuesped = idHuesped; }

    public int getDiasEstadia() { return diasEstadia; }
    public void setDiasEstadia(int diasEstadia) { this.diasEstadia = diasEstadia; }

    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }
}
