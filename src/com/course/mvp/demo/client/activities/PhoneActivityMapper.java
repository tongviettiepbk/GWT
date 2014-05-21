package com.course.mvp.demo.client.activities;

import com.course.mvp.demo.client.activities.home.HomeActivity;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.client.activities.mines.MinesweeperActivity;
import com.course.mvp.demo.client.activities.mines.MinesweeperPlace;
import com.course.mvp.demo.client.activities.setting.SettingActivity;
import com.course.mvp.demo.client.activities.setting.SettingPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PhoneActivityMapper implements ActivityMapper {
	
	private ClientFactoryImpl clientFactory;

	public PhoneActivityMapper(ClientFactoryImpl clientFactory) {
		this.clientFactory = clientFactory;
	}
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace)
			return new HomeActivity(clientFactory, place);
		else if (place instanceof MinesweeperPlace)
			return new MinesweeperActivity(clientFactory, place);
		else if (place instanceof SettingPlace)
			return new SettingActivity(clientFactory, place);
		return null;
	}

}
