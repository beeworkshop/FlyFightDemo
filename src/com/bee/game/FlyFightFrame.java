package com.bee.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 飞机游戏的主窗口
 * 
 * @author beeworkshop
 *
 */
public class FlyFightFrame extends Frame {
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image planeImage = GameUtil.getImage("images/plane.png");

	Plane plane = new Plane(planeImage, 250, 250);
	Bullet[] bullets = new Bullet[50];
	Explode bomb;

	Date startTime = new Date();
	Date endTime;
	long period;

	@Override
	public void paint(Graphics g) { // 自动调用，g相当于一只画笔
		Color color = g.getColor();
		Font font = g.getFont();

		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].drawBullet(g);

			// 碰撞检测
			boolean hit = bullets[i].getRect().intersects(plane.getRect());
			if (hit) {
				System.out.println("击中飞机");
				plane.alive = false;
				if (bomb == null) {
					bomb = new Explode(plane.x, plane.y);
					endTime = new Date();
					period = (long) ((endTime.getTime() - startTime.getTime()) / 1000);
				}

				bomb.draw(g);
			}
			if (!plane.alive) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("微软雅黑", Font.BOLD, 18));
				g.drawString("你坚持了：" + period + "秒", (int) plane.x, (int) plane.y);
			}
		}
		g.setFont(font);
		g.setColor(color);
	}

	/**
	 * 反复重画窗口
	 * 
	 * @author beeworkshop
	 *
	 */
	class PaintThread extends Thread {

		@Override
		public void run() {
			while (true) {
//				System.out.println("窗口重画");
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 进行键盘监听
	 * 
	 * @author beeworkshop
	 *
	 */
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println("按下：" + e.getKeyCode());
			plane.addDir(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
//			System.out.println("抬起：" + e.getKeyCode());
			plane.removeDir(e);
		}

	}

	/**
	 * 初始化窗口
	 */
	public void launchFram() {
		this.setTitle("打飞机^_*");
		this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		this.setLocation(400, 80);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		new PaintThread().start(); // 启动窗口重画线程
		addKeyListener(new KeyMonitor()); // 增加键盘的监听

		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}

	public static void main(String[] args) {
		FlyFightFrame f = new FlyFightFrame();
		f.launchFram();
	}

	private Image offScreenImage = null;

	/**
	 * 双缓冲处理
	 */
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

}
