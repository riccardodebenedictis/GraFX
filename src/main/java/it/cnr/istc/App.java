package it.cnr.istc;

import it.cnr.istc.grafx.Graph;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Graph");
        stage.setScene(new Scene(new Graph()));
        stage.show();
    }
}