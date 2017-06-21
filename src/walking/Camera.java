package walking;

public class Camera {

    public float x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(GameObject object) {       
        // DEZE MAP SIZE x = 66, y = 40
        
        x += ((object.getX() - x) - Walking.WIDTH / 2) * 0.05f;
        y +=((object.getY() - y) - Walking.HEIGHT / 2) * 0.05f; 
        
        if (x > (66*Walking.SIZE - Walking.WIDTH - (0.5 * 32))) {
            x = (66*Walking.SIZE - Walking.WIDTH - (32 / 2));
        }
        if (x < (0 + 0.5 * Walking.SIZE)) {
            x = 0 + (Walking.SIZE / 2);
        }
        if (y < (0 + (0.5 * Walking.SIZE))) {
            y = 0 + (Walking.SIZE / 2);
        }
        if (y > (40 * Walking.SIZE - Walking.HEIGHT - (0.5 * Walking.SIZE))) {
            y = (40 * Walking.SIZE - Walking.HEIGHT - (Walking.SIZE / 2));
        }
        
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void getY(int y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }
}
