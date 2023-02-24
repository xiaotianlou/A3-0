package ca.mcmaster.cas.se2aa4.a2.generator;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class VertexADT {
    private double x;
    private double y;
    private Color color;
    final ArrayList<SegmentADT> segments = new ArrayList<>();
    final int id;

    public VertexADT(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public Structs.Vertex toVertex(){
        Structs.Vertex v= Structs.Vertex.newBuilder().setX(x).setY(y).build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(getColor().getColorCode()).build();
        Structs.Vertex colored = Structs.Vertex.newBuilder(v).addProperties(color).build();
        return colored;
    }


}
