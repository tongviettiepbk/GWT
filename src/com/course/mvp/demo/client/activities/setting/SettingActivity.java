package com.course.mvp.demo.client.activities.setting;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.client.activities.mines.Mines;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class SettingActivity extends MGWTAbstractActivity {
	private final ClientFactoryImpl clientFactory;
	private Place place;
	Mines mines = new Mines();

	public SettingActivity(ClientFactoryImpl clientFactory, Place place) {
		this.clientFactory = clientFactory;
		this.setPlace(place);
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {

		final SettingViewGwtImpl view = new SettingViewGwtImpl();
		panel.setWidget(view);
		view.getBackButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		});

		view.getSettingButton().addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				switch (view.getSizeBox().getSelectedIndex()) {
				case 0: {
					mines.N_COLS = 16;
					mines.N_ROWS = 16;
				}break;
				case 1: {
					mines.N_COLS = 9;
					mines.N_ROWS = 9;
				}break;
					
				case 2: {
					mines.N_COLS = 12;
					mines.N_ROWS = 12;
				}break;
				case 3: {
					mines.N_COLS = 16;
					mines.N_ROWS = 16;
				}break;
				case 4: {
					mines.N_COLS = 20;
					mines.N_ROWS = 20;
				}break;						
				default:{
					mines.N_COLS = 16;
					mines.N_ROWS = 16;
				}
					break;
				}
				switch (view.getMineBox().getSelectedIndex()) {
				case 0:{
					mines.N_MINES =40;
				}break;

				case 1:{
					mines.N_MINES =10;
				}break;
				case 2:{
					mines.N_MINES =20;
				}break;
				case 3:{
					mines.N_MINES =30;
				}break;
				case 4:{
					mines.N_MINES =40;
				}break;
				default:mines.N_MINES =10;
					break;
				}
				clientFactory.getPlaceController().goTo(new HomePlace());
				
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
