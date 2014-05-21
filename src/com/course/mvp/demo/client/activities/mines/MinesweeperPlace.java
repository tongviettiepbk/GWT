package com.course.mvp.demo.client.activities.mines;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MinesweeperPlace extends Place{
	String token = "";

	public MinesweeperPlace() {
		super();
		token = "setting";
	}

	public String getToken() {
		return this.token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<MinesweeperPlace> {

		@Override
		public MinesweeperPlace getPlace(String token) {
			return new MinesweeperPlace();
		}

		@Override
		public String getToken(MinesweeperPlace place) {
			return place.getToken();
		}
		
	}
}
