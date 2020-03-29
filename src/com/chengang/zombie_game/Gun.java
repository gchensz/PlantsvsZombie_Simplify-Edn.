package com.chengang.zombie_game;
/**
 * 
 * FileName:Gun.java
 * @Description: 子弹类
 * @author  ChenGang
 * @date    2020年3月15日 下午11:05:04
 */

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Gun {
	private BufferedImage image;
	private Point point;
	private int typeNum;
	private int num;
	private boolean isHit;
	public Gun(BufferedImage image, Point point, int typeNum) {
		super();
		this.image = image;
		this.point = point;
		this.typeNum = typeNum;
	}
	
	
	public Rectangle getGunRec() {
		return new Rectangle(point.x,point.y,image.getWidth()/typeNum,image.getHeight());
	}
	
	public void gunMove() {
		//超出屏幕
		if(point.x>1200) {
			isHit=true;
			
		}
		point.x+=50;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public Point getPoint() {
		return point;
	}


	public void setPoint(Point point) {
		this.point = point;
	}


	public int getTypeNum() {
		return typeNum;
	}


	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public boolean isHit() {
		return isHit;
	}


	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	
	
	
	
}
