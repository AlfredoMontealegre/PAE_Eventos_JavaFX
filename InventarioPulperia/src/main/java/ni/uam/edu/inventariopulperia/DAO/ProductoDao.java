package ni.uam.edu.inventariopulperia.DAO;

import ni.uam.edu.inventariopulperia.Interfaces.Interfaz;
import ni.uam.edu.inventariopulperia.Modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao implements Interfaz<Producto> {
    private List<Producto> listaProducto;

    public ProductoDao() {
        this.listaProducto = new ArrayList<>();
    }
    @Override
    public void agregar(Producto entidad) {
        listaProducto.add(entidad);
    }

    @Override
    public List<Producto> obtenerProducto() {
        return listaProducto;
    }
}
