/**
 * 
 */
package de.unibonn.iai.eis.slicer.model;

/**
 * @author Kemele M. Endris
 *
 * Specifies the type of source the slicer is going to use
 */
public enum SourceType {

	/**
	 * SPARQL endpoint
	 */
	ENDPOINT,
	/**
	 * Jena TDB dataset
	 */
	TDB,
	/**
	 * RDF file (any serialization type could be used, NT, TTL, RDF/XML, RDF/JSON, JSON-LD)
	 */
	FILE
}
