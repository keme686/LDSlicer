/**
 * 
 */
package de.unibonn.iai.eis.slicer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.sparql.core.TriplePath;
import org.apache.jena.sparql.syntax.ElementOptional;
import org.apache.jena.sparql.syntax.ElementPathBlock;
import org.apache.jena.sparql.syntax.ElementVisitorBase;
import org.apache.jena.sparql.syntax.ElementWalker;

/**
 * @author Kemele M. Endris
 *
 */
public class SPARQLUtils {

	/**
	 * Extracts triples from SPARQL CONSTRUCT template
	 * 
	 * @param query
	 * @return
	 */
	public static List<Triple> getConstructTemplateTriples(Query query){
		if(query == null || !query.isConstructType())
			return null;
		return query.getConstructTemplate().getTriples();
	}
	public static List<Triple> getConstructTemplateTriples(String query){
		return getConstructTemplateTriples(toQueryObject(query));
	}
	
	/**
	 * Extracts optional pattern from a SPARQL query
	 * @param query
	 * @return
	 */
	public static List<List<Triple>> getOptionalPattern(Query query){
		final List<List<Triple>> paths= new ArrayList<List<Triple>>();
		ElementWalker.walk(query.getQueryPattern(), new ElementVisitorBase(){
			@Override
			public void visit(ElementOptional el) {
				ElementWalker.walk(el.getOptionalElement(), new ElementVisitorBase(){
					@Override
					public void visit(ElementPathBlock el) {
						ListIterator<TriplePath> lit = el.getPattern().iterator();
						List<Triple> path = new ArrayList<Triple>(); 
						while(lit.hasNext()){
							Triple tp = lit.next().asTriple();
							path.add(tp);
						}
						if(!paths.contains(path))
							paths.add(path);
					}
				});
			}					
		});
		return paths;
	}
	public static List<List<Triple>> getOptionalPattern(String query){
		return getOptionalPattern(toQueryObject(query));
	}
	/**
	 * 
	 * @param query
	 * @return
	 */
	public static Query toQueryObject(String query){
		try{
			Query q = QueryFactory.create(query);
			return q;
		}catch(Exception e){
			e.printStackTrace();
		}
		return QueryFactory.create();
	}

	
	/**
	 *  Extracts only Basic Graph Patterns (BGPs) of a query. 
	 * @param query
	 * @return
	 */
	public static List<Triple> getQueryBasicPatterns(Query query){
		final List<Triple> paths= new ArrayList<Triple>();
		final List<Triple> optpaths= new ArrayList<Triple>();
		ElementWalker.walk(query.getQueryPattern(), new ElementVisitorBase(){
			@Override
			public void visit(ElementPathBlock el) {				
				ListIterator<TriplePath> lit = el.getPattern().iterator();
				while(lit.hasNext()){
					Triple tp = lit.next().asTriple();
					paths.add(tp);
				}
			}	
			
			@Override
			public void visit(ElementOptional el) {
				ElementWalker.walk(el.getOptionalElement(), new ElementVisitorBase(){
					@Override
					public void visit(ElementPathBlock el) {
						ListIterator<TriplePath> lit = el.getPattern().iterator();						
						while(lit.hasNext()){
							Triple tp = lit.next().asTriple();
							optpaths.add(tp);
						}
						
					}
				});
			}
		});
		
		paths.removeAll(optpaths);
		return paths;
	}
	public static List<Triple> getQueryBasicPatterns(String query){
		return getQueryBasicPatterns(toQueryObject(query));
	}
	
	/**
	 *  Extracts all graph patterns of a query BGP + Optional graph patterns
	 * @param query
	 * @return
	 */
	public static List<Triple> getAllQueryPatterns(Query query){
		final List<Triple> paths= new ArrayList<Triple>();
		ElementWalker.walk(query.getQueryPattern(), new ElementVisitorBase(){
			@Override
			public void visit(ElementPathBlock el) {				
				ListIterator<TriplePath> lit = el.getPattern().iterator();
				while(lit.hasNext()){
					Triple tp = lit.next().asTriple();
					paths.add(tp);
				}
			}	
		});		
		return paths;
	}
	public static List<Triple> getAllQueryPatterns(String query){
		return getAllQueryPatterns(toQueryObject(query));
	}
	
	/**
	 *  Checks whether the provided list of triples contains all variable selection (i.e., ?s ?p ?o)
	 *    
	 * @param triples
	 * @return
	 */
	public static boolean containsAllVars(List<Triple> triples){
		if(triples == null || triples.isEmpty())
			return false;
		for(Triple t: triples){
			if(t.getSubject().isVariable() && t.getPredicate().isVariable() && t.getObject().isVariable()){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 *  Checks whether the given query contains all variable selection (i.e., ?s ?p ?o)
	 * @param query
	 * @return
	 */
	public static boolean containsAllVars(Query query){
		List<Triple> triples = getAllQueryPatterns(query);
		if(triples == null || triples.isEmpty())
			return false;
		for(Triple t: triples){
			if(t.getSubject().isVariable() && t.getPredicate().isVariable() && t.getObject().isVariable()){
				return true;
			}
		}
		
		return false;
	}
}
