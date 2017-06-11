package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends GameObject{

    Handler handler;
    
    public Box(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void update() {
        x += velX;
        y += velY;
        
        if(handler.isUp()) velY = -10; 
        else if(!handler.isDown()) velY = 0;
        
        if(handler.isDown()) velY = 10; 
        else if(!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = 10;
        else if(!handler.isLeft()) velX = 0;
        
        if(handler.isLeft()) velX = -10;
        else if(!handler.isRight()) velX = 0;
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);        
        g.fillRect(x, y, 40, 40);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}