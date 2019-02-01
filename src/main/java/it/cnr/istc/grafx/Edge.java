package it.cnr.istc.grafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Edge
 */
public class Edge {

    private static final double ARR_SIZE = 5;
    private final Graph graph;
    private final Node from, to;
    Color stroke = Color.BLACK;
    Color fill = Color.BLACK;

    Edge(Graph graph, Node from, Node to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    void render(GraphicsContext gc) {
        double slope = (to.location[1] - from.location[1]) / (to.location[0] - from.location[0]);
        double hsw = slope * (to.width + 4) / 2;
        double hsh = ((to.height + 4) / 2) / slope;
        double hh = (to.height + 4) / 2;
        double hw = (to.width + 4) / 2;

        // the intersection point..
        double x = 0, y = 0;

        if (-hh <= hsw && hsw <= hh) {
            // line intersects..
            if (to.location[0] < from.location[0]) {
                // right edge..
                x = to.location[0] + hw;
                y = to.location[1] + hsw;
            } else if (to.location[0] > from.location[0]) {
                // left edge..
                x = to.location[0] - hw;
                y = to.location[1] - hsw;
            }
        } else if (-hw <= hsh && hsh <= hw) {
            // line intersects..
            if (to.location[1] < from.location[1]) {
                // bottom edge..
                x = to.location[0] + hsh;
                y = to.location[1] + hh;
            } else if (to.location[1] > from.location[1]) {
                // top edge..
                x = to.location[0] - hsh;
                y = to.location[1] - hh;
            }
        }

        gc.setStroke(stroke);
        gc.strokeLine(from.location[0], from.location[1], x, y);

        // ArrowHead
        double angle = Math.atan2((y - from.location[1]), (x - from.location[0])) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        // point1
        double x1 = (-1.0 / 2.0 * cos + Math.sqrt(5) / 2 * sin) * ARR_SIZE + x;
        double y1 = (-1.0 / 2.0 * sin - Math.sqrt(5) / 2 * cos) * ARR_SIZE + y;
        // point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(5) / 2 * sin) * ARR_SIZE + x;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(5) / 2 * cos) * ARR_SIZE + y;

        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.closePath();

        gc.setFill(fill);
        gc.fill();
        gc.setLineWidth(1.5);
        gc.stroke();
        gc.setLineWidth(1);
    }
}