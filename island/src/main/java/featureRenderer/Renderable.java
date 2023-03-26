package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/24 11:18
 */
public interface Renderable {

    MeshADT Rendering(MeshADT m, Seed seed);
}
