package it.cnr.istc.grafx;

import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Graph
 */
public class Graph extends Pane {

    private final Canvas canvas = new Canvas();
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final GraphAnimation animation = new GraphAnimation();
    private final ObjectProperty<Color> background = new SimpleObjectProperty<>(Color.WHITESMOKE);
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(Font.getDefault());
    private final Collection<Node> nodes = new ArrayList<>();
    private final Collection<Edge> edges = new ArrayList<>();
    private double scale = 1;
    private double x, y;
    private double dx, dy;

    public Graph() {
        getChildren().add(canvas);

        setOnScroll((ScrollEvent event) -> scale += event.getDeltaY() * 0.01);
        setOnMousePressed((MouseEvent event) -> {
            x = event.getX() - dx;
            y = event.getY() - dy;
        });
        setOnMouseDragged((MouseEvent event) -> {
            dx = event.getX() - x;
            dy = event.getY() - y;
        });
    }

    /**
     * @return the font
     */
    public ObjectProperty<Font> fontProperty() {
        return font;
    }

    /**
     * @return the animation
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        final double x = snappedLeftInset();
        final double y = snappedTopInset();
        final double w = snapSizeX(getWidth()) - x - snappedRightInset();
        final double h = snapSizeY(getHeight()) - y - snappedBottomInset();
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
        canvas.setWidth(w);
        canvas.setHeight(h);
    }

    private class GraphAnimation extends AnimationTimer {

        private long last_update = System.nanoTime();

        @Override
        public void handle(long now) {
            long dt = now - last_update;

            gc.setFill(background.get());
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            gc.save();
            gc.translate(dx, dy);
            gc.scale(scale, scale);

            // TODO: Compute forces..

            for (Edge e : edges)
                e.render(gc);
            for (Node n : nodes)
                n.render(gc);

            gc.restore();
            last_update = now;
        }
    }
}