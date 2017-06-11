package walking;

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
    final static int WIDTH = 1000;
    final static int HEIGHT = 800;
    
    private double time;
    private GraphicsContext g;
    private Handler handler;
    
    @Override
    public void start(Stage stage) { 
        // Main Scene
        Scene scene = new Scene(createContent());
        
        // Scene KeyPressed en KeyReleased
        scene.setOnKeyPressed(e -> {
            new KeyInput(handler).keyPressed(e);      
        });
        scene.setOnKeyReleased(e -> {
            new KeyInput(handler).keyReleased(e);
        });
        scene.setOnMouseClicked(e ->{
            new KeyInput(handler).mousClick(e,e.getSceneX(),e.getSceneY());
        });
        
        handler = new Handler();
        
        /////////////////////////////////////////////////////////
        //Test object, blauwe box, OBJECTEN OM TE TESTEN HIER ZETTEN
        handler.addObject(new Box(100,100, ID.Player, handler));
        /////////////////////////////////////////////////////////
        
        //Main javaXF functies om het venster te maken
        stage.setTitle("Walking");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    //In houd geven van de scene, valt onder parent.
    private Parent createContent(){
        Pane root = new Pane();
        //Canvas om in te tekenen en te updaten, nodig voor grafics
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        g = canvas.getGraphicsContext2D();
        root.getChildren().addAll(canvas);
        
        //Timer, om fps te limiteren, LATER FPS tonen.
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                time += 0.17;
                if(time >=0.5){
                    update();
                    render();
                    time = 0;
                }
            }
            
        };
        timer.start();
        
        return root;
    }
   
    //Updaten van canvas
    private void update(){
        handler.update();
    }
    
    //Renderen van 
    private void render(){
        g.setFill(Color.GREY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
