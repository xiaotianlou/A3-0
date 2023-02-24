package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Objects;

public class Segment {
    private Point start;
    private Point end;
    private String color;
    private int id;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = 255 + "," + 0 + "," + 0 + "," + 0;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
    public int getId() {
        return id;
    }
    public String getColor() {
        return color;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment edge = (Segment) o;
        return Objects.equals(start, edge.start) &&
                Objects.equals(end, edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
    public String toString() {
        return "(" + start + " - " + end + ")";
    }
}