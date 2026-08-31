module ni.uam.edu.inventariopulperia {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.uam.edu.inventariopulperia to javafx.fxml;
    exports ni.uam.edu.inventariopulperia;
}