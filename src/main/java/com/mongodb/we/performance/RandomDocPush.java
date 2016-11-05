package com.mongodb.we.performance;

import java.util.Arrays;
import java.util.Random;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;

import static com.mongodb.helpers.Random.*;

public class RandomDocPush {

	public static void main(String[] args) {


		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");

		for (int i = 0; i < 1000000; i++) {



			Document document = new Document().append("name", getRandomString(17))
					.append("debutyear", getNumber(1970, 2016))
					.append("movies", Arrays.asList(getRandomString(7),getRandomString(9),getRandomString(12),getRandomString(17)))
					.append("rating",Arrays.asList( new Document()
							.append("ratingboard", "national")
							.append("score", getNumber(10,100))
							.append("rank", getNumber(1,10)),
							new Document()
							.append("ratingboard", "international")
							.append("score", getNumber(10,100))
							.append("rank", getNumber(1,10)),
							new Document()
							.append("ratingboard", "state")
							.append("score", getNumber(10,100))
							.append("rank", getNumber(1,10)),
							new Document()
							.append("ratingboard", "imdb")
							.append("score", getNumber(10,100))
							.append("rank", getNumber(1,10))));


					//			JsonWritter.print(document);

					collection.insertOne(document);

					//			JsonWritter.print(document);

		}
	}



}
