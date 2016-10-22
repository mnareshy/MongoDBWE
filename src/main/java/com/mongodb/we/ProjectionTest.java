package com.mongodb.we;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.helpers.JsonWritter;

public class ProjectionTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");


		Bson filter = Filters.or(Filters.eq("name", "nagarjuna"),Filters.gte("rating.rank", 1));

//		Bson projection = new Document("debutyear" , 1);
		
//		Bson projection = new Document("debutyear" , 1).append("_id", 0);
		
//		Bson projection = new Document("debutyear" , 1).append("name", 1).append("_id", 0);
		
//		Bson projection = new Document("debutyear" , 1).append("name", 1).append("_id", 0);


//		Bson projection = Projections.include("debutyear","name");
		
		
		Bson projection = Projections.fields(Projections.include("debutyear","name"),Projections.exclude("_id"));
				
				
		List<Document> actors = collection.find(filter)
										  .projection(projection)
										  .into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
