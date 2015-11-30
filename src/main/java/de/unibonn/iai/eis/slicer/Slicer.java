/**
 * 
 */
package de.unibonn.iai.eis.slicer;

import de.unibonn.iai.eis.slicer.exception.SlicerException;
import de.unibonn.iai.eis.slicer.model.SinkType;

/**
 * @author Kemele M. Endris
 *
 */
public interface Slicer {

	public void process(String inUri, String query, int depth, String outUri, SinkType sinkType) throws SlicerException;
}
