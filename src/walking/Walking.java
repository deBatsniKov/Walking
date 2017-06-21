package walking;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Walking extends Application {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 500;
    public final static int SIZE = 32;
    

    private BufferedImage world = null;
    private double time;
    private GraphicsContext g;
    private Handler handler;
    private Camera camera;

    @Override
    public void start(Stage stage) {

        //Timer, om fps te limiteren, LATER FPS tonen. Staat automatisch op 60fps
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }

        };
        timer.start();

        handler = new Handler();
        camera = new Camera(0, 0);

        // Main Scene
        Scene scene = new Scene(createContent());
        // Scene KeyPressed en KeyReleased
        scene.setOnKeyPressed(e -> {
            new KeyInput(handler).keyPressed(e);
        });
        scene.setOnKeyReleased(e -> {
            new KeyInput(handler).keyReleased(e);
        });
        scene.setOnMousePressed(e -> {
            new KeyInput(handler).mousClick(e, e.getSceneX(), e.getSceneY());
        });

        // Level lader met try/catch
        ImageLoader loader = new ImageLoader();
        try {
            world = loader.ImageLoader("/Data/World.png");
        } catch (IOException ex) {
            Logger.getLogger(Walking.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadLevel(world);

        /////////////////////////////////////////////////////////
        //Test object, blauwe box, OBJECTEN OM TE TESTEN HIER ZETTEN
        // Player intieert uit map!
        /////////////////////////////////////////////////////////
        //Main javaXF functies om het venster te maken
        stage.setTitle("Walking");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    //In houd geven van de scene, valt onder parent.
    private Parent createContent() {
        Pane root = new Pane();

        //Canvas om in te tekenen en te updaten, nodig voor grafics
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        g = canvas.getGraphicsContext2D();

        root.getChildren().addAll(canvas);
        return root;
    }

    //Updaten van canvas
    private void update() {

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) {
                camera.update(handler.object.get(i));
            }
        }
        handler.update();
    }

    //Renderen van
    private void render() {
        //Basic bacground rendering
        g.setFill(Color.GREY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //Translation for camera
        g.translate(-camera.getX(), -camera.getY());
        
        //Render all objects
        handler.render(g);
        
        //Translate back
        g.translate(camera.getX(), camera.getY());

    }

    private void loadLevel(BufferedImage image) {
        System.out.println("Loading Level");
        int w = image.getWidth();
        int h = image.getHeight();
        int blockCount = 0, mapCount = 0;
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                mapCount++;

                if (red == 255) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
                    blockCount++;
                    System.out.println("Block: " + blockCount + " added!");
                }
                if (blue == 255) {
                    handler.addObject(new Player(xx * 32, yy * 32, ID.Player, handler));
                    System.out.println("Player Added");
                }
            }
        }
        System.out.println("Mapcount:" + mapCount);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
