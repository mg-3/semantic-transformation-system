package com.data2discovery.sts.services;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by mgarcia on 4/12/16.
 */
@Component
public class RdfLoadingService {

    @Inject
    private RdfLoader rdfLoader;

    @Value("${sts.data.directory}")
    private String dataDirectory;

    @Value("${sts.rdf.file.suffix}")
    private String fileSuffix;

    /**
     * Take in the generated RDF and write to a file in the configured working directory
     *
     * @param istream - the RDF content stream
     * @param fname - the file that the stream will be written to
     * @throws IOException
     */
    public Map<String, String> load(InputStream istream, String fname) throws IOException {
        File dataDir = new File(dataDirectory);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        if (StringUtils.isEmpty(fname)) {
            fname = "no-name-provided";
        }
        File outfile = new File(dataDir, fname + "." + fileSuffix);
        FileWriter fwriter = new FileWriter(outfile, false);
        IOUtils.copy(istream, fwriter);
        IOUtils.closeQuietly(fwriter);
        return rdfLoader.httpLoad(outfile.getName());
    }
}
