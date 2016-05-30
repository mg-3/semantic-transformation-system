package com.data2discovery.sts.services

import org.apache.commons.io.IOUtils
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpPut
import org.apache.http.entity.FileEntity
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.io.File
import java.io.StringWriter
import java.nio.file.DirectoryStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.inject.Inject

/**
 * Created by mgarcia on 4/12/16.
 */
@Component
open class RdfLoader() {

    private val log: Logger = LoggerFactory.getLogger(RdfLoader::class.java)

    /**
     * The Spring environment
     */
    lateinit open var env: Environment
        @Inject set

    /**
     * HTTP client used for interacting with the Virtuoso APIs
     */
    lateinit open var asyncClient: CloseableHttpAsyncClient
        @Inject set


    /**
     * Load individual RDF file on a per request basis
     *
     * @param filename the name of the file to be read from
     */
    open fun httpLoad(filename: String): MutableMap<String, String> {
        val ttlDirStr = env.getProperty("sts.data.directory")
        val ttlFile = File(ttlDirStr, filename)
        log.info("Loading file {} ", ttlFile.canonicalPath)
        return doHttpRequest(ttlFile )
    }

    /**
     * Load all the turtle files in the designated directory
     */
    fun loadMultipleFiles(): MutableMap<String, String> {

        fun ttlFiles(): List<File> {
            val ttlDirStr = env.getProperty("sts.data.directory")
            val ttlDir = Paths.get(ttlDirStr)
            val ttlFileList = mutableListOf<File>()
            val stream: DirectoryStream<Path> = Files.newDirectoryStream(ttlDir, "*.ttl")
            stream.use {
                stream.map { p -> ttlFileList.add(p.toFile()) }
            }
            return ttlFileList
        }

        val statusMap = mutableMapOf<String, String>()
        val ttlFiles = ttlFiles()

        /* Load all the ttl files in the load directory */
        var count = 0
        val numFiles = ttlFiles.size

        for (ttlFile in ttlFiles) {
            val put = HttpPut(env.getProperty("sts.virtuoso.load.endpoint") + ttlFile.toPath().fileName.toString())
            put.entity = FileEntity( ttlFile )
            if (count++ == numFiles) {
                statusMap.putAll( doHttpRequest( ttlFile ) )
            } else {
                statusMap.putAll( doHttpRequest( ttlFile ) )
            }
        } // end for over ttl files
        return statusMap
    }

    /**
     * Get the asnyc Http client, build the put and submit the request to the server to load the
     * RDF file.  A successful RDF file submission will respond with HTTP status 201/Created.
     *
     * @param ttlFile the RDF file to submit
     */
    private fun doHttpRequest(ttlFile: File): MutableMap<String, String> {
        val put = HttpPut(env.getProperty("sts.virtuoso.load.endpoint") + ttlFile.toPath().fileName.toString())
        val statusMap = mutableMapOf<String, String>()
        put.entity = FileEntity( ttlFile )
        try {
            val future = asyncClient.execute(put, null)
            val response = future.get()
            val status = response.statusLine.statusCode

            if (status == HttpStatus.SC_CREATED) {
                val successMsg = String.format("RDF file was %s loaded successfully", ttlFile.name)
                log.info(successMsg)
                statusMap.put(String.format("[%s] ", ttlFile.toPath().fileName.toString()), successMsg)

            } else {
                val reason = response.statusLine.reasonPhrase
                log.error(reason)
                val content = response.entity.content
                val writer = StringWriter()
                IOUtils.copy(content, writer)
                val serverResponseMsg = writer.toString().replace(Regex("<.?p>", RegexOption.IGNORE_CASE), "")
                val errMsg = String.format(" RDF file [%s] was not loaded. Reason: %s",
                        ttlFile.name, serverResponseMsg)
                log.error(errMsg)
                statusMap.put(ttlFile.toPath().fileName.toString(), errMsg)
            }
        } catch (e: Exception) {
            log.error("Unable to HTTP PUT RDF file", e)
        }
        return statusMap
    }
}