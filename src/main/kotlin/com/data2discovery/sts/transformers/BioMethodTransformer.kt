package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.BioMethod
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
class BioMethodTransformer @Inject constructor(
        @Qualifier("bioMethodTemplate") var rrt: ST
) : RefdbTransformer<BioMethod> {

    override fun resultClass(): Class<BioMethod> {
        return BioMethod::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.BioMethod(" +
                "e.refBlgclMthdId, " +
                "e.blgclMthdDrvdShrtNm, " +
                "e.blgclMthdDescTxt, " +
                "e.refStsId, " +
                "e.refBlgclMthdTypGrpngId) " +
                "from RefBlgclMthdEntity as e"
    }

    override fun transform(queryResults: List<BioMethod>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "bm")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}
