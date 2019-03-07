package br.com.aceleradevsp.squad2.mapfood.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MapFoodUtils {
	  public static int getRandomNumber(int min, int max) {

			if (min >= max) {
				throw new IllegalArgumentException("max must be greater than min");
			}

			Random r = new Random();
			return r.nextInt((max - min) + 1) + min;
		}
	  
	  /*public static  LocalDateTime getRandonDate( LocalDate date) {; 
	  
		  
		  return null;
	  }*/
}
