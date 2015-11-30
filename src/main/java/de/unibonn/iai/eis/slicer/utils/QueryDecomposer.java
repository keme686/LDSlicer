/**
 * 
 */
package de.unibonn.iai.eis.slicer.utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

/**
 * @author Kemele M. Endris
 *
 */
public class QueryDecomposer {
	
	/**
	 * 
	 * @param model
	 * @param graph
	 * @param toInsert
	 * @return
	 */
	public static StringBuilder toUpdateString(Model model, String graph, boolean toInsert){
		StringBuilder queryBuff = new StringBuilder(SPARQLExecutor.prefixes() +"\n  " + (toInsert? " INSERT DATA ": " DELETE DATA ") + " { GRAPH  <" + graph+ "> { " );
		StmtIterator iterator = model.listStatements();
		while(iterator.hasNext()){
			Statement stmt = iterator.nextStatement();
			Resource s = stmt.getSubject();
			queryBuff.append("  <"+ s.toString() +">  ");
			Property p = stmt.getPredicate();
			queryBuff.append(" <" + p.toString() + "> ");
			RDFNode o = stmt.getObject();
			if(o.isURIResource() ) {
				queryBuff.append(" <"+o.toString()+"> ");
			} else if(o.isAnon()) {
				queryBuff.append(o);
			} else {
				String l = o.asLiteral().getString();
				l = l.replace("\\", "\\\\");
				l=l.replaceAll("\n", "\\\\n");
				l = l.replaceAll("\"", "\\\\\"");
				
				if(o.asLiteral().getDatatypeURI()!=null )
					queryBuff.append( "  \""+ l +"\"^^<" + o.asLiteral().getDatatypeURI() + ">  ");
				else 
					queryBuff.append( "  \""+ l +"\" ");
			}
			queryBuff.append(" . \n");
		}
		queryBuff.append(" } }");
		return queryBuff;
	}
	/**
	 * 
	 * @param model
	 * @param toInsert
	 * @return
	 */
	public static StringBuffer toUpdateString(Model model, boolean toInsert){
		StringBuffer builder = new StringBuffer(SPARQLExecutor.prefixes() +" " + (toInsert? " INSERT DATA ": " DELETE DATA ") + " {  " );
		StmtIterator iterator = model.listStatements();
		while(iterator.hasNext()){
			Statement stmt = iterator.nextStatement();
			Resource s = stmt.getSubject();
			builder.append("  <"+ s.toString() +">  ");
			Property p = stmt.getPredicate();
			builder.append(" <" + p.toString() + "> ");
			RDFNode o = stmt.getObject();
			if(o.isURIResource() ) {
				builder.append(" <"+o.toString()+"> ");
			} else if(o.isAnon()) {
				builder.append(o);
			} else {
				String l = o.asLiteral().getString();
				
				l = l.replace("\\", "\\\\");
				l=l.replaceAll("\n", "\\\\n");
				l = l.replaceAll("\"", "\\\\\"");
				if(o.asLiteral().getDatatypeURI()!=null)
					builder.append( "  \""+ l +"\"^^<" + o.asLiteral().getDatatypeURI() + ">  ");
				else 
					builder.append( "  \""+ l +"\" ");
			}
			builder.append(" . \n");
		}
		builder.append(" } ");
		return builder;
	}	
	
	
}
