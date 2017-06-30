package walking;

import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block extends GameObject {

    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext g){
        g.setFill(Color.BLACK);
        g.fillRect(x, y, Walking.SIZE, Walking.SIZE);
    }
    
    @Override
    public List getBounds() {
    return Arrays.asList(
                new Point2D(x,  y),
                new Point2D(x + Walking.SIZE,  y),
                new Point2D(x + Walking.SIZE,  y + Walking.SIZE),
                new Point2D(x,  y + Walking.SIZE)
        );
    }

}
