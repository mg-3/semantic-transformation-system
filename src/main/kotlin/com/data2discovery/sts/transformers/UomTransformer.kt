package com.data2discovery.sts.data.transformers

import com.data2discovery.sts.data.results.UomResult
import com.data2discovery.sts.transformers.RefdbTransformer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.stringtemplate.v4.ST
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by mgarcia on 4/7/16.
 */
@Component
open class UomTransformer @Inject constructor(
        @Qualifier("uomTemplate") var uomTemplate: ST) : RefdbTransformer<UomResult> {

    override fun resultClass(): Class<UomResult> {
        return UomResult::class.java
    }

    override fun queryString(): String {
        return "select new com.data2discovery.sts.data.results.UomResult(" +
                "s.refStsId, " +
                "s.stsShrtNm, " +
                "s.stsDescTxt, " +
                "u.refUomId, " +
                "u.uomNm, " +
                "u.uomShrtNm, " +
                "u.uomDescTxt) " +
                "from RefUomEntity as u, RefStsEntity as s where u.refStsId = s.refStsId"
    }

    override fun transform(queryResults: List<UomResult>): InputStream {

        queryResults.forEach { qr ->
            if (qr.uomDesc != null) {
                qr.setUomDescription(qr.uomDesc?.replace('"','\'') as String)
            }
        }
        uomTemplate.add("uoms", queryResults)
        val ostream = ByteArrayOutputStream()
        ostream.write(uomTemplate.render().toByteArray())
        val bytes = ostream.toByteArray()
        val byteStream = ByteArrayInputStream(bytes)
        uomTemplate.remove("uoms")
        return byteStream
    }

}