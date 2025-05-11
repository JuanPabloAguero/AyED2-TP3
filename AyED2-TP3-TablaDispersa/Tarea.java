import java.util.UUID;

public class Tarea {
    private String codigo; // 5 caracteres
    private String nombre;
    private String descripcion;
    private String estado; //pendiente, en progreso,finalizada
    // fechaInicio
    // fechaFin
    private boolean esAlta;

    public Tarea(String nombre, String descripcion, String estado) {
        this.codigo = UUID.randomUUID().toString().substring(0, 5); // Solo los primeros 5 caracteres;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.esAlta = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isEsAlta() {
        return esAlta;
    }

    // eliminar de forma lógica
    public void eliminar() {
        this.esAlta = false;
    }

    @Override
    public String toString() {
        return "Tarea [" + 
        "Código = " + codigo +  
        ", Nombre = " + nombre + 
        ", Descripción = " + descripcion + 
        ", Estado = " + estado + 
        ", esAlta = " + esAlta + 
        "]";
    }
}
