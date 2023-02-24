package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.util.ArrayList;

public class SegmentADT {
    private VertexADT start;
    private VertexADT end;
    private Color color;

    final ArrayList<PolygonADT> polygons = new ArrayList<>();
//    private Thickness thickness;
    final int id;

    public SegmentADT(VertexADT start, VertexADT end, Color color, int id) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VertexADT getStart() {
        return start;
    }

    public VertexADT getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }


    public Structs.Segment toSegment(){
        Structs.Segment s = Structs.Segment.newBuilder().setV1Idx(start.getId()).setV2Idx(end.getId()).build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(getColor().getColorCode()).build();
        Structs.Segment colored_s = Structs.Segment.newBuilder(s).addProperties(color).build();
        return colored_s;
    }
}

