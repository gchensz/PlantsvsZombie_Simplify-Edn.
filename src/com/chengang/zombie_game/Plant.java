/**
 * 
 */
package com.chengang.zombie_game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * FileName:Plant.java
 * @Description: TODO
 * @author  ChenGang
 * @date    2020年3月17日 下午3:08:19
 */
public class Plant {
	private BufferedImage image;//植物图片
	private int typeNum;
	private Point point;
	private int flagNum;
	
	private int num;
	
	//装子弹
	//豌豆子弹，仙人掌子弹，不同
	List<Gun> gunList=new ArrayList<>();
	
	private boolean isBeingEaten;
	private int plantHP;
	
	public void setGun() {
		//no bullet
		if(gunList.size()<1) {
			if(num==typeNum-1) {
				if(flagNum==Util.FLAGNUM_PEASHOOTER) {
					gunList.add(new Gun(Util.imgInfos.get("Gun.png"), new Point(point.x+75,point.y+10), 1));
				}else {
					gunList.add(new Gun(Util.imgInfos.get("Gun2.png"), new Point(point.x+75,point.y+10), 2));

				}
			}
		}
			
	}
	
	public Rectangle getPlantRec() {
		return new Rectangle(point.x,point.y,80,100);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getFlagNum() {
		return flagNum;
	}

	public void setFlagNum(int flagNum) {
		this.flagNum = flagNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Gun> getGunList() {
		return gunList;
	}

	public void setGunList(List<Gun> gunList) {
		this.gunList = gunList;
	}

	public boolean isBeingEaten() {
		return isBeingEaten;
	}

	public void setBeingEaten(boolean isEaten) {
		this.isBeingEaten = isEaten;
	}

	public int getPlantHP() {
		return plantHP;
	}

	public void setPlantHP(int plantHP) {
		this.plantHP = plantHP;
	}
	
	
}
