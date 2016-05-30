package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.ResultLevel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.stringtemplate.v4.ST
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by mgarcia on 5/29/16.
 */
@Component
class ResultLevelTransformer @Inject constructor(
        @Qualifier("resultLevelTemplate") var rrt: ST
) : RefdbTransformer<ResultLevel> {

    override fun resultClass(): Class<ResultLevel> {
        return ResultLevel::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.ResultLevel(" +
                "e.refRsltLvlId, " +
                "e.rsltLvlNm, " +
                "e.rsltLvlShrtNm, " +
                "e.rsltLvlDescTxt, " +
                "e.refStsId) " +
                "from RefRsltLvlEntity as e"
    }

    override fun transform(queryResults: List<ResultLevel>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "rl")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}
