/**
 * 
 */
package de.unibonn.iai.eis.slicer.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unibonn.iai.eis.slicer.Slicer;
import de.unibonn.iai.eis.slicer.exception.SlicerException;
import de.unibonn.iai.eis.slicer.model.SinkType;
import de.unibonn.iai.eis.slicer.utils.QueryDecomposer;
import de.unibonn.iai.eis.slicer.utils.SPARQLExecutor;

/**
 * @author Kemele M. Endris
 *
 */
public class EndpointSlicer implements Slicer {

	private static Logger log = LoggerFactory.getLogger(EndpointSlicer.class);
	private int offset = 1000;

	public EndpointSlicer(int offset) {
		this.offset = offset;
	}

	public void process(String inUri, String query, int depth, String outUri,
			SinkType sinkType) throws SlicerException {
		validate(inUri, query, depth, outUri, sinkType);
		// Query sliceQuery = getSliceQuery(query, depth);
		// Query sliceQuery = SPARQLUtils.toQueryObject(query);

		execSlicing(inUri, query, outUri, sinkType);
	}

	private void execSlicing(String endpoint, String query, String outUri, SinkType sinkType) throws SlicerException{
		int off = 0;
		Object sink = getSinkObject(outUri, sinkType);
		
		while(true){
			System.out.println("limit=" + offset + "  offset=" + off);
			Query sliceQuery = getNextSliceQuery(query, offset, off);
			System.out.println(sliceQuery +" " + sinkType);
			Model m = SPARQLExecutor.executeConstruct(endpoint, sliceQuery);
			if(m==null || m.isEmpty()){
                               System.out.println("Empty result returned by the endpoint");
				break;
			}
			
			if(sinkType == SinkType.ENDPOINT || sinkType == SinkType.TDB){
				boolean status = SPARQLExecutor.executeUpdate(sink, QueryDecomposer.toUpdateString(m, true).toString() );
				if(status){
					log.info(m.size() +  " results inserted to sink");
				}
			}else{
				try{

				PrintWriter out = new PrintWriter(new FileOutputStream(new File(outUri), true));
					//PrintStream out = new PrintStream(new File(outUri), "UTF-8");
				 // "RDF/XML-ABBREV", and "N3". The default value, represented by null is "RDF/XML".
				if(sinkType == SinkType.FILE_NT )
					m.write(out, "N-TRIPLE");
				else if(sinkType == SinkType.FILE_TTL)
					m.write(out, "TURTLE");
				else if(sinkType == SinkType.FILE_RDF_XML || sinkType == SinkType.FILE)
					m.write(out, "RDF/XML");
				else if(sinkType == SinkType.FILE_RDF_JSON)
					m.write(out, "RDF/JSON");
				}catch(Exception e){
					e.printStackTrace();					
					throw new SlicerException(e.getMessage());
				}				
				log.info(m.size() +  " results inserted to file");
				System.out.println(" results inserted to file");
			}
			off +=offset;
		}
	}

	private Query getNextSliceQuery(String query, int limit, int offset) {
		Query sliceQuery = SPARQLExecutor.toQueryObject(query);
		sliceQuery.setLimit(limit);
		sliceQuery.setOffset(offset);

		return sliceQuery;
	}

	/**
	 * validates the given parameters
	 * 
	 * @param inUri
	 *            - Source SPARQL ENDPOINT URL
	 * @param query
	 *            - Interest query to be used for slicing
	 * @param depth
	 *            - depth of the graph from the given query
	 * @param outUri
	 *            - output uri/name for the sliced data
	 * @param sinkType
	 *            - type of sink/target the slice will be written (determines
	 *            how the data is propagated to <link>outUri</link> )
	 * @throws SlicerException
	 *             - throws exception if one of the requirements are not met
	 */
	private void validate(String inUri, String query, int depth, String outUri,
			SinkType sinkType) throws SlicerException {
		if (!isAlive(inUri)) {
			throw new SlicerException(
					"Source Endpoint is not available. Please make sure you specify the correct SPARQL endpoint URL!");
		}
		if (!SPARQLExecutor.toQueryObject(query).isConstructType()) {
			throw new SlicerException(
					"Invalid \"Interest Query\". Please make sure your interest query is a valid SPARQL CONSTRUCT query");
		}
		if (sinkType == SinkType.ENDPOINT && !isAlive(outUri)) {
			throw new SlicerException(
					"Sink endpoint is not available. Please make sure you specify the correct SPARQL endpoint URL!");
		}
		if (sinkType == SinkType.ENDPOINT && !isWriteEnabled(outUri)) {
			throw new SlicerException(
					"I cannot update Sink endpoint! Please make sure you specify SPARQL update endpoint URL!");
		}
	}

	/**
	 * Checks if the specified SPARQL endpoint url alive and contains triples
	 * 
	 * @param url
	 *            SPARQL endpoint URL
	 * @return true - if the endpoint is alive and contains triples false -
	 *         otherwise
	 */
	private boolean isAlive(String url) {
		String query = "ASK WHERE{?s ?p ?o}";
		return SPARQLExecutor.executeAsk(url, query);
	}

	/**
	 * checks if the specified SPARQL endpoint is update enabled or not
	 * 
	 * @param url
	 *            - SPARQL endpoint URL
	 * @return true - if the update is enabled false - otherwise
	 */
	private boolean isWriteEnabled(String url) {
		String query = "INSERT DATA {}";
		return SPARQLExecutor.executeUpdate(url, query);
	}

	public Query getSliceQuery(String query, int depth) {
		Query sliceQuery = SPARQLExecutor.toQueryObject(query);
		sliceQuery.setLimit(offset);
		// sliceQuery.setOffset(offset);

		return sliceQuery;
	}

	private Object getSinkObject(String uri, SinkType sinkType) {
		if (sinkType == SinkType.ENDPOINT) {
			return uri;
		} else if (sinkType == SinkType.FILE) {
			return null;
		} else if (sinkType == SinkType.TDB) {
			Dataset ds = TDBFactory.createDataset(uri);
			return ds;
		} else
			return null;
	}

}
