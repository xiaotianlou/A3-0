package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.Random;

public class WhittakerDiagramsRenderer {


    public MeshADT WhittakerDiagramGenerator(MeshADT mesh, String DiagramType){
        for (PolygonADT p: mesh.getPolygons()){
            for(PolygonADT neighbour: p.getPolygons())
                if (neighbour.isIsland() == false){

                }
        }
        return mesh;
    }
    public PolygonADT BoundryColor(PolygonADT p, String DiagramType,Seed seed){
        Random random = new Random();

        int ran = seed.getSeedArray().get(p.getId()%seed.getSeedArray().size())%3;



        if(ran > 0.75 ){
            p.setColor("238,118,33");
        }else{
            p.setColor("255,215,0");
        }
        return p;
    }
}
