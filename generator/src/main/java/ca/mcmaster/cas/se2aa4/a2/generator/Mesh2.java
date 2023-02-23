package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.math.BigDecimal;
import java.util.*;

public class Mesh2 {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Polygon> polygons;
    private int scale = 2;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public void addVertex(Point point) {
        double x = point.getX();
        double y = point.getY();
        BigDecimal bdX = new BigDecimal(x);
        BigDecimal bdY = new BigDecimal(y);
        x = bdX.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        y = bdY.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        point = new Point(x, y);
        vertices.add(point);
    }

    public void addSegment(Point start, Point end) {
        Segment segment = new Segment(start, end);
        segments.add(segment);
    }

    public void addPolygon(List<Point> vertices) {
        Polygon polygon = new Polygon(vertices);
        polygons.add(polygon);
    }

    public void addCentroid() {
        Polygon polygon = new Polygon(vertices);
        polygons.add(polygon);
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesh2:\n");
        sb.append("Vertices:\n");
        for (Point vertex : vertices) {
            sb.append(vertex + "\n");
        }
        sb.append("Segments:\n");
        for (Segment segment : segments) {
            sb.append(segment + "\n");
        }
        return sb.toString();
    }

    public void removeDuplicates() {
        Set<Point> pointSet = new HashSet<>(vertices);
        Set<Segment> segmentSet = new HashSet<>(segments);
        Set<Polygon> polygonSet = new HashSet<>(polygons);
        vertices.clear();
        segments.clear();
        polygons.clear();
        vertices.addAll(pointSet);
        segments.addAll(segmentSet);
        polygons.addAll(polygonSet);
    }
    public void findNeighbors() {
        for (int i = 0; i < polygons.size(); i++) {
            Polygon polygon = polygons.get(i);
            for (int j = 0; j < polygons.size(); j++) {
                if (i == j) {
                    continue;
                }
                Polygon other = polygons.get(j);
                for (Segment segment : polygon.getSegments()) {
                    if (other.getSegments().contains(segment)) {
                        polygon.addNeighbor(j);
                        other.addNeighbor(i);
                        break;
                    }
                }
            }
        }
    }

    public Structs.Mesh transform() {
        Set<Structs.Vertex> verts = new HashSet<>();
        Set<Structs.Segment> segs = new HashSet<>();
        Set<Structs.Polygon> polys = new HashSet<>();
        for (Point p : this.getVertices()) {
            Structs.Vertex v = Structs.Vertex.newBuilder().setX(p.getX()).setY(p.getY()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(p.getColor()).build();
            Structs.Vertex colored = Structs.Vertex.newBuilder(v).addProperties(color).build();
            verts.add(colored);
        }

        List<Structs.Vertex> v_list = new LinkedList<>(verts);
        for (Segment s : this.getSegments()) {
            Structs.Segment seg = Structs.Segment.newBuilder().setV1Idx(findVertex(v_list, s.getStart().getX(), s.getStart().getY())).setV2Idx(findVertex(v_list, s.getEnd().getX(), s.getEnd().getY())).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(s.getColor()).build();
            Structs.Segment colored = Structs.Segment.newBuilder(seg).addProperties(color).build();
            segs.add(colored);
        }

        List<Structs.Segment> s_list = new LinkedList<>(segs);
        for (Polygon p : this.getPolygons()) {
            for (Segment s : p.getSegments()) {
                Structs.Polygon poly = Structs.Polygon.newBuilder().addSegmentIdxs(findSegment(s_list, findVertex(v_list, s.getStart().getX(), s.getStart().getY()), findVertex(v_list, s.getEnd().getX(), s.getEnd().getY()))).build();
                polys.add(poly);
            }
        }

        return Structs.Mesh.newBuilder().addAllVertices(v_list).addAllSegments(segs).addAllPolygons(polys).build();
    }

    private int findVertex(List<Structs.Vertex> vertexList, double x, double y) {
        int i = 0;
        for (Structs.Vertex v : vertexList) {
            if (v.getX() == x && v.getY() == y) {
                return i;
            }
            i++;
        }
        return -1;
    }
    private int findSegment(List<Structs.Segment>segmentlist, int x, int y) {
        int i =0;
        for (Structs.Segment s : segmentlist){
            if (s.getV1Idx() == x && s.getV2Idx() == y){
                return i;
            }
            i++;
        }
        return -1;
    }
}


