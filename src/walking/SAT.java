package walking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;

public class SAT {
    public static boolean isColliding(GameObject object1, GameObject object2){
        List<Point2D> axes = new ArrayList<>();
             
        axes.addAll(object1.getBounds());
        axes.addAll(object2.getBounds());
        
        axes.stream()
                .map(Point2D::normalize)
                .collect(Collectors.toList());
        
        List<Point2D> object1Corners = object1.getBounds();
        List<Point2D> object2Corners = object2.getBounds();

        for (Point2D axis : axes){
            double object1Min = object1Corners.stream().mapToDouble(p -> p.dotProduct(axis)).min().getAsDouble();
            double object1Max = object1Corners.stream().mapToDouble(p -> p.dotProduct(axis)).min().getAsDouble();
            
            double object2Min = object2Corners.stream().mapToDouble(p -> p.dotProduct(axis)).min().getAsDouble();
            double object2Max = object2Corners.stream().mapToDouble(p -> p.dotProduct(axis)).min().getAsDouble();
            
            if (object1Max  < object2Min || object2Max <  object1Min)
                return false;
        }
        return true;
    }
}
