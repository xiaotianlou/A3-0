package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

import static ca.mcmaster.cas.se2aa4.a2.generator.components.polygon.PolygonInitializing.polygonInitialize;
import static ca.mcmaster.cas.se2aa4.a2.generator.components.vertex.VertexInitializing.vertexInitialize;
import static ca.mcmaster.cas.se2aa4.a2.generator.components.segment.SegmentInitializing.segmentInitialize;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        MeshADT mesh = new MeshADT();
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                List<VertexADT> verticesPolygon = new LinkedList<>();
                List<SegmentADT> segmentsPolygon = new LinkedList<>();
                VertexADT a =  mesh.getVertex(x,y);
                VertexADT b =  mesh.getVertex(x+square_size,y);
                VertexADT c =  mesh.getVertex(x,y+square_size);
                VertexADT d =  mesh.getVertex(x+square_size,y+square_size);
                verticesPolygon.add(a);
                verticesPolygon.add(b);
                verticesPolygon.add(c);
                verticesPolygon.add(d);
                SegmentADT ab = mesh.getSegment(a,b);
                SegmentADT bd = mesh.getSegment(b,d);
                SegmentADT dc = mesh.getSegment(d,c);
                SegmentADT ca = mesh.getSegment(c,a);
                segmentsPolygon.add(ab);
                segmentsPolygon.add(bd);
                segmentsPolygon.add(dc);
                segmentsPolygon.add(ca);
                mesh.getPolygon(mesh,verticesPolygon,segmentsPolygon);
//                        (Vertex.newBuilder().setX((double) x).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
            }
            public Structs.Mesh generate() {
                Mesh2 mesh = new Mesh2();
                List<Point>vertices = new ArrayList<>();
                // Create all the vertices
                for (int x = 0; x < width; x += square_size) {
                    for (int y = 0; y < height; y += square_size) {
                        Point p1 = new Point(x, y);
                        Point p2 = new Point(x, y + square_size);
                        Point p3 = new Point(x + square_size, y + square_size);
                        Point p4 = new Point(x + square_size, y);
                        vertices.add(p1);
                        vertices.add(p2);
                        vertices.add(p3);
                        vertices.add(p4);
                        Polygon polygon = new Polygon(vertices);
                        mesh.addPolygon(polygon);
                        vertices.clear();
                    }
                }

                Random bag = new Random();
                for (Point p : mesh.getVertices()) {
                    int red = bag.nextInt(255);
                    int green = bag.nextInt(255);
                    int blue = bag.nextInt(255);
                    int alpha = bag.nextInt(255);
                    String colorCode = red + "," + green + "," + blue + "," + alpha;
                    p.setColor(colorCode);
                }

                for (Segment s : mesh.getSegments()) {
                    int red = bag.nextInt(255);
                    int green = bag.nextInt(255);
                    int blue = bag.nextInt(255);
                    int alpha = bag.nextInt(255);
                    String colorCode = red + "," + green + "," + blue + "," + alpha;
                    s.setColor(colorCode);
                }

                for (Polygon polygon:mesh.getPolygons()){
                    mesh.addVertex(polygon.getCentroid());
                    for (Point p: polygon.getVertices()) {
                        mesh.addVertex(p);
                    }
                    for (Segment s : polygon.getSegments()) {
                        mesh.addSegment(s);
                    }
                }

                return mesh.transform();
            }

        }

//        List<Vertex> verticesWithColors = vertexInitialize(width,height,square_size);
//
//        List<Segment> segmentsWithColors = segmentInitialize(width,height,square_size,verticesWithColors);
//        List<Polygon> polygons = polygonInitialize(verticesWithColors,segmentsWithColors);

//        return Mesh.newBuilder().addAllVertices(verticesWithColors).build();

    //    Set<Vertex> vertices = new HashSet<>();
//    // Create all the vertices
//        for(int x = 0; x < width; x += square_size) {
//        for(int y = 0 ; y < height; y += square_size) {
//            vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
//            vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//            vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//            vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
//        }
//    }
//
    // Distribute colors randomly. Vertices are immutable, need to enrich them
//    Set<Vertex> verticesWithColors = new HashSet<>();
//    Random bag = new Random();
//        for(Vertex v: vertices){
//        int red = bag.nextInt(255);
//        int green = bag.nextInt(255);
//        int blue = bag.nextInt(255);
//        String colorCode = red + "," + green + "," + blue;
//        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//        Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
//        verticesWithColors.add(colored);
//    }
//    List<Vertex> v_list = new LinkedList<>(verticesWithColors);
//    HashSet<Segment> segments = new HashSet<>();
//        for(int x = 0; x < width; x += square_size) {
//            for(int y = 0; y < height; y += square_size) {
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x+square_size,y)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x,y+square_size)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x+square_size,y)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y+square_size)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//        }
//    }
//
//
//        return Mesh.newBuilder().addAllVertices(v_list).addAllSegments(segments).build();
//}

        return mesh.toMesh();
    }
}