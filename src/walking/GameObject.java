package walking;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class GameObject {

    protected double x, y;
    protected double velX = 0, velY = 0;
    protected ID id;

    public GameObject(double x, double y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void update();
    public abstract void render(GraphicsContext g);
    public abstract Rectangle getBounds();

    public ID getID(){
        return id;
    }
    
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
