package ni.uam.edu.recepciondecafe;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ni.uam.edu.recepciondecafe.DAO.LotecafeDao;
import ni.uam.edu.recepciondecafe.Modelo.LoteCafe;

public class RecepcionController {
    LotecafeDao loteCafe = new LotecafeDao();
    private LoteCafe loteEnEdicion = null;
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
        LoteCafe seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            lblMensajeError.setText("Seleccione un lote");
            return;
        }
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar edición");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro que desea editar su lote: "
                + seleccionado.getCodigoLote() + "?");

        alerta.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                txtNombre.setText(seleccionado.getProductorLote());
                txtCodigo.setText(seleccionado.getCodigoLote());
                txtPesoKG.setText(""+ seleccionado.getPesoKgLote());
                loteEnEdicion = seleccionado;
            }
        });

    }
    private void eliminarLoteSeleccionado(){
        LoteCafe seleccionado = tablaLotes.getSelectionModel().getSelectedItem();

        if (seleccionado == null){
            lblMensajeError.setText("Seleccione un lote");
            return;
        }
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar eliminación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro que desea eliminar el lote con código: " + seleccionado.getCodigoLote() + "?");

        alerta.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {

                listaLoteCafe.remove(seleccionado);
                loteCafe.obtenerLista().remove(seleccionado);

                lblMensajeError.setText("Lote eliminado correctamente.");
                temporizador();
            }
        });

    }
    private void prepararTable(){
     colNombre.setCellValueFactory(
             fila -> new SimpleStringProperty(fila.getValue().getProductorLote()));
     colCodigo.setCellValueFactory(
             fila -> new SimpleStringProperty(fila.getValue().getCodigoLote()));
        colpesoKG.setCellValueFactory(
                fila -> new SimpleDoubleProperty(fila.getValue().getPesoKgLote()).asObject());
     tablaLotes.setItems(listaLoteCafe);
    }

    private void agregarDatos(){
        leerDatos();
    }

    @FXML
    protected void BtnactualizarLote() {

        if (loteEnEdicion == null) {
            lblMensajeError.setText("Seleccione un lote desde la tabla para editar.");
            temporizador();
            return;
        }

        if (!validarTexto(txtNombre)) { lblMensajeError.setText("Ingrese su nombre"); temporizador(); return; }
        if (!validarTexto(txtCodigo)) { lblMensajeError.setText("Ingrese un codigo válido"); temporizador(); return; }
        if (!validarDecimal(txtPesoKG)) { lblMensajeError.setText("Ingrese un peso válido"); temporizador(); return; }

        loteEnEdicion.setProductorLote(txtNombre.getText());
        loteEnEdicion.setCodigoLote(txtCodigo.getText());
        loteEnEdicion.setPesoKgLote(Double.parseDouble(txtPesoKG.getText()));

        tablaLotes.refresh();

        lblMensajeError.setText("Lote actualizado correctamente.");
        temporizador();

        loteEnEdicion = null;
        cleanView();
    }

    private void leerDatos(){
        if(!validarTexto(txtNombre)){
            lblMensajeError.setText("Ingrese su nombre");
            temporizador();
            return;
        }
        if (!validarTexto(txtCodigo)){
            lblMensajeError.setText("Ingrese un codigo válido");
            temporizador();
            return;
        }
        if (!validarDecimal(txtPesoKG)){
            lblMensajeError.setText("Ingrese un peso válido");
            temporizador();
            return;
        }

        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        double pesoKG = Double.parseDouble(txtPesoKG.getText());

        LoteCafe nuevoLote = new LoteCafe(nombre, codigo, pesoKG);
        loteCafe.agregar(nuevoLote);
        listaLoteCafe.add(nuevoLote);
        lblMensajeError.setText("Lote guardado.");
        temporizador();
        cleanView();
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
        lblMensajeError.setText("");
    }
    private void temporizador(){
        PauseTransition temporizador = new PauseTransition(Duration.seconds(2));
        temporizador.setOnFinished(evento -> lblMensajeError.setText(""));
        temporizador.play();
    }
}
