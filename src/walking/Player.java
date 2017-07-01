package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {

    Handler handler;
    public static double PLAYER_VELOCITY = 7.0;

    public Player(double x, double y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void update() {
        collisionBlock();

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
    public Rectangle getBounds() {
        return new Rectangle(x - 0.5 * Walking.SIZE, y - 0.5 * Walking.SIZE, Walking.SIZE, Walking.SIZE);
    }

    private void collisionBlock() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Block) {
                if (!place_free((x + velX), y, getBounds(), tempObject.getBounds())) {
                    velX = 0;
                }
                if (!place_free(x, (y + velY), getBounds(), tempObject.getBounds())) {
                    velY = 0;
                }
            }
        }
    }
        

    public boolean place_free(double x, double y, Rectangle thisRect, Rectangle otherRect) {
        thisRect.setX(x);
        thisRect.setY(y);
        if (thisRect.intersects(otherRect.getBoundsInParent())) {
            return false;
        }
        return true;
    }
}
