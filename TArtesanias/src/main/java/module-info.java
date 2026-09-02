module ni.uam.edu.tartesanias {

    requires javafx.controls;
    requires javafx.fxml;

    exports ni.uam.edu.tartesanias;
    exports ni.uam.edu.tartesanias.controllers;
    exports ni.uam.edu.tartesanias.modelos;

    opens ni.uam.edu.tartesanias.controllers
            to javafx.fxml;

    opens ni.uam.edu.tartesanias.modelos
            to javafx.fxml;
}