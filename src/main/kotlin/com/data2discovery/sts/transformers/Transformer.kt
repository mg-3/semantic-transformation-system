package com.data2discovery.sts.data.transformers

import com.data2discovery.sts.data.results.RefDbResult
import java.io.InputStream

/**
 * Created by mgarcia on 4/7/16.
 */
interface Transformer {

    fun transform(results: List<RefDbResult>): InputStream

}