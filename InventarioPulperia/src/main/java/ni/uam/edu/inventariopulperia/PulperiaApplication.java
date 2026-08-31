package ni.uam.edu.inventariopulperia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PulperiaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PulperiaApplication.class.getResource("Pulperia-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventario-Pulperia");
        stage.setScene(scene);
        stage.show();
    }
}
