package com.mongodb.helpers;

public class Random {

	static java.util.Random random = new java.util.Random();

	public static String getRandomString(int lenght) {
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder randomString = new StringBuilder();
		int index = 0 ;

		while (randomString.length() <= lenght) {

			index = random.nextInt(alphabets.length());

			randomString.append(alphabets.charAt(index));

		} 



		return randomString.toString();
	}

	public static int getNumber(int lowBound, int highBound){

		int year = 0;

		while (true) {


			year = random.nextInt(highBound);

			if(year > lowBound)
				break;

		}

		return year;

	}




	public static void main(String[] args) {

		System.out.println(getRandomString(17));
		System.out.println(getNumber(1977, 2016));



	}


}
