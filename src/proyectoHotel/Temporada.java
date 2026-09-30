package proyectoHotel; 

//Casco Cristian, Castro María José 

public enum Temporada {
    ALTA(1.5), MEDIA(1.2), BAJA(1.0);

    // ****Atributos****
    private double factorRecargo;

    // ****Constructor****
    Temporada(double factorRecargo) {
        this.factorRecargo = factorRecargo;
    }

    // ****Getters y Setters****

    public double getFactorRecargo() { return factorRecargo; }
    public void setFactorRecargo(double factorRecargo) { this.factorRecargo = factorRecargo; }
}
