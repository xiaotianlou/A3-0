package Importer;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.IOException;
import java.util.*;


public class Importer {
    public Mesh trans(String address) {
        MeshFactory fac = new MeshFactory();
        Structs.Mesh Rmesh = null;
        try {
            Rmesh = fac.read(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mesh mesh = new Mesh(1920,1920);

        List<Structs.Vertex> vlist = Rmesh.getVerticesList();
        List<Structs.Segment> slist = Rmesh.getSegmentsList();
        List<Structs.Polygon> plist = Rmesh.getPolygonsList();
        List<PairOfVertex> segments = new ArrayList<>();

        for (Structs.Segment s : slist) {
            Vertex v1 = registerV(vlist.get(s.getV1Idx()));
            Vertex v2 = registerV(vlist.get(s.getV2Idx()));
            PairOfVertex pov = new PairOfVertex(v1, v2);
            segments.add(pov);
        }


        for (Structs.Polygon p : plist) {
            Polygon polygon = new Polygon();

            int centroidIdx = p.getCentroidIdx();
            Vertex centroid = registerV(vlist.get(centroidIdx));
            polygon.add(centroid);

            for (int idx : p.getSegmentIdxsList()) {
                PairOfVertex pov = segments.get(idx);
                polygon.add(pov.contents()[0]);
                polygon.add(pov.contents()[1]);
            }
            for (int idx : p.getNeighborIdxsList()) {
                polygon.registerAsNeighbour(mesh.getPolygons().toArray(new Polygon[0])[idx]);
            }
            mesh.register(polygon);
        }

        return mesh;

    }
//
//
//    public void registerPolygons(Structs.Mesh mesh) {
//
//    }
//
//
//    public void registerSegments(Structs.Mesh mesh) {
//
//    }
//
//
    public Vertex registerV(Structs.Vertex v){
        float x = (float) v.getX();
        float y = (float) v.getY();
        Vertex vertex = new Vertex(x, y);
        return vertex;
    }
//    public Vertex registerS(Structs.Segment s){
//        int v1Idx = s.getV1Idx();
//        int v2Idx = s.getV2Idx();
//
//        PairOfVertex segment = new PairOfVertex(v1, v2);
//    }
}
