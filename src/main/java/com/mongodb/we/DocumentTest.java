package com.mongodb.we;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.helpers.JsonWritter;

public class DocumentTest {

	public static void main(String[] args) {

		Document documnet = new Document().append("title", "Shiridi Sai")
				.append("year", 1997)
				.append("actors", Arrays.asList("Chiranjeevi","balakrishna","sridevi","kota"))
				.append("rating", new Document()
						.append("score", 33)
						.append("rank", 7));


		JsonWritter.print(documnet);

	}

}
