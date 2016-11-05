package com.mongodb.we.performance;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.helpers.JsonWritter;

public class IndexTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");

		Document actor = null;

		long startTime = System.currentTimeMillis();

		actor = collection.find().first();

		long endTime = System.currentTimeMillis();


		JsonWritter.print(actor);

		System.out.println("Find first in COllection Time : "+ (endTime - startTime)  +"  milli sec");


		startTime = System.currentTimeMillis();

		Bson filter = Filters.eq("debutyear", 2012);

		List<Document> actors = collection.find(filter).into(new ArrayList<Document>());

		endTime = System.currentTimeMillis();

		/*				
		 for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
				Document document = (Document) iterator.next();

				JsonWritter.print(document);

			}

		JsonWritter.print(actor);*/

		System.out.println("Find with filter (debutyear eq 2012) in COllection Time : "+ (endTime - startTime)  +"  milli sec");

		//		create index on debutyear and rerun the program to see the performance improvement

		//create index using java driver

		Bson index = new Document("debutyear",1);

		startTime = System.currentTimeMillis();

		collection.createIndex(index);

		//		there are methods available for discovering and deleting indexes
		//		collection.dropIndex(index);
				collection.listIndexes();

		endTime = System.currentTimeMillis();

		System.out.println("Creating index on debutyear  : "+ (endTime - startTime)  +"  milli sec");

		startTime = System.currentTimeMillis();

		List<Document> actorsIndexed = collection.find(filter).into(new ArrayList<Document>());

		endTime = System.currentTimeMillis();


		System.out.println("Indexed - > Find with filter (debutyear eq 2012) in COllection Time : "+ (endTime - startTime)  +"  milli sec");


	}

}
