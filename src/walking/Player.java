package walking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

public class Player extends GameObject {

    Handler handler;
    public static double PLAYER_VELOCITY = 10.0;

    public Player(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        if (handler.isUp()) {
            velY = -PLAYER_VELOCITY;
        } else if (!handler.isDown()) {
            velY = 0.0;
        }

        if (handler.isDown()) {
            velY = PLAYER_VELOCITY;
        } else if (!handler.isUp()) {
            velY = 0.0;
        }

        if (handler.isRight()) {
            velX = PLAYER_VELOCITY;
        } else if (!handler.isLeft()) {
            velX = 0.0;
        }

        if (handler.isLeft()) {
            velX = -PLAYER_VELOCITY;
        } else if (!handler.isRight()) {
            velX = 0.0;
        }
    }

    @Override
    public void render(GraphicsContext g) {
        g.setFill(Color.BLUE);
        g.fillRect(x - 0.5 * Walking.SIZE, y - 0.5 * Walking.SIZE, Walking.SIZE, Walking.SIZE);
    }

    @Override
    public List getBounds() {
        return Arrays.asList(
                new Point2D(x - 0.5 * Walking.SIZE, y - 0.5 * Walking.SIZE),
                new Point2D(x + 0.5 * Walking.SIZE, y - 0.5 * Walking.SIZE),
                new Point2D(x + 0.5 * Walking.SIZE, y + 0.5 * Walking.SIZE),
                new Point2D(x - 0.5 * Walking.SIZE, y + 0.5 * Walking.SIZE)
        );
    }

    public void isCollidingBlock() {
        if (SAT.isColliding(handler.getGameObject(ID.Block), this)) {
            System.out.println("Collision Player - Block");
        }
    }
}
