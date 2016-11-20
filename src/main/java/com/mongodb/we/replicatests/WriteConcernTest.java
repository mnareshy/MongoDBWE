package com.mongodb.we.replicatests;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;
import com.mongodb.helpers.Random;
import com.mongodb.we.performance.RandomDocPush;

import sun.applet.Main;

public class WriteConcernTest {

	public static void main(String[] args) {



		WriteConcern writeConcern = WriteConcern.ACKNOWLEDGED;


		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12)
				.writeConcern(WriteConcern.MAJORITY).writeConcern(WriteConcern.JOURNALED)
				.maxWaitTime(1000)
				.requiredReplicaSetName("rs2").build();
		
		/*you will surely see the lag in execution time as the WriteConcern.MAJORITY,		
		WriteConcern.JOURNALED and the wait time = 1000 is set.*/

		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",1111),
				new ServerAddress("localhost",2222),
				new ServerAddress("localhost",3333)),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("movies");
		


		for (int j = 0; j < 100; j++) {



			collection.insertOne(	new Document().append("name", "movie"+j)
					.append("revinue",Random.getNumber(100000, 10000000) ));


		}


		System.out.println(collection.count());


	}

}
