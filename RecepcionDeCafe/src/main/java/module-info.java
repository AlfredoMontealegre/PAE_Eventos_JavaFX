module ni.uam.edu.recepciondecafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.uam.edu.recepciondecafe to javafx.fxml;
    exports ni.uam.edu.recepciondecafe;
}