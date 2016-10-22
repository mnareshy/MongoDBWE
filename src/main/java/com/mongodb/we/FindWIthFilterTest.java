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
import com.mongodb.helpers.JsonWritter;

public class FindWIthFilterTest {

	public static void main(String[] args) {
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");
		
//		Document filter = new Document().append("name", "nagarjuna");
		
//		Document filter = new Document().append("rating.rank", new Document().append("$eq", 1));
		
//		Bson filter = Filters.eq("name", "nagarjuna");
		
//		Bson filter = Filters.and(Filters.eq("name", "nagarjuna"),Filters.gte("rating.rank", 1));
		
		Bson filter = Filters.or(Filters.eq("name", "nagarjuna"),Filters.gte("rating.rank", 1));
		
		
		

		List<Document> actors = collection.find(filter).into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
