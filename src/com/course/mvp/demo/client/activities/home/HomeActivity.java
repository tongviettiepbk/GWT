package com.course.mvp.demo.client.activities.home;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.mines.MinesweeperPlace;
import com.course.mvp.demo.client.activities.setting.SettingPlace;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;

public class HomeActivity  extends MGWTAbstractActivity{
	public static int Size;
	private final ClientFactoryImpl clientFactory;
	private Place place;
	
	public HomeActivity(ClientFactoryImpl clientFactory, Place place) {
		this.clientFactory = clientFactory;
		this.setPlace(place);
	}
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		final HomeViewImpl view = new HomeViewImpl();
		panel.setWidget(view);
		
		view.getNewGameButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				Size = view.getsizeHeader().getOffsetHeight();
			clientFactory.getPlaceController().goTo(new MinesweeperPlace());	
			}
		});
		
		view.GetSettingButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new SettingPlace() );
			}
		});
		
		view.getIntruction().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				Dialogs.alert("Lienhe", "tongviettiepbk@gmail.com", null);
				
			}
		});
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
