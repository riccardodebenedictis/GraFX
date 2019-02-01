package it.cnr.istc;

import it.cnr.istc.grafx.Graph;
import it.cnr.istc.grafx.Node;
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
        Graph graph = new Graph();
        stage.setTitle("Graph");
        stage.setScene(new Scene(graph));
        stage.show();

        graph.getAnimation().start();

        Node n1 = graph.addNode("Node 1");
    }
}