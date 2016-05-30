package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.Gene
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.stringtemplate.v4.ST
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by mgarcia on 5/30/16.
 */
@Component
class GeneTransformer @Inject constructor(
        @Qualifier("geneTemplate") var rrt: ST
) : RefdbTransformer<Gene> {

    override fun resultClass(): Class<Gene> {
        return Gene::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.Gene(" +
                "e.refGeneId, " +
                "e.geneSymbl, " +
                "e.refStsId) " +
                "from RefGeneEntity as e"
    }

    override fun transform(queryResults: List<Gene>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "g")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}