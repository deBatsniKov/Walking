package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    public Rectangle getBounds() {
        return new Rectangle(x, y, Walking.SIZE, Walking.SIZE);
    }

}
