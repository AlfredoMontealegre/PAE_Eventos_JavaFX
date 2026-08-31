package ni.uam.edu.inventariopulperia.Interfaces;

import java.util.List;

public interface Interfaz <T>{
    public void agregar(T entidad);
    public List<T> obtenerProducto();
}
