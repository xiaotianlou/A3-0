import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.LagoonRenderer;
import featureRenderer.Renderer;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Mesh theMesh = specification.build();
//        new MeshFactory().write(mvp.Rendering(), "og.mesh");



        MeshADT meshADT = new MeshADT();
//        String input = "IOArea/inputoff.mesh";
        String input_c="IOArea/inputoffC.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh,meshADT);
        segmentImporter.read(aMesh,meshADT);
        polygonImporter.read(aMesh,meshADT);

        System.out.println(aMesh.getPolygons(1));
        Structs.Mesh output= meshADT.toMesh();


        new MeshFactory().write(output, "IOArea/outputC.mesh");//



        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//


    }
}