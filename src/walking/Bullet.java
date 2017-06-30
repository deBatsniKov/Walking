package walking;

import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends GameObject {

    final static int HEIGHT = 5;
    final static int WIDTH = 10;
    final static int VELOCITY = 3;

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
        this.velX = (VELOCITY * dx);
        this.velY = (VELOCITY * dy);
    }

    @Override
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

        isCollidingBullet();
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.RED);
        g.fillPolygon(pointX, pointY, 4);
        g.strokePolygon(pointX, pointY, 4);
    }

    @Override
    public List getBounds() {
        return Arrays.asList(
                new Point2D(pointX[0], pointY[0]),
                new Point2D(pointX[1], pointY[1]),
                new Point2D(pointX[2], pointY[2]),
                new Point2D(pointX[3], pointY[3])
        );
    }

    public void isCollidingBullet() {
        if (SAT.isColliding(handler.getGameObject(ID.Player), this)) {
            System.out.println("Collision Bullet - Block");
        }
    }

}
