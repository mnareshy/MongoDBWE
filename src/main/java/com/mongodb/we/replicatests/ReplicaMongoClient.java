package com.mongodb.we.replicatests;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;
import com.mongodb.helpers.Random;
import com.mongodb.we.performance.RandomDocPush;

import sun.applet.Main;

public class ReplicaMongoClient {

	public static void main(String[] args) throws InterruptedException {

			//		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",1111));

		/*client gets into wait state and timeouts a localhost:1111 is not primary. To overcome
		use Arrays.asList(.....), where the server will help to find the primary and client gets connected
		to primary.*/

		//		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",1111)));

		/*	in this case the server client trying to conect is alive but not primary, what if the server 
		clent is trying to connect is down. obviously it cannot connect. Hence the best practice is
		in Arrays.asList(....) give all the servier details.*/
		
		/*
		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",1111),
				new ServerAddress("localhost",2222),
				new ServerAddress("localhost",3333)));*/


		/*incase if you want to ensure the servers mentioned in the list are part of the repicaset, in
		this case it is "rs2" use MongoClientOptions and set requiredReplicaSetName.
		 In case of the driver finds that the servers listed are not part of repica set
		it wont conncet and throws exception.*/

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).requiredReplicaSetName("rs2").build();

		
		MongoClient mongoClient =  new  MongoClient(Arrays.asList(new ServerAddress("localhost",1111),
				new ServerAddress("localhost",2222),
				new ServerAddress("localhost",3333)),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("movies");


		for (int j = 0; j < 100; j++) {

			/*collection.insertOne(	new Document().append("name", "movie"+j)
					.append("revinue",Random.getNumber(100000, 10000000) ));*/
			
			/*In case if the primary goes dow while insertion in progress, client retuns with the Exception.
			there is  a way to retry or proceed further  if you can.
			
			This can be achieved by handling exception.*/
			
			
			try{
				collection.insertOne(	new Document().append("name", "movie"+j)
						.append("revinue",Random.getNumber(100000, 10000000) ));
			}
			catch (MongoException mongoException) {
				
				System.err.println(mongoException.getMessage());
			}
			
			/*When primary goes down , its throws exception but continues further. By the time the next
			record insertion if election process complets then the client reconncets to the new primary.
			
			In the above example its upto you how to handle the exception, in catch block you might want to 
			retry/write the err documents to a file / notify the user about the records not witten...etc
			its fairly upto you.
			*/

		}


		System.out.println(collection.count());


	}

}
