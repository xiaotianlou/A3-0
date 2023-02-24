package ca.mcmaster.cas.se2aa4.a2.generator.components.segment;

import java.util.LinkedList;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.generator.components.segment.Operators.extractColor_Number;
import static ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class SegmentInitializing {
    public static List<Segment> segmentInitialize(int width, int height, int square_size, List<Vertex> verticesWithColors) {

        LinkedList<Segment> segments = new LinkedList<>();
        for (int x = 0; x <= width; x += square_size) {
            for (int y = 0;  y< height; y += square_size) {
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors, x, y)).
                        setV2Idx(findVertex(verticesWithColors, x, y + square_size)).build());
            }
        }
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors, x, y)).
                        setV2Idx(findVertex(verticesWithColors, x + square_size, y)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x,y)).
//                        setV2Idx(findVertex(verticesWithColors,x,y+square_size)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x+square_size,y)).
//                        setV2Idx(findVertex(verticesWithColors,x+square_size,y+square_size)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x,y+square_size)).
//                        setV2Idx(findVertex(verticesWithColors,x+square_size,y+square_size)).build());
            }
        }


        List<Segment> segmentsWithColors = new LinkedList<>();
        for (Segment s : segments) {
            int[] tempA = extractColor_Number(verticesWithColors.
                    get(s.getV1Idx()).getProperties(0).getValue());
            int[] tempB = extractColor_Number(verticesWithColors
                    .get(s.getV2Idx()).getProperties(0).getValue());
            int[] tempC = {(tempA[0] + tempB[0]) / 2, (tempA[1] + tempB[1]) / 2, (tempA[2] + tempB[2]) / 2};
            String colorCode = tempC[0] + "," + tempC[1] + "," + tempC[2];
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Segment colored = Segment.newBuilder(s).addProperties(color).build();
            segmentsWithColors.add(colored);
        }
//        for (Segment l : segments) {
//            System.out.println(l);
//        }

        return segmentsWithColors;
    }

    private static int findVertex(List<Vertex> vertexList, double x, double y) {
        int i = 0;
        for (Vertex v : vertexList) {
            if (v.getX() == x && v.getY() == y) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
