package com.mongodb.we.sparkfreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.helpers.JsonWritter;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class SparkFMMongoTest {

	public static void main(String[] args) throws UnknownHostException {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFMMongoTest.class, "/");

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");

		Document actor = collection.find().first();
		JsonWritter.print(actor);

		Spark.get(new Route("/") {
			@Override
			public Object handle(final Request request,
					final Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("sample.ftl");
					helloTemplate.process(actor, writer);
				} catch (Exception e) {
					halt(500);
				}
				return writer;
			}
		});
	}

}
