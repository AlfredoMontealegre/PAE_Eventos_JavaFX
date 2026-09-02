package ni.uam.edu.tartesanias.dao;

import ni.uam.edu.tartesanias.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    private final List<Producto> productos = new ArrayList<>();

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }
}