package com.mongodb.we.aggregate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.helpers.JsonWritter;

public class AggregateTest {
	
	
	public static void main(String[] args) {
	
		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("movies");
		
		
		//Direct parsing of the query - results unpredicted in this example - TBD
		/*List<Document> pipeline = Arrays.asList( Document.parse("{\"$match\":"
				+ "{\"area\":\"andhra\"}},{\"$group\":{\"_id\":\"$director\",\"collection\":"
				+ "{\"$sum\":\"$collection\"},\"movies\":{\"$addToSet\":\"$name\"}}}"));*/
		
		
		//Direct parsing of the query
		

		
		/*List<Document> pipeline = Arrays.asList(new Document("$match",new Document("area","andhra")),
				
				new Document("$group",new Document("_id","$director")
				.append("collection", new Document("$sum","$collection"))
				.append("movies", new Document("$addToSet","$name")) ));
		
		*/
		
		
		//		using Builders
		
		List<Bson> pipeline = Arrays.asList(Aggregates.match(Filters.eq("area","andhra")),
										Aggregates.group("$director",
												Accumulators.sum("collection", "$collection"),
											    Accumulators.addToSet("movies", "$name")));

		
		List<Document> actors = collection.aggregate(pipeline).into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);
			

		}
	}

}
