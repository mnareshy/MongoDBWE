package com.mongodb.we;

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
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.helpers.JsonWritter;

public class DeleteTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");



		collection.updateOne(Filters.eq("name", "qqqq"),new Document("$set",
				new Document("name", "mahesh babu")
				.append("debutyear", 2000)
				.append("movies", Arrays.asList("murari","pokiri","svbc","srimantudu"))
				.append("rating", new Document()
						.append("score", 99)
						.append("rank", 1))),new UpdateOptions().upsert(true));



//		collection.deleteOne(new Document("name", "mahesh babu"));
		
		collection.deleteMany(Filters.eq("name", "mahesh babu"));

		
		List<Document> actors = collection.find().into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
