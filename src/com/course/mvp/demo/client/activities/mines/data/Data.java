package com.course.mvp.demo.client.activities.mines.data;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Data extends ClientBundle{
	Data IMPL = (Data)GWT.create(Data.class);
	@Source("com/course/mvp/demo/client/activities/mines/data/images/0.png")
	ImageResource image0();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/1.png")
	ImageResource image1();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/2.png")
	ImageResource image2();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/3.png")
	ImageResource image3();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/4.png")
	ImageResource image4();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/5.png")
	ImageResource image5();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/6.png")
	ImageResource image6();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/7.png")
	ImageResource image7();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/8.png")
	ImageResource image8();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/9.png")
	ImageResource image9();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/10.png")
	ImageResource image10();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/11.png")
	ImageResource image11();
	@Source("com/course/mvp/demo/client/activities/mines/data/images/12.png")
	ImageResource image12();
}
