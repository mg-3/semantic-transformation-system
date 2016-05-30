package com.data2discovery.sts.controllers;

import com.data2discovery.sts.services.SparqlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * The Controller allows for some interaction with a Virtuoso database.  Primarily it is intended
 * to be used to run SPARQL queries via the HTTP API but is also setup to run JDBC
 * SQL queries also.
 *
 * @author mgarcia on 12/17/15.
 */
@RestController
@Slf4j
public class VirtuosoController {

    @Inject
    private Environment env;

    @Named("virtDataSource")
    @Inject
    private DataSource virtuoso;

    @Inject
    private CloseableHttpAsyncClient asyncClient;

    @Inject
    private SparqlService sparqlService;

    @RequestMapping(value = "/virt/sparql", produces = "application/json")
    public ResponseEntity<String> sparqlQueryAsJson(
            @RequestParam String maxrows,
            @RequestParam String timeout,
            @RequestParam String rqfile
    ) throws ExecutionException, InterruptedException, IOException {

        /*
        * http://localhost:8890/sparql?default-graph-uri=query=<ENCODED_QUERY>
        *     &format=application/json&maxrows=100&timeout=0&debug=on
        */
        String sparqlEndpoint = env.getProperty("sts.virtuoso.sparql.endpoint");
        String sparql = URLEncoder.encode( sparqlService.sparqlQuery(rqfile).toString(), Charset.defaultCharset().name());
        String requestUri = "?default-graph-uri=&query=<ENCODED_QUERY>" +
                "&format=application/json&maxrows=<MAXROWS>&<TIMEOUT>=0&debug=on";
        requestUri = requestUri.replace("<ENCODED_QUERY>", sparql);
        requestUri = requestUri.replace("<MAXROWS>", maxrows);
        requestUri = requestUri.replace("<TIMEOUT>", timeout);

        HttpGet get = new HttpGet(sparqlEndpoint + requestUri);
        Future<HttpResponse> future = asyncClient.execute(get, null);
        HttpResponse response = future.get();
        int status = response.getStatusLine().getStatusCode();
        if (status == org.apache.http.HttpStatus.SC_BAD_REQUEST) {
            log.error(IOUtils.toString(response.getEntity().getContent()));
            return ResponseEntity.badRequest().body("{\"response\" : \"Unable to run sparql\"}");
        } else {
            return ResponseEntity.ok(IOUtils.toString(response.getEntity().getContent()));
        }
    }

    /**
     * In the event a SQL query is needed this method serves as a go-by
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/virt/sql")
    public ResponseEntity<List<String>> queryVirtuoso() throws SQLException {
        List<String> names = Lists.newArrayList();
        try (Connection cxn = virtuoso.getConnection();
                Statement stmt = cxn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("select * from information_schema.tables where table_name like '%RDF%'");
            while (resultSet.next()) {
                String tname = resultSet.getString("TABLE_NAME");
                names.add(tname);
            }
        }
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @Deprecated
    @RequestMapping(value = "/virt/bulkload")
    public ResponseEntity<String> bulkload() throws SQLException {

        try (Connection cxn = virtuoso.getConnection();
             CallableStatement loadDir = cxn.prepareCall("{call ld_dir_all(?,?,?)}");
             CallableStatement runProc = cxn.prepareCall("{call rdf_loader_run}")) {

            loadDir.setString(1, System.getProperty("user.home") + "/mdev/rdf/data/");
            loadDir.setString(2, "*.ttl");
            loadDir.setString(3, "graph http://www.data2discovery.com/sts");
            boolean x = loadDir.execute();
            boolean y = runProc.execute();
            log.warn("{} {}", x, y);
        }
        return ResponseEntity.ok("RDF content loaded.");
    }
}
