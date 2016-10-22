package com.mongodb.we;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import sun.applet.Main;

public class SimpleMongoClient {

	public static void main(String[] args) {

//		MongoClient mongoClient =  new  MongoClient(new ServerAddress());
		
//		MongoClient mongoClient =  new  MongoClient();
		
//		MongoClient mongoClient =  new  MongoClient("localhost",27017);
		
//		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017));
		
//		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",27017)));
		
//		MongoClient mongoClient =  new  MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		
//		MongoClientOptions clientOptions = MongoClientOptions.builder().build();
		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);
		
		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("movies");
		
	    System.out.println(collection.count());
	    
	    
	}

}
