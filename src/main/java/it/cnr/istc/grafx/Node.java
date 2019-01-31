package it.cnr.istc.grafx;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Node
 */
public class Node {

    private static final double BORDER = 5;
    private final Graph graph;
    private String text;
    double width, height;
    double[] location = new double[] { 0, 0 };
    double arc = 3;
    Color fill = Color.YELLOWGREEN;
    Color stroke = Color.BROWN;
    Color text_stroke = Color.BLACK;

    Node(final Graph graph, String text, double x, double y) {
        this.graph = graph;
        setText(text);
        location[0] = x;
        location[1] = y;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
        Bounds text_bounds = new Text(text).getBoundsInLocal();
        width = text_bounds.getWidth() + BORDER * 2;
        height = text_bounds.getHeight() + BORDER * 2;
    }

    void render(GraphicsContext gc) {
        gc.setFill(fill);
        gc.fillRoundRect(location[0] - width / 2, location[1] - height / 2, width, height, arc, arc);

        gc.setStroke(stroke);
        gc.strokeRoundRect(location[0] - width / 2, location[1] - height / 2, width, height, arc, arc);

        gc.setStroke(text_stroke);
        gc.setFont(graph.fontProperty().get());
        gc.strokeText(text, location[0] - width / 2 + BORDER, location[1] + BORDER);
    }
}