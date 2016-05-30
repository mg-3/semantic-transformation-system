package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.BioMethodVersion
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
class BioMethodVersionTransformer @Inject constructor(
        @Qualifier("bioMethodVersionTemplate") var rrt: ST
) : RefdbTransformer<BioMethodVersion> {

    override fun resultClass(): Class<BioMethodVersion> {
        return BioMethodVersion::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.BioMethodVersion(" +
                "e.refBlgclMthdVrsnId, " +
                "e.mthdVrsnDrvdShrtNm, " +
                "e.mthdVrsnDescTxt, " +
                "e.mthdVrsnDrvdNm, " +
                "e.refStsId, " +
                "e.refBlgclMthdId) " +
                "from RefBlgclMthdVrsnEntity as e"
    }

    override fun transform(queryResults: List<BioMethodVersion>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "bmv")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}
