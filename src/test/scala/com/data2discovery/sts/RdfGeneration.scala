package com.data2discovery.sts

import java.io.ByteArrayOutputStream

import org.apache.jena.rdf.model.{ModelFactory, Statement}
import org.junit.Test

import scala.xml.{Node, NodeSeq}

/**
  * Created by mgarcia on 2/3/16.
  */
class RdfGeneration {

  @Test
  def makeTriple: Unit = {
    val our = "http://our.company.com/ns#"
    val our_gene = "http://our.company.com/ref/gene/"
    val our_tissue = "http://our.company.com/ref/tissue/"
    val rdf = "http://www.w3.org/TR/rdf-schema/"
    val model = ModelFactory.createDefaultModel()
    model.setNsPrefix("our", our)
    model.setNsPrefix("our_gene", our_gene)
    model.setNsPrefix("our_tissue", our_tissue)
    model.setNsPrefix("rdf", rdf)

    //  our_gene:188 rdf:type our:Gene ;
    //  our:name “AP2 associated kinase 1” ;
    //  our:symbol “AAK1” .
    //  our_gene:335 rdf:type our:Gene ;
    //  our:name “tyrosine kinase, non-receptor, 2” ;
    //  our:symbol “TNK2” .

    def makeNode(id: Int, `type`: String, name: String, symbol: String): Statement = {
      val root = model.createResource(our_gene+id)
      val typeR = model.createResource(our + `type`)
      val prop1 = model.createProperty(rdf, "type")
      root
        .addProperty(model.createProperty(our + "name"),name)
        .addProperty(model.createProperty(our + "symbol"),symbol)
      model.createStatement(root, prop1, typeR)
    }

    val f = (s: Statement) => model.add(s)

    (makeNode(id = 188, `type`="Gene", name="AP2 associated kinase 1", symbol="AAK1") ::
      makeNode(id = 335, `type`="Gene", name="tyrosine kinase, non-receptor, 2", symbol="TNK2") ::
      makeNode(id = 2684, `type` ="Gene", name="cyclin-dependent kinase 9", symbol = "CDK9") :: Nil)
      .foreach( f(_) )

    val ostream = new ByteArrayOutputStream()
    model.write(ostream, "TURTLE")
    println(ostream.toString())
    model.close()

  }

  /**
    * Define a configuration to drive generation of specific RDF
    */
  @Test
  def tripleViaConfig(): Unit = {
//    @prefix rdf:   <http://www.w3.org/TR/rdf-schema/> .
//    @prefix our_gene: <http://our.company.com/ref/gene/> .
//    @prefix our_tissue: <http://our.company.com/ref/tissue/> .
//    @prefix our:   <http://our.company.com/ns#> .
//
//      our_gene:1
    //   our:name  "alpha-1-B glycoprotein" ;
//    our:symbol  "A1BG" ;
//    rdf:type    our:Gene .

    val conf = <rdf-output>
      <prefixes>
        <prefix id="1" p="our_gene" uri="http://our.company.com/ref/gene/"/>
        <prefix id="2" p="our_tissue" uri="http://our.company.com/ref/tissue/"/>
        <prefix id="3" p="rdf" uri="http://www.w3.org/TR/rdf-schema/"/>
        <prefix id="4" p="our" uri="http://our.company.com/ns#"/>
      </prefixes>
        <triple-spec>
          <subject prefix-id="1"/>
          <predicates>
            <predicate prefix-id="4" n="name"/>
            <predicate prefix-id="4" n="symbol"/>
            <predicate prefix-id="3" n="type">
              <object>our:Gene</object>
            </predicate>
          </predicates>
        </triple-spec>
    </rdf-output>

    (conf \\ "prefix").foreach((n: Node) => {
      println("@prefix" +" " + n\@"p" + " <" + n\@"uri" + "> .")
    })

    def lookupPrefixInfo(id: String) = {
      (conf \\ "prefix").filter((p: Node) => p\@"id" == id).head
    }

    (conf \\ "triple-spec" \ "subject")
      .map((n: NodeSeq) => lookupPrefixInfo(n\@"prefix-id") \@"p" + ":")
      .foreach((s: String) => print( s + "123"))

    println

    val prefixes = (conf \\ "predicates" \ "predicate")
      .map((n: NodeSeq) => {
        println(n\"object" text)
        lookupPrefixInfo(n\@"prefix-id") \@"p" + ":" + n\@"n"
      })

    prefixes.take(prefixes.length - 1).foreach( (s: String) => println("\t"+s+" \"Some data values\" ;"))
    prefixes.takeRight(1).foreach( (s: String) => println("\t"+s+" \"Some data values\" ."))
  }

}
