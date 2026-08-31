package ni.uam.edu.recepciondecafe;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import ni.uam.edu.recepciondecafe.DAO.LotecafeDao;
import ni.uam.edu.recepciondecafe.Modelo.LoteCafe;

public class RecepcionController {
    LotecafeDao loteCafe = new LotecafeDao();
    @FXML private TableView<LoteCafe> tablaLotes;
    @FXML private TableColumn<LoteCafe, String> colNombre;
    @FXML private TableColumn<LoteCafe, Double> colpesoKG;
    @FXML private TableColumn<LoteCafe, String> colCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPesoKG;
    @FXML private TextField txtCodigo;
    @FXML private Label lblMensajeError;
    private final ObservableList listaLoteCafe = FXCollections.observableArrayList();

    @FXML public void initialize(){

        MenuItem editar = new MenuItem("Editar lote");
        MenuItem eliminar = new MenuItem("Eliminar lote");
        ContextMenu menuOpciones = new ContextMenu(editar, eliminar);
        editar.setOnAction(e -> editarLoteSeleccionado());
        eliminar.setOnAction(e -> eliminarLoteSeleccionado());

        prepararTable();
        tablaLotes.setContextMenu(menuOpciones);
        }

    @FXML
    protected void ButtonGuardar() {
            agregarDatos();

    }
    private void editarLoteSeleccionado(){

    }
    private void eliminarLoteSeleccionado(){

    }
    private void prepararTable(){
     colNombre.setCellValueFactory(
             fila -> new SimpleStringProperty(fila.getValue().getProductor()));
     colCodigo.setCellValueFactory(
             fila -> new SimpleStringProperty(fila.getValue().getCodigo()));
        colpesoKG.setCellValueFactory(
                fila -> new SimpleDoubleProperty(fila.getValue().getPesoKG()).asObject());
     tablaLotes.setItems(listaLoteCafe);
    }

    private void agregarDatos(){
        leerDatos();
        listaLoteCafe.addAll(loteCafe.obtenerLista());
    }

    private void leerDatos(){
        if(!validarTexto(txtNombre)){
            lblMensajeError.setText("Ingrese su nombre");
            return;
        }
        if (!validarTexto(txtCodigo)){
            lblMensajeError.setText("Ingrese un codigo válido");
            return;
        }
        if (!validarDecimal(txtPesoKG)){
            lblMensajeError.setText("Ingrese un peso válido");
            return;
        }
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        double pesoKG = Double.parseDouble(txtPesoKG.getText());
        loteCafe.agregar(new LoteCafe(nombre, codigo,pesoKG));
        cleanView();
        probar();
    }

    private boolean validarTexto(TextField campo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validarDecimal(TextField campo){
        try{
            double numero = Double.parseDouble(campo.getText().trim());
            return numero > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private void cleanView(){
        txtNombre.clear();
        txtCodigo.clear();
        txtPesoKG.clear();
    }

    private void probar(){
        lblMensajeError.setText("" + loteCafe.obtenerLista().size());

    }

}
