package proyectoHotel;
// Apellido Nombre

import inputOuput.*;
import javax.swing.JOptionPane;

public class SistemaHotel {

    // Arrays para almacenar huéspedes y habitaciones
    static Huesped[] huespedes = new Huesped[50];
    static int cantHuespedes = 0;

    static Habitacion[] habitaciones = new Habitacion[20];
    static int cantHabitaciones = 0;

    public static void main(String[] args) {

        // Cargamos habitaciones predefinidas del hotel
        inicializarHabitaciones();

        int opcion;

        do {
            String menu =
                    "=====================================\n" +
                    "   HOTEL LAS CUMBRES - SISTEMA      \n" +
                    "=====================================\n" +
                    "--- GESTIÓN DE HUÉSPEDES ---\n" +
                    "1. Registrar nuevo huésped\n" +
                    "2. Listar huéspedes registrados\n" +
                    "3. Calcular costo total de estadía\n" +
                    "--- GESTIÓN DE HABITACIONES ---\n" +
                    "4. Listado de habitaciones disponibles\n" +
                    "5. Consultar estado de habitación\n" +
                    "--- RESERVAS Y TARIFAS ---\n" +
                    "6. Mostrar tarifas por tipo de habitación\n" +
                    "7. Mostrar tipos y temporadas disponibles\n" +
                    "=====================================\n" +
                    "0. Salir";

            String entrada = JOptionPane.showInputDialog(null, menu,
                    "Menú Principal - Hotel Las Cumbres", JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                int respuesta = Salida.mConfirmacion("¿Desea salir del sistema?", "Salir");
                if (respuesta == JOptionPane.YES_OPTION) {
                    Salida.mMensaje("¡Hasta luego! Gracias por usar el sistema.", "Salir");
                    System.exit(0);
                }
                opcion = -1;
            } else {
                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    Salida.mError("Ingrese un número válido.", "Error");
                    opcion = -1;
                }
            }

            switch (opcion) {
                case 1:
                    registrarHuesped();
                    break;
                case 2:
                    listarHuespedes();
                    break;
                case 3:
                    calcularCostoEstadia();
                    break;
                case 4:
                    listarHabitacionesDisponibles();
                    break;
                case 5:
                    consultarEstadoHabitacion();
                    break;
                case 6:
                    mostrarTarifas();
                    break;
                case 7:
                    mostrarTiposYTemporadas();
                    break;
                case 0:
                    Salida.mMensaje("¡Hasta luego! Gracias por usar el sistema.",
                            "Salir");
                    break;
                default:
                    if (opcion != -1) {
                        Salida.mError("Opción no válida. Ingrese un número del 0 al 7.", "Error");
                    }
                    break;
            }

        } while (opcion != 0);
    }

    /*--------------------------------------------------------- */
    /* Inicializa las habitaciones predefinidas del hotel        */
    /*--------------------------------------------------------- */
    private static void inicializarHabitaciones() {
        habitaciones[0] = new HabitacionSimple(101, EstadoHabitacion.DISPONIBLE, 5000);
        habitaciones[1] = new HabitacionSimple(102, EstadoHabitacion.OCUPADA, 5000);
        habitaciones[2] = new HabitacionSimple(103, EstadoHabitacion.DISPONIBLE, 5000);
        habitaciones[3] = new HabitacionDoble(201, EstadoHabitacion.DISPONIBLE, 8000);
        habitaciones[4] = new HabitacionDoble(202, EstadoHabitacion.MANTENIMIENTO, 8000);
        habitaciones[5] = new HabitacionDoble(203, EstadoHabitacion.DISPONIBLE, 8000);
        habitaciones[6] = new Suite(301, EstadoHabitacion.DISPONIBLE, 15000);
        habitaciones[7] = new Suite(302, EstadoHabitacion.OCUPADA, 15000);
        cantHabitaciones = 8;
    }

    /*--------------------------------------------------------- */
    /* Registra un nuevo huésped                                */
    /*--------------------------------------------------------- */
    private static void registrarHuesped() {
        if (cantHuespedes >= huespedes.length) {
            Salida.mAdvertencia("No se pueden registrar más huéspedes.", "Capacidad máxima");
            return;
        }

        String nombre = MiIngreso.pedirString("Nombre:", "Registrar Huésped");
        if (MiIngreso.cancelado) return;

        String apellido = MiIngreso.pedirString("Apellido:", "Registrar Huésped");
        if (MiIngreso.cancelado) return;

        String dni = MiIngreso.pedirString("DNI:", "Registrar Huésped");
        if (MiIngreso.cancelado) return;

        String idHuesped = MiIngreso.pedirString("ID Huésped:", "Registrar Huésped");
        if (MiIngreso.cancelado) return;

        int diasEstadia = MiIngreso.pedirEnteroPositivo("Días de estadía:", "Registrar Huésped");
        if (MiIngreso.cancelado) return;

        String[] opcionesPago = {"Anticipado", "Efectivo", "Tarjeta"};
        String formaPago = Ingreso.datoSelect("Forma de pago:", "Registrar Huésped",
                opcionesPago, JOptionPane.QUESTION_MESSAGE);

        huespedes[cantHuespedes] = new Huesped(nombre, apellido, dni,
                idHuesped, diasEstadia, formaPago);
        cantHuespedes++;

        Salida.mMensaje("Huésped registrado correctamente.", "Registro exitoso");
    }

    /*--------------------------------------------------------- */
    /* Lista todos los huéspedes registrados                    */
    /*--------------------------------------------------------- */
    private static void listarHuespedes() {
        if (cantHuespedes == 0) {
            Salida.mAdvertencia("No hay huéspedes registrados.", "Sin huéspedes");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=====================================\n");
        lista.append("       HUÉSPEDES REGISTRADOS         \n");
        lista.append("=====================================\n");
        for (int i = 0; i < cantHuespedes; i++) {
            lista.append("\n[" + (i + 1) + "]\n");
            lista.append(huespedes[i].obtenerDatos());
            lista.append("\n-------------------------------------");
        }
        Salida.mMensaje(lista.toString(), "Listado de Huéspedes");
    }

    /*--------------------------------------------------------- */
    /* Calcula el costo total de la estadía de un huésped       */
    /*--------------------------------------------------------- */
    private static void calcularCostoEstadia() {
        if (cantHuespedes == 0) {
            Salida.mAdvertencia("No hay huéspedes registrados.", "Sin huéspedes");
            return;
        }

        String idBuscar = MiIngreso.pedirString("Ingrese el ID del huésped:", "Calcular Costo");
        if (MiIngreso.cancelado) return;

        Huesped huesped = buscarHuesped(idBuscar);
        if (huesped == null) {
            Salida.mAdvertencia("Huésped no encontrado.", "No encontrado");
            return;
        }

        Temporada temporada = Ingreso.datoEnumSelect("Seleccione la temporada:",
                "Calcular Costo", JOptionPane.QUESTION_MESSAGE, Temporada.class);

        // Buscamos una habitación disponible para calcular la tarifa
        Habitacion habitacion = buscarHabitacionDisponible();
        if (habitacion == null) {
            Salida.mAdvertencia("No hay habitaciones disponibles.", "Sin habitaciones");
            return;
        }

        double tarifaPorNoche = habitacion.calcularTarifaSegunTemporada(temporada);
        double costoTotal = huesped.calcularCostoEstadia(tarifaPorNoche);

        String descuento = "";
        if (huesped.getFormaPago().equalsIgnoreCase("anticipado")) {
            descuento = "Descuento aplicado: 15% por pago anticipado";
        } else if (huesped.getDiasEstadia() > 7) {
            descuento = "Descuento aplicado: 10% por estadía mayor a 7 noches";
        } else {
            descuento = "Sin descuento aplicado";
        }

        Salida.mMensaje(
                "Huésped: " + huesped.getNombre() + " " + huesped.getApellido() +
                "\nDías de estadía: " + huesped.getDiasEstadia() +
                "\nTemporada: " + temporada +
                "\nTarifa por noche: $" + String.format("%.2f", tarifaPorNoche) +
                "\n" + descuento +
                "\nCosto total: $" + String.format("%.2f", costoTotal),
                "Costo de Estadía");
    }

    /*--------------------------------------------------------- */
    /* Lista las habitaciones disponibles ordenadas por número  */
    /*--------------------------------------------------------- */
    private static void listarHabitacionesDisponibles() {
        StringBuilder lista = new StringBuilder();
        lista.append("=====================================\n");
        lista.append("     HABITACIONES DISPONIBLES        \n");
        lista.append("=====================================\n");

        // Ordenamos por número de habitación antes de mostrar
        ordenarHabitacionesPorNumero();

        boolean hayDisponibles = false;
        for (int i = 0; i < cantHabitaciones; i++) {
            if (habitaciones[i].getEstado() == EstadoHabitacion.DISPONIBLE) {
                lista.append("\n" + habitaciones[i].obtenerDatos());
                lista.append("\n-------------------------------------");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            Salida.mAdvertencia("No hay habitaciones disponibles.", "Sin disponibilidad");
        } else {
            Salida.mMensaje(lista.toString(), "Habitaciones Disponibles");
        }
    }

    /*--------------------------------------------------------- */
    /* Consulta el estado de una habitación por número          */
    /*--------------------------------------------------------- */
    private static void consultarEstadoHabitacion() {
        int numero = MiIngreso.pedirEnteroPositivo("Ingrese el número de habitación:",
                "Consultar Estado");
        if (MiIngreso.cancelado) return;

        Habitacion habitacion = buscarHabitacionPorNumero(numero);
        if (habitacion == null) {
            Salida.mAdvertencia("Habitación no encontrada.", "No encontrada");
        } else {
            Salida.mMensaje(habitacion.consultarEstado(), "Estado de Habitación");
        }
    }

    /*--------------------------------------------------------- */
    /* Muestra las tarifas por noche según tipo y temporada     */
    /*--------------------------------------------------------- */
    private static void mostrarTarifas() {
        StringBuilder tarifas = new StringBuilder();
        tarifas.append("=====================================\n");
        tarifas.append("     TARIFAS POR TIPO Y TEMPORADA   \n");
        tarifas.append("=====================================\n");

        HabitacionSimple simple = new HabitacionSimple(0, EstadoHabitacion.DISPONIBLE, 5000);
        HabitacionDoble doble = new HabitacionDoble(0, EstadoHabitacion.DISPONIBLE, 8000);
        Suite suite = new Suite(0, EstadoHabitacion.DISPONIBLE, 15000);

        tarifas.append("\nHABITACIÓN SIMPLE (precio base: $5.000)");
        tarifas.append("\n  Temporada Alta:  $" + String.format("%.2f",
                simple.calcularTarifaSegunTemporada(Temporada.ALTA)));
        tarifas.append("\n  Temporada Media: $" + String.format("%.2f",
                simple.calcularTarifaSegunTemporada(Temporada.MEDIA)));
        tarifas.append("\n  Temporada Baja:  $" + String.format("%.2f",
                simple.calcularTarifaSegunTemporada(Temporada.BAJA)));

        tarifas.append("\n\nHABITACIÓN DOBLE (precio base: $8.000)");
        tarifas.append("\n  Temporada Alta:  $" + String.format("%.2f",
                doble.calcularTarifaSegunTemporada(Temporada.ALTA)));
        tarifas.append("\n  Temporada Media: $" + String.format("%.2f",
                doble.calcularTarifaSegunTemporada(Temporada.MEDIA)));
        tarifas.append("\n  Temporada Baja:  $" + String.format("%.2f",
                doble.calcularTarifaSegunTemporada(Temporada.BAJA)));

        tarifas.append("\n\nSUITE (precio base: $15.000)");
        tarifas.append("\n  Temporada Alta:  $" + String.format("%.2f",
                suite.calcularTarifaSegunTemporada(Temporada.ALTA)));
        tarifas.append("\n  Temporada Media: $" + String.format("%.2f",
                suite.calcularTarifaSegunTemporada(Temporada.MEDIA)));
        tarifas.append("\n  Temporada Baja:  $" + String.format("%.2f",
                suite.calcularTarifaSegunTemporada(Temporada.BAJA)));

        Salida.mMensaje(tarifas.toString(), "Tarifas por Noche");
    }

    /*--------------------------------------------------------- */
    /* Muestra tipos de habitación y temporadas disponibles     */
    /*--------------------------------------------------------- */
    private static void mostrarTiposYTemporadas() {
        StringBuilder info = new StringBuilder();
        info.append("=====================================\n");
        info.append("   TIPOS DE HABITACIÓN Y TEMPORADAS  \n");
        info.append("=====================================\n");
        info.append("\nTIPOS DE HABITACIÓN:\n");
        info.append("  - Simple: ideal para viajeros individuales\n");
        info.append("  - Doble: ideal para parejas o dos personas\n");
        info.append("  - Suite: máximo confort con vista a las sierras\n");
        info.append("\nTEMPORADAS:\n");
        info.append("  - Alta: mayor demanda, tarifas con recargo elevado\n");
        info.append("  - Media: demanda moderada, tarifas intermedias\n");
        info.append("  - Baja: menor demanda, tarifas base o mínimo recargo\n");
        info.append("\nDESCUENTOS DISPONIBLES:\n");
        info.append("  - 15% por pago anticipado\n");
        info.append("  - 10% por estadía mayor a 7 noches");

        Salida.mMensaje(info.toString(), "Tipos y Temporadas");
    }

    /*--------------------------------------------------------- */
    /* Métodos auxiliares                                       */
    /*--------------------------------------------------------- */

    private static Huesped buscarHuesped(String idBuscar) {
        for (int i = 0; i < cantHuespedes; i++) {
            if (huespedes[i].getIdHuesped().equalsIgnoreCase(idBuscar)) {
                return huespedes[i];
            }
        }
        return null;
    }

    private static Habitacion buscarHabitacionPorNumero(int numero) {
        for (int i = 0; i < cantHabitaciones; i++) {
            if (habitaciones[i].getNumero() == numero) {
                return habitaciones[i];
            }
        }
        return null;
    }

    private static Habitacion buscarHabitacionDisponible() {
        for (int i = 0; i < cantHabitaciones; i++) {
            if (habitaciones[i].getEstado() == EstadoHabitacion.DISPONIBLE) {
                return habitaciones[i];
            }
        }
        return null;
    }

    private static void ordenarHabitacionesPorNumero() {
        boolean interruptor = true;
        for (int i = 0; i < cantHabitaciones - 1 && interruptor; i++) {
            interruptor = false;
            for (int j = 0; j < cantHabitaciones - i - 1; j++) {
                if (habitaciones[j].getNumero() > habitaciones[j + 1].getNumero()) {
                    interruptor = true;
                    Habitacion aux = habitaciones[j];
                    habitaciones[j] = habitaciones[j + 1];
                    habitaciones[j + 1] = aux;
                }
            }
        }
    }
}
