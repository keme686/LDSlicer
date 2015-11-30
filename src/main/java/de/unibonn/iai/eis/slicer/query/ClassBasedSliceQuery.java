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
public class ClassBasedSliceQuery implements SliceQuery {

	public Query getSliceQuery(String interest, int depth) throws SliceQueryException {
		
		throw new SliceQueryException("Slice qeury exception occured");
	}

	public Query getSliceQuery(String interest, int forwardDepth,
			int backwardDepth) throws SliceQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
