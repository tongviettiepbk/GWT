package com.course.mvp.demo.client.activities.setting;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SettingPlace extends Place {
	String token = "";
	
	public SettingPlace(){
		token = "Setting";
	}
	
	public String getToken(){
		return this.token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<SettingPlace>{

		@Override
		public SettingPlace getPlace(String token) {
			return new SettingPlace();
		}

		@Override
		public String getToken(SettingPlace place) {
			return place.getToken();
		}
		
	}
}
