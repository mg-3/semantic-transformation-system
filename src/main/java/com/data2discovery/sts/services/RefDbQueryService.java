package com.data2discovery.sts.services;

import com.data2discovery.sts.jaxb.*;
import com.data2discovery.sts.transformers.RefdbTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mike on 11/20/15.
 */
@Slf4j
@Service
public class RefDbQueryService {

    @Inject
    private ApplicationContext appCtx;

    @Inject
    private EntityManager entityManager;

    private RDFMetaDataDefinitions rdfMetaDataDefinitions;
    private ST refdbToQueryTemplate;


    /**
     * Using the refdbKind determine what query to run and collection of results to yield
     * for further processing.
     *
     * @param resultClass the kind of class that will contain the query result
     * @param transformer the {@link RefdbTransformer} that takes the query results and generates an RDF stream
     * @return an InputStream containing the RDF content
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public <T, S extends RefdbTransformer<T>> InputStream queryByType(S transformer) throws IOException, ClassNotFoundException {
        TypedQuery<T> query0 = entityManager.createQuery(transformer.queryString(), transformer.resultClass());
        List<T> objectRows = query0.getResultList();
        log.debug("Query yielded {} results", objectRows.size());
        objectRows.stream().forEach(o -> log.trace(o.toString()));
        return transformer.transform(objectRows);
    }

    //Test setters
    public void setRdfMetaDataDefinitions(RDFMetaDataDefinitions rdfMetaDataDefinitions) {
        this.rdfMetaDataDefinitions = rdfMetaDataDefinitions;
    }

    public void setRefdbToQueryTemplate(ST template) {
        this.refdbToQueryTemplate = template;
    }

    public void setAppCtx(ApplicationContext ac) {
        this.appCtx = ac;
    }

    @Deprecated
    public String refdbConfigToQuery(String name, int maxResults) {
        refdbToQueryTemplate = this.appCtx.getBean("refdbToQueryTemplate", ST.class);
        rdfMetaDataDefinitions = this.appCtx.getBean("rdfMetaDataDefinitions", RDFMetaDataDefinitions.class);
        Category category = rdfMetaDataDefinitions.getRdbms().getCategories().getCategory()
                .stream()
                .filter(c -> c.getValue().equals(name))
                .collect(Collectors.toList()).get(0);
        Query queryInfo = category.getQuery();
        String desc = queryInfo.getDesc();
        List<Table> tables = queryInfo.getJoinDetails().getTables().getTable();
        tables.stream()
                .forEach(t -> {
                    String alias = t.getAlias();
                    List<SelectColumn> sc = t.getSelectColumn();
                    sc.forEach(c -> {
                        if (c.isAliasCol()) {
                            c.setName(alias + "." + c.getName());
                        }
                    });
                });
        Map<BigInteger, Table> tabMap = tables.stream().collect(Collectors.toMap(Table::getId, table -> table));
        List<String> joins = new ArrayList<>();
        tabMap.forEach((key, t) -> {
            if(t.getSelectColumn().size() > 0) {
                int id = t.getId().intValue();
                int jtOnIds = t.getJoinColumns().getJoinColumn()
                        .stream()
                        .map(jc -> jc.getJtOn().intValue())
                        .collect(Collectors.toList()).get(0);
                int lookup = (id * 10) + jtOnIds;
                int assocLookup = associatedJoinColumn(lookup);
                int assocTabId = assocLookup / 10;
                int assocColId = assocLookup % 10;
                Table assocTable = tabMap.get(BigInteger.valueOf(assocTabId));
                JoinColumn joinCol = assocTable.getJoinColumns().getJoinColumn()
                        .stream()
                        .filter(jc -> jc.getJtOn().intValue() == assocColId)
                        .collect(Collectors.toList()).get(0);
                joins.add("JOIN " + t.getName() + " " + t.getAlias() + " ON " + t.getAlias()
                        +"."+t.getJoinColumns().getJoinColumn().get(0).getName()
                        + "=" +assocTable.getAlias()+"."+joinCol.getName());

            } else {
                joins.add(t.getName() + " " + t.getAlias());
            }
        });

        Collections.reverse(joins);
        List<SelectColumn> selectCols = tables
                .stream()
                .flatMap(t -> t.getSelectColumn().stream())
                .collect(Collectors.toList());
        refdbToQueryTemplate.add("joins", joins);
        refdbToQueryTemplate.add("desc", desc);
        refdbToQueryTemplate.add("sc", selectCols);
        refdbToQueryTemplate.add("w", queryInfo.getWhereClause());
        if (maxResults > 0) {
            refdbToQueryTemplate.add("limit", maxResults);
        }
        String query = refdbToQueryTemplate.render();
        log.debug(query);
        Lists.newArrayList("joins", "sc", "w","desc","limit").forEach(s -> refdbToQueryTemplate.remove(s));
        return query;

    }

    private int associatedJoinColumn(int n) {
        return (n % 10) * 10 + (n / 10);
    }

}
