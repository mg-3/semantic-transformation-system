package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.ResultType
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
class ResultTypeTransformer @Inject constructor(
        @Qualifier("resultTypeTemplate") var rrt: ST
) : RefdbTransformer<ResultType> {

    override fun resultClass(): Class<ResultType> {
        return ResultType::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.ResultType(" +
                "e.refBlgclRsltTypId, " +
                "e.blgclRsltTypNm, " +
                "e.blgclRsltTypShrtNm, " +
                "e.blgclRsltTypDescTxt, " +
                "e.refStsId, e.refRsltLvlId) " +
                "from RefBlgclRsltTypEntity as e"
    }

    override fun transform(queryResults: List<ResultType>): InputStream {
        rrt.add("results", queryResults)
        rrt.add("resultPrefix", "rt")
        val ostream = ByteArrayOutputStream()
        ostream.write(rrt.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        rrt.remove("results")
        return byteStream
    }
}

