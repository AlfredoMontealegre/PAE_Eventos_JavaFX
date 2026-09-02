package ni.uam.edu.tartesanias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ArtesaniasApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                ArtesaniasApplication.class.getResource(
                        "tienda-view.fxml"
                )
        );

        Scene scene = new Scene(
                fxmlLoader.load(),
                900,
                650
        );

        stage.setTitle("Artesanías Güegüenses");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}