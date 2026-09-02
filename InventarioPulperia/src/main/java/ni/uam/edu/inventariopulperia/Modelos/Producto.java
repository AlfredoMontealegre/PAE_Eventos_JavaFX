package ni.uam.edu.inventariopulperia.Modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Producto {
    private String nombreProducto;
    private String codigoProducto;
    private double precioProducto;
    private int cantidadProducto;
}
