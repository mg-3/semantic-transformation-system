package com.data2discovery.sts.services

import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.StringWriter
import javax.inject.Inject

/**
 * Created by mgarcia on 4/16/16.
 */
@Service
class SparqlService() {

    private val log: Logger = LoggerFactory.getLogger(SparqlService::class.java)

    lateinit open var env: Environment
        @Inject set

    fun sparqlQuery(sparqlFileStr: String): StringWriter {
        val swriter = StringWriter()
        BufferedReader(FileReader("sparql"+ File.separator+sparqlFileStr))
                .use {
                    r -> IOUtils.copy(r, swriter)
                }
        return swriter
    }

}