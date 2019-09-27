/**
 * 
 */
package de.unibonn.iai.eis.slicer;


import de.unibonn.iai.eis.slicer.endpoint.EndpointSlicer;
// import de.unibonn.iai.eis.slicer.file.RDFFileSlicer;
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
	
	
	static String soccinterest_rev = "CONSTRUCT {"
			+ "   ?x 	?y 		?s. "
			+ "   ?x 	?z 		?a. "
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."		
			+ "	  ?x		?y 		?s."
			+ "	  ?x 		?z 		?a."					
			+ "}";
	static String soccerOneHopinterest = "CONSTRUCT {"
			+ "   ?o ?pr ?or. "			
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."
			+ "   ?s ?p ?o."		
			+ "   ?o ?pr ?or. "
			+ "}";
	
	static String soccerinterest = "CONSTRUCT {"
			+ "   ?s ?p ?o. "			
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/SoccerPlayer>."
			+ "   ?s ?p ?o."		
			+ "   OPTIONAL {"
			+ "        ?o ?pr ?or. "
			+ "        Optional{ "
			+ "              ?or ?pre ?ore."
			+ "         }"
			+ "      }"
			+ "}";
	static String filminterest = "CONSTRUCT {"
			+ "   ?s     ?p      ?o."
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Film>."
			+ "   ?s ?p ?o."
			+ "}";

	static String politicsinterest = "CONSTRUCT {"
			+ "   ?s     ?p      ?o."
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Politician>."
			+ "   ?s ?p ?o."
			+ "}";
	
	
	
	
	static String buildingInterest = "CONSTRUCT {"
			+ "    ?s     ?p      ?o."
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Building>."
			+ "   ?s ?p ?o"	
			+ "}";
			
	static String restaurantInterest = "CONSTRUCT {"
			+ "    ?s     ?p      ?o."
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Restaurant>."
			+ "   ?s ?p ?o"	
			+ "}";
	static String restaurantInterestAsTarget = "CONSTRUCT " 
			+ " WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Restaurant>."
			+ "   ?s "	
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
	
	
	
	
	static String politicsexampleinterest = "CONSTRUCT {"
			+ " ?s   a           <http://dbpedia.org/ontology/Politician>."
			+ " ?s    <http://xmlns.com/foaf/0.1/name>      ?name."
			+ " ?s    <http://dbpedia.org/ontology/abstract>    ?abstract."
			+ " ?s    <http://xmlns.com/foaf/0.1/depiction>  ?depiction."
			+ " ?s     <http://dbpedia.org/property/office>   ?office. "
			+ " ?s     <http://dbpedia.org/property/party>    ?party."
			+ " ?s     <http://dbpedia.org/ontology/nationality>  ?nationality."
			+ "   }"
			+ "WHERE { "
			+ "   ?s a <http://dbpedia.org/ontology/Politician>."
			+ " ?s    <http://xmlns.com/foaf/0.1/name>      ?name."
			+ " ?s    <http://dbpedia.org/ontology/abstract>    ?abstract."
			+ " ?s     <http://dbpedia.org/property/office>   ?office. "
			+ " ?s     <http://dbpedia.org/property/party>    ?party."
			+ " ?s     <http://dbpedia.org/ontology/nationality>  ?nationality."
			+ "OPTIONAL{?s    <http://xmlns.com/foaf/0.1/depiction>  ?depiction.}"
			+ "}";
	
	static String exampleQuery = "CONSTRUCT {  "
			+ "?s    a               <http://dbpedia.org/ontology/Restaurant>. "
			+ "?s    <http://www.w3.org/2000/01/rdf-schema#label>      ?label."
			+ "?s    <http://www.georss.org/georss/point>    ?point."
			+ "?s    <http://dbpedia.org/ontology/abstract>    ?abstract."
			+ "?s    <http://dbpedia.org/property/rating>      ?rating."
			+ "?s    <http://xmlns.com/foaf/0.1/depiction>  ?depiction."
			+ "?s    <http://dbpedia.org/property/headOfChief>     ?headOfChief"
			+ "}"
			
			+ "WHERE{"
			+ "?s    a               <http://dbpedia.org/ontology/Restaurant>. "
			+ "?s    <http://www.w3.org/2000/01/rdf-schema#label>      ?label."			
			+ "?s    <http://dbpedia.org/ontology/abstract>    ?abstract."
			//+ "OPTIONAL {"
			+ "?s    <http://dbpedia.org/property/rating>      ?rating."
			//+ "}"
			//+ "OPTIONAL {"
			+ "?s    <http://xmlns.com/foaf/0.1/depiction>  ?depiction."
			//+ "}"
			//+ "OPTIONAL {"
			+ "?s    <http://www.georss.org/georss/point>    ?point."
			//+ "}"
			+ "OPTIONAL {"
			+ "?s    <http://dbpedia.org/property/headOfChief>     ?headOfChief"
			+ "}}";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int offset = 5000;
		int depth = 1;
		// String inUri= "" // "https://query.wikidata.org/bigdata/namespace/wdq/sparql";//"http://live.dbpedia.org/sparql"; //  "restaurants.nt";//"http://butterbur17.iai.uni-bonn.de:8890/sparql"; //
		String outputFormat = "NT";
		// String outputName= "output" //"E:\\WDSlices\\WD_film-slice-truthy.nt";
		String sliceQuery = film_wikidata; 
		if(args == null || args.length < 3){
			System.out.println("Format: Main <sparql-endpoint>  <Slice-Query-str>  <out-file-name.rdf> <OutputFormat-NT,TTL,RDF/XML,RDF/JSON,JSONLD>");
			return;
		}
		String inUri = args[0];
	
		sliceQuery= args[1];
		String outputName=args[2];		
		System.out.println(sliceQuery);	
		/*if(args.length > 3){
			try{
				offset = Integer.parseInt(args[3]);
			}catch(Exception e){
				e.printStackTrace();
				offset = 5000;
			}
		}
		*/
		if(args.length > 3)
			outputFormat = args[3];
	
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
		//Slicer slicer = new RDFFileSlicer(offset);
		try{
			System.out.println(inUri + " " + outputName);
			slicer.process(inUri, sliceQuery, depth, outputName, sinkType);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	static String soccer_wikidata = "CONSTRUCT {"
			+ "   ?s     ?p      ?o."					
			+ "   }"
			+ "WHERE { "
			+ "   ?s <http://www.wikidata.org/prop/direct/P106> <http://www.wikidata.org/entity/Q937857>."
			+ "   ?s ?p ?o."
			+ "}";
	
	//politician-= Q82955
	static String film_wikidata = "CONSTRUCT {"
			+ "   ?s     ?p                                             ?o."
			//+ "   ?o     ?e                                             ?f."
			+ "   ?o     ?a                                             ?b."
			//+ "   ?f     ?k                                             ?l"
			+ "   }"
			+ "WHERE { "
			+ "   ?s <http://www.wikidata.org/prop/direct/P31> <http://www.wikidata.org/entity/Q11424>."
			+ "   ?s ?p ?o."
			+ " OPTIONAL{ "
			+ " 	 ?d    <http://wikiba.se/ontology#directClaim>        ?p."
			+ "      ?o    ?a   ?b"
			+ "   }"
			/*+ " OPTIONAL{"
			+ "     ?c  <http://wikiba.se/ontology#claim>       ?p."
			+ "     ?o  ?e                                      ?f."
			+ "      OPTIONAL{ "
			+ "        ?j      <http://wikiba.se/ontology#statementValue>    ?e."
			+ "        ?f      ?k                                            ?l"
			+ "      }"
			+ "  }"*/
			+ "}";
	
	static String film_full_wikidata = "CONSTRUCT{"
			+ "   ?s ?p  ?o."
			+ "   ?o ?a  ?b."
			+ "   ?o ?e  ?f."
			+ "   ?f ?h  ?i."
			+ "   ?f ?k  ?l."
			+ "   ?f ?m  ?n."
			+ "   ?n ?r  ?t."
			+ "}"
			+ "WHERE {"
			+ "    ?s <http://www.wikidata.org/prop/direct/P31> <http://www.wikidata.org/entity/Q11424>."
			+ "    ?s ?p ?o."
			+ " OPTIONAL{ "
			+ " 	 ?d    <http://wikiba.se/ontology#directClaim>    ?p."
			+ "      ?o    ?a                                         ?b"
			+ "   }"
			+ " OPTIONAL{"
			+ "     ?c  <http://wikiba.se/ontology#claim>       ?p."
			+ "     ?o  ?e                                      ?f."
			+ "      OPTIONAL{"
			+ "         ?g      <http://wikiba.se/ontology#qualifierValue>    ?e."
			+ "         ?f      ?h                                            ?i"
			+ "      }"
			+ "      OPTIONAL{ "
			+ "        ?j      <http://wikiba.se/ontology#statementValue>    ?e."
			+ "        ?f      ?k                                            ?l"
			+ "      }"
			+ "      OPTIONAL{"
			+ "         ?o      <http://www.w3.org/ns/prov#wasDerivedFrom>    ?f."
			+ "         ?f      ?m                                            ?n."
			+ "         OPTIONAL{"
			+ "           ?q      <http://wikiba.se/ontology#referenceValue>    ?m."
			+ "           ?n      ?r                                            ?t "
			+ "         }  "
			+ "      }"
			+ "   } "
			+ "}";
	
	static String full_wikidata_query = "CONSTRUCT{"
			+ "   ?s ?p  ?o."
			+ "   ?o ?a  ?b."
			+ "   ?o ?e  ?f."
			+ "   ?f ?h  ?i."
			+ "   ?f ?k  ?l."
			+ "   ?f ?m  ?n."
			+ "   ?n ?r  ?t."
			+ "}"
			+ "WHERE {"
			+ "    ?s <http://www.wikidata.org/prop/direct/P31> <http://www.wikidata.org/entity/Q11424>."
			+ "    ?s ?p ?o."
			+ " OPTIONAL{ "
			+ " 	 ?d    <http://wikiba.se/ontology#directClaim>    ?p."
			+ "       ?o    ?a                                         ?b"
			+ "   }"
			+ " OPTIONAL{"
			+ "     ?c  <http://wikiba.se/ontology#claim>       ?p."
			+ "     ?o  ?e                                      ?f."
			+ "      OPTIONAL{"
			+ "         ?g      <http://wikiba.se/ontology#qualifierValue>    ?e."
			+ "         ?f      ?h                                            ?i"
			+ "      }"
			+ "      OPTIONAL{ "
			+ "        ?j      <http://wikiba.se/ontology#statementValue>    ?e."
			+ "        ?f      ?k                                            ?l"
			+ "      }"
			+ "      OPTIONAL{"
			+ "         ?o      <http://www.w3.org/ns/prov#wasDerivedFrom>    ?f."
			+ "         ?f      ?m                                            ?n."
			+ "         OPTIONAL{"
			+ "           ?q      <http://wikiba.se/ontology#referenceValue>    ?m."
			+ "           ?n      ?r                                            ?t "
			+ "         }  "
			+ "      }"
			+ "   } "
			+ "}";
	
	
}
