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

public class UpdateReplaceUpserTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");


		//		collection.updateOne(Filters.eq("name", "chiranjeep"),new Document("$set",new Document("name", "chiranjeevi")));

		//		collection.replaceOne(Filters.eq("name", "chiranjeevi"),new Document("name", "chiranjeevi"));	

		/*collection.updateOne(Filters.eq("name", "mahesh babu"),new Document("$set",
				new Document("name", "mahesh babu")
				.append("debutyear", 2000)
				.append("movies", Arrays.asList("murari","pokiri","svbc","srimantudu"))
				.append("rating", new Document()
						.append("score", 99)
						.append("rank", 1))),new UpdateOptions().upsert(true));*/

		//collection.updateOne(Filters.eq("name", "mahesh babu"),Updates.set("debutyear", 2002));

		/*collection.updateOne(Filters.eq("name", "mahesh babu"),
				Updates.combine(Updates.set("debutyear", 2002),Updates.unset("rating.score")));*/

		collection.updateMany(Filters.eq("rating.rank",1),
				Updates.combine(Updates.set("rating.score", 99),Updates.set("updated",true)));


		List<Document> actors = collection.find().into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
