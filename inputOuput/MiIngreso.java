package inputOuput;

import javax.swing.JOptionPane;

public class MiIngreso {

    // Bandera que indica si el usuario canceló o cerró la ventana
    public static boolean cancelado = false;

    // Pide un entero, si el usuario cancela pone cancelado = true y devuelve 0
    public static int pedirEntero(String mensaje, String titulo) {
        String input = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            cancelado = true;
            return 0;
        }
        try {
            cancelado = false;
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Salida.mError("Debe ingresar un número entero válido.", "Error de Dato");
            return pedirEntero(mensaje, titulo);
        }
    }

    // Pide un entero positivo (mayor a cero)
    public static int pedirEnteroPositivo(String mensaje, String titulo) {
        int valor;
        do {
            valor = pedirEntero(mensaje, titulo);
            if (cancelado) return 0;
            if (valor <= 0) {
                Salida.mAdvertencia("El valor debe ser mayor a cero.", "Dato inválido");
            }
        } while (valor <= 0);
        return valor;
    }

    // Pide un entero dentro de un rango (desde - hasta, ambos inclusive)
    public static int pedirEnteroEnRango(String mensaje, String titulo, int desde, int hasta) {
        int valor;
        do {
            valor = pedirEntero(mensaje, titulo);
            if (cancelado) return 0;
            if (valor < desde || valor > hasta) {
                Salida.mAdvertencia("El valor debe estar entre " + desde + " y " + hasta + ".", "Dato inválido");
            }
        } while (valor < desde || valor > hasta);
        return valor;
    }

    // Pide un texto, si el usuario cancela pone cancelado = true y devuelve ""
    public static String pedirString(String mensaje, String titulo) {
        String input = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            cancelado = true;
            return "";
        }
        if (input.isEmpty()) {
            Salida.mError("El texto no puede estar vacío.", "Error de Dato");
            return pedirString(mensaje, titulo);
        }
        cancelado = false;
        return input;
    }

    // Consulta si el usuario quiere continuar o salir
    public static void consultarSalida() {
        String[] opciones = {"Continuar", "Salir"};
        int respuesta = Ingreso.datoBotones("¿Desea continuar o salir?", "Salir", opciones, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 1) {
            Salida.mMensaje("¡Hasta luego!", "Salir");
            System.exit(0);
        }
    }//Metodo para pedir reales 
    public static double pedirReal(String mensaje, String titulo) {
    String input = JOptionPane.showInputDialog(
            null,
            mensaje,
            titulo,
            JOptionPane.QUESTION_MESSAGE);

    if (input == null) {
        cancelado = true;
        return 0;
    }

    try {
        cancelado = false;
        return Double.parseDouble(input);
    } catch (NumberFormatException e) {
        Salida.mError("Debe ingresar un número válido.",
                "Error de Dato");
        return pedirReal(mensaje, titulo);
    }
}
}