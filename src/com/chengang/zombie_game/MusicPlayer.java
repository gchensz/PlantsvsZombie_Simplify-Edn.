package com.chengang.zombie_game;
/*
 * MusicPlayer
 ** 音乐类
 * chengang
 * 2020.3.12
 */

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	//全局音乐对象clip
	private Clip clip;
	public MusicPlayer(String filePath) {
		//音乐先读取进来
		File file=new File(filePath);
		//判断系统能不能读取
		AudioInputStream audio;
		
		//当前音频输入流 谁来获取   java system
		try {
			audio=AudioSystem.getAudioInputStream(file);
			clip=AudioSystem.getClip();
			clip.open(audio);
			
			
		}catch (UnsupportedAudioFileException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public void loop(int mode) {
		clip.loop(mode);
	}

}
