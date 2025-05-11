public class Main {

    public static void main(String[] args) {

        // Crear una tabla dispersa
        TablaDispersa tabla = new TablaDispersa();

        // Crear algunas tareas
        Tarea tarea1 = new Tarea("Tarea 1", "Descripción de la tarea 1", "pendiente");
        Tarea tarea2 = new Tarea("Tarea 2", "Descripción de la tarea 2", "en progreso");
        Tarea tarea3 = new Tarea("Tarea 3", "Descripción de la tarea 3", "finalizada");

        System.out.println(tarea1.toString());
        System.out.println(tarea2.toString());
        System.out.println(tarea3.toString());

        // Insertar tareas en la tabla
        tabla.insertar(tarea1);
        tabla.insertar(tarea2);
        tabla.insertar(tarea3);

        // Mostrar la tabla
        System.out.println("Tabla después de insertar tareas:");
        tabla.mostrarTabla();

        // Buscar una tarea
        String codigoABuscar = tarea1.getCodigo();
        Tarea tareaBuscada = tabla.buscar(codigoABuscar);
        if (tareaBuscada != null) {
            System.out.println("Tarea encontrada: " + tareaBuscada.toString());
        } else {
            System.out.println("Tarea no encontrada.");
        }

        // Eliminar una tarea
        String codigoAEliminar = tarea1.getCodigo();
        boolean eliminado = tabla.eliminar(codigoAEliminar);
        if (eliminado) {
            System.out.println("Tarea eliminada con éxito.");
        } else {
            System.out.println("No se pudo eliminar la tarea.");
        }

        // Mostrar la tabla después de eliminar una tarea
        System.out.println("Tabla después de eliminar una tarea:");
        tabla.mostrarTabla();

        // Intentar buscar la tarea eliminada
        Tarea tareaEliminada = tabla.buscar(codigoAEliminar);
    }
}