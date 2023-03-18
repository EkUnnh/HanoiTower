import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Disk extends Rectangle {

    private int size;

    public Disk(int size, int height, Color color) {
        this.size = size;
        setWidth(size * 20 + 20);
        setHeight(height);
        setArcWidth(10);
        setArcHeight(10);
        setFill(color);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    public int getSize() {
        return size;
    }
}
