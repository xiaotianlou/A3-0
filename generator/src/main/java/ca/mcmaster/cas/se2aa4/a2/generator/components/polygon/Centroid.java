package ca.mcmaster.cas.se2aa4.a2.generator.components.polygon;



import java.util.LinkedList;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class Centroid {
    public static void centroidInitialize(List<Vertex> vertices, List<Polygon> polygons){
        List<Vertex> vertices_centroid = new LinkedList<>();
        List<Vertex> vertices_centroid_colored = new LinkedList<>();

        for (Vertex v:vertices){
            if (v.getX()<500 && v.getY()<500){
                creat_centroid_list(v,vertices_centroid,polygons);
            }
        }
        color_centroid_list(vertices_centroid,vertices_centroid_colored);
        combine_vertex(vertices,vertices_centroid_colored);
        centroid_index(vertices,polygons);
    }

    private static void creat_centroid_list(Vertex v,List<Vertex> vertices_centroid,List<Polygon> polygons){
        double centroid_x = v.getX()+10;
        double centroid_y = v.getY()+10;
        vertices_centroid.add(Vertex.newBuilder().setX(centroid_x).setY(centroid_y).build());

    }
    private static void color_centroid_list(List<Vertex> vertices_centroid,List<Vertex> vertices_centroid_colored){
        for (Vertex v:vertices_centroid){
            Property color = Property.newBuilder().setKey("rgb_color").setValue(255+","+0+","+0).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            vertices_centroid_colored.add(colored);
        }
    }
    private static void combine_vertex(List<Vertex> vertices, List<Vertex> vertices_centroid_colored){
        for (Vertex v:vertices_centroid_colored){
            vertices.add(v);
        }
    }
    private static void centroid_index(List<Vertex> vertices,List<Polygon> polygons){
        for (int n = 676;n<vertices.size();n++){
        polygons.add(Polygon.newBuilder().setCentroidIdx(n).build());
        }
    }
}
