/**
 * 
 */
package de.unibonn.iai.eis.slicer;


import de.unibonn.iai.eis.slicer.endpoint.EndpointSlicer;
import de.unibonn.iai.eis.slicer.model.SinkType;

/**
 * @author Kemele M. Endris
 *
 */
public class Main {

	String qury = "CONSTRUCT {"
			+ "   ?s     ?p      ?o."
			+ "   ?o    ?pr     ?or."
			+ "   ?or   ?pre   ?ore."
			+ "   }"
			+ "WHERE {    "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."
			+ "   ?s ?p ?o."
			+ "   OPTIONAL {"
			+ "        ?o ?pr ?or. "
			+ "        Optional{ "
			+ "              ?or ?pre ?ore."
			+ "         }"
			+ "      }"
			+ "}";
	
	static String interest = "CONSTRUCT {"
			+ "   ?s     ?p      ?o."
			+ "   ?x 	 ?y 	 ?s "			
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."
			+ "   ?s ?p ?o."
			+ "   OPTIONAL{ ?x ?y ?s}"		
			+ "}";
	
	static String interest2 = "CONSTRUCT {"
			+ "   ?s     ?p      ?o. "
			+ "   ?x 	?y 		?s. "
			+ "   ?x 	?z 		?a. "
			+ "   ?o 	?b 		?c."			
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."
			+ "   ?s ?p ?o. "
			+ "   OPTIONAL { "
			+ "		?x		?y 		?s."
			+ "		?x 		?z 		?a."
			+ "   }"
			+ "	  OPTIONAL{ "
			+ "    ?o 		?b      ?c"
			+ "   }"		
			+ "}";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int offset = 5000;
		int depth = 1;
		String inUri="http://dbpedia.org/sparql";
		String outputFormat = "NT";
		if(args == null || args.length < 3){
			System.out.println("Format: ");
			return;
		}
		inUri = args[0];
		String sliceQuery= args[1];
		String outputName=args[2];		
		if(args.length > 3){
			try{
				offset = Integer.parseInt(args[3]);
			}catch(Exception e){
				e.printStackTrace();
				offset = 5000;
			}
		}
		if(args.length > 4)
			outputFormat = args[4];
		SinkType sinkType = SinkType.FILE;
		if(outputFormat.equalsIgnoreCase("NT"))
			sinkType =SinkType.FILE_NT;
		else if(outputFormat.equalsIgnoreCase("TTL")){
			sinkType =SinkType.FILE_TTL;
		}else if(outputFormat.equalsIgnoreCase("RDF/XML")){
			sinkType =SinkType.FILE_RDF_XML;
		}else if(outputFormat.equalsIgnoreCase("RDF/JSON")){
			sinkType =SinkType.FILE_RDF_JSON;
		}else if(outputFormat.equalsIgnoreCase("JSONLD")){
			sinkType =SinkType.FILE_JSONLD;
		}
		
		Slicer slicer = new EndpointSlicer(offset);
		try{
			slicer.process(inUri, sliceQuery, depth, outputName, sinkType);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
}
