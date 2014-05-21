package com.course.mvp.demo.client.activities.mines;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;


public class MinesweeperViewGwtImpl implements IsWidget{
	LayoutPanel main = new LayoutPanel();
	HeaderPanel headerPanel = new HeaderPanel();
	HeaderButton backButton = new HeaderButton();
	VerticalPanel centerPanel;
	
	
	public MinesweeperViewGwtImpl(){
		backButton.setText("Back");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);		
		centerPanel = new VerticalPanel();
		centerPanel.setHeight(Window.getClientHeight()+ "px");
		main.add(centerPanel);
	}
	
	public HeaderPanel getHeaderPanel(){
		return this.headerPanel;
	}
	
	public HeaderButton getBackButton() {
		return this.backButton;
	}
	
	public VerticalPanel getCenterLayout() {
		return centerPanel;
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
	
	
}
