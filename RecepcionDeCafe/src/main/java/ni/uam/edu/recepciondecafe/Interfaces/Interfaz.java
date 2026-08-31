package ni.uam.edu.recepciondecafe.Interfaces;

import ni.uam.edu.recepciondecafe.Modelo.LoteCafe;

import java.util.List;

public interface Interfaz <T> {
    public void agregar(T entidad);
    public List<LoteCafe> obtenerLista();
    public void eliminar(T entidad);
}
