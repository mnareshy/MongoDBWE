package com.mongodb.we.replicatests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;
import com.mongodb.helpers.Random;
import com.mongodb.we.performance.RandomDocPush;

import sun.applet.Main;

public class ReadPreferanceTest {

	public static void main(String[] args) {





		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12)
				.maxWaitTime(1000)
				.readPreference(ReadPreference.primaryPreferred())
				.requiredReplicaSetName("rs2").build();
		

		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",1111),
				new ServerAddress("localhost",2222),
				new ServerAddress("localhost",3333)),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> movies =   mongoDB.getCollection("movies");
		


		List<Document> names = movies.find().into(new ArrayList<Document>());

		for (Iterator iterator = names.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
