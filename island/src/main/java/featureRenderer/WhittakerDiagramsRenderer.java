package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.Random;

public class WhittakerDiagramsRenderer implements Renderable{
    public PolygonADT BoundryColor(PolygonADT p,Seed seed){
        Random random = new Random();
        int ran = seed.getSeedArray().get(p.getId()%seed.getSeedArray().size())%4;
        if(ran > 0.75 ){
            p.setColor("238,118,33");
        }else{
            p.setColor("255,215,0");
        }
        return p;
    }
    public PolygonADT InnerColor(PolygonADT p,Seed seed, String biome){
        int ran = seed.getSeedArray().get(p.getId()%seed.getSeedArray().size())%5;
        if(ran < 0.75 ){
            p.setColor("46,139,87");}
        else{
            if (biome == "Tropical_Rain_Forest"){
            p.setColor("0,255,0");
            }else if(biome == "Tropical_Seasonal_Forest"){
            p.setColor("154,205,50");
            }else if(biome == "Temperate_Grassland_and_Desert") {
            p.setColor("255,215,0");
            }else if(biome == "Temperate_Rain_Forest") {
            p.setColor("0,250,154");
            }else if(biome == "Taiga") {
            p.setColor("0,100,0");
            }else if(biome == "Tundra") {
            p.setColor("0,206,209");
            }else  {
            p.setColor("238,118,33");
            }
        }
        return p;
    }


    public MeshADT Rendering(MeshADT m, Seed seed) {
        String biome = seed.getBiome();
        if (biome==""){
            return null;
        }
        for (PolygonADT p: m.getPolygons()){
            //all island color
            if(p.isIsland()){
                InnerColor(p,seed,biome);
            }
            //bound colour
            for(PolygonADT neighbour: p.getPolygons()){
                if(p.isIsland()){
                    //1 from sea
                    if (!neighbour.isIsland()){
                        BoundryColor(p,seed);
                        //2 from sea
                        for(PolygonADT nei: p.getPolygons()){
                            //2 from sea island
                            if (nei.isIsland() ){
                                BoundryColor(nei,seed);
                            }
                        }
                    }
                }
            }
        }

        return m;
    }
}
