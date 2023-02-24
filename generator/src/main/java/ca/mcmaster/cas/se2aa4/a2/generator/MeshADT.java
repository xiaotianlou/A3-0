package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

import java.util.List;

public class MeshADT {
    final private ArrayList<VertexADT> vertices = new ArrayList<>();
    final private ArrayList<SegmentADT> segments = new ArrayList<>();
    final ArrayList<PolygonADT> polygons = new ArrayList<>();

    public VertexADT getVertex(double x,double y){
        for (VertexADT v:vertices){
            if(v.getX() == x && v.getY()==y){
                return v;
            }
        }
        VertexADT v = new VertexADT(x,y,vertices.size());
        Color.setColor(v);
        vertices.add(v);
        return v;
    }
    public SegmentADT getSegment(VertexADT start,VertexADT end){
        for (SegmentADT s:segments){
            if (
                    ((start == s.getStart() && end ==s.getEnd()) ||
                            (start == s.getEnd() && end == s.getStart()))
            ){
                return s;
            }
        }
        SegmentADT s = new SegmentADT(start,end,null,segments.size());
        Color.setColor(vertices,s);
        segments.add(s);
        return s;
    }

    public PolygonADT getPolygon(MeshADT mesh,List<VertexADT> vertices, List<SegmentADT> segments){
        for (PolygonADT p:polygons){
            if (p.equals(vertices,segments)){
                return p;
            }
        }
        PolygonADT p = new PolygonADT(mesh,segments,vertices,polygons.size());
        polygons.add(p);
        return p;
    }
    private void centroidToVertex(){
        for (PolygonADT p:polygons){
            vertices.add(p.getCentroid());
        }
    }


    public Structs.Mesh toMesh(){
        Structs.Mesh.Builder builder = Structs.Mesh.newBuilder();
        centroidToVertex();
        for (VertexADT v:vertices){
            builder.addVertices(v.toVertex());
        }
        for (SegmentADT s:segments){
            builder.addSegments(s.toSegment());
        }
        for (PolygonADT p:polygons){
            builder.addPolygons(p.toPolygon());
        }
        for (Structs.Polygon p:builder.getPolygonsList()){
            System.out.println(p);
        }
//        for (Structs.Segment s:builder.getSegmentsList()){
//            System.out.println(s);
//        }
        return builder.build();
    }


}
