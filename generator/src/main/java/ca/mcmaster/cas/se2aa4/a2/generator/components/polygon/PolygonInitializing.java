package ca.mcmaster.cas.se2aa4.a2.generator.components.polygon;


import static ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.LinkedList;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.generator.components.polygon.Centroid.centroidInitialize;

public class PolygonInitializing {
    public static List<Polygon> polygonInitialize(List<Vertex> vertices,List<Segment> segments){
        List<Polygon> polygons = new LinkedList<>();
        centroidInitialize(vertices,polygons);
        for(Vertex v:vertices){
            System.out.println(v);
            System.out.println("==========");
        }
        System.out.println("----------------------------------------------");
        for (Polygon p:polygons){
            System.out.println(p.getCentroidIdx());
        }
        return polygons;
        }
}

