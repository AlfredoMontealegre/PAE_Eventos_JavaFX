package ni.uam.edu.recepciondecafe.DAO;

import ni.uam.edu.recepciondecafe.Interfaces.Interfaz;
import ni.uam.edu.recepciondecafe.Modelo.LoteCafe;

import java.util.ArrayList;
import java.util.List;

public class LotecafeDao implements Interfaz <LoteCafe> {
    List<LoteCafe> listaLoteCafe;
    public LotecafeDao(){listaLoteCafe = new ArrayList<>();
    }
    @Override
    public void agregar(LoteCafe entidad) {
        listaLoteCafe.add(entidad);
    }

    @Override
    public List<LoteCafe> obtenerLista() {
        return listaLoteCafe;
    }

    @Override
    public void eliminar(LoteCafe entidad) {
        listaLoteCafe.remove(entidad);
    }


}
