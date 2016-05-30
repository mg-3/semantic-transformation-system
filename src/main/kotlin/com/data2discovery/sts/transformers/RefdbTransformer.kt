package com.data2discovery.sts.transformers

import java.io.InputStream

interface RefdbTransformer<T> {

    fun resultClass() : Class<T>
    fun transform(queryResults: List<T>): InputStream
    fun queryString(): String

}