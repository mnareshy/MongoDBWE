package com.mongodb.we;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;

public class InsertTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");

		Document document = new Document().append("name", "chiranjeevi")
				.append("debutyear", 1977)
				.append("movies", Arrays.asList("ydmd","challange","tagore","kidi no 150"))
				.append("rating", new Document()
						.append("score", 99)
						.append("rank", 1));


		JsonWritter.print(document);

		collection.insertOne(document);

		JsonWritter.print(document);

		Document chiranjeevi = new Document().append("name", "chiranjeevi")
				.append("debutyear", 1977)
				.append("movies", Arrays.asList("ydmd","challange","tagore","kidi no 150"))
				.append("rating", new Document()
						.append("score", 99)
						.append("rank", 1));
		Document nagarjuna = new Document().append("name", "nagarjuna")
				.append("debutyear", 1977)
				.append("movies", Arrays.asList("vikram","ninne pelladuta","sgcn","oopiri"))
				.append("rating", new Document()
						.append("score", 99)
						.append("rank", 2));

		collection.insertMany(Arrays.asList(chiranjeevi,nagarjuna));

		JsonWritter.print(chiranjeevi);
		JsonWritter.print(nagarjuna);

	}

}
