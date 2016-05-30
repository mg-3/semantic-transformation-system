package com.data2discovery.sts

import java.io.StringReader

import com.data2discovery.sts.repository.{GeneRepository, TissueEntityRepository}
import org.apache.commons.csv.{CSVFormat, CSVParser}
import org.junit.Assert.{assertEquals, assertNotNull}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

/**
  * Created by mike on 10/16/15.
  */
//@RunWith(classOf[SpringJUnit4ClassRunner])
//@SpringApplicationConfiguration(classes = Array[Class[_]](classOf[SemanticTransformationSystem]))
//@WebAppConfiguration
//@IntegrationTest(Array[String]("server.port=9090"))
class MyTest {

    @Autowired
    var appCtx: ApplicationContext = _

//    @Test
    def parseCsv(): Unit = {
        val reader = new StringReader("1,2,\"ab, cd\"\n3,4,foo,bar")
        val format: CSVFormat = CSVFormat.DEFAULT
        format.withRecordSeparator('\n')
        val csv = new CSVParser(reader, format)
        val recs = csv.getRecords
        assertNotNull(recs)
        assertEquals(2, csv.getRecordNumber)
        assertEquals(3, recs.get(0).size())
        assertEquals(4, recs.get(1).size())
    }

//    @Test
    def tissueRepo(): Unit = {
        assertNotNull(appCtx)
        val repo: TissueEntityRepository = appCtx.getBean(classOf[TissueEntityRepository])
        val entity = repo.findOne(1)
        assertNotNull(entity)
    }

//    @Test
    def geneRepo(): Unit = {
        assertNotNull(appCtx)
        val repo: GeneRepository = appCtx.getBean(classOf[GeneRepository])
        val entity = repo.findByGeneId("HGNC:5")
        assertNotNull(entity)
    }
}
