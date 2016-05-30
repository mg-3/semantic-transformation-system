package com.data2discovery.sts

import com.data2discovery.sts.configuration.STSConfiguration
import com.data2discovery.sts.data.results.UomResult
import com.data2discovery.sts.data.transformers.UomTransformer
import org.apache.commons.io.IOUtils
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.stringtemplate.v4.STGroupFile
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.io.PrintWriter

/**
 * Created by mgarcia on 4/12/16.
 */
class TemplateTests {

    fun testData() : List<UomResult> {
        val objInputStream = ObjectInputStream(FileInputStream(File("/m01/uomdata.dat")))
        return objInputStream.readObject() as List<UomResult>
    }

    @Test
    fun templateTest_1() {
        val data = testData()
        val uomGroup = STGroupFile("templates/uom.stg", '$', '$')
        uomGroup.load()
        val template = uomGroup.getInstanceOf("uomTemplate")
        template.add("uoms", data)
        val pw = PrintWriter(System.out)
        pw.write(template.render())
        pw.flush()
    }

    @Test
    fun uomTransformerTest() {
        val conf = STSConfiguration()
        val uomTfr = UomTransformer(conf.uomTemplate())
        val istream = uomTfr.transform(testData())
        assertNotNull(istream)
        IOUtils.copy(istream, System.out)
    }
}