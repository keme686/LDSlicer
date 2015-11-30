/**
 * 
 */
package de.unibonn.iai.eis.slicer.query;

import org.apache.jena.query.Query;

import de.unibonn.iai.eis.slicer.exception.SliceQueryException;

/**
 * @author Kemele M. Endris
 *
 */
public interface SliceQuery {
	public Query getSliceQuery(String interest, int depth) throws SliceQueryException;
	public Query getSliceQuery(String interest, int forwardDepth, int backwardDepth) throws SliceQueryException;
}
