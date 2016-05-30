package com.data2discovery.sts.virtuoso;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Really basic test to see if can add individual triples to a Virtuoso Graph from Java, and on a
 * following session (jvm run) see if they're there.
 * <p/>
 * Based on description and example code described at following page:
 * <p/>
 * http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VirtJenaProvider
 */
public class VirtuosoJenaProviderTest {

    static String urlString = "jdbc:virtuoso://localhost:1111";

    static String username = "dba";

    static String password = "dba";

//    static String urlString = "jdbc:virtuoso://shackleton.data2discovery.net:8890/spqrql";
//    static String username = "dba";
//    static String password = "rootbeer";

    static String graphName = "Example3";

    static Node createURI(String uriString) {
        return NodeFactory.createURI(uriString);
    }

    static Node foo1 = createURI("http://example.org/#foo1");

    static Node bar1 = createURI("http://example.org/#bar1");

    static Node baz1 = createURI("http://example.org/#baz1");

    static Node foo2 = createURI("http://example.org/#foo2");

    static Node bar2 = createURI("http://example.org/#bar2");

    static Node baz2 = createURI("http://example.org/#baz2");

    static Node foo3 = createURI("http://example.org/#foo3");

    static Node bar3 = createURI("http://example.org/#bar3");

    static Node baz3 = createURI("http://example.org/#baz3");

    /**
     * Executes a SPARQL query against data pre-populated in virtuoso.
     */
    @Test
    public void readExisting() {
//        VirtGraph set = new VirtGraph(urlString, username, password);

//        Query sparql = QueryFactory.create( "SELECT * WHERE { GRAPH ?graph { ?s ?p ?o } } limit 100" );

//        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory
//                .create("SELECT * WHERE { GRAPH MYGRAPH { ?s ?p ?o } } limit 1000", set);
//
//        ResultSet results = vqe.execSelect();
//        while (results.hasNext()) {
//            QuerySolution result = results.nextSolution();
//            RDFNode graph = result.get("MYGRAPH");
//            RDFNode s = result.get("s");
//            RDFNode p = result.get("p");
//            RDFNode o = result.get("o");
//            System.out.println(graph + " { " + s + " " + p + " " + o + " . }");
//        }
    }

    public void addTriples() {
        List<Triple> triples = new ArrayList<>();

//        VirtGraph graph = new VirtGraph(graphName, urlString, username, password);

//        graph.clear();

//        System.out.println( "graph.isEmpty() = " + graph.isEmpty());
        System.out.println("Add 3 triples to graph <Example3>.");

//        graph.performAdd(new Triple(foo1, bar1, baz1));
//        graph.performAdd(new Triple(foo2, bar2, baz2));
//        graph.performAdd(new Triple(foo3, bar3, baz3));

//        System.out.println( "graph.isEmpty() = " + graph.isEmpty());
//        System.out.println("graph.getCount() = " + graph.getCount());

        triples.add(new Triple(foo1, bar1, baz1));
        triples.add(new Triple(foo2, bar2, baz2));

        System.out.println("Remove 2 triples from graph <Example3>");
//        graph.remove(triples);
//        System.out.println("graph.getCount() = " + graph.getCount());
        System.out.println("Please check result with isql tool.");
    }

    /**
     * Check if the triples added by method addTriples() are still there.  Meant to be run in a
     * separate session.
     */
    public void checkAddedTriples() {
        Node foo3 = createURI("http://example.org/#foo3");
        Node bar3 = createURI("http://example.org/#bar3");
        Node baz3 = createURI("http://example.org/#baz3");

//        VirtGraph graph = new VirtGraph(graphName, urlString, username, password);

//        final boolean containsTriple3 = graph.contains( new Triple( foo3, bar3, baz3 ) );
//        System.out.println( "contains triple_3 = " + containsTriple3 );

//        System.out.println("triples count = " + graph.getCount());
    }

    @Test
    public void interactWithVirtuoso() {
        readExisting();
        addTriples();
        checkAddedTriples();
    }

}
