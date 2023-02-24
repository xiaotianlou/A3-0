package ca.mcmaster.cas.se2aa4.a2.generator.components.segment;

public class Operators {
    static int[] extractColor_Number(String val) {
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new int[]{red,green,blue};
    }

}
