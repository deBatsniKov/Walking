package walking;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();
    private boolean up = false, down = false, left = false, right = false;

    //Rendering and updating GameObjects
    public void update() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.update();
        }
    }

    public void render(GraphicsContext g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    //Add or Remove GameObjects
    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }

    //All getters en setters
    public GameObject getGameObject(ID id) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if(tempObject.getID() == id){
                return tempObject;
            }
        }
        return null;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return this.up;
    }

    public boolean isDown() {
        return this.down;
    }

    public boolean isLeft() {
        return this.left;
    }

    public boolean isRight() {
        return this.right;
    }
}
