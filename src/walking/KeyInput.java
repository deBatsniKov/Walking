package walking;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class KeyInput {

    Handler handler;

    KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.W) {
            handler.setUp(true);
        }
        if (e.getCode() == KeyCode.S) {
            handler.setDown(true);
        }
        if (e.getCode() == KeyCode.A) {
            handler.setLeft(true);
        }
        if (e.getCode() == KeyCode.D) {
            handler.setRight(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.W) {
            handler.setUp(false);
        }
        if (e.getCode() == KeyCode.S) {
            handler.setDown(false);
        }
        if (e.getCode() == KeyCode.A) {
            handler.setLeft(false);
        }
        if (e.getCode() == KeyCode.D) {
            handler.setRight(false);
        }
    }

    void mousClick(MouseEvent e, double shootDirectionX, double shootDirectionY) {
        // NIEUW bullet object aanmaken van locatie PLAYER
        handler.addObject(new Bullet(handler.getGameObject(ID.Player).getX(), handler.getGameObject(ID.Player).getY(), ID.Bullet, handler, (int)e.getSceneX(), (int)e.getSceneY()));
    }
}
