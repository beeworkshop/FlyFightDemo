package com.bee.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 飞机游戏的主窗口
 * 
 * @author beeworkshop
 *
 */
public class FrameDemo1 extends JFrame {
	Image ball = GameUtil.getImage("images/ball.png");

	@Override
	public void paint(Graphics g) { // 自动调用，g相当于一只画笔

		Color color = g.getColor();
		g.setColor(Color.BLUE);
		Font font = g.getFont();

		g.drawLine(100, 100, 300, 300);
		g.drawRect(100, 100, 300, 300);
		g.drawOval(100, 100, 300, 300);
		g.fillRect(100, 100, 30, 30);
		g.setColor(Color.RED);
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		g.drawString("在这里", 150, 150);

		g.drawImage(ball, 150, 150, null);

		g.setFont(font);
		g.setColor(color);
	}

	/**
	 * 初始化窗口
	 */
	public void launchFram() {
		this.setTitle("打飞机^_*");
		this.setSize(600, 600);
		this.setLocation(400, 80);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	public static void main(String[] args) {
		FrameDemo1 f = new FrameDemo1();
		f.launchFram();
	}

}
