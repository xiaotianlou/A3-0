package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

import java.util.ArrayList;
import java.util.List;

public class polygonImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var p:mesh.getPolygonsList()){
            ArrayList <VertexADT> vertices = new ArrayList<>();
            ArrayList <SegmentADT> segments = new ArrayList<>();
            VertexADT centroid = meshADT.getVertices().get(p.getCentroidIdx());
            for (var s:p.getSegmentIdxsList()){
                SegmentADT segment = meshADT.getSegments().get(s);
                segments.add(segment);
                if (!vertices.contains(segment.getStart())){
                    vertices.add(segment.getStart());
                }
                if (!vertices.contains(segment.getEnd())){
                    vertices.add(segment.getEnd());
                }
            }
            PolygonADT polygonADT = meshADT.getPolygon(vertices,segments,centroid);
            for (int n = p.getPropertiesCount();n>0;n--){

                if (n == 1){
                    polygonADT.setColor(p.getProperties(0).getValue());
                }
            }
        }
        for (int n=0;n<mesh.getPolygonsList().size();n++){
            ArrayList<PolygonADT> polygons = new ArrayList<>();
            PolygonADT polygonADT = meshADT.getPolygons().get(n);
            for (var p:mesh.getPolygons(n).getNeighborIdxsList()){
                polygons.add(meshADT.getPolygons().get(p));
            }
            polygonADT.setPolygons(polygons);
        }
    }
}
