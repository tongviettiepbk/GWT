package com.course.mvp.demo.client.activities.setting;


import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MListBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class SettingViewGwtImpl implements IsWidget{
	private LayoutPanel main = new LayoutPanel();
	private WidgetList widgetList = new WidgetList();
	private HeaderPanel headerPanel = new HeaderPanel();
	private HeaderButton backButton = new HeaderButton();
	private Button setting = new Button();
	
	private MListBox SizeBox = new MListBox();
	private MListBox MineBox = new MListBox();
	
	private void addSize() {
		SizeBox.addItem("Number Of Box ");
		SizeBox.addItem("9x9");
		SizeBox.addItem("12x12");
		SizeBox.addItem("16x16");
		SizeBox.addItem("20x20");
	}
	
	private void addMine(){
		MineBox.addItem("Number Of Mines");
		MineBox.addItem("10 mines");
		MineBox.addItem("20 mines");
		MineBox.addItem("30 mines");
		MineBox.addItem("40 mines");
	}
	public SettingViewGwtImpl(){
		headerPanel.setCenter("Setting");
		backButton.setText("Back");
		setting.setText("Apply");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);
		
		addSize();
		addMine();
		widgetList.add(MineBox);
		widgetList.add(SizeBox);
		widgetList.add(setting);
		main.add(widgetList);
		
	}
	
	public HeaderButton getBackButton(){
		return this.backButton;
	}

	public Button getSettingButton(){
		return this.setting;
	}
	
	public ListBox getMineBox(){
		return this.MineBox;
	}
	
	public ListBox getSizeBox(){
		return this.SizeBox;
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
	
}

