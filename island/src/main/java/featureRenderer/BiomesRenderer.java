package featureRenderer;


import transformation.builtinADT.Biome;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

public class BiomesRenderer {
    public MeshADT determineBiome(MeshADT mesh) {
        for (PolygonADT p: mesh.getPolygons()){
            int temperature = p.getTemperature();
            int humidity = p.getElevation();
        if (20 <= temperature ) {
            if( humidity >= 250){
                p.setBiome(Biome.Tropical_Rain_Forest);
            }else if(humidity >= 50){
                p.setBiome(Biome.Tropical_Seasonal_Forest);
            }
        } else if (temperature >= 10 ) {
            if( humidity >= 225){
                p.setBiome(Biome.Temperate_Rain_Forest);
            }else if(humidity >= 125){
                p.setBiome(Biome.Temperate_Deciduous_Forest);
            }else if(humidity >=25){
                p.setBiome(Biome.Temperate_Grassland_and_Desert);
            }
        } else if (temperature >= 3) {
            if( humidity >= 200){
                p.setBiome(Biome.Temperate_Rain_Forest);
            }else if(humidity >= 75){
                p.setBiome(Biome.Temperate_Deciduous_Forest);
            }else if(humidity >=15){
                p.setBiome(Biome.Temperate_Grassland_and_Desert);
            }
        } else if (temperature >= -5) {
            if( humidity >= 30){
                p.setBiome(Biome.Taiga);
            }else if(humidity >= 10){
                p.setBiome(Biome.Temperate_Grassland_and_Desert);
            }
        } else if (temperature < -5) {
            p.setBiome(Biome.Tundra);
        } else  {
            p.setBiome(Biome.Subtropical_Desert);
        }
    }
        return mesh;

    }
}
