package transformation.builtinADT;

import TerrainFeatures.Aquifer;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class PolygonADT {
    final int id;
    //    private final MeshADT mesh;
    private int[] color = new int[]{255, 255, 255};
    private List<PolygonADT> polygons;
    private List<SegmentADT> segments;
    private List<VertexADT> vertices;
    private boolean isIsland = false;

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    private Biome biome=Biome.None;




    private VertexADT centroid;
    private int elevation = 0;
    private Aquifer waterContent;
    private int temperature = 25;

    public PolygonADT(List<SegmentADT> segments, List<VertexADT> vertices, VertexADT centroid, int id) {
        this.segments = segments;
        this.vertices = vertices;
        this.id = id;
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public boolean equals(List<VertexADT> vertices, List<SegmentADT> segments) {
        if (this.vertices.size() != vertices.size() || this.segments.size() != segments.size()) {
            return false;
        }
        for (int n = 0; n < vertices.size(); n++) {
            if (vertices.get(n) != this.vertices.get(n)) {
                return false;
            }
        }
        for (int n = 0; n < segments.size(); n++) {
            if (segments.get(n) != this.segments.get(n)) {
                return false;
            }
        }
        return true;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public void setColor(String c) {
        int n = 0;
        for (String s : c.split(",")) {
            color[n] = Integer.parseInt(s);
            n++;
        }
    }

    public String getColorCode() {
        return color[0] + "," + color[1] + "," + color[2];
    }

    public List<VertexADT> getVertices() {
        return vertices;
    }

    public void setVertices(List<VertexADT> vertices) {
        this.vertices = vertices;
    }

    public VertexADT getCentroid() {
        return centroid;
    }

    public void setCentroid(VertexADT centroid) {
        this.centroid = centroid;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public boolean isIsland() {
        return isIsland;
    }

    public void setIsland(boolean island) {
        isIsland = island;
    }

    public Aquifer getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(Aquifer waterContent) {
        this.waterContent = waterContent;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public List<PolygonADT> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<PolygonADT> polygons) {
        this.polygons = polygons;
    }

    public List<SegmentADT> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentADT> segments) {
        this.segments = segments;
    }

    public Structs.Polygon toPolygon() {
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        List<Integer> neighbours = new ArrayList<>();
        for (SegmentADT s : segments) {
            builder.addSegmentIdxs(s.getId());
        }
        for (PolygonADT neighbourPolygon : polygons) {
            builder.addNeighborIdxs(neighbourPolygon.getId());
        }
        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder();

        builder.addProperties(propertyBuilder.setKey("rgb_color").setValue(getColorCode()));


        builder.addProperties(propertyBuilder.setKey("elevation").setValue(String.valueOf(elevation)));

        builder.addProperties(propertyBuilder.setKey("temperature").setValue("0"));

        builder.addProperties(propertyBuilder.setKey("waterContent").setValue("0"));

        builder.setCentroidIdx(centroid.id);

        builder.addAllNeighborIdxs(neighbours);

        return builder.build();
    }

}
