package com.data2discovery.sts.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.ext.com.google.common.collect.Lists;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by mike on 11/9/15.
 */
@Slf4j
@Service
public class RDFService {

    private static final String TDB_DIRECTORY = "./data/tdb";

    public Model onTheFlyRDF(List<Map<String, Object>> content, boolean closeAfter) {
        String uri = "http://com.data2discovery/refdb2rdf";
        String skos = "http://www.w3.org/2004/02/skos/core#";
        String gene = "http://com.data2discovery/gene";
        String tissue = "http://com.data2discovery/tissue";
        String geneSymbol = "http://com.data2discovery/gene/symbol";
        String geneName = "http://com.data2discovery/gene/name";

        Model model = ModelFactory.createDefaultModel();
        Property geneProp = model.createProperty(gene, "");
        Property tissueProp = model.createProperty(tissue, "");
        Property geneSymbolProp = model.createProperty(geneSymbol, ":symbol");
        Property geneNameProp = model.createProperty(geneName, ":name");

        Resource geneResource = model.createResource(gene);
        Resource tissueResource = model.createResource(tissue);
        String c_gn = "";
        String c_gs = "";

        List<Statement> geneStatements = Lists.newLinkedList();
        List<Statement> tissueStatements = Lists.newLinkedList();

        for (Map<String, Object> m : content) {
            String gn = m.get("subject").toString();
            String gs = m.get("predicate").toString();

            if (!(gn.equals(c_gn) && gs.equals(c_gs))) {
                Statement st = model.createStatement(geneResource,
                        model.createProperty(gene, "/" + "GENE_ID"), gn);
                st.getPredicate().addProperty(geneSymbolProp, gs).addProperty(geneNameProp, gn);
                st.getPredicate().addProperty(tissueProp,
                        "TISSUE_ID")
                        .addProperty(tissueProp, m.get("object").toString());

                geneStatements.add(st);
//                Resource resource = model.createResource(tissue);
//                resource.addProperty(tissueProp,
//                        model.createResource(gene)
//                                .addProperty(geneNameProp, gn)
//                                .addProperty(geneSymbolProp, gs));
            }
            model.getResource(gene).addProperty(tissueProp, m.get("object").toString());

//            Statement st2 = model.createStatement(tissueResource, model.createProperty(tissue,
//                    "/"+m.get("TISSUE_ID").toString()),
//                    m.get("TISSUE_NAME").toString());
//            tissueStatements.add(st2);

//            model.getResource(tissue)
//                    .addProperty(tissueProp, m.get("TISSUE_NAME").toString());

            c_gn = gn;
            c_gs = gs;
        }

        model.add(geneStatements);
//        model.add(tissueStatements);

        if (closeAfter) {
            model.close();
        }
        return model;
    }

    public Model createRDF(List<Map<String, Object>> content, boolean closeAfter) {
        /*
        String uri = "http://data2discovery/refdb2rdf";
        String gene = "http://data2discovery/gene";
        String tissue = "http://data2discovery/tissue";
        String geneSymbol = "http://data2discovery/gene/symbol";
        String geneName = "http://data2discovery/gene/name";

        new File(TDB_DIRECTORY).mkdirs();
        Dataset dataset = TDBFactory.createDataset(TDB_DIRECTORY);
        dataset.begin(ReadWrite.WRITE);
        Model model = dataset.getDefaultModel();
        Property geneProp = model.createProperty(gene, "g");
        Property tissueProp = model.createProperty(tissue, "t");
        Property geneSymbolProp = model.createProperty(tissue, ":symbol");
        Property geneNameProp = model.createProperty(tissue, ":name");

        content.forEach(m -> {
            Resource resource = model.createResource(uri);
            resource.addProperty(geneProp,
                    model.createResource()
                            .addProperty(geneNameProp, m.get("GENE_NAME").toString())
                            .addProperty(geneSymbolProp, m.get("GENE_SYMBOL").toString()))
                    .addProperty(tissueProp, m.get("TISSUE_NAME").toString());
        });
        dataset.commit();
        dataset.end();
        // release resources
        dataset.close();
        if (closeAfter) {
            model.close();
        }
        return model;
        */
        return null;
    }

    public byte[] readRDFFromTDB() throws IOException {
        /*
        ByteArrayOutputStream out = new ByteArrayOutputStream(512 * 1024);
        BufferedOutputStream ostream = new BufferedOutputStream(out);
        GZIPOutputStream gzip = new GZIPOutputStream(ostream);
        Dataset dataset = TDBFactory.createDataset(TDB_DIRECTORY);
        dataset.begin(ReadWrite.READ);
        Model model = dataset.getDefaultModel();
        dataset.end();
        model.write(gzip);
        gzip.close();
        log.debug("Finsihed reading TDB data");
        return out.toByteArray();
        */
        return null;
    }
}
