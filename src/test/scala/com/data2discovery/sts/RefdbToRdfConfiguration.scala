package com.data2discovery.sts

import java.io.FileInputStream
import java.util.Collections
import javax.inject.Inject
import javax.xml.bind.JAXBContext

import com.data2discovery.sts.jaxb._
import com.data2discovery.sts.services.RefDbQueryService
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.junit.{Assert, Before, Ignore, Test}
import org.springframework.boot.test.{SpringApplicationConfiguration, WebIntegrationTest}
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.stringtemplate.v4.ST

import scala.collection.mutable


/**
  * Tests to check building of RDF query to RefDB using StringTemplate
  *
  * @author mgarcia
  * @since 1/30/16
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array[Class[_]](classOf[SemanticTransformationSystem]))
@WebIntegrationTest(Array[String]("server.port=9090"))
class RefdbToRdfConfiguration {

  private[this] var metaDataDefs: RDFMetaDataDefinitions= _
  private[this] val template =
    """
      |-- Query for "$name$"
      |SELECT $\n$$sc:{c|$\t$$c.name$}; separator=",\n"$
      |FROM $joins:{j|$j$}; separator="\n"$
      |$if(!w)$NO WHERE$endif$
      |$w$
    """.stripMargin
  private[this] val st = new ST(template, '$', '$')

  @Inject
  private[this] var appCtx: ApplicationContext = _;

  @Before
  def setup(): Unit = {
    val jaxbCtx = JAXBContext.newInstance("com.data2discovery.sts.jaxb")
    val um = jaxbCtx.createUnmarshaller()
    val istream = new FileInputStream("config/RDFMetaDataDefinitions.xml")
    metaDataDefs = um.unmarshal(istream).asInstanceOf[RDFMetaDataDefinitions]

  }

  def findJoinColumn(n: Int): Int = {
    (n % 10) * 10 + (n / 10).toInt
  }

  @Test
  def checkJoinNumbers(): Unit = {
    assertTrue(findJoinColumn(24) == 42)
    assertTrue(findJoinColumn(55) == 55)
    assertTrue(findJoinColumn(31) == 13)
  }

  @Test
  def confToQuery(): Unit = {
    import scala.collection.JavaConversions._
    assertTrue(this.metaDataDefs.isInstanceOf[RDFMetaDataDefinitions])
    /* Get the first category of a given name and generate the RDF query */
    val queryInfo = this.metaDataDefs.getRdbms.getCategories.getCategory
      .filter(catType => catType.getValue == "genes").head.getQuery
    assertTrue(queryInfo.isInstanceOf[Query])
    val name = queryInfo.getDesc
    val tables = queryInfo.getJoinDetails.getTables.getTable

    tables.transform( (t: Table) => {
      val alias = t.getAlias
      val sCols = t.getSelectColumn
      sCols.foreach((sc: SelectColumn) => sc.setName((alias+"."+sc.getName)))
      t
    })

    val tabMap = tables.map { (t: Table) => (t.getId.intValue(), t)}.toMap

    val joins: java.util.List[String] = mutable.ListBuffer[String]()

    for(k <- tabMap.keys) {
      val t = tabMap(k)
      if(t.getSelectColumn.size() > 0) {
        val id = t.getId.intValue()
        val jtOnIds = t.getJoinColumns.getJoinColumn.map((jc: JoinColumn) => jc.getJtOn.intValue())
        val lookup = (id * 10) + jtOnIds(0)
        val assocLookup = findJoinColumn(lookup)
        val assocTabId = assocLookup / 10
        val assocColId = assocLookup % 10
        val assocTable = tabMap(assocTabId)
        val joinCol = assocTable.getJoinColumns.getJoinColumn.filter((jc: JoinColumn) => jc.getJtOn.intValue() == assocColId).head
        joins += ("JOIN " + t.getName + " " + t.getAlias + " ON " + t.getAlias+"."+t.getJoinColumns.getJoinColumn.head.getName
          + "=" +assocTable.getAlias+"."+joinCol.getName)
      } else {
        joins += t.getName + " " + t.getAlias
      }
    }
    Collections.reverse(joins)

    val selectCols: java.util.List[SelectColumn] = tables.map((t: Table) => t.getSelectColumn).flatten.toList

    val where = queryInfo.getWhereClause
    this.st.add("joins", joins)
    this.st.add("name", name)
    this.st.add("sc", selectCols)
    this.st.add("w", where)
    println(this.st.render())
  }

  @Ignore
  def queryServiceCheck(): Unit = {
    val bean = this.appCtx.getBean(classOf[RefDbQueryService]);
    val query = bean.refdbConfigToQuery("genes",10).trim
    println(query)
    Assert.assertEquals("""-- Query for "genes-and-tissues"
                          |select * from (
                          |SELECT
                          |	g.gene_name as subject,
                          |	g.gene_symbol as predicate,
                          |	t.tissue_name as object
                          |FROM gene_tissue gt
                          |JOIN tissue t ON t.tissue_id=gt.tissue_id
                          |JOIN gene g ON g.gene_id=gt.gene_id
                          |WHERE 1=1
                          |) where rownum <= 10""".stripMargin, query)
  }
}
