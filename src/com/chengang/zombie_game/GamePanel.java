package com.chengang.zombie_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class GamePanel extends JPanel{
	
	//随机数
	Random random=new Random();
	
	//离散的阳光
	List<Sun> sunList=new ArrayList<>();
	//收集的阳光总数
	private Integer sunAmount=50;
	//可用阳光
	private int nowSun;
	
	//plant cd time
	private int sunflowerCDTime=0;
	private int peaCDTime=0;
	private int cactusCDTime=0;
	
	//option card
	private int pltChosen=0;
	
	//zombie击杀总数
	private Integer zombieDeadAmount=0;
	
	//游戏地图
	private MyRectangle[] grassMap=new MyRectangle[45];
	
	//植物/僵尸实体集
	List<Plant> plantsList=new ArrayList<>();
	List<Zombie> zombiesList=new ArrayList<>();
	
	//zombie num
	int count=1;
	
	//music
	MusicPlayer mp=null;
	
	//dont init bullet ,reduce hardware resource
	
	//-----------------------------
	public GamePanel() {
		//init map
		for(int i=0;i<9;i++) 
			for(int j=0;j<5;j++) 
				//构造虚体游戏地图
				grassMap[i+j*9]=new MyRectangle(215+i*70, 80+j*97, 70, 97);
		
		//处理线程
		new MyThread().start();
		new PlantCDTimeThread().start();
	}
	
	//绘制动态图
	/**
	 * 
	 * @param img  目标图片
	 * @param p  坐标系
	 * @param imgx  图片位置
	 * @param imgy
	 * @param num	第几张图片
	 * @param typeNum  类型
	 * @param graphics  画笔
	 */
	public void defferImg(BufferedImage img,Point p,int imgx,int imgy,int typeNum,int num,Graphics graphics) {
		int ht=img.getHeight();
		int wd=img.getWidth()/typeNum;//图片里的第几帧
		graphics.drawImage(img, p.x, p.y, p.x+imgx, p.y+imgy, num*wd,0,(num+1)*wd,ht, this);
	}
	
	public int getRandom(int num) {
		return random.nextInt(num);
	}
	
	public void drawBackground(Graphics graphics) {
		graphics.drawImage(Util.imgInfos.get("background1.jpg"),0,0,1200,600,this);
		graphics.drawImage(Util.imgInfos.get("sunAmount.gif"),50,0,120,30,this);
		graphics.drawImage(Util.imgInfos.get("IMG00000.bmp"),200,0,120,30,this);
		
		//
		graphics.setColor(Color.BLACK);
		
		//draw map matrix
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 5; j++) {
//				graphics.drawRect(215+i*70, 80+j*97, 70, 97);
//			}
//		}
		
		//font
		graphics.setFont(new Font("Serif",Font.BOLD,32) );
		graphics.drawString(sunAmount.toString(), 90,25);
		graphics.drawString(zombieDeadAmount.toString(), 240, 25);
		
		
	}
	
	
	//--game--
	//1.zombie status
	public void setZombieStatus(Zombie zom) {
		if(zom.isStop()) {
			zom.setImage(Util.imgInfos.get("Zombie_"+zom.getType()+".png"));
			zom.setType(1);
			zom.setTypeNum(Util.zombieMap.get(zom.getType()));
			zom.setStop(false);
		}else {
			zom.setImage(Util.imgInfos.get("Zombie_"+zom.getType()+".png"));
			zom.setType(1);
			zom.setTypeNum(11);
			zom.setStop(true);
		}
	}
	
	//2.植物选项卡状态
	public void setPlantCard(Graphics graphics,int time,int flg,String img,int y) {
		if(time>5) {//cd时间到了
			//状态1.cd到了 2.选中卡片 3.cd 没到 4.sun不够
			if(pltChosen == flg) {
				graphics.drawImage(Util.imgInfos.get(img+"_1.png"), 0, y,    80, 80,   this);
			}else {
				graphics.drawImage(Util.imgInfos.get(img+".png"), 0, y,  80, 80,   this);
			}
		}else {
			graphics.drawImage(Util.imgInfos.get(img+"_2.png"), 0, y,  80, 80,   this);
		}
		
		
	}
	
	
	
	
	
	//draw option card
	public void drawCard(Graphics graphics) {
		// 
		nowSun=sunAmount/50;
		switch (nowSun) {
			case 0:
				graphics.drawImage(Util.imgInfos.get("SunFlower_2.png"), 0, 80,    80, 80,   this);
				graphics.drawImage(Util.imgInfos.get("Peashooter_2.png"), 0, 160,    80, 80,   this);
				graphics.drawImage(Util.imgInfos.get("Cactus_2.png"), 0, 240,    80, 80,   this);
				break;
			case 1:
				//设置植物的内容
				//常规方法
				//graphics.drawImage(Util.infos.get("SunFlower.png"), 0, 80,    240, 80,   this);
				setPlantCard(graphics, sunflowerCDTime, Util.FLAGNUM_SUMFLOWER, "SunFlower", 80);
				graphics.drawImage(Util.imgInfos.get("Peashooter_2.png"), 0, 160,    80, 80,   this);
				graphics.drawImage(Util.imgInfos.get("Cactus_2.png"), 0, 240,    80, 80,   this);
				break;
			case 2:
				setPlantCard(graphics, sunflowerCDTime, Util.FLAGNUM_SUMFLOWER, "SunFlower", 80);
				setPlantCard(graphics, peaCDTime, Util.FLAGNUM_PEASHOOTER, "Peashooter", 160);
				graphics.drawImage(Util.imgInfos.get("Cactus_2.png"), 0, 240,    80, 80,   this);
				break;
			case 3:
				setPlantCard(graphics, sunflowerCDTime, Util.FLAGNUM_SUMFLOWER, "SunFlower", 80);
				setPlantCard(graphics, peaCDTime, Util.FLAGNUM_PEASHOOTER, "Peashooter", 160);
				setPlantCard(graphics, cactusCDTime, Util.FLAGNUM_CACTUS, "Cactus", 240);
				break;
			default:
				setPlantCard(graphics, sunflowerCDTime, Util.FLAGNUM_SUMFLOWER, "SunFlower", 80);
				setPlantCard(graphics, peaCDTime, Util.FLAGNUM_PEASHOOTER, "Peashooter", 160);
				setPlantCard(graphics, cactusCDTime, Util.FLAGNUM_CACTUS, "Cactus", 240);
				break;
		}
		
	}
	
	//draw zombie
	public void drawZombie(Graphics graphics) {
		for(int i=0;i<zombiesList.size();i++) {
			Zombie zombie=zombiesList.get(i);
			defferImg(zombie.getImage(), new Point(zombie.getPoint().x,zombie.getPoint().y-50), 100, 120, zombie.getTypeNum(),zombie.getType(), graphics);
			zombie.move();
			
			if(zombie.getPoint().x<100) {
				graphics.setColor(Color.RED);
				graphics.setFont(new Font("Serif",Font.BOLD,50));
				graphics.drawString("你的脑子被僵尸吃掉了", 330, 230);
			}
			
		}
	}
	
	
	//draw sun
	public void drawSun(Graphics graphics) {
		for(int i=0;null!=sunList && i<sunList.size();i++) {
			Sun sun=sunList.get(i);
			int sunw=sun.getImage().getWidth()/Util.FRAMENUM_SUN;
			int sunh=sun.getImage().getHeight();
			
			//draw
			graphics.drawImage(sun.getImage(), sun.getSunPoint().x, sun.getSunPoint().y, 
								sun.getSunPoint().x+50, sun.getSunPoint().y+50,
								sun.getFrameNum()*sunw, 0, 
								(sun.getFrameNum()+1)*sunw, sunh,
								this);
			
		}
	}
	
	//draw plant
	public void drawPlant(Graphics graphics) {
		for(int i=0;i<plantsList.size();i++) {
			Plant plant=plantsList.get(i);
			
			plant.setGun();
			
			defferImg(plant.getImage(), plant.getPoint(), 80, 100, plant.getTypeNum(), plant.getNum(),graphics);
//			Point p=plant.getPoint();
//			BufferedImage img=plant.getImage();
//			int num=plant.getTypeNum();
//			int ht=plant.getImage().getHeight();
//			int wd=plant.getImage().getWidth()/plant.getTypeNum();//图片里的第几帧
//			graphics.drawImage(plant.getImage(), p.x, p.y, p.x+80, p.y+100, num*wd,0,(num+1)*wd,ht, this);
			
			//bullet
			for(int j=0;null!=plant.getGunList() && j<plant.getGunList().size();j++) {
				Gun gun=plant.gunList.get(j);
				defferImg(gun.getImage(), gun.getPoint(), 24, 24, gun.getTypeNum(), gun.getNum(), graphics);
				gun.gunMove();
				
				//处理子弹
				for(int k=0;k<zombiesList.size();k++) {
					Zombie zombie=zombiesList.get(k);
					if(gun.getGunRec().intersects(zombie.getZombieRec())) {
						plant.getGunList().remove(gun);
						zombie.setZombieHP(zombie.getZombieHP()-1);
						if(zombie.getZombieHP() ==0 ) {
							zombiesList.remove(zombie);
							zombieDeadAmount++;
						}
						
						mp=new MusicPlayer("sound/shieldhit2.wav");
						mp.loop(0);
						
					}
					if(gun.isHit()) {
						plant.getGunList().remove(gun);
					}	
				}
				//
				for(int k=0;k<zombiesList.size();k++) {
					Zombie zombie=zombiesList.get(k);
					if(plant.getPlantRec().intersects(zombie.getZombieRec())) {
						////////
						if(zombie.isStop()) {
							//获取僵尸状态，设置新的状态
							setZombieStatus(zombie);
							//修改为被吃状态
							plant.setBeingEaten(true);
						}
						//////////////
						
					}
					if(zombie.isStop() && plant.getPlantHP() ==0 ) {
						plantsList.remove(plant);
						setZombieStatus(zombie);
					}
				}
				
				
				
				
			}
			
		}
	}
	
	public void moveSun() {
		for(int i=0;i<sunList.size();i++) {
			Sun sun=sunList.get(i);
			
			sun.setFrameNum(sun.getFrameNum()+1);//当前阳光设为第n+1帧
			if(sun.getFrameNum() == Util.FRAMENUM_SUN-1) 
				sun.setFrameNum(0);//帧数归零
			
			//检测鼠标点击收集阳光，继续掉落或收集
			if(sun.isClicked())
				sun.clickMove();
			else
				sun.drop();
			
			//该阳光被点击收集
			if(sun.getSunPoint().x<60) {
				sunList.remove(sun);
				sunAmount+=25;
			}
		}
	}
	
	//点击阳光
	public void clickSun(MouseEvent e) {
		//遍历检查离散阳光是否被点击收集
		for(Sun sun:sunList) {
			Rectangle rectangle=sun.getSunRec();
			//点击检测
			if(rectangle.contains(e.getPoint())) 
				sun.setClicked(true);
		}
		
	}
	
	//点击植物选项卡
	public void clickCard(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3) {
			pltChosen=0;
		}
		if(nowSun >1 && e.getButton() == MouseEvent.BUTTON1) {
			//判断 选中哪种植物 && 该植物冷却时间完成
			if(Util.SFLREC.contains(e.getPoint()) && sunflowerCDTime > 5)
				pltChosen=Util.FLAGNUM_SUMFLOWER;
			if(Util.PSTREC.contains(e.getPoint()) && sunflowerCDTime > 5)
				pltChosen=Util.FLAGNUM_PEASHOOTER;
			if(Util.CACTUREC.contains(e.getPoint()) && sunflowerCDTime > 5)
				pltChosen=Util.FLAGNUM_CACTUS;
		}
	}
	
	public void addZomie() {
		if(plantsList.size()>=1 && zombiesList.size()<1) {//已经有植物了且没一个僵尸
			for(int i=0;i<count;i++) {
				int type=getRandom(3)+1;//随机生成僵尸种类
				zombiesList.add(
						new Zombie(Util.imgInfos.get("Zombie_"+type+".png"), 
							new Point(1200+getRandom(10),80+getRandom(5)*97),
							Util.zombieMap.get(type), 
							type, type*2)
				);
			}
			
			if(count<5)
				count++;
		}
	}
	
	//绘制所有东西
	public void drawImg(int index,int sunNeeded,int img,int type) {
		Plant plant=new Plant();
		grassMap[index].setPlanted(Util.PLANTED);
		sunAmount-=sunNeeded;
		plant.setImage(Util.imgInfos.get("1.png"));///////////////////////////////TODO
		plant.setPoint(new Point(grassMap[index].x,grassMap[index].y));
		plant.setTypeNum(type);
		plant.setFlagNum(img);
		plant.setPlantHP(pltChosen*2);
		plantsList.add(plant);
		pltChosen=Util.FLAGNUM_NULL;
		repaint();
		
	}
	
	public void addPlant(MouseEvent e) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				if(grassMap[i+j*9].contains(e.getPoint()) && !grassMap[i+j*9].isPlanted()) {
					if(pltChosen == Util.FLAGNUM_SUMFLOWER) {
						drawImg(i+j*9, 50, Util.FLAGNUM_SUMFLOWER, Util.FRAMENUM_SUNFLOWER);
						sunflowerCDTime=0;
					}
					if(pltChosen == Util.FLAGNUM_PEASHOOTER) {
						drawImg(i+j*9, 100, Util.FLAGNUM_PEASHOOTER, Util.FRAMENUM_PEASHOOTER);
						peaCDTime=0;
					}
					if(pltChosen == Util.FLAGNUM_CACTUS) {
						drawImg(i+j*9, 150, Util.FLAGNUM_CACTUS, Util.FRAMENUM_CACTUS);
						cactusCDTime=0;
					}
					/////////////////////////////////////////biaoji
					
				}
			}
		}
	}
	
	public void plantThread() {
		for(Plant plant:plantsList) {
			plant.setNum(plant.getNum()+1);
			if(plant.getNum() == plant.getTypeNum()) {
				plant.setNum(0);
			}
			for (Gun gun : plant.getGunList()) {
				gun.setNum(plant.getNum()+1);
				if(gun.getNum() == gun.getTypeNum()) {
					gun.setNum(0);
				}
			}
		}
	}
	
	public void zombieThread() {
		//集合类 先进行null判断！！！！！
		for (int i = 0; i < zombiesList.size(); i++) {
			Zombie zombie=zombiesList.get(i);
			zombie.setType(zombie.getType()-1);
			if(zombie.getType() == zombie.getTypeNum()) {
				zombie.setType(1);
			}
		}	
	}
	
	
	class PlantCDTimeThread extends Thread{
		public void run() {
			while(true) {
				//植物冷却时间自动计时
				sunflowerCDTime++;
				peaCDTime++;
				cactusCDTime++;
				
				//检查地图上植物是否被吃
				for (Plant plant : plantsList) {
					if(plant.isBeingEaten()) {
						plant.setPlantHP(plant.getPlantHP()-1);
					}
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	
	class MyThread extends Thread{
		public void run() {
			while(true) {
				moveSun();
				plantThread();
				zombieThread();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				repaint();
				
			}
		}
	}
	
	//游戏初始化
	public void paint(Graphics graphics) {
		super.paint(graphics);
		while(sunList.size()<3) {
			sunList.add(new Sun(new Point(getRandom(800)+200,-(getRandom(100))),getRandom(400)));
		}
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				clickCard(event);
				clickSun(event);
				addPlant(event);
			}
		});
		drawBackground(graphics);
		drawCard(graphics);
		drawPlant(graphics);
		drawSun(graphics);
		addZomie();
		drawZombie(graphics);
		
		
	}
	
	
	
}
