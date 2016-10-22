package com.mongodb.we;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.helpers.JsonWritter;

public class SortLimitSkipTest {

	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		MongoDatabase mongoDB = mongoClient.getDatabase("test");
		MongoCollection<Document> collection =   mongoDB.getCollection("actors");


		Bson filter = Filters.or(Filters.eq("name", "nagarjuna"),Filters.gte("rating.rank", 1));

		Bson projection = Projections.fields(Projections.include("debutyear","name"),Projections.exclude("_id"));

		//		Bson sort = new Document().append("rating.rank", 1);

		//		Bson sort = new Document().append("rating.rank", -1);

		//		Bson sort = new Document().append("rating.rank", 1).append("debutyear", -1);

		//		Bson sort = Sorts.ascending("rating.rank");

		//		Bson sort = Sorts.descending("rating.rank");

		Bson sort = Sorts.orderBy(Sorts.descending("rating.rank"),Sorts.descending("debutyear"));

		/*List<Document> actors = collection.find(filter)
				.sort(sort)
				.projection(projection)
				.into(new ArrayList<Document>());*/

		/*List<Document> actors = collection.find(filter)
				.projection(projection)
				.sort(sort)
				.limit(1)
				.into(new ArrayList<Document>());*/

		List<Document> actors = collection.find(filter)
				.projection(projection)
				.sort(sort)
				.limit(1)
				.skip(2)
				.into(new ArrayList<Document>());

		for (Iterator iterator = actors.iterator(); iterator.hasNext();) {
			Document document = (Document) iterator.next();

			JsonWritter.print(document);

		}

	}

}
