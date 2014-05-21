package com.course.mvp.demo.client.activities.mines;

import com.course.mvp.demo.client.GreetingService;
import com.course.mvp.demo.client.GreetingServiceAsync;
import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.home.HomeActivity;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class MinesweeperActivity extends MGWTAbstractActivity {
	private GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final ClientFactoryImpl clientFactory;
	private Place place;
	private  HomeActivity home;

	public MinesweeperActivity(ClientFactoryImpl clientFactory, Place place) {
		this.clientFactory = clientFactory;
		this.setPlace(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		int h = Window.getClientHeight();
		int w = Window.getClientWidth();
		int x = home.Size;
		MinesweeperViewGwtImpl view = new MinesweeperViewGwtImpl();
		Mines mines = new Mines(w,h-x,x);
		view.getCenterLayout().add(mines.getCanvas());
		panel.setWidget(view);
		view.getBackButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		});
		

	}

	public GreetingServiceAsync getGreetingService() {
		return greetingService;
	}

	public void setGreetingService(GreetingServiceAsync greetingService) {
		this.greetingService = greetingService;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
