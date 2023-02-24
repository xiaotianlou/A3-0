package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;
import java.util.List;

public class PolygonADT {
    private final MeshADT mesh;
    private List<SegmentADT> segments;
    private List<VertexADT> vertices;
    final int id;

    public PolygonADT(MeshADT mesh,List<SegmentADT> segments, List<VertexADT> vertices, int id) {
        this.mesh = mesh;
        this.segments = segments;
        this.vertices = vertices;
        for (SegmentADT s:segments){
            s.polygons.add(this);
        }
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public boolean equals(List<VertexADT> vertices, List<SegmentADT> segments) {
        if(this.vertices.size()!= vertices.size() ||this.segments.size()!=segments.size()){
            return false;
        }
        for (int n = 0; n< vertices.size(); n++){
            if (vertices.get(n) != this.vertices.get(n)){
                return false;
            }
        }
        for (int n=0;n<segments.size();n++){
            if (segments.get(n) !=this.segments.get(n)){
                return false;
            }
        }
        return true;
    }

    VertexADT getCentroid(){
        double x = 0;
        double y = 0;
        for (VertexADT v:vertices){
            x += v.getX();
            y += v.getY();
        }
        return mesh.getVertex(x/segments.size(),y/segments.size());
    }

    public Structs.Polygon toPolygon(){
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        HashSet<Integer> neighbours = new HashSet<>();
        for (SegmentADT s:segments){
            builder.addSegmentIdxs(s.getId());
            for (PolygonADT neighbourPolygon:s.polygons){
                if (neighbourPolygon != this) neighbours.add(neighbourPolygon.getId());
            }
        }
        builder.setCentroidIdx(getCentroid().id);

        builder.addAllNeighborIdxs(neighbours);

        return builder.build();
    }


}
