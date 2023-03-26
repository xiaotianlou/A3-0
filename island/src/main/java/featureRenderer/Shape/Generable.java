package featureRenderer.Shape;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;

public interface Generable {

    Mesh Rendering(Mesh m, Seed seed);


}
