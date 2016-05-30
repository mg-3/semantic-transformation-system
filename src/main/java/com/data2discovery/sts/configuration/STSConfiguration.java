package com.data2discovery.sts.configuration;

import com.data2discovery.sts.jaxb.RDFMetaDataDefinitions;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;
import virtuoso.jdbc4.VirtuosoDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by mike on 11/11/15.
 */
@PropertySource("file:config/application.properties")
@Configuration
@Slf4j
public class STSConfiguration {

    @Inject
    private Environment environment;

    @Bean
    public CloseableHttpAsyncClient asyncHttpClient() {
        BasicCredentialsProvider creds = new BasicCredentialsProvider();
        creds.setCredentials(new AuthScope(
                        environment.getProperty("virtuoso.server"),
                        Integer.parseInt( environment.getProperty("virtuoso.http.port"))),
                new UsernamePasswordCredentials(
                        environment.getProperty("virtuoso.user"),
                        environment.getProperty("virtuoso.pwd")
                ));

        CloseableHttpAsyncClient asyncClient = HttpAsyncClients.custom()
                .setDefaultCredentialsProvider(creds)
                .setDefaultHeaders(Arrays.asList(
                        new BasicHeader(HttpHeaders.ACCEPT, "*/*"),
                        new BasicHeader(HttpHeaders.EXPECT, "100-continue")
                )).build();
        asyncClient.start();
        return asyncClient;
    }

    @Bean
    public Properties dbProperties() throws IOException {
        log.debug("{}", environment.getProperty("refdb.user"));
        Properties dbProps = new Properties();
        dbProps.setProperty("user", environment.getProperty("refdb.user"));
        dbProps.setProperty("server", environment.getProperty("refdb.server"));
        dbProps.setProperty("dbname", environment.getProperty("refdb.database.name"));
        dbProps.setProperty("port", environment.getProperty("refdb.port"));
        String dontShowPwd = dbProps.getProperty("pwd", environment.getProperty("refdb.pwd"));
        dbProps.setProperty("pwd", "*********");
        log.debug("*** DB-PROPS={}", dbProps);
        dbProps.setProperty("pwd", dontShowPwd);
        return dbProps;
    }

    @Bean(name = "refdbDataSource")
    @Primary
    public DataSource dataSource() throws IOException {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        try {
            Properties p = dbProperties();
            ds.setDriverClassName("oracle.jdbc.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@" + p.get("server") + ":" + p.get("port") + ":" + p.get("dbname"));
            ds.setUsername(String.valueOf(p.get("user")));
            ds.setPassword(String.valueOf(p.get("pwd")));
            ds.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initial-size")));
            ds.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maximum-pool-size")));
            ds.getConnection().isValid(100);
            log.info("*** Connected to RefDB data-source.");
            return ds;
        } catch (Exception e) {
            log.error("#################################################");
            log.error(">>> Unable to connect to RefDB <<<");
            log.error(">>> Please ensure that the RefDB connection configuration is setup");
            log.error(">>> and access via JDBC to the server is open. <<<");
            log.error("#################################################");
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "virtDataSource")
    public VirtuosoDataSource virtDataSource() {
        try {
            VirtuosoDataSource ds = new VirtuosoDataSource();
            ds.setUser(environment.getProperty("virtuoso.user"));
            ds.setPassword(environment.getProperty("virtuoso.pwd"));
            ds.setServerName(environment.getProperty("virtuoso.server"));
            ds.setPortNumber(Integer.parseInt(environment.getProperty("virtuoso.port")));
            ds.getConnection().isValid(100);
            log.info("*** Connected to Virtuoso data-source.");
            return ds;
        } catch (Exception e) {
            log.error("#################################################");
            log.error(">>> It looks like there is no configured Virtuoso instance to connect to or server is unavailable. <<<");
            log.error("#################################################");
            return new VirtuosoDataSource();
        }
    }

    @Bean
    @Scope(value = "prototype")
    public STGroupFile biologicalTemplates() {
        return new STGroupFile("templates/biomethod.stg", '$', '$');
    }

    @Bean(name = "resultLevelTemplate")
    @Scope(value = "prototype")
    public ST resultLevelTemplate() {
        ST template = biologicalTemplates().getInstanceOf("resultLevelTemplate");
        assert template != null;

        return template;
    }

    @Bean(name = "resultTypeTemplate")
    @Scope(value = "prototype")
    public ST resultTypeTemplate() {
        ST template = biologicalTemplates().getInstanceOf("resultTypeTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "bioMethodTemplate")
    @Scope(value = "prototype")
    public ST bioMethodTemplate() {
        ST template = biologicalTemplates().getInstanceOf("bioMethodTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "bioMethodVersionTemplate")
    @Scope(value = "prototype")
    public ST bioMethodVersionTemplate() {
        ST template = biologicalTemplates().getInstanceOf("bioMethodVersionTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "bioTargetTemplate")
    @Scope(value = "prototype")
    public ST bioTargetTemplate() {
        ST template = biologicalTemplates().getInstanceOf("bioTargetTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "geneTemplate")
    @Scope(value = "prototype")
    public ST geneTemplate() {
        ST template = biologicalTemplates().getInstanceOf("geneTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "uomTemplate")
    @Primary
    public ST uomTemplate() {
        STGroupFile uomGroup = new STGroupFile("templates/uom.stg", '$', '$');
        uomGroup.load();
        ST template = uomGroup.getInstanceOf("uomTemplate");
        assert template != null;
        return template;
    }

    @Bean(name = "refdbToQueryTemplate")
    @Scope(value = "prototype")
    public ST refdbToQueryTemplate() {
        STGroupFile stsGroup = new STGroupFile("templates/sts.stg", '$', '$');
        ST template = stsGroup.getInstanceOf("metaToQueryWithLimits");
        assert template != null;
        return template;
    }

    @Bean
    @Scope(value = "prototype")
    public RDFMetaDataDefinitions rdfMetaDataDefinitions() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbCtx = JAXBContext.newInstance("com.data2discovery.sts.jaxb");
        Unmarshaller um = jaxbCtx.createUnmarshaller();
        FileInputStream istream = new FileInputStream("config/RDFMetaDataDefinitions.xml");
        RDFMetaDataDefinitions metaDataDefinitions = (RDFMetaDataDefinitions) um.unmarshal(istream);
        return metaDataDefinitions;
    }
}
