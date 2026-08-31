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
    private String Nombre;
    private String codigo;
    private double precio;
    private int cantidad;
}
