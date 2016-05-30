package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.BioTarget
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
class BioTargetTransformer @Inject constructor(
        @Qualifier("bioTargetTemplate") var rrt: ST
) : RefdbTransformer<BioTarget> {

    override fun resultClass(): Class<BioTarget> {
        return BioTarget::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.BioTarget(" +
                "e.refBlgclTrgtId, " +
                "e.blgclTrgtDrvdShrtNm, " +
                "e.blgclTrgtDrvdNm, " +
                "e.blgclTrgtDescTxt, " +
                "e.refStsId) " +
                "from RefBlgclTrgtEntity as e"
    }

    override fun transform(queryResults: List<BioTarget>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "bt")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}
