package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Random;

public class Color {
    private String colorCode;

    public Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public static void setColor(VertexADT v){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        String colorCode = red + "," + green + "," + blue;
        Color c = new Color(colorCode);
        v.setColor(c);
    }

    public static void setColor(ArrayList<VertexADT> vertices, SegmentADT s){
        Color v1_color = s.getStart().getColor();
        Color v2_color = s.getStart().getColor();
        s.setColor(combineColor(v1_color,v2_color));

    }

    private static Color combineColor(Color v1,Color v2){
        String v1c = v1.colorCode;
        String v2c = v2.colorCode;
        int[] tempA = extractColor_Number(v1c);
        int[] tempB = extractColor_Number(v2c);
        int[] tempC = {(tempA[0] + tempB[0]) / 2, (tempA[1] + tempB[1]) / 2, (tempA[2] + tempB[2]) / 2};
        String colorCode = tempC[0] + "," + tempC[1] + "," + tempC[2];
        Color c = new Color(colorCode);
        return c;

    }

    private static int[] extractColor_Number(String val) {
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new int[]{red,green,blue};
    }


}
