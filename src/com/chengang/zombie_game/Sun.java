package com.chengang.zombie_game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sun {
	//变量声明  小驼峰命名法
	//1.变量声明  2.函数声明
	private BufferedImage image=Util.imgInfos.get("Sun.png");
	private Point sunPoint;
	private int frameNum;
	private int lastY;
	private boolean isClicked;
	
	public Rectangle getSunRec() {
		return new Rectangle(sunPoint.x,sunPoint.y,50,50);
	}
	
	public Sun(Point p) {
		this.sunPoint=sunPoint;
	}
	public Sun(Point p,int y) {
		this.sunPoint=p;
		this.lastY=y;
	}
	
	public void clickMove() {
		if(sunPoint.x>50) {
			sunPoint.x-=70;
			/////////////////////////
			sunPoint.y-=70;
		}
	}
	
	public void drop() {
		if(sunPoint.y<lastY) {
			sunPoint.y+=3;///////////
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Point getSunPoint() {
		return sunPoint;
	}

	public void setSunPoint(Point sunPoint) {
		this.sunPoint = sunPoint;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int num) {
		this.frameNum = num;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	
	
}
