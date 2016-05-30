#Semantic Component System (STS)
---
STS is a project for Eli Lilly.  The goal is to read SQL content from their RefDB and generate RDF content from meta-data driven configuration. 

##Oracle JDBC Driver
---
Download from http://www.oracle.com/technetwork/database/features/jdbc/default-2280470.html and then install using maven as so:
```
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true
```
```
mvn install:install-file -DgroupId=com.openlink.virtuoso -DartifactId=virtjdbc4 -Dversion=4.1 -Dpackaging=jar -Dfile=virtjdbc4_1.jar -DgeneratePom=true
```
```
mvn install:install-file -DgroupId=com.openlink.virtuoso -DartifactId=virt_jena2 -Dversion=2 -Dpackaging=jar -Dfile=virt_jena2.jar -DgeneratePom=true
```

##Running via Docker
---
Build the image with the following command.
```
  docker build -t lilly/sts:v1 .
```
Once built it can then be run with:
```
  docker run -d --name STS -p 8090:8090 lilly/sts:v1  
```
To control the docker image once its up and running the following commands can be used:
```
  docker stop STS
```
```
  docker start STS
```

#REST API's
##STS SPARQL Query Service
<pre>E.g. /virt/sparql?maxrows=8&timeout=0&rqfile=uom.rq</pre>
###HTTP Request Parameters
<table class="t1">
<tr>
        <th id="3">Parameter </a>
        </th>
        <th id="4">Notes </a>
        </th>
        <th id="5">Required </a>
        </th>
        <th id="6">Occurrence </a>
        </th>
            </tr>
<tr>
              <td> <code>maxrows</code> </td>
              <td> Max triples to be returned </td>
              <td> Yes </td>
              <td align="center">   </td>
            </tr>
<tr>
              <td> <code>timeout</code> </td>
              <td> Timeout SPARQL query  </td>
              <td> Yes </td>
              <td align="center">   </td>
            </tr>
<tr>
              <td> <code>rqfile</code> </td>
              <td> Name of the SPARQL query file </td>
              <td> Yes </td>
              <td align="center">   </td>
            </tr>
</table>
<br/>
##STS RDF Load Service
<pre>E.g. /rdfload/{kind}?bulk=yes&fn=uom</pre>
###HTTP Request Parameters
<table class="t1">
<tr>
        <th id="3">Parameter </a>
        </th>
        <th id="4">Notes </a>
        </th>
        <th id="5">Required </a>
        </th>
        <th id="6">Occurrence </a>
        </th>
            </tr>
<tr>
              <td> <code>kind</code> </td>
              <td> The kind or type of logical load (e.g. UOM) </td>
              <td> Yes </td>
              <td align="center">   </td>
            </tr>
<tr>
              <td> <code>bulk</code> </td>
              <td> Yes to load to configured Virtuoso; No for RDF response </td>
              <td> No </td>
              <td align="center">   </td>
            </tr>
<tr>
              <td> <code>fn</code> </td>
              <td> Prefix of RDF file (saved as TURTLE) </td>
              <td> Yes </td>
              <td align="center">   </td>
            </tr>
</table>
Release Notes
---
0.1
Added support for connecting to local Virtuoso server - i) host=localhost, ii) port=1111, iii) user=dba, iv) pwd=dba
These parameters will be moved to an external configuration.  

0.2
In this update, externally configured RefDb meta-data has been added.  The file driving the data-driven RDF generation is
 config/RDFMetaDataDefinitions.xml.  This will allow RDF triple generation via a REST interface.  Also the configuration for both Refdb and Virtuoso has been externalized. 
 
0.3
In this update the Dockerfile was updated to allow for simpler startup.  Once the container is running, the user can bash into the container to edit the configuration file for STS, exit, commit the container changes and restart.  

0.4
This release includes the initial end-to-end RDF workflow. Data from RefDb is transformed into RDF and stored to Virtuoso.  The Virtuoso loading mechanism has been changed from the proprietary stored procedures to usage of the Virtuoso HTTP APIs. This allows for better design by a) not having to rely on tight coupling to Virtuoso libraries and b) the HTTP APIs have shown capable of providing valuable runtime responses aiding in troubleshooting.  

See following Virtuoso resources:
---
1. [Sparql](http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VOSSparqlProtocol)
2. [RDF Loading](http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VirtRDFInsert)

 
