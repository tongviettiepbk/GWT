package com.course.mvp.demo.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl {
	private SimpleEventBus eventBus;
	private PlaceController placeController;
	
	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}

	public PlaceController getPlaceController() {
		return placeController;
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
}
