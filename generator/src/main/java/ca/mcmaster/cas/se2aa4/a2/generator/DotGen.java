package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        Set<Vertex> vertices = new HashSet<>();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
            }
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        Set<Vertex> verticesWithColors = new HashSet<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }

        List<Vertex> v_list = new ArrayList<>(verticesWithColors);
        HashSet<Segment> segments = new HashSet<>();
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                //color

                Property line_color =cal_color(v_list.get(findVertex(v_list,x,y)),v_list.get(findVertex(v_list,x+square_size,y)));
                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x+square_size,y)).addProperties(line_color).build());

                line_color =cal_color(v_list.get(findVertex(v_list,x,y)),v_list.get(findVertex(v_list,x,y+square_size)));
                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x,y+square_size)).addProperties(line_color).build());

                line_color =cal_color(v_list.get(findVertex(v_list,x+square_size,y)),v_list.get(findVertex(v_list,x+square_size,y+square_size)));
                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x+square_size,y)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).addProperties(line_color).build());

                line_color=cal_color(v_list.get(findVertex(v_list,x,y+square_size)),v_list.get(findVertex(v_list,x+square_size,y+square_size)));
                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y+square_size)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).addProperties(line_color).build());

            }
        }


        return Mesh
                .newBuilder()
                .addAllVertices(v_list)
                .addAllSegments(segments)
                .build();
    }

    private Property cal_color(Vertex a,Vertex b){

        String val1 = null;
        String val2 = null;
        for (Property p : a.getPropertiesList()) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                System.out.println("测试1");
                val1 = p.getValue();
            }
        }
        for (Property p : b.getPropertiesList()) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                System.out.println("测试2");
                val2 = p.getValue();
            }
        }
        String[] raw = val1.split(",");
        String[] raw1 = val2.split(",");
        int red = (Integer.parseInt(raw[0])+Integer.parseInt(raw1[0]))/2;
        int green =(Integer.parseInt(raw[1])+Integer.parseInt(raw1[1]))/2;
        int blue = (Integer.parseInt(raw[2])+Integer.parseInt(raw1[2]))/2;


        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return  color;
    }

    private int findVertex(List<Vertex> vertexList,double x,double y){
        int i=0;
        for (Vertex v:vertexList){
            if(v.getX()==x&&v.getY()==y){
                return i;
            }
            i++;
        }
        return -1;
    }



}
