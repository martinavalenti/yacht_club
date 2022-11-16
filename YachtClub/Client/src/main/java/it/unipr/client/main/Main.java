package it.unipr.client.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * The {@code Main} class contains the {@code public static void main}
 * of the Client module
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignForm.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle("Sign Form");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/logo.png"))));
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    /**
     * Main method of the Client application
     *
     * @param args parameters passed as arguments
     * @since 1.0
     */
    public static void main(String[] args) {
        launch();
    }
}