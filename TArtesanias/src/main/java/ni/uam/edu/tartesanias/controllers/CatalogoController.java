package ni.uam.edu.tartesanias.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.uam.edu.tartesanias.dao.ProductoDao;
import ni.uam.edu.tartesanias.modelos.Producto;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;

public class CatalogoController {

    private final ProductoDao listado = new ProductoDao();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label lblImagen;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Image> colImagen;


    @FXML
    protected void seleccionarImagenOnClick() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File archivo = fileChooser.showOpenDialog(
                imgProducto.getScene().getWindow()
        );

        if (archivo != null) {

            Image imagen = new Image(
                    archivo.toURI().toString()
            );

            imgProducto.setImage(imagen);

            lblImagen.setText(
                    archivo.getName()
            );
        }
    }


    @FXML
    protected void guardarOnClick() {

        String nombre = txtNombre.getText();
        String categoria = txtCategoria.getText();

        double precio;

        try {

            precio = Double.parseDouble(
                    txtPrecio.getText()
            );

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Error",
                    "El precio debe ser un número."
            );

            return;
        }

        Image imagen = imgProducto.getImage();

        Producto producto = new Producto(
                nombre,
                categoria,
                precio,
                imagen
        );

        listado.agregar(producto);

        tblProductos.getItems().setAll(
                listado.obtenerProductos()
        );

        limpiarCampos();

        mostrarMensaje(
                "Producto guardado",
                "El producto se agregó correctamente."
        );
    }


    @FXML
    protected void nuevoOnClick() {
        limpiarCampos();
    }


    @FXML
    protected void buscarOnClick() {

        String texto = txtBuscar.getText()
                .toLowerCase();

        if (texto.isEmpty()) {

            tblProductos.getItems().setAll(
                    listado.obtenerProductos()
            );

            return;
        }

        tblProductos.getItems().setAll(
                listado.obtenerProductos()
                        .stream()
                        .filter(producto ->
                                producto.getNombre()
                                        .toLowerCase()
                                        .contains(texto)
                        )
                        .toList()
        );
    }


    @FXML
    protected void ventasOnClick() {

        mostrarMensaje(
                "Ventas",
                "Sección de ventas."
        );
    }


    @FXML
    protected void ayudaOnClick() {

        mostrarMensaje(
                "Ayuda",
                "Catálogo de artesanías nicaragüenses."
        );
    }


    private void limpiarCampos() {

        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();

        imgProducto.setImage(null);

        lblImagen.setText(
                "No se ha seleccionado una imagen"
        );
    }


    private void mostrarMensaje(
            String titulo,
            String mensaje
    ) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colImagen.setCellValueFactory(
                new PropertyValueFactory<>("imagen")
        );

        colImagen.setCellFactory(column -> new TableCell<>() {

            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {

                super.updateItem(image, empty);

                if (empty || image == null) {

                    setGraphic(null);

                } else {

                    imageView.setImage(image);
                    imageView.setFitHeight(60);
                    imageView.setFitWidth(80);
                    imageView.setPreserveRatio(true);

                    setGraphic(imageView);
                }
            }
        });
    }
}