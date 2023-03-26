package featureRenderer;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.export.Exporter;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.BackGroundEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.CircleIslandEnricher;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 16:03
 */
public class LagoonRenderer  { //独立的MVP类 和其他类无关

    Mesh m;
    public LagoonRenderer(Mesh m) {
        this.m=m;
    }

    public Structs.Mesh Rendering() {
        //get centreid of centre poly relax
        int cen_x = 1920 / 2;
        int cen_y = 1920 / 2;
        int circle_size = 800;
        List<Vertex> centre_bag = new ArrayList<Vertex>();
        List<Vertex> centre_bag2 = new ArrayList<Vertex>();
        List<Vertex> centre_bag3 = new ArrayList<Vertex>();
        List<Vertex> centre_bag4 = new ArrayList<Vertex>();
        double space = Math.sqrt(1920 * 1920 / 1000);


        for (Polygon p : m.getPolygons()) {
            float x = p.centroid().x();
            float y = p.centroid().y();
            //先找
            double distance = Math.sqrt(Math.pow(x - cen_x, 2) + Math.pow(y - cen_y, 2));
            if (distance < circle_size) {
                centre_bag.add(p.centroid());
                if (distance < circle_size - space) {
                    centre_bag2.add(p.centroid());
                    if (distance < circle_size / 2) {
                        centre_bag3.add(p.centroid());
                        if (distance < circle_size / 2 - space) {
                            centre_bag4.add(p.centroid());
                        }
                    }
                }
            }
        }

        Structs.Mesh exported = new Exporter().run(m);
        exported = new BackGroundEnricher(new Color(13, 108, 185)).process(exported);
        exported = new CircleIslandEnricher(new Color(153, 220, 211), centre_bag).process(exported);
        exported = new CircleIslandEnricher(new Color(255, 255, 255), centre_bag2).process(exported);
        exported = new CircleIslandEnricher(new Color(153, 220, 211), centre_bag3).process(exported);
        exported = new CircleIslandEnricher(new Color(146, 198, 250), centre_bag4).process(exported);

        return exported;
    }
}
