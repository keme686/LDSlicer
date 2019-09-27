# LDSlicer
Linked Data Slicer

# Run LDSlicer
Currently LDSlicer supports only slicing from SPARQL Endpoints.

```
$ git clone https://github.com/keme686/LDSlicer.git
$ cd LDSlicer 
$ mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```

Then run the following to slice your endpoint:
```
java -cp target/LDSlicer-0.0.1-SNAPSHOT-jar-with-dependencies.jar de.unibonn.iai.eis.slicer.Main  [ENDPOINT] [QUERY-CONSTRUCT] [OUTPUT] [FORMAT]
```
## Example
To extract all restaurants from DBpedia endpoint run the following:
```
$ java -cp target/LDSlicer-0.0.1-SNAPSHOT-jar-with-dependencies.jar de.unibonn.iai.eis.slicer.Main  http://dbpedia.org/sparql "CONSTRUCT {?s ?p ?o} WHERE {?s a <http://dbpedia.org/ontology/Restaurant>. ?s ?p ?o}"  ./restaurants.nt NT
```
This command will start slicing process by rungin the slice query:
```
CONSTRUCT {?s ?p ?o} 
WHERE {
      ?s a <http://dbpedia.org/ontology/Restaurant>. 
      ?s ?p ?o
      }
```
Output file name will be `restaurants.nt` and output format in `NT` - N-Triples.
The following output format parameters are allowed:
- `NT`
- `TTL`
- `RDF/XML`
- `RDF/JSON`
- `JSONLD`


