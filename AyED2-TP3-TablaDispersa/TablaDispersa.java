public class TablaDispersa {
    private final int tamanioTabla = 101;
    private Tarea[] tabla;
    private int numElementos;
    private double factorCarga;

    public TablaDispersa() {
        this.tabla = new Tarea[tamanioTabla];
        this.numElementos = 0;
        this.factorCarga = 0.0;
    }

    private double obtenerValorNumerico(String codigo) { // convierte el código de la tarea a un valor numérico
        double valor = 0.0;

        // Se suman los valores ASCII de cada carácter del código y se convierte a un valor numérico
        for (int i = 0; i < codigo.length(); i++) {
            valor += (int) codigo.charAt(i);
        }
        return valor;
    }

    private int calcularPosicion(String codigo) { // Método de la Multiplicación
        double A = 0.6180339887;
        double valor = obtenerValorNumerico(codigo);

        // Se multiplica el valor por A y se toma la parte decimal
        double producto = valor * A; 
        double decimal = producto - Math.floor(producto);

        // Se multiplica el decimal por el tamaño de la tabla
        return (int)(decimal * tamanioTabla);
    }

    private int resolverColision(int posicionInicial, int i) { // Exploración cuadrática
        return (posicionInicial + i * i) % tamanioTabla;
    }

    public double calcularFactorCarga() { // Calcula el factor de carga
        return (double) numElementos / tamanioTabla;
    }

    public boolean insertar(Tarea tarea) {
        // Verifica si la tabla está llena
        if (numElementos >= tamanioTabla) {
            System.out.println("La tabla está llena. No se puede insertar la tarea.");
            return false;
        }

        String codigo = tarea.getCodigo();
        int posicionInicial = calcularPosicion(codigo);
        int posicion = posicionInicial;

        for (int i = 0; i < tamanioTabla; i++) {
            // Si la posición está vacía, se inserta la tarea
            if (tabla[posicion] == null) {
                tabla[posicion] = tarea;
                numElementos++;
                factorCarga = calcularFactorCarga(); // Actualiza el factor de carga
                System.out.println("Tarea insertada en la posición: " + posicion);
                System.out.println("Factor de carga: " + factorCarga);

                return true;

            // Si la posición ya tiene un elemento, se verifica si es el mismo código
            } else if (tabla[posicion].getCodigo().equals(codigo)) {
                System.out.println("El código ya existe en la posición: " + posicion);
                return false; // El código ya existe

            // Si hay una colisión, se resuelve con exploración cuadrática
            } else {
                posicion = resolverColision(posicionInicial, i);
            }
        }
        System.out.println("No se pudo insertar la tarea. La tabla está llena.");
        return false; // No se pudo insertar
    }

    public Tarea buscar(String codigo) { // Busca una tarea activa por su código
        int posicionInicial = calcularPosicion(codigo);
        int posicion = posicionInicial;
        for (int i = 0; i < tamanioTabla; i++) {
            // Si la posición está vacía, la tarea no existe
            if (tabla[posicion] == null) {
                System.out.println("La tarea no existe.");
                return null;
            }

            // Si se encuentra la tarea, se verifica si está activa
            if (tabla[posicion].getCodigo().equals(codigo)) {
                if (tabla[posicion].isEsAlta()) {
                    return tabla[posicion]; // Tarea activa encontrada
                } else {
                    System.out.println("La tarea ha sido eliminada lógicamente.");
                    return null; // Tarea eliminada lógicamente
                }
            }
            // Se resuelve la colisión
            posicion = resolverColision(posicionInicial, i);
        }
        System.out.println("La tarea no existe.");
        return null; // No se encontró la tarea
    }

    public boolean eliminar(String codigo) { // Elimina una tarea de forma lógica
        Tarea tarea = buscar(codigo);
        if (tarea != null) {
            tarea.eliminar(); // Cambia el estado de la tarea a inactiva
            System.out.println("Tarea eliminada: " + tarea.toString());
            return true;
        }
        return false; // No se encontró la tarea
    }

    public void mostrarTabla() { // Muestra la tabla sólo con las tareas que están activas
        for (int i = 0; i < tamanioTabla; i++) {
            if (tabla[i] != null && tabla[i].isEsAlta()) {
                System.out.println("Posición " + i + ": " + tabla[i].toString());
            }
        }
    }
}
