package com.data2discovery.sts.transformers

import com.data2discovery.sts.data.results.ResultLevel
import com.data2discovery.sts.data.results.ResultType
import com.data2discovery.sts.data.results.UomResult
import org.springframework.beans.factory.annotation.Qualifier
import javax.inject.Inject

/**
 * Created by mgarcia on 5/29/16.
 */
class TransformerMap @Inject constructor(
        @Qualifier("uomTransformer") var uomTransformer: RefdbTransformer<UomResult>,
        @Qualifier("resultTypeTransformer") var resultTypeTransformer: RefdbTransformer<ResultType>,
        @Qualifier("resultLevelTransformer") var resultLevelTransformer: RefdbTransformer<ResultLevel>,
        val transformerMap: Map<String, Any> = hashMapOf(
                "uom" to uomTransformer,
                "rtype" to resultTypeTransformer,
                "rlevel" to resultLevelTransformer
                )
) {


}