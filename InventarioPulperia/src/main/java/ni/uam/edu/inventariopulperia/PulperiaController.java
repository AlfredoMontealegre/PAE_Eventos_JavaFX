package ni.uam.edu.inventariopulperia;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import ni.uam.edu.inventariopulperia.DAO.ProductoDao;
import ni.uam.edu.inventariopulperia.Modelos.Producto;


public class PulperiaController {


    ProductoDao listaProducto = new ProductoDao();
    @FXML private TextField txtNombre;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtBuscar;
    @FXML private Button btnAgregar;
    @FXML private Button btnBuscar;
    @FXML private Label lblMensajeError;
    @FXML private Label lblRespuestaBuscar;

    @FXML private void initialize(){
        PauseTransition temporizador = new PauseTransition(Duration.seconds(3));
    }

    @FXML
    protected void btnGuardar(ActionEvent event) {
        leerDatos();
    }

    @FXML
    private void buscarConEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            buscarCodigo();
        }
    }

    @FXML
    private void btnBuscar() {
        buscarCodigo();
    }

    private void buscarCodigo() {
        if (!validarTexto(txtBuscar)) {
            lblRespuestaBuscar.setText("Ingrese un código válido");
            temporizador();
            return;
        }
        if (!validarExistencia(txtBuscar)) {
            lblRespuestaBuscar.setText("No se encontró el producto");
            temporizador();
        }
    }
    private void leerDatos(){
        if(!validarTexto(txtNombre)){
            lblMensajeError.setText("Ingrese el nombre");
            temporizador();
            return;
        }
        if (!validarDecimal(txtPrecio)){
            lblMensajeError.setText("Ingrese un precio válido");
            temporizador();
            return;
        }
        if(!validarTexto(txtCodigo)){
            lblMensajeError.setText("Ingrese el codigo");
            temporizador();
            return;
        }
        if(!validarEntero(txtCantidad)){
            lblMensajeError.setText("Ingrese una cantidad válida");
            temporizador();
            return;
        }

        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());
        listaProducto.agregar(new Producto(nombre, codigo, precio, cantidad));
        cleanView();
        lblMensajeError.setText("Agregado correctamente");
        temporizador();
    }
    private void cleanView(){
        txtNombre.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        txtCodigo.clear();
    }

    private boolean validarTexto(TextField campo) {
        if (campo.getText() == null || campo.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }
    private boolean validarEntero(TextField campo) {
        try {
            int numero = Integer.parseInt(campo.getText().trim());
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validarDecimal(TextField campo){
        try{
            double numero = Double.parseDouble(campo.getText().trim());
            return numero > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }
    
    private boolean validarExistencia(TextField campo) {
        if (listaProducto.obtenerProducto().isEmpty()) {
            lblRespuestaBuscar.setText("La lista está vacía");
            return false;
        }
        String codigoBuscado = campo.getText().trim();
        for (Producto producto : listaProducto.obtenerProducto()) {
            if (producto.getCodigoProducto().equalsIgnoreCase(codigoBuscado)) {
                lblRespuestaBuscar.setText("Producto encontrado: " + producto.getNombreProducto());
                txtBuscar.clear();
                return true;
            }
        }
        return false;
    }
    private void temporizador(){
        PauseTransition temporizador = new PauseTransition(Duration.seconds(2));
        temporizador.setOnFinished(evento -> lblMensajeError.setText(""));
        temporizador.play();
    }
}