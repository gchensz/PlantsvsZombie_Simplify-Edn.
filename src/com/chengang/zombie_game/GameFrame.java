package com.chengang.zombie_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



////////// 封装 继承 多态  三个特点   (第四大特点  覆写
public class GameFrame extends JFrame {
	public GameFrame() {
		//设置程序窗口参数
		this.setTitle("Plants vs. Zombies");
		this.setSize(1200,600);
		//隐藏问题 客户调用 更好
		//用户不可以调整窗口大小
		this.setResizable(false);
		this.setLocationRelativeTo(null);;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//添加背景音乐
		MusicPlayer musicPlayer=new MusicPlayer("sound/植物大战僵尸.wav");
		musicPlayer.loop(-1);
		
		//程序添加窗口监听事件
		this.addWindowListener(new WindowAdapter() {
			//点击窗口时,触发事件
			public void windowClosing(WindowEvent e) {
//				int dialog=JOptionPane.showConfirmDialog(null, "quit?","QUIT",JOptionPane.OK_CANCEL_OPTION);
//				if(dialog==JOptionPane.OK_OPTION) {
//					System.exit(0);
//				}
			}
		});
		
		//定义游戏内容窗口
		GamePanel gamePanel = new GamePanel();
		this.getContentPane().add(gamePanel);
		this.setVisible(true);
		
	}
	
	
	public static void  main(String[] args) {
		//创建游戏程序
		GameFrame frame=new GameFrame();
	}
	
}
