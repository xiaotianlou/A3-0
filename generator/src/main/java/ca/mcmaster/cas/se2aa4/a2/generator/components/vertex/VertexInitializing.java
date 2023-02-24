package ca.mcmaster.cas.se2aa4.a2.generator.components.vertex;

import java.util.*;

import static ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class VertexInitializing {
    public static List<Vertex> vertexInitialize(int width, int height, int square_size){
        List<Vertex> vertices = new LinkedList<>();
        for(int x = 0; x <= width; x += square_size) {
            for(int y = 0; y <= height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
            }
        }
        List<Vertex> verticesWithColors = new LinkedList<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }
//        for(Vertex v:verticesWithColors){
//            System.out.println(v);
//        }
//        System.out.println("==============================================");
        return verticesWithColors;
    }

}
