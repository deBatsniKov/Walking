package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Bullet extends GameObject {

    final static int HEIGHT = 5;
    final static int WIDTH = 10;
    final static double BULLET_VELOCITY = 5;

    Handler handler;
    double directionX, directionY;
    double angle, dx, dy;

    public double[] pointX = new double[4];
    public double[] pointY = new double[4];

    double testX, testY;

    public Bullet(double x, double y, ID id, Handler handler, double directionX, double directionY) {
        super(x, y, id);
        this.handler = handler;
        this.directionX = directionX;
        this.directionY = directionY;

        //Calculate the angle of the bullet and the X en Y distance to travel
        this.angle = Math.atan2(directionY - y, directionX - x);
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
        this.velX = (BULLET_VELOCITY * dx);
        this.velY = (BULLET_VELOCITY * dy);
    }

    @Override
    public void update() {
        // Give the speed
        x += velX;
        y += velY;
    }

    @Override
    public void render(GraphicsContext g) {

        g.save();
        Rotate r = new Rotate(Math.toDegrees(angle), x , y);
        g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        g.setFill(Color.RED);
        g.fillRect(x - 5, y - 2.5, 10, 5);
        g.strokeRect(x - 5, y - 2.5, 10, 5);
        g.restore();
    }
    
    // MANUAL CALCULATIONS
    /*
    public void update() {
        // Give the speed
        x += velX;
        y += velY;

        // Compute rotation
        pointX[0] = x + ((HEIGHT / 2) * dy) - ((WIDTH / 2) * dx);
        pointY[0] = y - ((WIDTH / 2) * dy) - ((HEIGHT / 2) * dx);

        pointX[1] = x + ((HEIGHT / 2) * dy) + ((WIDTH / 2) * dx);
        pointY[1] = y + ((WIDTH / 2) * dy) - ((HEIGHT / 2) * dx);

        pointX[2] = x - ((HEIGHT / 2) * dy) + ((WIDTH / 2) * dx);
        pointY[2] = y + ((WIDTH / 2) * dy) + ((HEIGHT / 2) * dx);

        pointX[3] = x - ((HEIGHT / 2) * dy) - ((WIDTH / 2) * dx);
        pointY[3] = y - ((WIDTH / 2) * dy) + ((HEIGHT / 2) * dx);
 
    }
    
    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.RED);
        g.fillPolygon(pointX, pointY, 4);
        g.strokePolygon(pointX, pointY, 4);
    }
    */

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
