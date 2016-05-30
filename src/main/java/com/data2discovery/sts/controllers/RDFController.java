package com.data2discovery.sts.controllers;

import com.data2discovery.sts.data.transformers.UomTransformer;
import com.data2discovery.sts.services.RDFService;
import com.data2discovery.sts.services.RdfLoadingService;
import com.data2discovery.sts.services.RefDbQueryService;
import com.data2discovery.sts.transformers.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * Created by mike on 11/9/15.
 */
@Slf4j
@RestController
public class RDFController {

    @Inject
    private ApplicationContext appCtx;

    private Map<String, Class<? extends RefdbTransformer>> transformerMap;

    @Inject
    private RefDbQueryService refDbQueryService;

    @Inject
    private RDFService rdfService;

    @Inject
    private RdfLoadingService rdfLoadingService;

    @PostConstruct
    public void init() {
        Map<String, Class<? extends RefdbTransformer>> tfmrMap = new HashMap();
        tfmrMap.put("uom", UomTransformer.class);
        tfmrMap.put("rt", ResultTypeTransformer.class);
        tfmrMap.put("rl", ResultLevelTransformer.class);
        tfmrMap.put("bm", BioMethodTransformer.class);
        tfmrMap.put("bmv", BioMethodVersionTransformer.class);
        tfmrMap.put("bt", BioTargetTransformer.class);
        tfmrMap.put("g", GeneTransformer.class);
        transformerMap = tfmrMap;
    }

    @Transactional
    @RequestMapping(value = "/rdfload/{refdb_kind}")
    public ResponseEntity<String> uomQuery(
            @PathVariable("refdb_kind") String kind,
            @RequestParam(required = false, value = "bulk") boolean bulkload,
            @RequestParam(required = false, value = "fn") String fileName,
            HttpServletResponse response) throws IOException, ClassNotFoundException {

        response.setHeader("Content-Type", TEXT_PLAIN_VALUE);
        InputStream bistream = null;
        ServletOutputStream httpOutputStream = null;

        if (!transformerMap.containsKey(kind)) {
            return ResponseEntity.badRequest()
                    .body(String.format("STS service does not understand the request with [%s].  It supports: %s",
                            kind,
                            transformerMap.keySet()));
        }

        try {
            Class<? extends RefdbTransformer> transformerClz = transformerMap.get(kind);
            RefdbTransformer bean = appCtx.getBean(transformerClz);
            bistream = refDbQueryService.queryByType(bean);

            if (bulkload) {
                Map<String, String> statusMap = rdfLoadingService.load(bistream, fileName);
                if (statusMap.isEmpty()) {
                    return ResponseEntity.badRequest().body("There was a problem loading RDF file(s)");
                } else {
                    return ResponseEntity.ok("RDF files were loaded to Virtuoso: " + statusMap.toString());
                }

            } else {
                httpOutputStream = response.getOutputStream();
                IOUtils.copy(bistream, httpOutputStream);
                return ResponseEntity.ok("Done");
            }
        } finally {
            IOUtils.closeQuietly(bistream);
            IOUtils.closeQuietly(httpOutputStream);
        }
    }

    @Deprecated
    @RequestMapping(value = "/readrdf", produces = APPLICATION_XML_VALUE)
    public HttpEntity<byte[]> readSomeRDFFromTDB(
            HttpServletRequest req, HttpServletResponse resp
    ) throws IOException {
        ResponseEntity<byte[]> responseEntity;
        byte[] content = rdfService.readRDFFromTDB();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_ENCODING, "gzip");
        responseEntity = new ResponseEntity<>(content, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @Deprecated
    @RequestMapping(value = "/view1")
    public ModelAndView index(Locale locale, org.springframework.ui.Model model) {
        log.info("Locale is {}", locale);
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "STS Gene content");
        mv.addObject("beanInfo", "<p>");
        mv.setViewName("index");
        return mv;
    }

}
