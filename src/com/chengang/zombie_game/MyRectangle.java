package com.chengang.zombie_game;

import java.awt.Rectangle;

public class MyRectangle extends Rectangle{
	//判断方框是否已经有植物
	private boolean planted=Util.NOPLANT;
	
	public MyRectangle(int x, int y ,int width ,int height) {
		super.x=x;
		super.y=y;
		super.width=width;
		super.height=height;
	}
	
	//判断是否种植了植物
	public boolean isPlanted() {
		return planted;
	}
	//没有
	public void setPlanted(boolean planted) {
		this.planted = planted;
	}
	
}
