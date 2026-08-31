package ni.uam.edu.recepciondecafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecepcionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RecepcionApplication.class.getResource("Recepcion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Recepcion-Café");
        stage.setScene(scene);
        stage.show();
    }
}
