package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/24 11:18
 */
public interface Renderable {

    Mesh Rendering(Mesh m, Seed seed);
}
