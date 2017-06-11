package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject {

    Handler handler;
    double directionX, directionY;
    final static int VELOCITY = 20;
    double angle, dx, dy;
    
    public Bullet(int x, int y, ID id, Handler handler, double directionX, double directionY) {
        super(x, y, id);
        this.handler = handler;
        this.directionX = directionX;
        this.directionY = directionY;
        
        //Calculate the angle of the bullet and the X en Y distance to travel
        this.angle = Math.atan2(directionY - y,directionX - x);
        this.dx = (float)Math.cos(angle);
        this.dy = (float)Math.sin(angle);
    }

    @Override
    public void update() {
        x += velX;
        y += velY;
        
        //Calculation of velocity X and Y   
        velX = (float) (VELOCITY * dx);
        velY = (float) (VELOCITY * dy);
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.RED);   
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
