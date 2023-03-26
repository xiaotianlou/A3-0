package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import featureRenderer.Shape.LagoonGenerator;
import transformation.builtinADT.MeshADT;

public class ShapeRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {

        return new LagoonGenerator().Rendering(m,new Seed());

    }
}
