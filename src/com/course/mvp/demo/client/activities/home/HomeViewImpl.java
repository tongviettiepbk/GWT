package com.course.mvp.demo.client.activities.home;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

public class HomeViewImpl implements IsWidget {
	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private Button newgame = new Button("New Game");
	private Button setting = new Button("Setting");
	private Button intruction = new Button("Intruction");
	
	public HomeViewImpl() {
		main.setWidth("100%");
		headerPanel.setCenter("Mines");
		main.add(headerPanel);
		main.add(newgame);
		main.add(setting);
		main.add(intruction);
	}
	public Button getNewGameButton() {
		return this.newgame;
	}
	
	public Button GetSettingButton(){
		return this.setting;
	}
	
	public Button getIntruction(){
		return this.intruction;
	}
	
	public HeaderPanel getsizeHeader(){
		return this.headerPanel;
	}
	@Override
	public Widget asWidget() {
		return main;
	}

}
