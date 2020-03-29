/**
 * 
 */
package com.chengang.zombie_game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * FileName:Zombie.java
 * @Description: 僵尸类
 * @author  ChenGang
 * @date    2020年3月17日 下午3:00:27
 */
public class Zombie {
	//僵尸图片 ，位置，哪种僵尸，帧数，HP
	
	private BufferedImage image;
	private Point point;
	//帧数标记
	private int typeNum;
	//僵尸类型
	private int type;
	
	
	private boolean isStop;
	private int zombieHP;
//	public Zombie(BufferedImage image, Point point, int typeNum, int type, boolean isStop, int zombieHP) {
//		super();
//		this.image = image;
//		this.point = point;
//		this.typeNum = typeNum;
//		this.type = type;
//		this.isStop = isStop;
//		this.zombieHP = zombieHP;
//	}
	public Zombie(BufferedImage image, Point point, int typeNum, int type, int zombieHP) {
		super();
		this.image = image;
		this.point = point;
		this.typeNum = typeNum;
		this.type = type;
		this.zombieHP = zombieHP;
	}
	
	
	public Rectangle getZombieRec() {
		return new Rectangle(point.x,point.y,60,87);
	}
	
	

	public void move() {
		//isStop true,僵尸停下；false，没停，继续走
		if(!isStop)
			this.point.x-=3;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public int getZombieHP() {
		return zombieHP;
	}

	public void setZombieHP(int zombieHP) {
		this.zombieHP = zombieHP;
	}
	
	
	
}
