package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject {

    Handler handler;
    int directionX, directionY;
    final static int VELOCITY = 2;
    double angle, dx, dy;
    final double HEIGHT = 5;
    final double WIDTH = 10;

    public Bullet(int x, int y, ID id, Handler handler, int directionX, int directionY) {
        super(x, y, id);
        this.handler = handler;
        this.directionX = directionX;
        this.directionY = directionY;

        //Calculate the angle of the bullet and the X en Y distance to travel
        this.angle = Math.atan2(directionY - y, directionX - x);
        this.dx = Math.cos(angle);
        this.dy = Math.sin(angle);
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        //Calculation of velocity X and Y   
        velX = (VELOCITY * dx);
        velY = (VELOCITY * dy);
    }

    @Override
    public void render(GraphicsContext g) {
        // Polgyon maken this.x = rcX this.y = rcY (rotationcenter) Width en height en angle hebben wel. Dus om point x,y moeten we een polygon berekenen.
        double[] pointX = new double[4];
        double[] pointY = new double[4];
        pointX[0] = x + ((HEIGHT / 2) * Math.sin(angle)) - ((WIDTH / 2) * Math.cos(angle));
        pointY[0] = y - ((WIDTH / 2) * Math.sin(angle)) - ((HEIGHT / 2) * Math.cos(angle));

        pointX[1] = x + ((HEIGHT / 2) * Math.sin(angle)) + ((WIDTH / 2) * Math.cos(angle));
        pointY[1] = y + ((WIDTH / 2) * Math.sin(angle)) - ((HEIGHT / 2) * Math.cos(angle));

        pointX[2] = x - ((HEIGHT / 2) * Math.sin(angle)) + ((WIDTH / 2) * Math.cos(angle));
        pointY[2] = y + ((WIDTH / 2) * Math.sin(angle)) + ((HEIGHT / 2) * Math.cos(angle));

        pointX[3] = x - ((HEIGHT / 2) * Math.sin(angle)) - ((WIDTH / 2) * Math.cos(angle));
        pointY[3] = y - ((WIDTH / 2) * Math.sin(angle)) + ((HEIGHT / 2) * Math.cos(angle));
        g.setFill(Color.RED);
        g.fillPolygon(pointX, pointY, 4);
        g.strokePolygon(pointX, pointY, 4);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
