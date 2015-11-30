/**
 * 
 */
package de.unibonn.iai.eis.slicer.model;

/**
 * @author Kemele M. Endris
 * 
 * specifies the output sink type for the sliced dataset
 */
public enum SinkType {
	/**
	 * SPARQL endpoint
	 */
	ENDPOINT,
	/**
	 * Jena TDB
	 */
	TDB,
	/**
	 * RDF file, default serialization will be used
	 */
	FILE,
	/**
	 * N-Triple file
	 */
	FILE_NT,
	/**
	 * TTL file
	 */
	FILE_TTL,
	/**
	 * RDF/XML file
	 */
	FILE_RDF_XML,
	/**
	 * RDF/JSON file
	 */
	FILE_RDF_JSON,
	/**
	 * Json-LD file format
	 */
	FILE_JSONLD	
	
	
}
