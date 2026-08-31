package ni.uam.edu.recepciondecafe.Modelo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoteCafe {
    private String productor;
    private String codigo;
    private double pesoKG;
}
