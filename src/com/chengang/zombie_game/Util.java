package com.chengang.zombie_game;
/**
 * 
 * @author syber
 *
 */

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Util {
	public static final Rectangle SFLREC=new Rectangle(0,80,80,80);
	public static final Rectangle PSTREC=new Rectangle(0,160,80,80);
	public static final Rectangle CACTUREC=new Rectangle(0,80,80,80);
	public static final boolean PLANTED=true;
	public static final boolean NOPLANT=false;
	
	
	//plant flag
	public static final int FLAGNUM_SUMFLOWER=1;
	public static final int FLAGNUM_PEASHOOTER=2;
	public static final int FLAGNUM_CACTUS=3;
	public static final int FLAGNUM_NULL=0;
	//zombie falg
	public static final int FLAGNUM_ZOMBIE1=1;
	public static final int FLAGNUM_ZOMBIE2=2;
	public static final int FLAGNUM_ZOMBIE3=3;
	public static final int FLAGNUM_ZOMBIENULL=0;
	
	//resource frame_rate
	public static final int FRAMENUM_SUN=13;
	public static final int FRAMENUM_PEASHOOTER=13;
	public static final int FRAMENUM_SUNFLOWER=18;
	public static final int FRAMENUM_CACTUS=11;
	public static final int FRAMENUM_ZOMBIE1=21;
	public static final int FRAMENUM_ZOMBIE2=12;
	public static final int FRAMENUM_ZOMBIE3=14;
	
	public static HashMap<Integer, Integer> zombieMap=new HashMap<>();
	public static HashMap<String, BufferedImage> imgInfos=new HashMap<>();
	
	static {
		zombieMap.put(FLAGNUM_ZOMBIE1, FRAMENUM_ZOMBIE1);
		zombieMap.put(FLAGNUM_ZOMBIE2, FRAMENUM_ZOMBIE2);
		zombieMap.put(FLAGNUM_ZOMBIE3, FRAMENUM_ZOMBIE3);
		
		File[] files=new File("resours").listFiles();

		for(File file:files) {
			try {
				imgInfos.put(file.getName(), ImageIO.read(file));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
